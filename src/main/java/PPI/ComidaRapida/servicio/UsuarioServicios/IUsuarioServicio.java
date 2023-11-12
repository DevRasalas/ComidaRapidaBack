package PPI.ComidaRapida.servicio.UsuarioServicios;

import PPI.ComidaRapida.modelo.Usuarios;

public interface IUsuarioServicio {
    public Usuarios logguearUsuario(String correo, String password);
    public void registrarUsuario(Usuarios usuarios);
}
