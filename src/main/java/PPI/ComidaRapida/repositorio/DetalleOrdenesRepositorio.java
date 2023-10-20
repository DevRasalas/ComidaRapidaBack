package PPI.ComidaRapida.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import PPI.ComidaRapida.modelo.OrdenesDetalles;

public interface DetalleOrdenesRepositorio extends JpaRepository<OrdenesDetalles, Integer>{
    
}
