package NegocioImpl;

import java.util.ArrayList;

import Dao.DaoPrestamo;
import DaoImpl.DaoPrestamoImpl;
import Entidades.Prestamo;
import Negocio.PrestamoNeg;

public class PrestamoNegImpl implements PrestamoNeg {

    private DaoPrestamo prestamoDao = new DaoPrestamoImpl();;

    public PrestamoNegImpl() {
    }

    @Override
    public boolean solicitarPrestamo(Prestamo prestamo) {
        return prestamoDao.solicitarPrestamo(prestamo);
    }

    @Override
    public Prestamo obtenerPrestamoPorNumero(int numeroPres) {
        return prestamoDao.obtenerPrestamoPorNumero(numeroPres);
    }

    @Override
    public ArrayList<Prestamo> listarPrestamosPorCliente(String dniCliente) {
        return prestamoDao.listarPrestamosPorCliente(dniCliente);
    }

    @Override
    public ArrayList<Prestamo> listarTodos() {
        return prestamoDao.listarTodos();
    }

    @Override
    public boolean pagarCuota(int numeroPres, float monto, int cuentaAsociada) {
        return prestamoDao.pagarCuota(numeroPres, monto, cuentaAsociada);
    }

	@Override
	public ArrayList<Prestamo> listarConFiltro(String filtro) {
		return prestamoDao.listarConFiltro(filtro);
	}

	@Override
	public boolean actualizarEstado(int numPrestamo, String estado) {
		return prestamoDao.actualizarEstado(numPrestamo, estado);
	}
	
}
