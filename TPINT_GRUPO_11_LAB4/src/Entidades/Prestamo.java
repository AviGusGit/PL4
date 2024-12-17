package Entidades;

import java.sql.Date;

public class Prestamo {

	private int numeroPres;
	private String dniCliente;
	private java.sql.Date fecha;
	private float importePagar;
	private float importePedido;
	private String plazoPago;
	private float montoMes;
	private int numeroCuenta;
	private int cuotas;
	private String estado;
	
	// Constructores
	public Prestamo() {}

	public Prestamo(int numeroPres, String dniCliente, Date fecha, float importePagar, float importePedido,
			String plazoPago, float montoMes, int cuotas,int numeroCuenta, String estado) {
		super();
		this.numeroPres = numeroPres;
		this.dniCliente = dniCliente;
		this.fecha = fecha;
		this.importePagar = importePagar;
		this.importePedido = importePedido;
		this.plazoPago = plazoPago;
		this.montoMes = montoMes;
		this.cuotas = cuotas;
		this.estado = estado;
		this.setNumeroCuenta(numeroCuenta);
	}
	
	//Getters & Setters

	public int getNumeroPres() {
		return numeroPres;
	}

	public void setNumeroPres(int numeroPres) {
		this.numeroPres = numeroPres;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public java.sql.Date getFecha() {
		return fecha;
	}

	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}

	public float getImportePagar() {
		return importePagar;
	}

	public void setImportePagar(float importePagar) {
		this.importePagar = importePagar;
	}

	public float getImportePedido() {
		return importePedido;
	}

	public void setImportePedido(float importePedido) {
		this.importePedido = importePedido;
	}

	public String getPlazoPago() {
		return plazoPago;
	}

	public void setPlazoPago(String plazoPago) {
		this.plazoPago = plazoPago;
	}

	public float getMontoMes() {
		return montoMes;
	}

	public void setMontoMes(float montoMes) {
		this.montoMes = montoMes;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	
}
