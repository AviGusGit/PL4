package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Prestamo;
import NegocioImpl.PrestamoNegImpl;

@WebServlet("/ListarPrestamos")
public class ListarPrestamos extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    public ListarPrestamos() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		PrestamoNegImpl prestamoNeg = new PrestamoNegImpl();
		
		ArrayList<Prestamo> listaP = prestamoNeg.listarTodos();
		
		if(request.getAttribute("filtro") != null && request.getAttribute("btnFiltrar") != null) {
			
		}
		
		request.setAttribute("listaP", listaP);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ListadoPrestamos.jsp");   
        rd.forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		PrestamoNegImpl prestamoNeg = new PrestamoNegImpl();
		
		ArrayList<Prestamo> listaP = prestamoNeg.listarTodos();
		
		request.setAttribute("listaP", listaP);
		
		RequestDispatcher rd = request.getRequestDispatcher("/AdminPrestamos.jsp");   
        rd.forward(request, response);
	}
}
