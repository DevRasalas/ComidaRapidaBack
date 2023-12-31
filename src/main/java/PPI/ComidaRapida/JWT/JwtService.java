package PPI.ComidaRapida.JWT;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import PPI.ComidaRapida.modelo.Usuarios;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
    private static final String SECRET_KEY="passwordestupidamentelargaantihackerequisdedede";
    
    public String getToken(UserDetails usuarios) {
        return getToken(new HashMap<>(), usuarios);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", obtenerIdUsuario(user));
        claims.put("userCorreo", obtenerCorreo(user));
        claims.put("userNombre", obtenerNombre(user));
        claims.put("userApellido", obtenerApellido(user));
        claims.put("userEdad", obtenerEdad(user));
        return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(user.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes); 
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !IsTokenExpired(token));
    }
    private Claims getAllClaims(String token){
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    public <T> T getClaim(String Token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(Token);
        return claimsResolver.apply(claims);
    }
   private Date getExpiration(String token){
        return getClaim(token,Claims::getExpiration);
   }
   private Boolean IsTokenExpired(String Token){
        return getExpiration(Token).before(new Date());
   }
   private Integer obtenerIdUsuario(UserDetails user){
        if (user instanceof Usuarios){
            return ((Usuarios) user).getIdUsuario();
        }
        return null;
   }
   private String obtenerCorreo(UserDetails user){
        if (user instanceof Usuarios){
            return ((Usuarios) user).getCorreo();
        }
        return null;
   }
   private String obtenerNombre(UserDetails user){
        if (user instanceof Usuarios){
            return ((Usuarios) user).getNombre();
        }
        return null;
   }
   private String obtenerApellido(UserDetails user){
        if (user instanceof Usuarios){
            return ((Usuarios) user).getApellido();
        }
        return null;
   }
   private Integer obtenerEdad(UserDetails user){
        if (user instanceof Usuarios){
            return ((Usuarios) user).getEdad();
        }
        return null;
   }
}
