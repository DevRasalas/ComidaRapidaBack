package PPI.ComidaRapida.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import PPI.ComidaRapida.JWT.JwtService;
import PPI.ComidaRapida.User.Role;
import PPI.ComidaRapida.modelo.Usuarios;
import PPI.ComidaRapida.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepositorio usuarioRepositorio;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public final static Logger LOGGER = LoggerFactory.getLogger(AuthService.class);
    public AuthResponse login(LoginRequest loginRequest) {
        LOGGER.info(loginRequest.getCorreo()+" "+loginRequest.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getCorreo(), loginRequest.getPassword()));
        UserDetails user = usuarioRepositorio.findByCorreo(loginRequest.getCorreo()).orElseThrow();
        LOGGER.info(user.getUsername()+" "+user.getPassword());
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
        .token(token)
        .build();
    }
    public AuthResponse registrar(RegistroRequest registroRequest) {
        Usuarios usuarios = Usuarios.builder()
            .correo(registroRequest.getCorreo())
            .roles(Role.cliente)
            .password(passwordEncoder.encode(registroRequest.getPassword()))
            .build();
        usuarioRepositorio.save(usuarios);

        return AuthResponse.builder()
            .token(jwtService.getToken(usuarios))
            .build();

    }

}
