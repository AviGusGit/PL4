package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import NegocioImpl.CuentaNegImpl;

@WebServlet("/EliminarCuenta")
public class EliminarCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EliminarCuenta() {
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CuentaNegImpl cuentaNeg = new CuentaNegImpl();
		ArrayList<Integer> numCuentas = cuentaNeg.obtenerNumCuenta();
		
		request.setAttribute("numCuentas", numCuentas);


        if (request.getParameter("btnAceptar") != null) {
        	
            try {
                int numeroCuenta = Integer.parseInt(request.getParameter("numero"));
                
                boolean resultado = cuentaNeg.eliminarCuenta(numeroCuenta);

                String mensaje = resultado ? "Cuenta eliminada correctamente." : "Error al eliminar la cuenta.";
                request.setAttribute("mensaje", mensaje);

            } catch (Exception e) {
            	
                e.printStackTrace();
                request.setAttribute("mensaje", "Número de cuenta inválido.");
            }
        } 
        
        RequestDispatcher rd = request.getRequestDispatcher("/EliminarCuentas.jsp");
        rd.forward(request, response);
	}

}
