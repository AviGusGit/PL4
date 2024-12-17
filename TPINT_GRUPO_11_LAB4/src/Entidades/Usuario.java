package Entidades;

public class Usuario {
    private int idUsuario;
    private String dniCliente;
    private String usuario;
    private String contrasena;
    private boolean estado;
    private TipoUsuario tipoUsuario;
    
    

    public Usuario() {	}
    
	public Usuario(int idUsuario, String dniCliente, String usuario, String contrasena, boolean estado,
			TipoUsuario tipoUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.dniCliente = dniCliente;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.estado = estado;
		this.tipoUsuario = tipoUsuario;
	}
	// Getters y setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getDniCliente() { return dniCliente; }
    public void setDniCliente(String dniCliente) { this.dniCliente = dniCliente; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public boolean getEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}
