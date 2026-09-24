package br.edu.ufersa.pw.todo.todoAPI.todo.dto;

import br.edu.ufersa.pw.todo.todoAPI.todo.Estado;

public enum EstadoDTO {
    EM_ANDAMENTO,
    CONCLUIDO,
    ATRASADO;
    public Estado toDomain() {
        return switch (this) {
            case EM_ANDAMENTO -> Estado.EM_ANDAMENTO;
            case CONCLUIDO -> Estado.CONCLUIDO;
            case ATRASADO -> Estado.ATRASADO;
        };
    }
    public static EstadoDTO fromDomain(Estado estado) {
        if (estado == null) return null;
        return switch (estado) {
            case EM_ANDAMENTO -> EstadoDTO.EM_ANDAMENTO;
            case CONCLUIDO -> EstadoDTO.CONCLUIDO;
            case ATRASADO -> EstadoDTO.ATRASADO;
        };
    }
}
