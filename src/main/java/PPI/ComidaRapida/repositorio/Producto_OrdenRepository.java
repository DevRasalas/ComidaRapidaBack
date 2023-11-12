package PPI.ComidaRapida.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PPI.ComidaRapida.modelo.Producto_Ordenes;

@Repository
public interface Producto_OrdenRepository extends JpaRepository<Producto_Ordenes, Integer> {
    
}
