package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import Entidades.Cliente;
import Entidades.Usuario;
import Entidades.TipoUsuario;

import NegocioImpl.ClienteNegImpl;
import NegocioImpl.UsuarioNegImpl;

@WebServlet("/AgregarClienteServlet")
public class AgregarCliente extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String btnAceptar = request.getParameter("btnAceptar");
        
        if (btnAceptar != null) {
            // Obtener parámetros del cliente
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
            
            java.sql.Date fechaNac = java.sql.Date.valueOf(fechaNacimiento);

            // Crear objeto Cliente
            Cliente cliente = new Cliente(dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNac, direccion, localidad, provincia, correo, telefono, true);
            ClienteNegImpl clienteNeg = new ClienteNegImpl();
            boolean resultadoCliente = clienteNeg.agregarCliente(cliente);

            if (resultadoCliente) {
                // Crear y agregar Usuario asociado
                String usuarioNombre = request.getParameter("usuario");   // Parámetro de nombre de usuario
                String contrasena = request.getParameter("contrasena");   // Parámetro de contraseña
                
                Usuario usuario = new Usuario();
                usuario.setDniCliente(request.getParameter("dni")); // Asociar el DNI del cliente al usuario
                usuario.setUsuario(usuarioNombre);
                usuario.setContrasena(contrasena);

                // Establecer TipoUsuario por defecto (ajustar según necesidad)
                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setIdTipo(2);  // Ejemplo: 2 = usuario estándar
                usuario.setTipoUsuario(tipoUsuario);

                // Agregar usuario
                UsuarioNegImpl usuarioNeg = new UsuarioNegImpl();
                boolean resultadoUsuario = usuarioNeg.agregarUsuario(usuario);

                if (resultadoUsuario) {
                    request.setAttribute("mensaje", "Cliente y usuario agregados correctamente.");
                } else {
                    request.setAttribute("mensaje", "Cliente agregado, pero hubo un error al registrar el usuario.");
                }
            } else {
                request.setAttribute("mensaje", "El cliente con DNI " + dni + " ya existe en la base de datos.");
            }

            // Redirigir a la página JSP
            RequestDispatcher rd = request.getRequestDispatcher("/AgregarClientes.jsp");   
            rd.forward(request, response);
        }
    }
}
