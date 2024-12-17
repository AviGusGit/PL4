<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="stylesheet">
<title>Eliminar Cuentas</title>
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
			<h1 class="title-container">Eliminar Cuenta</h1>
		</div>
		<div>
			<jsp:include page="HTML/MenuABMLCuentas.html"></jsp:include>
		</div>

		<h3 style="text-align: center;">Seleccione el Número de Cuenta a
			Eliminar</h3>

		<form action="EliminarCuenta" method="get" style="text-align: center;"  onsubmit="return eliminarCuenta()">
			<div class="form-item" style="display: flex; justify-content: center;">
				<select id="numero" name="numero" required>
				<option value="">Seleccione una cuenta</option>
					<%
                        ArrayList<Integer> nCuentas = (ArrayList<Integer>) request.getAttribute("numCuentas");
                        if (nCuentas != null && !nCuentas.isEmpty()) {
                            for (int numero : nCuentas) {
                    %>
					<option value="<%= numero %>"><%= numero %></option>
					<%
                            }
                        } else {
                    %>
					<option value="">No hay cuentas disponibles</option>
					<%
                        }
                    %>
				</select>
			</div>

			<div style="margin-top: 20px;">
				<button type="submit" name="btnAceptar" class="btn-primary">Eliminar</button>
				<button type="button" onclick="window.location.href='ListarCuentas'"
					class="btn-secondary">Cancelar</button>
			</div>
		</form>
	</div>
	<script>
    function eliminarCuenta() {
        return confirm("¿Está seguro que deseas eliminar esta cuenta ?");
    }
</script>
</body>
</html>
