package Negocio;

import java.util.ArrayList;
import Entidades.Prestamo;

public interface PrestamoNeg {
    boolean solicitarPrestamo(Prestamo prestamo);
    boolean pagarCuota(int numeroPres, float monto, int cuentaAsociada);
    boolean actualizarEstado(int numPrestamo, String estado);
    Prestamo obtenerPrestamoPorNumero(int numeroPres);
    ArrayList<Prestamo> listarPrestamosPorCliente(String dniCliente);
    ArrayList<Prestamo> listarTodos();
    ArrayList<Prestamo> listarConFiltro(String filtro);
}
