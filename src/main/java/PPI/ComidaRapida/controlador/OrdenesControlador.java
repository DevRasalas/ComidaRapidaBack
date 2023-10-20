package PPI.ComidaRapida.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import PPI.ComidaRapida.modelo.Ordenes;
import PPI.ComidaRapida.modelo.OrdenesDetalles;
import PPI.ComidaRapida.servicio.OrdenesServicio;

@RestController
//Une con Angular
@CrossOrigin("http://localhost:4200")
public class OrdenesControlador {
    @Autowired
    private OrdenesServicio ordenesServicio;

    @PostMapping("/ordenes")
    public Ordenes guardarOrden(@RequestBody Ordenes ordenes){
        ordenId = ordenes.getId_orden();
        return this.ordenesServicio.addOrdenes(ordenes);
    }
    @PostMapping("/detalles-ordenes")
    public OrdenesDetalles guardarDetalles(@RequestBody OrdenesDetalles ordenesDetalles){
        ordenesDetalles.setId_orden(ordenId);
        return this.ordenesServicio.addDetalles(ordenesDetalles);
    }
    
    @GetMapping("/ordenes")
    public List<Ordenes> mostrarOrdenes(){
        return this.ordenesServicio.mostrarOrdenes();
    }
    private Integer ordenId = -3;
}

