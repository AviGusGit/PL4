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
import NegocioImpl.CuentaNegImpl;


@WebServlet("/ListarCuentas")
public class ListarCuentas extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public ListarCuentas() {
    	
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CuentaNegImpl cuentaNeg = new CuentaNegImpl();
		ArrayList<Cuenta> lista= cuentaNeg.obtenerCuentas();
		
		request.setAttribute("listaC", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ListadoCuentas.jsp");   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CuentaNegImpl cuentaNeg = new CuentaNegImpl();
		ArrayList<Cuenta> lista= cuentaNeg.obtenerCuentas();
		
		request.setAttribute("listaC", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ListadoCuentas.jsp");   
        rd.forward(request, response);
	}

}
