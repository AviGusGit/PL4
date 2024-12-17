package NegocioImpl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.el.stream.Optional;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import DaoImpl.DaoUsuario;

import Entidades.Usuario;
public class UsuarioNeg {

	private DaoUsuario daoUs;
	private Usuario us;
	
	public UsuarioNeg() {
		daoUs=new DaoUsuario();
	}
	
	public Usuario existe(String contrasena, String usuarioBuscado) {
        ArrayList<Usuario> list = new ArrayList<Usuario>();
        ResultSet tabla = daoUs.ObtenerUsuarios();
        
        try {
            while (tabla.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(tabla.getInt(1));  
                u.setDniCliente(tabla.getString(2));
                u.setUsuario(tabla.getString(3));
                u.setContrasena(tabla.getString(4));
                //u.setTipoUsuario(tabla.getInt(5));
                u.setEstado(tabla.getBoolean(6));
                
                list.add(u);
            }
            
            
           java.util.Optional<Usuario> usuarioEncontrado = list.stream()
                .filter(u -> u.getUsuario().equals(usuarioBuscado) && u.getContrasena().equals(contrasena))
                .findFirst();  // Devuelve el primer usuario que coincida
            
            // Si el usuario es encontrado, lo retorna, si no, retorna null
            return usuarioEncontrado.orElse(null);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;  // Si hubo algún problema o no se encontró el usuario
    }
	
}