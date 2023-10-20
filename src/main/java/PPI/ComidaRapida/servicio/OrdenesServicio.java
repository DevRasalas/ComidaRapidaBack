package PPI.ComidaRapida.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PPI.ComidaRapida.modelo.Ordenes;
import PPI.ComidaRapida.modelo.OrdenesDetalles;
import PPI.ComidaRapida.repositorio.DetalleOrdenesRepositorio;
import PPI.ComidaRapida.repositorio.OrdenesRepositorio;
import jakarta.transaction.Transactional;

@Service
public class OrdenesServicio implements IOrdenesServicio {

    @Autowired
    private OrdenesRepositorio ordenesRepositorio;
    @Autowired
    private DetalleOrdenesRepositorio detalleOrdenesRepositorio;

    @Override
    @Transactional
    public List<Ordenes> mostrarOrdenes() {
        return this.ordenesRepositorio.findAll();
    }

    @Override
    @Transactional
    public Ordenes addOrdenes(Ordenes ordenes) {
        return this.ordenesRepositorio.save(ordenes);
    }

    @Override
    @Transactional
    public OrdenesDetalles addDetalles(OrdenesDetalles detalles) {
        return this.detalleOrdenesRepositorio.save(detalles);
    }
    
    
}
