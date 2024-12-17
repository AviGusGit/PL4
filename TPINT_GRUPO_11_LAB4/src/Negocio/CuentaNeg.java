package Negocio;

import java.util.ArrayList;

import Entidades.Cuenta;

public interface CuentaNeg {

	public ArrayList<Cuenta> obtenerCuentas();
	public Cuenta obtenerUno(int numeroCuenta);
	public boolean agregarCuenta(Cuenta cuenta);
	public boolean modificarCuenta(Cuenta cuenta);
	public boolean eliminarCuenta(int numeroCuenta);
	public ArrayList<Integer> obtenerNumCuenta();
	public ArrayList<Integer> obtenerCuentasCliente(String dniCliente);
	public boolean maxCuentas(String dniCliente);
	public ArrayList<String> obtenerCbuTransf(int cuentaExcluida);
	public Cuenta obtenerUltimaCuenta();
	
}
