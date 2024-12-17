package Entidades;

public class TipoUsuario {
	
    private int idTipo;
    private String descripcion;

    public TipoUsuario() {
	}
    
    public TipoUsuario(int idTipo) {
		super();
		this.idTipo = idTipo;
	}
	public TipoUsuario(int idTipo, String descripcion) {
		super();
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}
	// Getters y setters
    public int getIdTipo() { return idTipo; }
    public void setIdTipo(int idTipo) { this.idTipo = idTipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
