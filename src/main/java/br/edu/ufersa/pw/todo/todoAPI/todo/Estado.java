package br.edu.ufersa.pw.todo.todoAPI.todo;

import java.util.Set;

public enum Estado {
    EM_ANDAMENTO,
    CONCLUIDO,
    ATRASADO;



    public boolean podeTransicionarPara(Estado novoEstado) {
        return switch (this) {
            case EM_ANDAMENTO -> Set.of(CONCLUIDO, ATRASADO).contains(novoEstado);
            case CONCLUIDO -> false; // Estado terminal
            case ATRASADO -> Set.of(EM_ANDAMENTO, CONCLUIDO).contains(novoEstado);
        };
    }
}
