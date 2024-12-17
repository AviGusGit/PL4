package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Dao.DaoUsuario;
import Entidades.Usuario;
import Entidades.TipoUsuario;

public class DaoUsuarioImpl implements DaoUsuario {
	
    private Conexion cn;
	
    public DaoUsuarioImpl() {
    }

    @Override
    public Usuario devolverUsuario(String contrasena, String usuario) {
        cn = new Conexion();
        cn.Open();

        Usuario usu = null;

        try {
            String query = "SELECT IDUsuario_U, dniCliente_U, usuario_U, contrasena_U, tipoUsuario_U " +
                           "FROM usuarios " +
                           "WHERE estado_U = 1 AND usuario_U = ? AND contrasena_U = ?";

            PreparedStatement ps = cn.getConnection().prepareStatement(query);
            ps.setString(1, usuario); 
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) { 
                usu = new Usuario(); 
                usu.setIdUsuario(rs.getInt("IDUsuario_U"));
                usu.setDniCliente(rs.getString("dniCliente_U"));
                usu.setUsuario(rs.getString("usuario_U"));
                usu.setContrasena(rs.getString("contrasena_U"));

                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setIdTipo(rs.getInt("tipoUsuario_U"));
                usu.setTipoUsuario(tipoUsuario);
            }

            rs.close(); 
            ps.close(); 
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }

        return usu;
    }
    
    @Override
    public boolean agregarUsuario(Usuario usuario) {
        cn = new Conexion();
        cn.Open();
        boolean agregado = false;

        try {
            String query = "INSERT INTO usuarios (dniCliente_U, usuario_U, contrasena_U, tipoUsuario_U, estado_U) " +
                           "VALUES (?, ?, ?, ?, 1)";

            PreparedStatement ps = cn.getConnection().prepareStatement(query);
            ps.setString(1, usuario.getDniCliente());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getContrasena());
            ps.setInt(4, usuario.getTipoUsuario().getIdTipo());  // Asegúrate de tener un `TipoUsuario`

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                agregado = true;
                System.out.println("Usuario agregado exitosamente.");
            }

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }

        return agregado;
    }

}
