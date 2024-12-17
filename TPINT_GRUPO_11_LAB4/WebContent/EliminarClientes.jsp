<%@page import="Entidades.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
  <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<title>Eliminar Clientes</title>
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
            ArrayList<String> dniClientes = (ArrayList<String>) request.getAttribute("dniClientes");
		%>
		<h3 style="display: flex; justify-content: center;">
			Seleccione el DNI del cliente a eliminar:
		</h3>
		
		<form action="EliminarCliente" method="get"  onsubmit="return eliminarCliente()" >
			<div class="form-item" style="display: flex; justify-content: center;">
			
				<select id="dni" name="dni" required>
				<option value="">Seleccione un cliente</option>
			<%
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

				<div class="form-item"
					style="display: flex; justify-content: flex-end; margin-right: 100px;">
					<button type="submit" class="btn-primary" name="btnEliminar" style="margin-right: 10px;">Eliminar</button>
					
				</div>
		</form>
	</div>
	<script>
    function eliminarCliente() {
        return confirm("¿Está seguro que deseas eliminar este cliente ?");
    }
</script>
	
</body>
</html>