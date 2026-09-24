package br.edu.ufersa.pw.todo.todoAPI.todo;

import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoResponseDTO;

import java.util.List;

public interface TodoInternalApi {
    List<TodoResponseDTO> listarResumoPorUsuario(Long usuarioId);
}
