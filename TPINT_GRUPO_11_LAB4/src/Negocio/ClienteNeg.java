package Negocio;

import java.util.ArrayList;
import java.util.List;

import Entidades.Cliente;
import Entidades.Prestamo;

public interface ClienteNeg {

	public ArrayList<Cliente> obtenerClientes();
	public Cliente obtenerUno(String dni);
	public boolean agregarCliente(Cliente cliente);
	public boolean modificarCliente(Cliente cliente);
	public boolean eliminarCliente(String dni);
    public ArrayList<String> obtenerDNIClientes();
	public boolean existeCliente(String dni);
	public void autorizarPrestamo(int idPrestamo,Cliente cliente);
	public List<Prestamo> obtenerPrestamos(String dni);
}
