package br.edu.ufersa.pw.todo.todoAPI.auth;

import br.edu.ufersa.pw.todo.todoAPI.auth.dtos.AuthDTOs;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthService authService;
    public AuthController(AuthenticationManager authenticationManager,
                          TokenService tokenService,
                          AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTOs.TokenResponseDTO> login(@RequestBody @Valid AuthDTOs.LoginRequestDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = authenticationManager.authenticate(authToken);
        String token = tokenService.generateToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new AuthDTOs.TokenResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid AuthDTOs.RegisterRequestDTO dto) {
        authService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
