package NegocioImpl;

import java.util.ArrayList;
import java.util.List;

import Dao.DaoCliente;
import Entidades.Cliente;
import Entidades.Prestamo;
import Negocio.ClienteNeg;
import DaoImpl.DaoClienteImpl;

public class ClienteNegImpl implements ClienteNeg {

	private DaoCliente daoCliente = new DaoClienteImpl();
	
	
	public ClienteNegImpl() {
		
	}
	
	public ClienteNegImpl(DaoCliente daoCliente) {
		this.daoCliente = daoCliente;
	}
	
	@Override
	public ArrayList<Cliente> obtenerClientes() {
		return daoCliente.obtenerClientes();
	}

	@Override
	public Cliente obtenerUno(String dni) {
		return daoCliente.obtenerUno(dni);
	}

	@Override
	public boolean agregarCliente(Cliente cliente) {
		return daoCliente.agregarCliente(cliente);
	}

	@Override
	public boolean modificarCliente(Cliente cliente) {
		return daoCliente.modificarCliente(cliente);
	}

	@Override
	public boolean eliminarCliente(String dni) {
	    return daoCliente.eliminarCliente(dni);
	}
	@Override
    public ArrayList<String> obtenerDNIClientes(){
		return daoCliente.obtenerDNIClientes();
	}

	@Override
	public boolean existeCliente(String dni) {
		return daoCliente.existeCliente(dni);
	}

	@Override
	public void autorizarPrestamo(int idPrestamo, Cliente cliente) {
		daoCliente.asignarPrestamo(idPrestamo,cliente);
		
	}

	@Override
	public List<Prestamo> obtenerPrestamos(String dni) {
		return daoCliente.obtenerPrestamos(dni);

	}
	

}
