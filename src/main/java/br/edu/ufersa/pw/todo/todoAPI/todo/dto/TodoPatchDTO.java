package br.edu.ufersa.pw.todo.todoAPI.todo.dto;

import java.time.LocalDate;

public record TodoPatchDTO(String item, LocalDate prazo, EstadoDTO estado) {

}
