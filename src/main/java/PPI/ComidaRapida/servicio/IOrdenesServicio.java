package PPI.ComidaRapida.servicio;

import java.util.List;
import java.util.Map;

import PPI.ComidaRapida.modelo.Ordenes;
import PPI.ComidaRapida.modelo.Producto_Ordenes;

public interface IOrdenesServicio {
    public List<Ordenes> mostrarOrdenes();
    public List<Producto_Ordenes> mostrarProducto_Ordenes();
    public void crearOrdenConProducto(Map<String, Object> ordenes);
}
