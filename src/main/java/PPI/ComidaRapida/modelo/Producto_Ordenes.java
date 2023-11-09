package PPI.ComidaRapida.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Producto_Ordenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name="prod_ord_gen", sequenceName="prod_ord_seq", allocationSize=30)
    private Integer id;
    @ManyToOne
    private Ordenes ordenes;
    @ManyToOne
    private Producto producto;
    private Integer cantidad;
    private Integer precioFinal;
}
