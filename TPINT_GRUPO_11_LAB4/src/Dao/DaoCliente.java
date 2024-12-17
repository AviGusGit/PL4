package Dao;

import Entidades.Cliente;
import Entidades.Prestamo;

import java.util.ArrayList;
import java.util.List;

public interface DaoCliente {

	public ArrayList<Cliente> obtenerClientes();
	public Cliente obtenerUno(String dni);
	public boolean agregarCliente(Cliente cliente);
	public boolean modificarCliente(Cliente cliente);
	public boolean eliminarCliente(String dni);
	public ArrayList<String> obtenerDNIClientes();
	public boolean existeCliente(String dni);
	public void asignarPrestamo(int idPrestamo, Cliente cliente);
	public List<Prestamo> obtenerPrestamos(String dni);

}
