package Servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidades.Cuenta;
import NegocioImpl.ClienteNegImpl;
import NegocioImpl.CuentaNegImpl;


@WebServlet("/ModificarCuenta")

public class ModificarCuenta extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ModificarCuenta() {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	RequestDispatcher rd = request.getRequestDispatcher("/ModificarCuentas.jsp");
        rd.forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        CuentaNegImpl cuentaNeg = new CuentaNegImpl();
        ArrayList<Integer> numCuentas = cuentaNeg.obtenerNumCuenta();
        
        request.setAttribute("numCuentas", numCuentas);
        
		ClienteNegImpl clienteNeg = new ClienteNegImpl();
        ArrayList<String> dniClientes = clienteNeg.obtenerDNIClientes();

        request.setAttribute("dniClientes", dniClientes);

        String numParam = request.getParameter("numero");
        
        if (numParam != null && !numParam.isEmpty()) {
        	
            try {
                int num = Integer.parseInt(numParam);
                Cuenta cuenta = cuentaNeg.obtenerUno(num);
                
                request.setAttribute("listaC", cuenta);
                request.setAttribute("numSeleccionado", num);
                request.setAttribute("dniSeleccionado", cuenta.getDniCliente());
                
            } catch (NumberFormatException e) {
            	
                request.setAttribute("mensaje", "Formato de número inválido.");
            }
        }else {
            request.setAttribute("mensaje", "Seleccione una cuenta para cargar los datos.");
        }
        
    	if(request.getParameter("btnAceptar")!=null) {
    		
            String numeroCuenta = request.getParameter("numero");
            String dniCliente = request.getParameter("dni");
            String fechaCreacion = request.getParameter("fechaCreacion");
            String tipoCuenta = request.getParameter("tipo");
            String cbu = request.getParameter("cbu");
            String saldo = request.getParameter("saldo");
            
            Cuenta cuenta = new Cuenta();
            
            cuenta.setNumeroCuenta(Integer.parseInt(numeroCuenta));
            cuenta.setDniCliente(dniCliente);
            cuenta.setFechaCreacion(Date.valueOf(fechaCreacion));
            cuenta.setTipoCuentaInt(Integer.parseInt(tipoCuenta));
            cuenta.setCbu(cbu);
            cuenta.setSaldo(Float.parseFloat(saldo));

            boolean estado = cuentaNeg.modificarCuenta(cuenta);

            HttpSession sessionMen = request.getSession();
            
            if (estado) {
                sessionMen.setAttribute("mensaje", "Cuenta modificada correctamente!");
            } else {
                sessionMen.setAttribute("mensaje", "El cliente con DNI " + dniCliente + " ha alcanzado el máximo de cuentas activas.");

            }
            
            response.sendRedirect(request.getRequestURL().toString());
            return; 
    	}

        RequestDispatcher rd = request.getRequestDispatcher("/ModificarCuentas.jsp");
        rd.forward(request, response);
    }


}
