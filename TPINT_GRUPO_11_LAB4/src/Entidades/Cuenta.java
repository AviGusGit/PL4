package Entidades;

import java.util.Date;

public class Cuenta {
	
	private int numeroCuenta;
	private String dniCliente;
	private Date fechaCreacion;
	private TipoCuenta tipoCuenta = new TipoCuenta();
	private String cbu;
	private float saldo;
	private boolean estado;
	
	// Constructores
	
	public Cuenta() {}

	public Cuenta(int numeroCuenta, String dniCliente, Date fechaCreacion, TipoCuenta tipoCuenta, String cbu, float saldo,
			boolean estado) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.dniCliente = dniCliente;
		this.fechaCreacion = fechaCreacion;
		this.tipoCuenta = tipoCuenta;
		this.cbu = cbu;
		this.saldo = saldo;
		this.estado = estado;
	}

	
	// Getters & Setters
	
	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta TipoCuenta) {
		this.tipoCuenta = TipoCuenta;
	}	
	public void setTipoCuentaInt(int TipoCuenta) {
		this.tipoCuenta.setIdTipo(TipoCuenta);  
	}


	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
