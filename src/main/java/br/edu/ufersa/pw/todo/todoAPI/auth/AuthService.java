package br.edu.ufersa.pw.todo.todoAPI.auth;

import br.edu.ufersa.pw.todo.todoAPI.auth.dtos.AuthDTOs;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UsuarioRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; }
    public void registrar(AuthDTOs.RegisterRequestDTO dto) {
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado no sistema.");
        }
        String encryptedPassword = passwordEncoder.encode(dto.password());
        Usuario usu = new Usuario(dto.email(), encryptedPassword, dto.role());
        userRepository.save(usu);
    }
}