package br.edu.ufersa.pw.todo.todoAPI.auth.dtos;

import br.edu.ufersa.pw.todo.todoAPI.auth.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public interface AuthDTOs {
    public record LoginRequestDTO(
            @NotBlank(message = "O e-mail é obrigatório")
            @Email(message = "Formato de e-mail inválido")
            String email,
            @NotBlank(message = "A senha é obrigatória")
            String password ) {}
    public record TokenResponseDTO(String token) {}
    public record RegisterRequestDTO(
            @NotBlank(message = "O e-mail é obrigatório")
            @Email(message = "Formato de e-mail inválido")
            String email,
            @NotBlank(message = "A senha é obrigatória")
            @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
            String password,
            @NotNull(message = "O perfil (role) é obrigatório")
            UserRole role ) {}
}
