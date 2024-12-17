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
import Entidades.Prestamo;
import Entidades.TipoMovimiento;
import NegocioImpl.ClienteNegImpl;
import NegocioImpl.CuentaNegImpl;
import NegocioImpl.MovimientoNegImpl;


@WebServlet("/AgregarCuenta")
public class AgregarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AgregarCuenta() {
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		ClienteNegImpl clienteNeg = new ClienteNegImpl();
        ArrayList<String> dniClientes = clienteNeg.obtenerDNIClientes();

        request.setAttribute("dniClientes", dniClientes);

        
		if(request.getParameter("btnAceptar") != null) {
			
	        String dniCliente = request.getParameter("dni");
	        String fechaCreacion = request.getParameter("fechaCreacion");
	        int tipoCuenta = Integer.parseInt(request.getParameter("tipo"));
	        String cbu = request.getParameter("cbu");
	        
	        Cuenta cuenta = new Cuenta();
	        
	        cuenta.setDniCliente(dniCliente);
	        cuenta.setFechaCreacion(java.sql.Date.valueOf(fechaCreacion));
	        
	        cuenta.setTipoCuentaInt(tipoCuenta);
	        cuenta.setCbu(cbu);
	        

	        CuentaNegImpl cuentaNegImpl = new CuentaNegImpl();
	        boolean resultado = cuentaNegImpl.agregarCuenta(cuenta);

            if (resultado) {
                request.setAttribute("mensaje", "Cuenta agregada correctamente.");
                
                Movimiento movimiento = new Movimiento();
        		MovimientoNegImpl movimientoNeg = new MovimientoNegImpl();
        		TipoMovimiento tipoMovimiento = new TipoMovimiento();
        		cuenta = cuentaNegImpl.obtenerUltimaCuenta();
        		tipoMovimiento.setIdTipoMovimiento(1);
        		String detalle = "Alta de Cuenta";
        		float importe = 10000.00f;
        		int numeroCuenta = cuenta.getNumeroCuenta();
        		
        		movimiento.setDetalle(detalle);
        		movimiento.setFecha(new java.sql.Date(System.currentTimeMillis()));
        		movimiento.setImporte(importe);
        		movimiento.setNumeroCuenta(numeroCuenta);
        		movimiento.setTipoMovimiento(tipoMovimiento);
        		
        		try{
        			movimientoNeg.insertarMovimiento(movimiento);
        		}catch(Exception e) {
        			e.getMessage();
        		}
        		
            } else {
                request.setAttribute("mensaje", "El cliente con DNI " + dniCliente + " ha alcanzado el máximo de cuentas activas.");
            }
		}

		RequestDispatcher rd = request.getRequestDispatcher("/AgregarCuentas.jsp");   
        rd.forward(request, response); 
	}

}
