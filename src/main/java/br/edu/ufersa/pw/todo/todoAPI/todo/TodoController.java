package br.edu.ufersa.pw.todo.todoAPI.todo;

import br.edu.ufersa.pw.todo.todoAPI.auth.Usuario;
import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoCreateDTO;
import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoPatchDTO;
import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoResponseDTO;
import br.edu.ufersa.pw.todo.todoAPI.todo.dto.TodoUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@Validated
public class TodoController {
    private final TodoApplicationService service;
    public TodoController(TodoApplicationService service) {
        this.service = service; }
    @GetMapping()
    public ResponseEntity<List<TodoResponseDTO>> listar(
            @AuthenticationPrincipal Usuario usuarioAutenticado) {
        return ResponseEntity.ok(service.listarTodas(usuarioAutenticado));
    }
    @PostMapping
    public ResponseEntity<TodoResponseDTO> criar(
            @AuthenticationPrincipal Usuario usuarioAutenticado,
            @RequestBody @Valid TodoCreateDTO dto,
            UriComponentsBuilder uriBuilder) {
        TodoResponseDTO salvo = service.criarTarefa(dto,usuarioAutenticado);
        URI uri = uriBuilder
                .path("/api/v1/todos/{todoId}")
                .buildAndExpand(salvo.id())
                .toUri();
        return ResponseEntity.created(uri).body(salvo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoResponseDTO> buscarPorId(
            @PathVariable Long userId,
            @PathVariable Long todoId) {
        return null;}

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoResponseDTO> atualizar(
            @PathVariable Long userId,
            @PathVariable Long todoId,
            @RequestBody TodoUpdateDTO dto) {
        return null;}

    @PatchMapping("/{todoId}")
    public ResponseEntity<TodoResponseDTO> alterarParcial(
            @PathVariable Long userId,
            @PathVariable Long todoId,
            @RequestBody TodoPatchDTO dto) {
        return null;
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> remover(
            @PathVariable Long userId,
            @PathVariable Long todoId) {
        return null;}
}
