package Entidades;
import java.util.Date;

public class Movimiento {
    private int codigoMovimiento;
    private int numeroCuenta;
    private Date fecha;
    private String detalle;
    private Float importe;
    private TipoMovimiento tipoMovimiento;  // Relación agregada

    // Constructor vacío
    public Movimiento() {}

    // Constructor con parámetros
    public Movimiento(int codigoMovimiento, int numeroCuenta, Date fecha, String detalle, float importe, TipoMovimiento tipoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
        this.numeroCuenta = numeroCuenta;
        this.fecha = fecha;
        this.detalle = detalle;
        this.importe = importe;
        this.tipoMovimiento = tipoMovimiento;
    }

    // Getters y Setters
    public int getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(int codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
}
