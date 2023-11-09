package PPI.ComidaRapida.servicio.ProductoServicios;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import PPI.ComidaRapida.modelo.Producto;

public interface IProductosServicio {
    void init() throws IOException;
    String almacenarImagen(MultipartFile multipartFile, String nombre);
    Producto almacenarProducto(Producto producto);
    Resource cargarComoRecurse(String nombreImagen);
    List<Producto> mostrarProductos();
    void eliminarProducto(Integer idProducto);
    Producto actualizarProducto(Producto producto);
}
