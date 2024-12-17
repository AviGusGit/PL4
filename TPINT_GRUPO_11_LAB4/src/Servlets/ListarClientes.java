package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Cliente;
import NegocioImpl.ClienteNegImpl;

@WebServlet("/ListarClienteServlet")
public class ListarClientes extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    public ListarClientes() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Entra por haber hecho click en el hyperlink mostrar usuarios
		ClienteNegImpl clienteNeg = new ClienteNegImpl();
		ArrayList<Cliente> lista= clienteNeg.obtenerClientes();
		
		request.setAttribute("listaC", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ListadoClientes.jsp");   
        rd.forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		ClienteNegImpl clienteNeg = new ClienteNegImpl();
		ArrayList<Cliente> lista= clienteNeg.obtenerClientes();
		
		request.setAttribute("listaC", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ListadoClientes.jsp");   
	    rd.forward(request, response);
	}
}
