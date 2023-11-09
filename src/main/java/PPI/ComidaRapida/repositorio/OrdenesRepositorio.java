package PPI.ComidaRapida.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import PPI.ComidaRapida.modelo.Ordenes;

public interface OrdenesRepositorio extends JpaRepository<Ordenes, Integer> {
    @Query(value = "SELECT pr.nombre_producto, po.cantidad, pr.precio_producto, po.precio_final " +
            "FROM producto_ordenes po " +
            "INNER JOIN ordenes o ON po.ordenes_id_orden = o.id_orden " +
            "INNER JOIN producto pr ON pr.id_producto = po.producto_id_producto " +
            "WHERE o.id_orden = :ordenId", nativeQuery = true)
    List<Object[]> obtenerDatosDeOrden(@Param("ordenId") Integer ordenId);
    
    @Query(value="SELECT * from ordenes where idUsuario =  :idUsuario", nativeQuery = true)
    List<Ordenes> obtenerOrdenes(@Param("idUsuario") Integer idUsuario);
}
