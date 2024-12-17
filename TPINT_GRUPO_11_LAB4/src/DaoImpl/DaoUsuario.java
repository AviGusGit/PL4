package DaoImpl;

import java.sql.ResultSet;

public class DaoUsuario {

	
	private Conexion con;
	
	public DaoUsuario() {
		con=new Conexion();
		
	}
	
	
	public ResultSet ObtenerUsuarios() {
		try {
			
			con.Open();
			String query="SELECT * FROM usuarios; ";
			ResultSet tabla= con.query(query);
			if(tabla!=null) {
				 System.out.println("OBTUVO RESULTADOS");
			}
			return tabla;
			
		}catch( Exception E) {
			
			E.printStackTrace();
		}finally {
			con.close();
			
			
		}
		return null;
		
		
				
	}
}