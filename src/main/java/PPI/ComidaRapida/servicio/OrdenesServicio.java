package PPI.ComidaRapida.servicio;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PPI.ComidaRapida.modelo.Ordenes;
import PPI.ComidaRapida.modelo.Producto;
import PPI.ComidaRapida.modelo.Producto_Ordenes;
import PPI.ComidaRapida.repositorio.OrdenesRepositorio;
import PPI.ComidaRapida.repositorio.ProductoRepositorio;
import PPI.ComidaRapida.repositorio.Producto_OrdenRepository;
import jakarta.transaction.Transactional;

@Service
public class OrdenesServicio implements IOrdenesServicio {

    @Autowired
    private OrdenesRepositorio ordenesRepository;
    @Autowired
    private Producto_OrdenRepository producto_OrdenRepository;
    @Autowired
    private ProductoRepositorio productoRepository;

    @Override
    public List<Ordenes> mostrarOrdenes() {
        return this.ordenesRepository.findAll();
    }

    @Override
    public List<Producto_Ordenes> mostrarProducto_Ordenes(){
        return this.producto_OrdenRepository.findAll();
    }
  


    @Override
    @Transactional
    public void crearOrdenConProducto(Map<String, Object> ordenes) {
        // Extraer la orden del JSON
        
        Map<String, Object> ordenData = (Map<String, Object>) ordenes.get("orden");
        Ordenes nuevaOrden = new Ordenes();
        nuevaOrden.setIdUsuario(1);
        nuevaOrden.setDireccion(ordenData.get("direccion").toString());
        nuevaOrden.setMontoTotal(Integer.valueOf(ordenData.get("montoTotal").toString()));
        ordenesRepository.save(nuevaOrden);
       
        // Extraer la lista de productos del JSON
        List<Map<String, Object>> productosData = (List<Map<String, Object>>)ordenes.get("productos");

        // Recorrer la lista de productos y crear relaciones ProductoOrden
        for (Map<String, Object> productoData : productosData) {
            Integer idProducto = Integer.valueOf(productoData.get("idProducto").toString());
            Producto producto = productoRepository.findById(idProducto).orElse(null);
            Integer cantidadProd = Integer.valueOf(productoData.get("cantidad").toString());
            //producto.setPrecioProducto(Integer.valueOf(productoData.get("precioFinal").toString()));
            if (producto != null) {
                Producto_Ordenes productoOrden = new Producto_Ordenes();
                productoOrden.setOrdenes(nuevaOrden);
                productoOrden.setProducto(producto);
                productoOrden.setCantidad(cantidadProd);
                productoOrden.setPrecioFinal(producto.getPrecioProducto()*cantidadProd);
                producto_OrdenRepository.save(productoOrden);
            }
        }
        
    }

    
    
}
