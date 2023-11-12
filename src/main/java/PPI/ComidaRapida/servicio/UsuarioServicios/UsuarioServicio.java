package PPI.ComidaRapida.servicio.UsuarioServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PPI.ComidaRapida.modelo.Usuarios;
import PPI.ComidaRapida.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio implements IUsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Override
    public Usuarios logguearUsuario(String correo, String password) {
        return this.usuarioRepositorio.obtnerDatosUsuario(correo, password);
        
    }

    @Override
    public void registrarUsuario(Usuarios usuarios) {
        this.usuarioRepositorio.save(usuarios);
    }
    
}
