package br.edu.ufersa.pw.todo.todoAPI.todo;

import br.edu.ufersa.pw.todo.todoAPI.auth.Usuario;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
@Entity
@Table(name="tb_todos")
class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private Usuario usu;
    @Column(nullable = false)
    private String item;
    @Column
    private LocalDate prazo;
    @Enumerated(EnumType.STRING)
    @Column
    private Estado estado;
    @Column
    private LocalDate conclusao;

    protected Todo() {
    }

    private Todo(Builder builder) {
        this.id = builder.id;
        this.usu = builder.usu;
        this.item = builder.item;
        this.prazo = builder.prazo;
        this.estado = builder.estado;
        this.conclusao = builder.conclusao;
    }

    public void concluir() {
        validarTransicao(Estado.CONCLUIDO);
        this.estado = Estado.CONCLUIDO;
        this.conclusao = LocalDate.now();
    }

    public void indicarAtraso() {
        validarTransicao(Estado.ATRASADO);
        this.estado = Estado.ATRASADO;
    }

    public void prorrogarPrazo(LocalDate novoPrazo) {
        if (this.estado == Estado.CONCLUIDO) {
            throw new IllegalStateException("Não é possível alterar o prazo de tarefas concluídas.");
        }
        Objects.requireNonNull(novoPrazo, "O novo prazo é obrigatório.");
        if (this.prazo != null && novoPrazo.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("O novo prazo não pode ser anterior à data de hoje.");
        }
        if (this.prazo != null && novoPrazo.isBefore(this.prazo)) {
            throw new IllegalArgumentException("O novo prazo não pode antecipar o prazo já vigente.");
        }
        this.prazo = novoPrazo;
        this.estado = Estado.EM_ANDAMENTO;
    }

    public void renomearItem(String novoItem) {
        if (this.estado == Estado.CONCLUIDO) {
            throw new IllegalStateException("Não é permitido renomear tarefas concluídas.");
        }
        if (novoItem == null || novoItem.isBlank()) {
            throw new IllegalArgumentException("A descrição do item não pode ser vazia.");
        }
        this.item = novoItem;
    }

    private void validarTransicao(Estado proximoEstado) {
        if (!this.estado.podeTransicionarPara(proximoEstado)) {
            throw new IllegalStateException(
                    String.format("Transição inválida: tarefa está em '%s' e não pode ir para '%s'.",
                            this.estado, proximoEstado)
            );
        }
    }

    public Long getId() { return id; }
    public Usuario getUsu() { return usu; }
    public String getItem() { return item; }
    public LocalDate getPrazo() { return prazo; }
    public Estado getEstado() { return estado; }
    public LocalDate getConclusao() { return conclusao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id != null && id.equals(todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usu, item);
    }

    public static class Builder {
        // Obrigatórios
        private final Usuario usu;
        private final String item;

        // Opcionais com valores padrão explícitos
        private Long id;//ATENÇÃO AQUI
        private LocalDate prazo = LocalDate.now();
        private Estado estado = Estado.EM_ANDAMENTO;
        private LocalDate conclusao;

        public Builder(Usuario usu, String item) {
            this.usu = Objects.requireNonNull(usu, "O usuário é obrigatório!");
            if (item == null || item.isBlank()) {
                throw new IllegalArgumentException("O item é obrigatório!");
            }
            this.item = item;
        }

        public Builder comId(Long id) {
            this.id = id;
            return this;
        }

        public Builder comPrazo(LocalDate prazo) {
            this.prazo = prazo;
            return this;
        }

        public Builder comEstado(Estado estado) {
            this.estado = estado;
            return this;
        }

        public Builder comConclusao(LocalDate conclusao) {
            this.conclusao = conclusao;
            return this;
        }

        public Todo build() {
            validarInvariantes();
            return new Todo(this);
        }

        private void validarInvariantes() {
             if (this.conclusao != null && this.conclusao.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("A data de conclusão não pode ser anterior a hoje");
            }
            if (this.prazo != null && this.conclusao != null && this.conclusao.isBefore(this.prazo)) {
                throw new IllegalArgumentException("A conclusão não pode ser anterior ao prazo estipulado");
            }
        }
    }
}

