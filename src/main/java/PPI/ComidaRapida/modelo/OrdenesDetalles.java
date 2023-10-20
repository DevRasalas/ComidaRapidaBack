package PPI.ComidaRapida.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "detalle_ordenes")
public class OrdenesDetalles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_detalle;
    Integer id_orden;
    Integer id_producto;
    Integer cantidad;
    Integer precio;
}