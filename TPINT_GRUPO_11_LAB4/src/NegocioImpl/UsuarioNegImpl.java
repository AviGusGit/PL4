package NegocioImpl;

import Dao.DaoUsuario;
import DaoImpl.DaoUsuarioImpl;

import Entidades.Usuario;
import Negocio.UsuarioNeg;

public class UsuarioNegImpl implements UsuarioNeg {

	private DaoUsuario daoUsuario = new DaoUsuarioImpl();
	
	
	public UsuarioNegImpl() {
		
	}
	
	public UsuarioNegImpl(DaoUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}
	

	@Override
	public Usuario devolverUsuario(String contrasena, String usuarioBuscado) {
		return daoUsuario.devolverUsuario(contrasena, usuarioBuscado);
	}

	public boolean agregarUsuario(Usuario usuario) {
		return daoUsuario.agregarUsuario(usuario);
	}
	
}