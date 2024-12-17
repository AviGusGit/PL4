<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
<title>Agregar Cuentas</title>
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
			<h1 class="title-container">CUENTAS</h1>
			
		</div>
		<div>
			<jsp:include page="HTML/MenuABMLCuentas.html"></jsp:include>
		</div>
		
		<form action="AgregarCuenta" method="get">
		
			<div class="form-container"
				style="display: flex; flex-wrap: wrap; justify-content: space-between;">

				<div class="form-item">
				<label for="dni">DNI</label>
				
				<select id="dni" name="dni" required>
                <option value="">Seleccione un cliente</option>

                <% 
                    ArrayList<String> dniClientes = (ArrayList<String>) request.getAttribute("dniClientes");
                    
                    if (dniClientes != null && !dniClientes.isEmpty()) {
                        for (String dni : dniClientes) {
                %>
                    <option value="<%= dni %>"><%= dni %></option>
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
					<label for="fechaCreacion">Fecha Creación</label> <input
						type="date" id="fechaCreacion" name="fechaCreacion" required value="<%= new java.sql.Date(System.currentTimeMillis()) %>">
				</div>

				<div class="form-item">
					<label>Tipo</label>
					<div class="radio-group">
						<label><input type="radio" name="tipo" value="1" required>Cuenta Corriente</label>
						<label><input type="radio" name="tipo" value="2">Caja de Ahorro</label>
					</div>
				</div>

				<div class="form-item">
					<label for="cbu">CBU</label> <input type="tel" id="cbu" name="cbu"
						pattern="^[0-9]+$"
						title="Solo se aceptan NÚMEROS, sin puntos, comas o guiones."
						required>
				</div>

				<div class="form-item">
					<label>Saldo</label> <label>10000</label>
				</div>

			</div>

			<div
				style="margin-top: 20px; display: flex; justify-content: flex-end;">
				<button type="submit" class="btn-primary" name="btnAceptar"
					style="margin-right: 10px;">Agregar</button>
				<button type="submit" class="btn-secondary" name="btnCancelar"
					onclick="window.location.href='inicioBanco.jsp'"
					style="margin-right: 70px;">Cancelar</button>
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