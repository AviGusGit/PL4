<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
<title>Agregar Clientes</title>
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
		<form action="AgregarClienteServlet" method="post">
			<div class="form-container"
				style="display: flex; flex-wrap: wrap; justify-content: space-between;">
				<div class="form-item">
					<label for="dni">DNI</label> <input type="teL" id="dni" name="dni"
						pattern="^[0-9]+$"
						title="Solo se aceptan NÚMEROS, sin puntos, comas o guiones."
						required>
				</div>
				<div class="form-item">
					<label for="cuil">CUIL</label> <input type="teL" id="cuil"
						name="cuil" pattern="^[0-9]+$"
						title="Solo se aceptan NÚMEROS, sin puntos, comas o guiones."
						required>
				</div>
				<div class="form-item">
					<label for="nombre">Nombre</label> <input type="text" id="nombre"
						name="nombre" pattern="[A-Za-záéíóúÁÉÍÓÚÑñ]+"
						title="Solo se aceptan LETRAS." required>
				</div>
				<div class="form-item">
					<label for="apellido">Apellido</label> <input type="text"
						id="apellido" name="apellido" pattern="[A-Za-záéíóúÁÉÍÓÚÑñ]+"
						title="Solo se aceptan LETRAS." required>
				</div>
				<div class="form-item">
					<label for="correo">Correo</label> <input type="email" id="correo"
						name="correo" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}">
				</div>
				<div class="form-item">
					<label for="telefono">Teléfono</label> <input type="tel"
						id="telefono" name="telefono" pattern="^[0-9]+$"
						title="Solo se aceptan NÚMEROS, sin puntos, comas o guiones."
						required>
				</div>
				<div class="form-item">
					<label for="localidad">Localidad</label> <select id="localidad"
						name="localidad" required>
						<option value="Pilar">Pilar</option>
						<option value="Rosario">Tigre</option>
						<option value="La Plata">Escobar</option>
						<option value="San Juan">San Miguel</option>
					</select>
				</div>
				<div class="form-item">
					<label for="provincia">Provincia</label> 
					<select id="provincia" name="provincia" required>
						<option value="Buenos Aires">Buenos Aires</option>
						<option value="Córdoba">Córdoba</option>
						<option value="Santa Fe">Santa Fe</option>
						<option value="Mendoza">Entre Ríos</option>
					</select>
				</div>
				<div class="form-item">
					<label for="nacionalidad">Nacionalidad</label> 
					<select id="nacionalidad" name="nacionalidad" required>
						<option value="Argentina">Argentina</option>
						<option value="Chile">Chile</option>
						<option value="Uruguay">Uruguay</option>
						<option value="Paraguay">Paraguay</option>
					</select>
				</div>
				<div class="form-item">
					<label>Sexo</label>
					<div class="radio-group">
						<label><input type="radio" name="sexo" value="M" required>Masculino</label>
						<label><input type="radio" name="sexo" value="F"> Femenino</label>
						<label><input type="radio" name="sexo" value="O"> Otro</label>
					</div>
				</div>
				<div class="form-item">
					<label for="fechaNacimiento">Fecha de Nacimiento</label> 
					<input type="date" id="fechaNacimiento" name="fechaNacimiento" placeholder="aaaa-mm-dd" required pattern="\d{4}-\d{2}-\d{2}">
				</div>
				<div class="form-item">
					<label for="direccion">Dirección</label> <input type="text"
						id="direccion" name="direccion" required>
				</div>
				
			<div class="form-item">	<label for="usuario">Usuario:</label>
    <input type="text" id="usuario" name="usuario" required>
</div>

				<div class="form-item">
					<label for="contrasena">Contraseña</label> <input type="password"
						id="contrasena" name="contrasena" required><br><br>
				</div>
				<div class="form-item">
					<label for="contrasenaRep">Repetir Contraseña</label> <input
						type="password" id="contrasenaRep" name="contrasenaRep" required>
				</div>

				<div class="form-item" style="margin-top: 20px">
					<button type="submit" name="btnAceptar" class="btn-primary">Agregar</button>
					<button type="button" name="btnCancelar" onclick="window.location.href='inicioBanco.jsp'" class="btn-secondary">Cancelar</button>
				</div>
			</div>
		</form>
		
		<% 
    		String mensaje = (String) request.getAttribute("mensaje");
    		if (mensaje != null && !mensaje.isEmpty()) {
		%>
		    <div class="mensaje">
		        <%= mensaje %>
		    </div>
		<% 
		    }
		%>
	</div>
</body>
</html>
