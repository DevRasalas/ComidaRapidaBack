package PPI.ComidaRapida.modelo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FormProducto {
    MultipartFile file;
    String nombre;
    Integer precio;
}
