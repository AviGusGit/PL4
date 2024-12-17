package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Cuenta;
import Entidades.Movimiento;
import NegocioImpl.CuentaNegImpl;
import NegocioImpl.MovimientoNegImpl;


@WebServlet("/HistorialCliente")
public class HistorialCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HistorialCliente() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	    CuentaNegImpl cuentaNeg = new CuentaNegImpl();
	    MovimientoNegImpl movNeg = new MovimientoNegImpl();
		
        String numeroParam = request.getParameter("numeroCuenta");
        
        if (numeroParam != null && !numeroParam.isEmpty()) {
            try {
            	
                int numeroCuenta = Integer.parseInt(numeroParam);
                Cuenta cuentaSelec = cuentaNeg.obtenerUno(numeroCuenta); 
                
                ArrayList<Movimiento> listaMovs = movNeg.obtenerMovimientosPorCuenta(numeroCuenta);
                
                request.setAttribute("cuenta", cuentaSelec); 
                request.setAttribute("numCuentaSelec", numeroCuenta);
                request.setAttribute("listaMovs", listaMovs);
                
            } catch (NumberFormatException e) {
                request.setAttribute("mensaje", "Formato de Cuenta inválido.");
         
            }
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/HistorialCliente.jsp");   
        rd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
