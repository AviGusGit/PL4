package Servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Cliente;
import Entidades.Prestamo;
import Negocio.PrestamoNeg;
import NegocioImpl.PrestamoNegImpl;

@WebServlet("/PedirPrestamos")
public class PedirPrestamos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PedirPrestamos() {
    }

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Comienzo pedir préstamo GET");
        RequestDispatcher rd = request.getRequestDispatcher("/PedirPrestamos.jsp");
        rd.forward(request, response);
    }

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Comienzo pedir préstamo POST");

        try {
        	
        	Cliente cliente = (Cliente) request.getSession().getAttribute("Cliente");
            if (cliente == null) {
                throw new Exception("No se encontró el cliente en la sesión.");
            }
            
            // Obtener el DNI del cliente directamente desde la sesión
            String dniCliente = cliente.getDni();
            
            //String dniCliente = request.getParameter("dniCliente");
            float importePedido = Float.parseFloat(request.getParameter("monto"));
            int cuotas = Integer.parseInt(request.getParameter("cuotas"));
            String plazoPago = request.getParameter("cuotas");
            int numeroCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
            float montoMes = (importePedido * 1.2f) / cuotas;
            float importePagar = importePedido * 1.2f;
            
            Prestamo prestamo = new Prestamo();
            prestamo.setDniCliente(dniCliente);
            prestamo.setNumeroCuenta(numeroCuenta);
            prestamo.setImportePedido(importePedido);
            prestamo.setImportePagar(importePagar);
            prestamo.setCuotas(cuotas);
            prestamo.setMontoMes(montoMes);
            prestamo.setPlazoPago(plazoPago+" meses");
            prestamo.setFecha(new java.sql.Date(System.currentTimeMillis()));
            prestamo.setEstado("Pedido"); 
            
            System.out.println("Datos del Prestamo:");
            System.out.println("DNI Cliente: " + prestamo.getDniCliente());
            System.out.println("Numero cuenta: " + prestamo.getNumeroCuenta());
            System.out.println("Importe Pedido: " + prestamo.getImportePedido());
            System.out.println("Importe Pagar: " + prestamo.getImportePagar());
            System.out.println("Monto mes: " + prestamo.getMontoMes());
            System.out.println("Cuotas: " + prestamo.getCuotas());
            System.out.println("Plazo de Pago: " + prestamo.getPlazoPago());
            System.out.println("Fecha: " + prestamo.getFecha());
            System.out.println("Estado: " + prestamo.getEstado());

            PrestamoNeg prestamoNegocio = new PrestamoNegImpl();
            boolean exito = prestamoNegocio.solicitarPrestamo(prestamo);

            if (exito) {
                request.setAttribute("mensajeExito", "el préstamo ha sido solicitado exitosamente.");
                System.out.println("Prestamo solicitado con exito");
            } else {
                request.setAttribute("mensajeError", "no se pudo registrar la solicitud del préstamo.");
                System.out.println("Hubo un error");
            }

        } catch (Exception e) {
           
            request.setAttribute("mensajeError", "ocurrio un error al procesar la solicitud: " + e.getMessage());
            System.out.println("Hubo un error de: " + e.getMessage());
        }

       
        RequestDispatcher rd = request.getRequestDispatcher("/PedirPrestamos.jsp");
        rd.forward(request, response);
    }
}

