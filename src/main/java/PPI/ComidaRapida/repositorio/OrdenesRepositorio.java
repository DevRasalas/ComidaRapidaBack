package PPI.ComidaRapida.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import PPI.ComidaRapida.modelo.Ordenes;

public interface OrdenesRepositorio extends JpaRepository<Ordenes, Integer> {
    
}
