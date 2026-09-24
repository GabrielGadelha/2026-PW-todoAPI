package br.edu.ufersa.pw.todo.todoAPI.todo.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TodoCreateDTO(
        @NotBlank(message = "O item é obrigatório!")
        @Size(min = 3, max = 255, message = "O item deve ter entre 3 e 255 caracteres.")
        String item,
        @FutureOrPresent(message = "O prazo não pode ser uma data no passado.")
        LocalDate prazo,
        EstadoDTO estado
) {
    // Construtor compacto mantido exclusivamente para aplicar regras de valores padrão
    public TodoCreateDTO {
        if (prazo == null) {
            prazo = LocalDate.now();}
        if (estado == null) {
            estado = EstadoDTO.EM_ANDAMENTO;}
    }
}