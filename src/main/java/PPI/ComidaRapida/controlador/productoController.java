package PPI.ComidaRapida.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import PPI.ComidaRapida.modelo.Producto;
import PPI.ComidaRapida.servicio.ProductoServicios.ProductoServicio;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("media")
@AllArgsConstructor
public class productoController {
    @Autowired
    private final ProductoServicio productoServicio;
    public final static Logger LOGGER = LoggerFactory.getLogger(productoController.class);
    private final HttpServletRequest request;
    @PostMapping("producto")
    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("nombre") String nombre, @RequestParam("precio") Integer precio ){
        
        String url;
        
        String path = productoServicio.almacenarImagen(multipartFile, nombre);
       
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        
        try{
            
            
             url = ServletUriComponentsBuilder.fromHttpUrl(host).path("/media/").path(path).toUriString();
             LOGGER.info("me ejecute" + url);
        }catch (Exception exception){
            LOGGER.info(exception.getMessage());
            url = "Biznaga";
        }
        Producto producto = new Producto();
        producto.setNombreProducto(nombre);
        producto.setPrecioProducto(precio);
        producto.setUrlImagen(url);
        productoServicio.almacenarProducto(producto);
       
        LOGGER.info(url);
        
        return Map.of("url",url);

    }
    @GetMapping("producto")
    public List<Producto> mostrarProducto(){
        return productoServicio.mostrarProductos();
    }
    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getfile(@PathVariable String filename) throws IOException{
        Resource file = productoServicio.cargarComoRecurse(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
    }

    @PostMapping("eliminar-producto")
    public void eliminarProducto(@RequestParam("idProducto") Integer idProducto){
        this.productoServicio.eliminarProducto(idProducto);
    }
    @PostMapping("actualizar-producto")
    public Map<String, String> actualizarProducto(@RequestParam("Id") Integer id, @RequestParam("file") MultipartFile multipartFile, @RequestParam("nombre") String nombre, @RequestParam("precio") Integer precio ){
        
        String url;
        
        String path = productoServicio.almacenarImagen(multipartFile, nombre);
       
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        
        try{
            
            
             url = ServletUriComponentsBuilder.fromHttpUrl(host).path("/media/").path(path).toUriString();
             LOGGER.info("me ejecute" + url);
        }catch (Exception exception){
            LOGGER.info(exception.getMessage());
            url = "Biznaga";
        }
        Producto producto = new Producto();
        producto.setIdProducto(id);
        producto.setNombreProducto(nombre);
        producto.setPrecioProducto(precio);
        producto.setUrlImagen(url);
        productoServicio.almacenarProducto(producto);
       
        LOGGER.info(url);
        
        return Map.of("url",url);
    }
}
