package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidades.Cliente;
import Entidades.Prestamo;
import Entidades.Usuario;
import NegocioImpl.ClienteNegImpl;
import NegocioImpl.CuentaNegImpl;
import NegocioImpl.PrestamoNegImpl;
import NegocioImpl.UsuarioNegImpl;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Login() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  if (request.getParameter("btnAceptarLogin") != null) {
			  
			  String usuario = request.getParameter("usuario");
			  String contrasena = request.getParameter("contrasena");
			   
			 UsuarioNegImpl usuarioNeg = new UsuarioNegImpl();
			 Usuario u =  usuarioNeg.devolverUsuario(contrasena, usuario);
			 //PrestamoNegImpl negPrestamo = new PrestamoNegImpl();
			 //ArrayList<Prestamo>prestamoPorUsuario = new ArrayList<>();
			 
			 HttpSession sessionUsuario = request.getSession();
			 
			 if(u!=null) {
				 
				 sessionUsuario.setAttribute("usuarioLogueado", true);
				 sessionUsuario.setAttribute("usuarioRol", u.getTipoUsuario().getIdTipo());
				 
				if(u.getTipoUsuario().getIdTipo()== 1) {
					
				    RequestDispatcher rd = request.getRequestDispatcher("/inicioBanco.jsp");   
			        rd.forward(request, response);
			            
				}else if(u.getTipoUsuario().getIdTipo() == 2){
					
					//Obtenemos los datos del cliente con el atributo dniCliente de Usuario y lo pasamos a una variable session para 
					//tener su info en todos los servlets cliente mismo con sus cuentas
					
					String dni = u.getDniCliente();
					ClienteNegImpl clienteNeg = new ClienteNegImpl();
					Cliente cliente = clienteNeg.obtenerUno(dni);
					//prestamoPorUsuario = negPrestamo.listarPrestamosPorCliente(dni);
					CuentaNegImpl cuentaNeg = new CuentaNegImpl();
					ArrayList<Integer> listaCuentas = cuentaNeg.obtenerCuentasCliente(cliente.getDni());
					
					float saldoT = cuentaNeg.obtenerTotalCuenta(dni);
					
					int cantCuentas = listaCuentas.size();
					
					sessionUsuario.setAttribute("Cliente", cliente);
					sessionUsuario.setAttribute("listaCuentas", listaCuentas);
					sessionUsuario.setAttribute("cantCuentas", cantCuentas);
					sessionUsuario.setAttribute("SaldoTotal", saldoT);
					//sessionUsuario.setAttribute("numeroDePrestamoPorUsuario", prestamoPorUsuario);
			        RequestDispatcher rd = request.getRequestDispatcher("/PerfilCliente.jsp");   
		            rd.forward(request, response);
		            
				}
				 
			 }else {
			
				 sessionUsuario.setAttribute("usuarioLogueado", false);
				 RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");   
		         rd.forward(request, response);
			   
			 } 
			 
		  }
	}

}