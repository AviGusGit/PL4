package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NegocioImpl.ClienteNegImpl;


@WebServlet("/EliminarCliente")
public class EliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EliminarCliente() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClienteNegImpl clienteNeg = new ClienteNegImpl();
		ArrayList<String> dniClientes = clienteNeg.obtenerDNIClientes();
		
		request.setAttribute("dniClientes", dniClientes);
		
		if(request.getParameter("btnEliminar") != null) {
			
			try {
				String dni = (String)request.getParameter("dni");
			
		        boolean resultado = clienteNeg.eliminarCliente(dni);
	
	            String mensaje = resultado ? "Cuenta eliminada correctamente." : "Error al eliminar la cuenta.";
	            request.setAttribute("mensaje", mensaje);
            
			} catch (Exception e) {
            	
                e.printStackTrace();
                request.setAttribute("mensaje", "Número de cuenta inválido.");
            }

		}
		
        RequestDispatcher rd = request.getRequestDispatcher("/EliminarClientes.jsp");   
        rd.forward(request, response);
	}

}
