<%@page import="Entidades.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<title>Modificar Cuentas</title>
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
		<% 
		
		Cuenta cuenta = (Cuenta)request.getAttribute("listaC");
		
		ArrayList<Integer> numCuentas = (ArrayList<Integer>)request.getAttribute("numCuentas");
		ArrayList<String> dniClientes = (ArrayList<String>) request.getAttribute("dniClientes");
		%>

		<form action="ModificarCuenta" method="get">

			<div class="form-container"
				style="display: flex; flex-wrap: wrap; justify-content: space-between;">
				<div class="form-item">
					<label for="numero">Número de Cuenta</label>
					<select id="numero" name="numero" onchange="this.form.submit()" required>

						<option value="">Seleccione una cuenta</option>
			<%
            	
	        	int numSeleccionado = 0;
	        	if(request.getAttribute("numSeleccionado") != null) {
	        		numSeleccionado = (Integer)request.getAttribute("numSeleccionado");
	        	}
	        		
              	if (numCuentas != null && !numCuentas.isEmpty()) {
              	for (int numero : numCuentas) {
           %>
						<option value="<%= numero %>"
							<%= numero == numSeleccionado ? "selected" : "" %>><%= numero %></option>
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

				<div class="form-item">
				<label for="dni">DNI Cliente</label>
				<select id="dni" name="dni" required>
				<option value=""></option>

                <% 
                	String dniSeleccionado = (String) request.getAttribute("dniSeleccionado");
                    
                    if (dniClientes != null && !dniClientes.isEmpty()) {
                        for (String dni : dniClientes) {
                %>
                    <option value="<%= dni %>" <%= (dniSeleccionado != null && dniSeleccionado.equals(dni)) ? "selected" : "" %> > <%= dni %>
            		</option>
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
					<label for="fechaCreacion">Fecha Creación</label> 
					<input type="date" id="fechaCreacion" name="fechaCreacion" value="<%= cuenta != null ? cuenta.getFechaCreacion() : "" %>" required>
				</div>

				<div class="form-item">
					<label>Tipo</label>
					<div class="radio-group">
						<label>
						<input type="radio" name="tipo" value="1" <%= cuenta != null && cuenta.getTipoCuenta().getIdTipo() == 1 ? "checked" : "" %>>Cuenta Corriente</label>
						 <label>
						 <input type="radio" name="tipo" value="2" <%= cuenta != null && cuenta.getTipoCuenta().getIdTipo() == 2 ? "checked" : "" %>>Caja de Ahorro</label>
					</div>
				</div>

				<div class="form-item">
					<label for="cbu">CBU</label> 
					<input type="tel" id="cbu" name="cbu" pattern="^[0-9]+$" title="Solo se aceptan NÚMEROS, sin puntos, comas o guiones." value="<%= cuenta != null ? cuenta.getCbu() : "" %>" required>
				</div>

				<div class="form-item">
					<label for="saldo">Saldo</label> 
					<input type="text" id="saldo" name="saldo" value="<%= cuenta != null ? cuenta.getSaldo() : "" %>" required>
				</div>

			</div>

			<div
				style="margin-top: 20px; display: flex; justify-content: flex-end;">
				<button type="submit" class="btn-primary" name="btnAceptar"
					style="margin-right: 10px; background-color: green;">Aceptar</button>
				<button type="submit" class="btn-secondary" name="btnCancelar"
					style="margin-right: 70px;">Cancelar</button>
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