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



@WebServlet("/TransferirCliente")
public class TransferirCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TransferirCliente() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CuentaNegImpl cuentaNeg = new CuentaNegImpl();
        
        String numCuenta = request.getParameter("numeroCuenta");
        
        if (numCuenta != null && !numCuenta.isEmpty()) {
        	
            try {
                int cuentaExcluida = Integer.parseInt(numCuenta);

                ArrayList<String> cbuCuentas = cuentaNeg.obtenerCbuTransf(cuentaExcluida);

                request.setAttribute("cuentaSeleccionada", cuentaExcluida);
                request.setAttribute("cbuCuentas", cbuCuentas);
                
            } catch (NumberFormatException e) {
            	
                request.setAttribute("mensaje", "Formato de número inválido.");
            }
            
        }else {
            request.setAttribute("mensaje", "Seleccione una cuenta para cargar los datos.");
        }
		
        RequestDispatcher rd = request.getRequestDispatcher("/TransferirCliente.jsp");   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
