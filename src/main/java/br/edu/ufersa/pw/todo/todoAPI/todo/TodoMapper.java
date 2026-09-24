package br.edu.ufersa.pw.todo.todoAPI.todo;

import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoCreateDTO;
import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoResponseDTO;
import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoUpdateDTO;
import br.edu.ufersa.pw.todo.todoAPI.auth.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    // Método default: você controla a invocação do Builder com construtor protegido/específico
    default Todo toEntity(TodoCreateDTO dto, Long userId) {
        if (dto == null) {
            return null;}
        Todo.Builder builder = new Todo.Builder(new Usuario(userId), dto.item());
        if (dto.prazo() != null) {
            builder.comPrazo(dto.prazo());}
        if (dto.estado() != null) {
            builder.comEstado(dto.estado().toDomain());}
        return builder.build();}
    // 2. Converte Entidade JPA -> DTO de Saída (Response)
    TodoResponseDTO toResponse(Todo entity);
    // 3. Converte Lista de Entidades -> Lista de DTOs de Saída
    List<TodoResponseDTO> toResponseList(List<Todo> entities);
    // 4. Atualiza uma Entidade existente com dados do DTO de Update
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TodoUpdateDTO dto, @MappingTarget Todo entity);
}
