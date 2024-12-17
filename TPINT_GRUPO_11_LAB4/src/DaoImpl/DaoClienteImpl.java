package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Dao.DaoCliente;
import Entidades.Cliente;
import Entidades.Prestamo;

public class DaoClienteImpl implements DaoCliente {
	
	private Conexion cn;

	// Constructor 
	
	public DaoClienteImpl()
	{
	}


	// Metodos

    public ArrayList<Cliente> obtenerClientes() {

    	cn = new Conexion();
		cn.Open();
		
		ArrayList<Cliente> list = new ArrayList<Cliente>();
		
		try {
			ResultSet rs = cn.query("SELECT DNI_CL, CUIL_CL, nombre_CL, apellido_CL, sexo_CL, nacionalidad_CL, fechaNac_CL, direccion_CL, localidad_CL, provincia_CL, correo_CL, telefono_CL FROM clientes WHERE estado_CL=1");
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setDni(rs.getString("DNI_CL"));
				cliente.setCuil(rs.getString("CUIL_CL"));
				cliente.setNombre(rs.getString("nombre_CL"));
				cliente.setApellido(rs.getString("apellido_CL"));
				cliente.setSexo(rs.getString("sexo_CL"));
				cliente.setNacionalidad(rs.getString("nacionalidad_CL"));
				cliente.setFechaNacimiento(rs.getDate("fechaNac_CL"));
				cliente.setDireccion(rs.getString("direccion_CL"));
				cliente.setLocalidad(rs.getString("localidad_CL"));
				cliente.setProvincia(rs.getString("provincia_CL"));
				cliente.setCorreo(rs.getString("correo_CL"));
				cliente.setTelefono(rs.getString("telefono_CL"));


				list.add(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}

		return list;
	}
    
    
    @Override
    public ArrayList<String> obtenerDNIClientes() {
    	cn = new Conexion();
        cn.Open();

        ArrayList<String> dniList = new ArrayList<>();
        try {
            ResultSet rs = cn.query("SELECT DNI_CL FROM clientes WHERE estado_CL = 1");
            
            while (rs.next()) {
                dniList.add(rs.getString("DNI_CL"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }

        return dniList;
    }
	@Override
	public Cliente obtenerUno(String dni) {
	    cn = new Conexion();
	    cn.Open();

	    Cliente cliente = null;

	    try {
	        String query = "SELECT DNI_CL, CUIL_CL, nombre_CL, apellido_CL, sexo_CL, nacionalidad_CL, fechaNac_CL, " +
	                       "direccion_CL, localidad_CL, provincia_CL, correo_CL, telefono_CL " +
	                       "FROM clientes WHERE estado_CL = 1 AND DNI_CL = ?";
	        
	        PreparedStatement ps = cn.getConnection().prepareStatement(query);
	        ps.setString(1, dni);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            cliente = new Cliente();
	            cliente.setDni(rs.getString("DNI_CL"));
	            cliente.setCuil(rs.getString("CUIL_CL"));
	            cliente.setNombre(rs.getString("nombre_CL"));
	            cliente.setApellido(rs.getString("apellido_CL"));
	            cliente.setSexo(rs.getString("sexo_CL"));
	            cliente.setNacionalidad(rs.getString("nacionalidad_CL"));
	            cliente.setFechaNacimiento(rs.getDate("fechaNac_CL"));
	            cliente.setDireccion(rs.getString("direccion_CL"));
	            cliente.setLocalidad(rs.getString("localidad_CL"));
	            cliente.setProvincia(rs.getString("provincia_CL"));
	            cliente.setCorreo(rs.getString("correo_CL"));
	            cliente.setTelefono(rs.getString("telefono_CL"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return cliente;
	}

	@Override
	public boolean agregarCliente(Cliente cliente) {
		
		if(existeCliente(cliente.getDni())) {
			return false;
		}
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "INSERT INTO CLIENTES (DNI_CL, CUIL_CL, nombre_CL, apellido_CL, sexo_CL, nacionalidad_CL, fechaNac_CL, direccion_CL, localidad_CL, provincia_CL, correo_CL, telefono_CL, estado_CL ) VALUES ('"
			    + cliente.getDni() + "','" + cliente.getCuil() + "','" + cliente.getNombre() + "','"
			    + cliente.getApellido() + "','" + cliente.getSexo() + "','" + cliente.getNacionalidad() + "','"
			    + cliente.getFechaNacimiento() + "','" + cliente.getDireccion() + "','" + cliente.getLocalidad()
			    + "','" + cliente.getProvincia() + "','" + cliente.getCorreo() + "','" + cliente.getTelefono() + "', 1)";

		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return estado;
	}

	@Override
	public boolean modificarCliente(Cliente cliente) {
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE CLIENTES SET " +
	               "dni_CL = '" + cliente.getDni() + "', " +
	               "cuil_CL = '" + cliente.getCuil() + "', " +
	               "nombre_CL = '" + cliente.getNombre() + "', " +
	               "apellido_CL = '" + cliente.getApellido() + "', " +
	               "sexo_CL = '" + cliente.getSexo() + "', " +
	               "nacionalidad_CL = '" + cliente.getNacionalidad() + "', " +
	               "fechaNac_CL = '" + cliente.getFechaNacimiento() + "', " +
	               "direccion_CL = '" + cliente.getDireccion() + "', " +
	               "localidad_CL = '" + cliente.getLocalidad() + "', " +
	               "provincia_CL = '" + cliente.getProvincia() + "', " +
	               "correo_CL = '" + cliente.getCorreo() + "', " +
	               "telefono_CL = '" + cliente.getTelefono() + "' " +
	               "WHERE dni_CL = '" + cliente.getDni() + "'";
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}

	@Override
	public boolean eliminarCliente(String dni) {
		
	    boolean estado = true;
	    cn = new Conexion();
	    
	    String query = "UPDATE CLIENTES SET estado_CL = 0 WHERE dni_CL = ?";
	    
	    try {
	        cn.Open(); 
	        PreparedStatement ps = cn.getConnection().prepareStatement(query);
	        ps.setString(1, dni);
	        
	        int filasAfectadas = ps.executeUpdate(); 
	        
	        estado = filasAfectadas > 0;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        estado = false; 
	        
	    } finally {
	        cn.close(); 
	    }
	    
	    return estado;
	}



	@Override
	public boolean existeCliente(String dni) {
		
		ArrayList<String> listDniClientes = new ArrayList<String>();
		
		
		for(String d : listDniClientes) {
			
			if(d.equals(dni)) {
				return true;
			}
		}
		
		return false;
	}


	@Override
	public void asignarPrestamo(int idPrestamo, Cliente cliente) {
		 cn = new Conexion();
		    cn.Open();

	

		    try {
		        String query = "INSERT INTO MOVIMIENTOS VALUES(?,?,?,?,?)";
		        
		        PreparedStatement ps = cn.getConnection().prepareStatement(query);
		        ps.setInt(1, 100);
		        Long time = System.currentTimeMillis();
				ps.setDate(2, new java.sql.Date(time ));
				ps.setString(3, "descripcion");
				ps.setFloat(4,1000);
				ps.setInt(5, 2);
		        ps.executeUpdate();

		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        cn.close();
		    }
		
	}


	@Override
	public List<Prestamo> obtenerPrestamos(String dni) {
		  cn = new Conexion();
		    cn.Open();

		    List<Prestamo> prestamos= new ArrayList<>();

		    try {
		        String query = "SELECT * " +
		                       "FROM prestamos WHERE dniCliente_P = ?";
		        
		        PreparedStatement ps = cn.getConnection().prepareStatement(query);
		        ps.setString(1, dni);
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            Prestamo prestamo = new Prestamo();
		            prestamo.setFecha(rs.getDate("fecha_P"));
		            prestamo.setImportePagar(rs.getFloat("importePagar_P"));
		            prestamo.setImportePedido(rs.getFloat("importePedido_P"));
		            prestamo.setPlazoPago(rs.getString("plazoPago_P"));
		            prestamo.setMontoMes(rs.getFloat("montoPorMes_P"));
		            prestamo.setCuotas(rs.getInt("cuota_P"));
		            prestamo.setEstado(rs.getString("estado_P"));
		            prestamo.setNumeroPres(rs.getInt("numeroPres_P"));
		            
		            prestamos.add(prestamo);
		          
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        cn.close();
		    }
		return prestamos;
		
	}

}
