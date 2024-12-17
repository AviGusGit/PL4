package Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import Entidades.Movimiento;

public interface DaoMovimiento {

	public boolean insertarMovimiento(Movimiento movimiento) throws SQLException;
	public Movimiento obtenerMovimiento(int codigoMovimiento) throws SQLException;
	public ArrayList<Movimiento> obtenerMovimientosPorCuenta(int numeroCuenta);
	public ArrayList<Movimiento> obtenerMovimientosPorCliente(int dniCliente);
}
