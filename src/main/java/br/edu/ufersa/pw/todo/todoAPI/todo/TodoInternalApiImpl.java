package br.edu.ufersa.pw.todo.todoAPI.todo;

import br.edu.ufersa.pw.todo.todoAPI.todo.dto.EstadoDTO;
import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TodoInternalApiImpl implements TodoInternalApi {

    private final TodoRepository repository;
    TodoInternalApiImpl(TodoRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<TodoResponseDTO> listarResumoPorUsuario(Long usuarioId) {
        return repository.findByUsuId(usuarioId).stream()
                .map(t -> new TodoResponseDTO(t.getId(),
                        t.getItem(),t.getPrazo(),
                        EstadoDTO.fromDomain(t.getEstado())))
                .toList();
    }
}

