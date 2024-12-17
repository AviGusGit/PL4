package Entidades;

public class TipoCuenta {

	private int idTipo=0;
	private String nombreTipo="";
	
	public TipoCuenta() {
		
	}
	public TipoCuenta(int idTiipo ,String descripcion) {
		
		idTipo=idTiipo;
		nombreTipo=descripcion;
		
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}
	
	
	
}
