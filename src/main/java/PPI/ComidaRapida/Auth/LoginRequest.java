package PPI.ComidaRapida.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder    
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String correo;
    private String password;
}
