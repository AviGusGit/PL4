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
import NegocioImpl.ClienteNegImpl;


@WebServlet("/ModificarCliente")
public class ModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ModificarCliente() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher rd = request.getRequestDispatcher("/ModificarClientes.jsp");   
            rd.forward(request, response);

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//CARGA LOS 
		ClienteNegImpl clienteNeg = new ClienteNegImpl();
        ArrayList<String> dniClientes = clienteNeg.obtenerDNIClientes();


        request.setAttribute("dniClientes", dniClientes);
		
		//OBTIENE EL CLIENTE 
        String dniParam = request.getParameter("dni");
        
        if (dniParam != null && !dniParam.isEmpty()) {
            try {
            	
                Cliente cliente = clienteNeg.obtenerUno(dniParam); 
                request.setAttribute("listaC", cliente); 
                request.setAttribute("dniSeleccionado", dniParam);
                
            } catch (NumberFormatException e) {
                request.setAttribute("mensaje", "Formato de DNI inválido.");
            }
        } else {
            request.setAttribute("mensaje", "Seleccione un DNI para cargar los datos.");
        }
        
        
        if (request.getParameter("btnAceptar") != null) {
            String dni = request.getParameter("dni");
            String cuil = request.getParameter("cuil");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String sexo = request.getParameter("sexo");
            String nacionalidad = request.getParameter("nacionalidad");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String direccion = request.getParameter("direccion");
            String localidad = request.getParameter("localidad");
            String provincia = request.getParameter("provincia");
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");

            Cliente cliente = new Cliente();
            cliente.setDni(dni);
            cliente.setCuil(cuil);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setSexo(sexo);
            cliente.setNacionalidad(nacionalidad);
            cliente.setFechaNacimiento(java.sql.Date.valueOf(fechaNacimiento));
            cliente.setDireccion(direccion);
            cliente.setLocalidad(localidad);
            cliente.setProvincia(provincia);
            cliente.setCorreo(correo);
            cliente.setTelefono(telefono);

            boolean estado = clienteNeg.modificarCliente(cliente);

            HttpSession sessionMen = request.getSession();
            
            if (estado) {
                sessionMen.setAttribute("mensaje", "Cliente modificado correctamente!");
            } else {
                sessionMen.setAttribute("mensaje", "Hubo un error al modificar el cliente.");

            }  
            response.sendRedirect(request.getRequestURL().toString());
            return;

        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/ModificarClientes.jsp");
        rd.forward(request, response);

	}

}
