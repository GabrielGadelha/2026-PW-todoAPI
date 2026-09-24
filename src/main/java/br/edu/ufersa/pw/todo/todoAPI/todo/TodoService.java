package br.edu.ufersa.pw.todo.todoAPI.todo;

import br.edu.ufersa.pw.todo.todoAPI.shared.exception.OperacaoInvalidaException;
import org.springframework.stereotype.Service;

@Service
// @Service do Spring para injeção, mas conceitualmente é um Domain Service
class TodoService {
    private final TodoRepository repository;
    public TodoService(TodoRepository repository) {
        this.repository = repository; }
    // A assinatura fala a linguagem do negócio
    public void validarCriacao(Todo novaTarefa, Long userId) {
        boolean tituloJaExiste = repository
                .existsByItemAndUsuId(novaTarefa.getItem(),userId);
        if (tituloJaExiste) {
            throw new OperacaoInvalidaException(
                    "Não é permitido criar tarefa com título duplicado: "
                            + novaTarefa.getItem()); }
    }
}