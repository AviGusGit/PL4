package DaoImpl;

import java.sql.*;
import java.util.ArrayList;
import Dao.DaoPrestamo;
import Entidades.Cliente;
import Entidades.Cuenta;
import Entidades.Prestamo;

public class DaoPrestamoImpl implements DaoPrestamo {
	
    private Conexion conexion;

    private Conexion cn;
	// Metodos


    
    public DaoPrestamoImpl() {
    }

    
    public DaoPrestamoImpl(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean solicitarPrestamo(Prestamo prestamo) {

        // Inicializar la conexión
        cn = new Conexion();
        cn.Open();
        
        boolean estado = true;

        // Crear la consulta SQL usando concatenación de cadenas
        String query = "INSERT INTO prestamos (dniCliente_P, numeroCuenta_P, fecha_P, importePagar_P, importePedido_P, plazoPago_P, MontoPorMes_P, cuotas_P, estado_P) VALUES ('"
                + prestamo.getDniCliente() + "', " 
                + prestamo.getNumeroCuenta() + ", '"
                + prestamo.getFecha() + "', "
                + prestamo.getImportePagar() + ", "
                + prestamo.getImportePedido() + ", '"
                + prestamo.getPlazoPago() + "', "
                + prestamo.getMontoMes() + ", "
                + prestamo.getCuotas() + ", '"
                + prestamo.getEstado() + "')";
        
        try {
            // Ejecutar la consulta
            estado = cn.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            estado = false;
        } finally {
            // Cerrar la conexión
            cn.close();
        }

        return estado;
    }


    @Override
    public boolean actualizarEstado(int numPrestamo, String estado) {

        // Inicializar la conexión
        cn = new Conexion();
        cn.Open();

        boolean resultado = true;

        // Crear la consulta SQL usando concatenación de cadenas
        String query = "UPDATE prestamos SET estado_P = '" + estado + "' WHERE numeroPres_P = " + numPrestamo;

        try {
            // Ejecutar la consulta
            resultado = cn.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            resultado = false;
        } finally {
            // Cerrar la conexión
            cn.close();
        }

        return resultado;
    }


    @Override
    public Prestamo obtenerPrestamoPorNumero(int numeroPres) {
    	cn = new Conexion();
		cn.Open();
        String query = "SELECT * FROM prestamos WHERE numeroPres_P = ?";
        
        try {
        	PreparedStatement ps = cn.getConnection().prepareStatement(query);
            ps.setInt(1, numeroPres);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setNumeroPres(rs.getInt("numeroPres_P"));
                prestamo.setDniCliente(rs.getString("dniCliente_P"));
                prestamo.setNumeroCuenta(rs.getInt("numeroCuenta_P"));
                prestamo.setFecha(rs.getDate("fecha_P"));
                prestamo.setImportePagar(rs.getFloat("importePagar_P"));
                prestamo.setImportePedido(rs.getFloat("importePedido_P"));
                prestamo.setPlazoPago(rs.getString("plazoPago_P"));
                prestamo.setMontoMes(rs.getFloat("montoPorMes_P"));
                prestamo.setCuotas(rs.getInt("cuotas_P"));
                prestamo.setEstado(rs.getString("estado_P"));
                return prestamo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Prestamo> listarPrestamosPorCliente(String dniCliente) {
    	
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        String query = "SELECT * FROM prestamos WHERE dni_cliente = ?";
        
        try {
        	PreparedStatement ps = conexion.getConnection().prepareStatement(query);
        	
            ps.setString(1, dniCliente);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setNumeroPres(rs.getInt("numero_pres"));
                prestamo.setDniCliente(rs.getString("dni_cliente"));
                prestamo.setNumeroCuenta(rs.getInt("numeroCuenta"));
                prestamo.setFecha(rs.getDate("fecha"));
                prestamo.setImportePagar(rs.getFloat("importe_pagar"));
                prestamo.setImportePedido(rs.getFloat("importe_pedido"));
                prestamo.setPlazoPago(rs.getString("plazo_pago"));
                prestamo.setMontoMes(rs.getFloat("monto_mes"));
                prestamo.setCuotas(rs.getInt("cuotas"));
                prestamo.setEstado(rs.getString("estado"));
                prestamos.add(prestamo);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return prestamos;
    }

    @Override
    public ArrayList<Prestamo> listarTodos() {
    	
    	conexion = new Conexion();
		conexion.Open();
		
		ArrayList<Prestamo> list = new ArrayList<Prestamo>();
		
		try {
			ResultSet rs = conexion.query("SELECT numeroPres_P, dniCliente_P, numeroCuenta_P, fecha_P, importePagar_P, importePedido_P, plazoPago_P, montoPorMes_P, cuotas_P FROM PRESTAMOS WHERE estado_P = 'Pedido'");
			
			while (rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setNumeroPres(rs.getInt("numeroPres_P"));
				prestamo.setDniCliente(rs.getString("dniCliente_P"));
				prestamo.setNumeroCuenta(rs.getInt("numeroCuenta_P"));
				prestamo.setFecha(rs.getDate("fecha_P"));
				prestamo.setImportePagar(rs.getFloat("importePagar_P"));
				prestamo.setImportePedido(rs.getFloat("importePedido_P"));
				prestamo.setPlazoPago(rs.getString("plazoPago_P"));
				prestamo.setMontoMes(rs.getFloat("montoPorMes_P"));
				prestamo.setCuotas(rs.getInt("cuotas_P"));

				list.add(prestamo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexion.close();
		}

		return list;
    }

    @Override
    public boolean pagarCuota(int numeroPres, float monto, int cuentaAsociada) {
    	
    	String query = "UPDATE prestamos SET cuotas_P = cuotas_P - 1, importePagar_P = importePagar_P - ? " +
                "WHERE numeroPres_P = ? AND cuotas_P > 0 AND importePagar_P >= ?";
       try {
       PreparedStatement ps = conexion.getConnection().prepareStatement(query);
      
           ps.setFloat(1, monto);
           ps.setInt(2, numeroPres);
           ps.setFloat(3, monto);
           return ps.executeUpdate() > 0;
       } catch (SQLException e) {
           e.printStackTrace();
           return false;
       }
    }


	@Override
	public ArrayList<Prestamo> listarConFiltro(String filtro) {
		return null;
	}
}
