package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Dao.DaoCuenta;
import Entidades.Cuenta;
import Entidades.TipoCuenta;

public class DaoCuentaImpl implements DaoCuenta {
	
	private Conexion cn;

	public DaoCuentaImpl() {
		
	}
	
	@Override
	public ArrayList<Cuenta> obtenerCuentas() {
		
    	cn = new Conexion();
		cn.Open();
		
		ArrayList<Cuenta> list = new ArrayList<Cuenta>();
		
		try {
			ResultSet rs = cn.query("SELECT CU.numeroCuenta_CU, CU.dniCliente_CU, CU.fechaCreacion_CU, CU.IDtipoCuenta_CU, CU.CBU_CU, CU.saldo_CU, TC.Descripcion from CUENTAS CU INNER JOIN  TIPOCUENTA TC  ON CU.IDtipoCuenta_CU = TC.IDtipoCuenta WHERE CU.estado_CU = 1;");
			
			// "SELECT numeroCuenta_CU, dniCliente_CU, fechaCreacion_CU, nombre as tipoCuenta, CBU_CU, saldo_CU FROM CUENTAS INNER JOIN TIPOCUENTA WHERE IDtipoCuenta_CU = IDtipoCuenta"
			
			while (rs.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setNumeroCuenta(rs.getInt("numeroCuenta_CU"));
				cuenta.setDniCliente(rs.getString("dniCliente_CU"));
				cuenta.setFechaCreacion(rs.getDate("fechaCreacion_CU"));
				int tipoDeCuenta =rs.getInt("numeroCuenta_CU");
				String Descripcion = rs.getString("Descripcion");
				TipoCuenta auxC = new TipoCuenta(tipoDeCuenta,Descripcion);
				cuenta.setTipoCuenta(auxC);				
				cuenta.setCbu(rs.getString("CBU_CU"));
				cuenta.setSaldo(rs.getFloat("saldo_CU"));

				list.add(cuenta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}

		return list;
	}

	@Override
	public Cuenta obtenerUno(int numeroCuenta) {
	    cn = new Conexion();
	    cn.Open();

	    Cuenta cuenta = null;

	    try {
	        String query = "SELECT numeroCuenta_CU, dniCliente_CU, fechaCreacion_CU, IDtipoCuenta_CU, CBU_CU, saldo_CU " +
	                       "FROM CUENTAS WHERE estado_CU = 1 AND numeroCuenta_CU = ?";
	        
	        PreparedStatement ps = cn.getConnection().prepareStatement(query);
	        ps.setInt(1, numeroCuenta); 
	        
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            cuenta = new Cuenta();
				
	            cuenta.setNumeroCuenta(rs.getInt("numeroCuenta_CU"));
	            cuenta.setDniCliente(rs.getString("dniCliente_CU"));
	            cuenta.setFechaCreacion(rs.getDate("fechaCreacion_CU"));
	            
	            cuenta.setTipoCuentaInt(rs.getInt("numeroCuenta_CU"));
				
	            cuenta.setCbu(rs.getString("CBU_CU"));
	            cuenta.setSaldo(rs.getFloat("saldo_CU"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close(); 
	    }

	    return cuenta;
	}
	
	

	@Override
	public boolean agregarCuenta(Cuenta cuenta) {
		
		if(maxCuentas(cuenta.getDniCliente())) {
			return false;
		}
		
		boolean estado=true;

		cn = new Conexion();
		cn.Open();	

		String query = "INSERT INTO CUENTAS (dniCliente_CU, fechaCreacion_CU, IDtipoCuenta_CU, CBU_CU, saldo_CU, estado_CU) VALUES ('"
	              + cuenta.getDniCliente() + "', '"
	              + cuenta.getFechaCreacion() + "', "
	              + cuenta.getTipoCuenta().getIdTipo() + ", '"
	              + cuenta.getCbu() + "', 10000.00, 1)";

		System.out.println(query);
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
	public boolean modificarCuenta(Cuenta cuenta) {
		
		if(maxCuentas(cuenta.getDniCliente())) {
			return false;
		}
		
	    boolean estado = true;

	    cn = new Conexion();
	    cn.Open();

	    String query = "UPDATE CUENTAS SET "
	                 + "dniCliente_CU = '" + cuenta.getDniCliente() + "', "
	                 + "fechaCreacion_CU = '" + cuenta.getFechaCreacion() + "', "
	                 + "IDtipoCuenta_CU = " + cuenta.getTipoCuenta().getIdTipo() + ", "
	                 + "CBU_CU = '" + cuenta.getCbu() + "', "
	                 + "saldo_CU = " + cuenta.getSaldo()
	                 + " WHERE numeroCuenta_CU = " + cuenta.getNumeroCuenta();

	    try {
	        estado = cn.execute(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        estado = false;
	    } finally {
	        cn.close();
	    }

	    return estado;
	}
	
	@Override
	public boolean eliminarCuenta(int numeroCuenta) {
		
		boolean estado = true;
		
		cn = new Conexion();
		cn.Open();		 
		
		String query = "UPDATE CUENTAS SET estado_CU=0 WHERE numeroCuenta_CU="+numeroCuenta;
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
	
	
    public ArrayList<Integer> obtenerNumCuenta() {
    	cn = new Conexion();
        cn.Open();

        ArrayList<Integer> List = new ArrayList<>();
        
        try {
            ResultSet rs = cn.query("SELECT numeroCuenta_CU FROM cuentas WHERE estado_CU = 1");
            
            while (rs.next()) {
            	
                List.add(rs.getInt("numeroCuenta_CU"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }

        return List;
    }

	@Override
	public ArrayList<Integer> obtenerCuentasCliente(String dniCliente) {
		cn = new Conexion();
		cn.Open();

		ArrayList<Integer> list = new ArrayList<Integer>();

		try {
		    String query = "SELECT numeroCuenta_CU FROM CUENTAS WHERE estado_CU=1 AND dniCliente_CU = ?";
		    
		    PreparedStatement ps = cn.getConnection().prepareStatement(query);
		    
		    ps.setString(1, dniCliente); 
		    
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {

		        list.add(rs.getInt("numeroCuenta_CU"));
		    }
		    
		    rs.close();
		    ps.close();

		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    cn.close();
		}

		return list;

	}
	@Override
	public float obtenerTotal(String dniCliente) {
		
		cn = new Conexion();
		cn.Open();

	    float total = 0.0f;
	    System.out.println("entro a la fx " + total);
	    try {
	        String query = "SELECT SUM(saldo_CU) AS saldoTotal FROM CUENTAS WHERE dniCliente_CU = ? AND estado_CU = 1;";

	        // Usamos PreparedStatement con parámetro
	        PreparedStatement ps = cn.getConnection().prepareStatement(query);
	        ps.setString(1, dniCliente);  // Usamos el parámetro 'dniCliente' en la consulta
	        ResultSet rs = ps.executeQuery();

	        // Verificamos si hay resultados
	        if (rs.next()) {
	            System.out.println("intentando agarrar el valor");
	            // Usamos getObject para obtener el saldo total como un objeto
	            Object saldoTotalObj = rs.getObject("saldoTotal");

	            if (saldoTotalObj != null) {
	                // Convertimos el objeto a Float
	                total = ((Number) saldoTotalObj).floatValue();
	                System.out.println("El saldo es: " + total);
	            }
	        }
	        rs.close();
	        ps.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            cn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    return total;
	}

	
	@Override
	public boolean maxCuentas(String dniCliente) {
		
		int cantCuentas = obtenerCuentasCliente(dniCliente).size();
		
		if(cantCuentas >= 3) {
			return true;
		}
		
		return false;
	}

	@Override
	public ArrayList<String> obtenerCbuTransf(int cuentaExcluida) {
		
	    Conexion cn = new Conexion();
	    ArrayList<String> cbuList = new ArrayList<>();

	    try {
	        cn.Open();
	        String query = "SELECT CBU_CU FROM cuentas WHERE estado_CU = 1 AND numeroCuenta_CU != ?";
	        PreparedStatement ps = cn.getConnection().prepareStatement(query);
	        ps.setInt(1, cuentaExcluida);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            cbuList.add(rs.getString("CBU_CU"));
	        }

	        rs.close();
	        ps.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return cbuList;
	}
	
	@Override
	public Cuenta obtenerUltimaCuenta() {
	    cn = new Conexion();
	    cn.Open();

	    Cuenta cuenta = null;

	    try {
	        // Consulta para obtener la última cuenta ingresada ordenada por el número de cuenta descendente
	        String query = "SELECT numeroCuenta_CU, dniCliente_CU, fechaCreacion_CU, IDtipoCuenta_CU, CBU_CU, saldo_CU "
	                     + "FROM CUENTAS WHERE estado_CU = 1 ORDER BY numeroCuenta_CU DESC LIMIT 1";
	        
	        PreparedStatement ps = cn.getConnection().prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            cuenta = new Cuenta();
	            cuenta.setNumeroCuenta(rs.getInt("numeroCuenta_CU"));
	            cuenta.setDniCliente(rs.getString("dniCliente_CU"));
	            cuenta.setFechaCreacion(rs.getDate("fechaCreacion_CU"));
	            
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setIdTipo(rs.getInt("IDtipoCuenta_CU"));
	            cuenta.setTipoCuenta(tipoCuenta);

	            cuenta.setCbu(rs.getString("CBU_CU"));
	            cuenta.setSaldo(rs.getFloat("saldo_CU"));
	        }

	        rs.close();
	        ps.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return cuenta;
	}
	/*
	 public boolean realizarTransferencia(int cuentaOrigen, int cuentaDestino, float monto) {
    Conexion cn = new Conexion();
    boolean estado = false;

    try {
        cn.Open();

        // Verificar saldo de la cuenta origen
        String consultaSaldo = "SELECT saldo_CU FROM cuentas WHERE numeroCuenta_CU = " + cuentaOrigen;
        ResultSet rs = cn.query(consultaSaldo);
        if (rs.next() && rs.getFloat("saldo_CU") >= monto) {

            // Descontar saldo de la cuenta origen
            String updateOrigen = "UPDATE cuentas SET saldo_CU = saldo_CU - " + monto + 
                                  " WHERE numeroCuenta_CU = " + cuentaOrigen;
            cn.execute(updateOrigen);

            // Incrementar saldo en la cuenta destino
            String updateDestino = "UPDATE cuentas SET saldo_CU = saldo_CU + " + monto + 
                                   " WHERE numeroCuenta_CU = " + cuentaDestino;
            cn.execute(updateDestino);

            // Registrar movimiento en cuenta origen
            String movimientoOrigen = "INSERT INTO movimientos (numeroCuenta_M, fecha_M, detalle_M, importe_M, IDtipoMovimientos_M) " +
                                       "VALUES (" + cuentaOrigen + ", CURDATE(), 'Transferencia enviada', -" + monto + ", 4)";
            cn.execute(movimientoOrigen);

            // Registrar movimiento en cuenta destino
            String movimientoDestino = "INSERT INTO movimientos (numeroCuenta_M, fecha_M, detalle_M, importe_M, IDtipoMovimientos_M) " +
                                        "VALUES (" + cuentaDestino + ", CURDATE(), 'Transferencia recibida', " + monto + ", 4)";
            cn.execute(movimientoDestino);

            estado = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        cn.close();
    }

    return estado;
}
	 */

}
