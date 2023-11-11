package PPI.ComidaRapida.servicio.OrdenesServicios;

import java.util.List;
import java.util.Map;

import PPI.ComidaRapida.modelo.Ordenes;

public interface IOrdenesServicio {
    public List<Ordenes> mostrarOrdenes(Integer idUsuario);
    public List<Object[]> mostrarProducto_Ordenes(Integer idOrden);
    public void crearOrdenConProducto(Map<String, Object> ordenes);
}
