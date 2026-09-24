package br.edu.ufersa.pw.todo.todoAPI.auth;

import jakarta.persistence.*;

@Embeddable
public record Senha(
        @Column(name = "senha", nullable = false)
        String segredo) {
    public Senha{
        if(segredo==null || segredo.isBlank())
            throw new IllegalArgumentException("A senha é obrigatória!");
        if (segredo.length()<6)
            throw new IllegalArgumentException("A senha deve possuir 6 dígitos ou mais!");
    }
}
