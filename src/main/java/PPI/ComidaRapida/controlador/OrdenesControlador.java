package PPI.ComidaRapida.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import PPI.ComidaRapida.modelo.Ordenes;
import PPI.ComidaRapida.modelo.Producto_Ordenes;
import PPI.ComidaRapida.servicio.OrdenesServicio;

@RestController
//Une con Angular
@CrossOrigin("http://localhost:4200")
public class OrdenesControlador {
    @Autowired
    private OrdenesServicio ordenesServicio;
    
    @PostMapping("/ordenes")
    public void guardarOrden(@RequestBody Map<String, Object> ordenes){
         this.ordenesServicio.crearOrdenConProducto(ordenes);
    }
    
    
    @GetMapping("/ordenes")
    public List<Ordenes> mostrarOrdenes(@RequestParam("idUsuario") Integer idUsuario ){
        return this.ordenesServicio.mostrarOrdenes(idUsuario);
    }
    @GetMapping("/producto_ordenes")
    public List<Object[]> mostrarProductoOrdenes(@RequestParam("idOrden") Integer idOrden){
        return this.ordenesServicio.mostrarProducto_Ordenes(idOrden);
    }
}

