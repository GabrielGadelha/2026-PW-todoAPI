package br.edu.ufersa.pw.todo.todoAPI.todo.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TodoUpdateDTO(
        @NotBlank(message = "O item é obrigatório!")
        @Size(min = 3, max = 255, message = "O item deve ter entre 3 e 255 caracteres.")
        String item,
        @NotNull(message = "O prazo é obrigatório!")
        @FutureOrPresent(message = "O prazo não pode ser uma data no passado.")
        LocalDate prazo,
        @NotNull(message = "O estado é obrigatório!")
        EstadoDTO estado
) {}
