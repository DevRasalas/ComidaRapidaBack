package PPI.ComidaRapida.servicio;

import java.util.List;

import PPI.ComidaRapida.modelo.Ordenes;
import PPI.ComidaRapida.modelo.OrdenesDetalles;

public interface IOrdenesServicio {
    public List<Ordenes> mostrarOrdenes();
    public Ordenes addOrdenes(Ordenes ordenes);
    public OrdenesDetalles addDetalles (OrdenesDetalles detalles);
}
