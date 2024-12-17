package Servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import NegocioImpl.PrestamoNegImpl;

@WebServlet("/PagarPrestamos")
public class PagarPrestamos extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private PrestamoNegImpl prestamoNegocio = new PrestamoNegImpl();

    public PagarPrestamos() {
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/PagarPrestamos.jsp");   
        rd.forward(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
           
            int numeroPres = Integer.parseInt(request.getParameter("numeroPres"));
            float monto = Float.parseFloat(request.getParameter("monto"));
            int cuentaAsociada = Integer.parseInt(request.getParameter("numeroCuenta"));

           
            boolean exito = prestamoNegocio.pagarCuota(numeroPres, monto, cuentaAsociada);

            if (exito) {
                request.setAttribute("mensajeExito", "la cuota se pago exitosamente.");
            } else {
                request.setAttribute("mensajeError", "error al pagar la cuota");
            }

        } catch (Exception e) {
           
            request.setAttribute("mensajeError", "ocurrio un error al procesar el pago: " + e.getMessage());
        }

      
        RequestDispatcher rd = request.getRequestDispatcher("/PagarPrestamos.jsp");
        rd.forward(request, response);
    }
}
