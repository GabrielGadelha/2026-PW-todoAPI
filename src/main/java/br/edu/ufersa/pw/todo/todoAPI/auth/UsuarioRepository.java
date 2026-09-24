package br.edu.ufersa.pw.todo.todoAPI.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
