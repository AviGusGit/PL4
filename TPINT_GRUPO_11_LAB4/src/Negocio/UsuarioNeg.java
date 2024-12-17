package Negocio;

import Entidades.Usuario;

public interface UsuarioNeg {

	public Usuario devolverUsuario(String contrasena, String usuarioBuscado);
}
