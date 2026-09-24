package br.edu.ufersa.pw.todo.todoAPI.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TodoResponseDTO(
        @NotNull(message = "O identificador da tarefa não pode ser nulo na resposta.")
        Long id,
        @NotBlank(message = "O item da tarefa não pode ser vazio na resposta.")
        String item,
        @NotNull(message = "O prazo não pode ser nulo na resposta.")
        LocalDate prazo,
        @NotNull(message = "O estado da tarefa não pode ser nulo na resposta.")
        EstadoDTO estado){
}
