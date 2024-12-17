<%@page import="Entidades.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<title>Modificar Clientes</title>
</head>
	<%
	//VERIFICA QUE HAYA UN USUARIO LOGUEADO, SINO DEVUELVE AL LOGIN
	int rol = Integer.parseInt(session.getAttribute("usuarioRol").toString()); 
	if (session == null || session.getAttribute("usuarioLogueado") == null || rol == 2) {
		%>
        <script>
        alert("Debe iniciar sesión como Administrador.");
        window.location.href = "Login.jsp"; 
    	</script>
	<%
	return;
	}
	%>
<body>
	<div class="container">
		<div class="header">
			<img src="image/logo.png" alt="logo MAZE BANK" width="125"
				height="90" style="margin-top: 7px;">
			<h1 class="title-container">CLIENTES</h1>
		</div>
		<div>
			<jsp:include page="HTML/MenuABMLClientes.html"></jsp:include>
		</div>
		<% 
		
		Cliente cli = (Cliente)request.getAttribute("listaC");
		
		%>
		<form action="ModificarCliente" method="get">

			<div class="form-container"
				style="display: flex; flex-wrap: wrap; justify-content: space-between;">
				<div class="form-item">
					<label for="dni">DNI</label> 
					<select id="dni" name="dni" onchange="this.form.submit()" required>

						<option value="">Seleccione un cliente</option>
			<% 
	            ArrayList<String> dniClientes = (ArrayList<String>) request.getAttribute("dniClientes");
	        	String dniSeleccionado = (String) request.getAttribute("dniSeleccionado");
	            if (dniClientes != null && !dniClientes.isEmpty()) {
	                for (String dni : dniClientes) { 
        	%>
						<option value="<%= dni %>"
							<%= dni.equals(dniSeleccionado) ? "selected" : "" %>><%= dni %></option>
						<% 
                }
            } else { 
        %>
						<option value="">No hay clientes disponibles</option>
						<% 
            } 
        %>
					</select>
				</div>
				<div class="form-item">
					<label for="cuil">CUIL</label> <input type="tel" id="cuil"
						name="cuil" pattern="^[0-9]+$"
						title="Solo se aceptan NÚMEROS, sin puntos, comas o guiones."
						value="<%= cli != null ? cli.getCuil() : "" %>" required>
				</div>

				<div class="form-item">
					<label for="nombre">Nombre</label> <input type="text" id="nombre"
						name="nombre" pattern="[A-Za-záéíóúÁÉÍÓÚÑñ]+"
						title="Solo se aceptan LETRAS."
						value="<%= cli != null ? cli.getNombre() : "" %>" required>
				</div>

				<div class="form-item">
					<label for="apellido">Apellido</label> <input type="text"
						id="apellido" name="apellido" pattern="[A-Za-záéíóúÁÉÍÓÚÑñ]+"
						title="Solo se aceptan LETRAS."
						value="<%= cli != null ? cli.getApellido() : "" %>" required>
				</div>

				<div class="form-item">
					<label for="correo">Correo</label> <input type="email" id="correo"
						name="correo" value="<%= cli != null ? cli.getCorreo() : "" %>"
						required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}">
				</div>

				<div class="form-item">
					<label for="telefono">Teléfono</label> <input type="tel"
						id="telefono" name="telefono"
						value="<%= cli != null ? cli.getTelefono() : "" %>" required>
				</div>

				<div class="form-item">
					<label for="localidad">Localidad</label> <select id="localidad"
						name="localidad" required>
						<option value="Pilar"
							<%= cli != null && "Pilar".equals(cli.getLocalidad()) ? "selected" : "" %>>Pilar</option>
						<option value="Rosario"
							<%= cli != null && "Rosario".equals(cli.getLocalidad()) ? "selected" : "" %>>Rosario</option>
						<option value="La Plata"
							<%= cli != null && "La Plata".equals(cli.getLocalidad()) ? "selected" : "" %>>La
							Plata</option>
						<option value="San Juan"
							<%= cli != null && "San Juan".equals(cli.getLocalidad()) ? "selected" : "" %>>San
							Juan</option>
					</select>
				</div>

				<div class="form-item">
					<label for="provincia">Provincia</label> <select id="provincia"
						name="provincia" required>
						<option value="Buenos Aires"
							<%= cli != null && "Buenos Aires".equals(cli.getProvincia()) ? "selected" : "" %>>Buenos
							Aires</option>
						<option value="Córdoba"
							<%= cli != null && "Córdoba".equals(cli.getProvincia()) ? "selected" : "" %>>Córdoba</option>
						<option value="Santa Fe"
							<%= cli != null && "Santa Fe".equals(cli.getProvincia()) ? "selected" : "" %>>Santa
							Fe</option>
						<option value="Mendoza"
							<%= cli != null && "Mendoza".equals(cli.getProvincia()) ? "selected" : "" %>>Mendoza</option>

					</select>
				</div>

				<div class="form-item">
					<label for="nacionalidad">Nacionalidad</label> <select
						id="nacionalidad" name="nacionalidad" required>
						<option value="Argentina"
							<%= cli != null && "Argentina".equals(cli.getNacionalidad()) ? "selected" : "" %>>Argentina</option>
						<option value="Chile"
							<%= cli != null && "Chile".equals(cli.getNacionalidad()) ? "selected" : "" %>>Chile</option>
						<option value="Uruguay"
							<%= cli != null && "Uruguay".equals(cli.getNacionalidad()) ? "selected" : "" %>>Uruguay</option>
						<option value="Paraguay"
							<%= cli != null && "Paraguay".equals(cli.getNacionalidad()) ? "selected" : "" %>>Paraguay</option>
					</select>
				</div>

				<div class="form-item">
					<label>Sexo</label>
					<div class="radio-group">
						<label><input type="radio" name="sexo" value="M"
							<%= cli != null && "M".equals(cli.getSexo()) ? "checked" : "" %>>
							Masculino</label> <label><input type="radio" name="sexo"
							value="F"
							<%= cli != null && "F".equals(cli.getSexo()) ? "checked" : "" %>>
							Femenino</label> <label><input type="radio" name="sexo" value="O"
							<%= cli != null && "O".equals(cli.getSexo()) ? "checked" : "" %>>
							Otro</label>
					</div>
				</div>

				<div class="form-item">
					<label for="fechaNacimiento">Fecha de Nacimiento</label> <input
						type="date" id="fechaNacimiento" name="fechaNacimiento" placeholder="aaaa-mm-dd" 
						value="<%= cli != null ? cli.getFechaNacimiento() : "" %>"
						required pattern="\d{4}-\d{2}-\d{2}">
				</div>
				<div class="form-item">
					<label for="direccion">Dirección</label> <input type="text"
						id="direccion" name="direccion"
						value="<%= cli != null ? cli.getDireccion() : "" %>" required>
				</div>

				<div class="form-item">
					<label for="contrasena">Contraseña</label> <input type="password"
						id="contrasena" name="contrasena">
				</div>

				<div class="form-item">
					<label for="contrasenaRep">Repetir Contraseña</label> <input
						type="password" id="contrasenaRep" name="contrasenaRep">
				</div>

				<div class="form-item" style="margin-top: 20px">
					<button type="submit" name="btnAceptar" class="btn-primary"
						style="background-color: green;">Aceptar</button>
					<button type="submit" name="btnCancelar" class="btn-secondary">Cancelar</button>
				</div>
			</div>
		</form>

		<% 
			HttpSession sessionMen = request.getSession(false);
		    if (sessionMen != null) {
		        String mensaje = (String) sessionMen.getAttribute("mensaje");
		        if (mensaje != null && !mensaje.isEmpty()) {
		%>
		<div class="alert alert-success alert-dismissible fade show"
			role="alert" style="margin-top: 20px;">
			<strong> <%= mensaje %></strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
		            session.removeAttribute("mensaje");
		        }
		    }
		%>
	</div>
</body>
</html>