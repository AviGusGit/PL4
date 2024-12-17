package NegocioImpl;

import java.util.ArrayList;

import Dao.DaoCuenta;
import DaoImpl.DaoCuentaImpl;
import Entidades.Cuenta;
import Negocio.CuentaNeg;

public class CuentaNegImpl implements CuentaNeg {
	
	private DaoCuenta cuentaDao = new DaoCuentaImpl();

	public CuentaNegImpl() {
		
	}
	
	public float obtenerTotalCuenta(String dniCliente){
		
		System.out.println("------DNI-------------"+dniCliente+"---------");
		float total =cuentaDao.obtenerTotal(dniCliente);
		System.out.println("------total-------------"+total+"---------");
	    return cuentaDao.obtenerTotal(dniCliente);
	}
	
	public CuentaNegImpl(DaoCuenta cuentaDao) {
		this.cuentaDao = cuentaDao;
	}
	
	@Override
	public ArrayList<Cuenta> obtenerCuentas() {
		return cuentaDao.obtenerCuentas();
	}

	@Override
	public Cuenta obtenerUno(int numeroCuenta) {
		return cuentaDao.obtenerUno(numeroCuenta);
	}

	@Override
	public boolean agregarCuenta(Cuenta cuenta) {
		return cuentaDao.agregarCuenta(cuenta);
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		return cuentaDao.modificarCuenta(cuenta);
	}

	@Override
	public boolean eliminarCuenta(int numeroCuenta) {
		return cuentaDao.eliminarCuenta(numeroCuenta);
	}
	
	@Override
	public ArrayList<Integer> obtenerNumCuenta() {
		return cuentaDao.obtenerNumCuenta();
	}

	@Override
	public ArrayList<Integer> obtenerCuentasCliente(String dniCliente) {
		return cuentaDao.obtenerCuentasCliente(dniCliente);
	}

	@Override
	public boolean maxCuentas(String dniCliente) {
		return cuentaDao.maxCuentas(dniCliente);
	}

	@Override
	public ArrayList<String> obtenerCbuTransf(int cuentaExcluida) {
		return cuentaDao.obtenerCbuTransf(cuentaExcluida);
	}

	@Override
	public Cuenta obtenerUltimaCuenta() {
		return cuentaDao.obtenerUltimaCuenta();
	}
	
	
}
