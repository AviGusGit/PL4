package Dao;

import Entidades.Usuario;

public interface DaoUsuario {

	public Usuario devolverUsuario(String contrasena, String usuario);
	boolean agregarUsuario(Usuario usuario);
}
