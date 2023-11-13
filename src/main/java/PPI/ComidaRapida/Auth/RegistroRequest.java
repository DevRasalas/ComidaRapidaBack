package PPI.ComidaRapida.Auth;

import PPI.ComidaRapida.User.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroRequest {
    
    private String password;
    @Enumerated(EnumType.STRING)
    private Role roles;
    private String correo;
    private String nombre;
    private String apellido;
    private Integer edad;
}
