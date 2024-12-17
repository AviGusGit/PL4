package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidades.Movimiento;
import Entidades.Prestamo;
import Entidades.TipoMovimiento;
import NegocioImpl.MovimientoNegImpl;
import NegocioImpl.PrestamoNegImpl;

@WebServlet("/AutorizarPrestamos")
public class AutorizarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AutorizarPrestamos() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrestamoNegImpl prestamoNeg = new PrestamoNegImpl();
		
		ArrayList<Prestamo> listaP = prestamoNeg.listarTodos();
		
		request.setAttribute("listaP", listaP);
		
        String numeroPres = request.getParameter("numeroPres");
        
        if (numeroPres != null && !numeroPres.isEmpty()) {
        	
            try {

            	int num = Integer.parseInt(numeroPres);

                request.setAttribute("numeroPres", num);
                
            } catch (NumberFormatException e) {
            	
                request.setAttribute("mensaje", "Formato de número inválido.");
            }
            
        }else {
            request.setAttribute("mensaje", "Seleccione un préstamo para cargar los datos.");
        }
		
		RequestDispatcher rd = request.getRequestDispatcher("/AutorizarPrestamos.jsp");   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numeroPres = request.getParameter("numeroPres");
		PrestamoNegImpl negocio = new PrestamoNegImpl();
		int numprestamo = Integer.valueOf(numeroPres);
		String estado;
		String btnAutorizar = request.getParameter("btnAutorizar");
		String btnRechazar = request.getParameter("btnRechazar");
		
		if (btnAutorizar != null) {
		
		Movimiento movimiento = new Movimiento();
		MovimientoNegImpl movimientoNeg = new MovimientoNegImpl();
		Prestamo prestamo = negocio.obtenerPrestamoPorNumero(numprestamo);
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
		estado = "Aprobado";
		negocio.actualizarEstado(numprestamo,estado);
		tipoMovimiento.setIdTipoMovimiento(2);
		String detalle = "Alta de un Prestamo";
		float importe = prestamo.getImportePedido();
		
		movimiento.setDetalle(detalle);
		movimiento.setFecha(new java.sql.Date(System.currentTimeMillis()));
		movimiento.setImporte(importe);
		movimiento.setNumeroCuenta(prestamo.getNumeroCuenta());
		movimiento.setTipoMovimiento(tipoMovimiento);
		
		//PARA CHEQUEAR
		System.out.println("Datos del movimiento:");
        System.out.println("Detalle: " + movimiento.getDetalle());
        System.out.println("numero cuenta: " + movimiento.getNumeroCuenta());
        System.out.println("fecha: " + movimiento.getFecha());
        System.out.println("importe: " + movimiento.getImporte());
        System.out.println("tipo movimiento: " + movimiento.getTipoMovimiento().getIdTipoMovimiento());
        
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
        
		try{
			movimientoNeg.insertarMovimiento(movimiento);
		}catch(Exception e) {
			e.getMessage();
		}

		}
		else if(btnRechazar !=null) {
		estado = "Rechazado";
		negocio.actualizarEstado(numprestamo,estado);
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/Mensaje.jsp");   
        rd.forward(request, response);
	}

}
