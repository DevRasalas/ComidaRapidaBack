package PPI.ComidaRapida.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import PPI.ComidaRapida.modelo.Producto_Ordenes;

public interface Producto_OrdenRepository extends JpaRepository<Producto_Ordenes, Integer> {
    
}
