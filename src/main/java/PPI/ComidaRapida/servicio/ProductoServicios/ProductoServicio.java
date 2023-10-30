package PPI.ComidaRapida.servicio.ProductoServicios;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import PPI.ComidaRapida.modelo.Producto;
import PPI.ComidaRapida.repositorio.ProductoRepositorio;
import jakarta.annotation.PostConstruct;

@Service
public class ProductoServicio implements IProductosServicio{

    @Value("${media.location}")
    private String direccionImagen;
    public final static Logger LOGGER = LoggerFactory.getLogger(ProductoServicio.class);
    private Path ubicacionRaiz;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Override
    @PostConstruct
    public void init() throws IOException {
        ubicacionRaiz = Paths.get(direccionImagen);
        Files.createDirectories(ubicacionRaiz);
    }

    @Override
    public String almacenarImagen(MultipartFile multipartFile) {
        try{
            if(multipartFile.isEmpty()){
            throw new RuntimeException("Archivo vacio");
        }
        
        
       
        String nombreArchivo = multipartFile.getOriginalFilename();
        
        Path destinoImagen = ubicacionRaiz.resolve(Paths.get(nombreArchivo)).normalize().toAbsolutePath();
        
        
        try(InputStream inputStream = multipartFile.getInputStream() ){
            Files.copy(inputStream, destinoImagen, StandardCopyOption.REPLACE_EXISTING);
        }
        return nombreArchivo;
        } catch(IOException exception){
            throw new RuntimeException("Fallo al guardar iamagen", exception);
        }
    }

    @Override
    public Resource cargarComoRecurse(String nombreImagen) {
        try{
            Path file = ubicacionRaiz.resolve(nombreImagen);
           org.springframework.core.io.Resource resourse = new UrlResource(file.toUri());
            if(resourse.exists() || resourse.isReadable()){
                return resourse;
            }else{
                throw new RuntimeException("No se pudo leer" + nombreImagen);
            }
        } catch(MalformedURLException exception){
                throw new RuntimeException("No se pudo leer el archivo"+ nombreImagen);
        }
    }

    @Override
    public Producto almacenarProducto(Producto producto) {
        return this.productoRepositorio.save(producto);
    }

    @Override
    public List<Producto> mostrarProductos() {
        return this.productoRepositorio.findAll();
    }

    
    
}
