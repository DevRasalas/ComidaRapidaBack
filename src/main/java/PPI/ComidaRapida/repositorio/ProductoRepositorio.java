package PPI.ComidaRapida.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import PPI.ComidaRapida.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{
    
}
