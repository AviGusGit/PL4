<%@page import="Entidades.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
<link href="CSS/menu.css" rel="StyleSheet">
<title>Perfil del Cliente</title>
</head>
	<%
	//VERIFICA QUE HAYA UN USUARIO LOGUEADO, SINO DEVUELVE AL LOGIN
	int rol = Integer.parseInt(session.getAttribute("usuarioRol").toString()); 
	if (session == null || session.getAttribute("usuarioLogueado") == null || rol == 1) {
	%>
        <script>
        alert("Debe iniciar sesión como Cliente.");
        window.location.href = "Login.jsp"; 
    	</script>
	<%
	return;
	}
	%>
<body>

	<div class="container">

		<div class="header"
			style="display: flex; justify-content: space-between;">
			<img src="image/logo.png" alt="logo MAZE BANK" width="125"
				height="90" style="margin-top: 7px;">
			<h1
				style="font-size: 24px; color: #333; text-transform: uppercase; margin-right: 10px;">Perfil
				del Cliente</h1>
		</div>

		<div style="margin-top: 20px;">
			<jsp:include page="HTML/MenuCliente.html"></jsp:include>
		</div>

		<% 
		Cliente cli = (Cliente)session.getAttribute("Cliente");
	    if (cli != null) {
		%>

		<div style="background-color: #f9f9f9; padding: 20px; border: 1px solid #ddd; border-radius: 8px; margin-top: 20px;">
			<h2 style="text-align: left color: #444;">Información Personal</h2>

			<div style="display: table; width: 60%; margin-top: 20px;">
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>DNI:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getDni() : "" %></div>
				</div>
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>CUIL:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getCuil() : "" %></div>
				</div>
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>Nombre:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getNombre() : "" %></div>
				</div>
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>Apellido:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getApellido() : "" %></div>
				</div>
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>Correo:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getCorreo() : "" %></div>
				</div>
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>Teléfono:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getTelefono() : "" %></div>
				</div>
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>Localidad:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getLocalidad() : "" %></div>
				</div>
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>Provincia:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getProvincia() : "" %></div>
				</div>
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>Nacionalidad:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getNacionalidad() : "" %></div>
				</div>
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>Sexo:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getSexo() : "" %></div>
				</div>
				<div style="display: table-row;">
					<div style="display: table-cell; padding: 8px;">
						<strong>Fecha de Nacimiento:</strong>
					</div>
					<div style="display: table-cell; padding: 8px;"><%= cli != null ? cli.getFechaNacimiento() : "" %></div>
					
				</div>
				<form method="post" action="Logout" onsubmit="return confirmLogout()">
				<button type="submit" id="btnLogout" name="btnLogout" style="background: red; margin-top: 20px;" class="btn-primary">CERRAR SESIÓN</button>
				</form>
						<script>
                     function confirmLogout() {
                       return confirm("¿Está seguro de que desea cerrar sesión?");
                        }
          </script>
			</div>
		</div>
		<%
	    }else{
	    %>	
	        <script>
	        alert("Cliente no encontrado.");
	        window.location.href = "Login.jsp"; 
	    	</script>
	   <%
	    }
        %>
	</div>
</body>
</html>

