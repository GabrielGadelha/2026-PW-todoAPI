package br.edu.ufersa.pw.todo.todoAPI.todo;

import br.edu.ufersa.pw.todo.todoAPI.auth.Usuario;
import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoCreateDTO;
import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class TodoApplicationService {
    private final TodoService todoDomainService;
    private final TodoRepository todoRepository;
    private final TodoMapper mapper;
    public TodoApplicationService(TodoService todoDomainService,
                                  TodoRepository todoRepository,
                                  TodoMapper mapper) {
        this.todoDomainService = todoDomainService;
        this.todoRepository = todoRepository;
        this.mapper = mapper;}
    @Transactional
    public TodoResponseDTO criarTarefa(TodoCreateDTO requestDTO, Usuario usu) {
        Todo novaTarefa = mapper.toEntity(requestDTO, usu.getId());
        todoDomainService.validarCriacao(novaTarefa, usu.getId());
        Todo tarefaSalva = todoRepository.save(novaTarefa);
        return mapper.toResponse(tarefaSalva);}
    @Transactional(readOnly = true)
    public List<TodoResponseDTO> listarTodas(Usuario usu) {
        List<Todo> tarefas = todoRepository.findByUsu(usu);
        return mapper.toResponseList(tarefas);
    }
}
