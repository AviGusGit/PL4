<%@page import="Entidades.Cliente"%>
<%@page import="Entidades.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
<link href="CSS/menu.css" rel="StyleSheet">
<link href="CSS/table.css" rel="StyleSheet">
<title>TRANSFERIR</title>
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
		<% 
		
		Cliente cli = (Cliente)session.getAttribute("Cliente");
		if (cli != null && session.getAttribute("cantCuentas") != null) {
	    	int cantCuentas = (Integer)session.getAttribute("cantCuentas");
	    	float total = ((Float) session.getAttribute("SaldoTotal")).floatValue();

		%>
		<div class="header"
			style="display: flex; justify-content: space-between;">

			<img src="image/logo.png" alt="logo MAZE BANK" width="125"
				height="90" style="margin-top: 7px;">
			<div
				style="margin-right: 20px; font-size: 15px; color: #555; line-height: 0.5;">
				<p>
					<strong>Nombre:</strong> <%= cli != null ? cli.getNombre() + " " + cli.getApellido() : "" %>
				</p>
				<p>
					<strong>Total Cuentas:</strong> <%= cantCuentas %>
				</p>
				<p>
					<strong>Saldo Total:</strong> $<%=total%>
				</p>
			</div>
		</div>
		
		<%
	    }else{
	    	out.println("Cliente no encontrado.");
	    }
        %>

		<div>
			<jsp:include page="HTML/MenuCliente.html"></jsp:include>
		</div>

		<h2
			style="display: flex; justify-content: center; margin-bottom: 40px;">Realizar
			Transferencia</h2>

		<% 
		
        ArrayList<Integer> cuentasCliente = (ArrayList<Integer>) session.getAttribute("listaCuentas");
		
		%>
		
		<form action="TransferirCliente" method="get" onsubmit="confirmTrans()">

			<div class="form-container">
				<div class="form-item" style="margin-top: 10px; margin-left: 10px; display: flex; flex-direction: row;">
				<label for="numeroCuenta">Número de Cuenta</label>
				<select id="numeroCuenta" name="numeroCuenta" onchange="this.form.submit()" required>
		
				<option value="">Seleccione una cuenta</option>
				<% 
				int cuentaSeleccionada = 0;
						
					if(request.getAttribute("cuentaSeleccionada") != null){
						cuentaSeleccionada = (Integer)request.getAttribute("cuentaSeleccionada");
					}
						
				if (cuentasCliente != null && !cuentasCliente.isEmpty()) {
			          for (int num : cuentasCliente) { 
		        %>
						<option value="<%= num %>" <%= num == cuentaSeleccionada ? "selected" : "" %>><%= num %></option>
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
					<label for="cbu">CBU Destino:</label>
					<select id="cbu" name="cbu" required>
	                <option value="">Seleccione un CBU</option>
	
	                <% 
	                    ArrayList<String> cbuCuentas = (ArrayList<String>) request.getAttribute("cbuCuentas");
	                    
	                    if (cbuCuentas != null && !cbuCuentas.isEmpty()) {
	                        for (String cbu : cbuCuentas) {
	                %>
	                    <option value="<%= cbu %>"><%= cbu %></option>
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
					<label for="monto">Monto a Transferir:</label> <input type="number"
						id="monto" name="monto" min="1" step="0.01" required>
				</div>
				
				

			</div>

			<div class="form-item" 
				style="display: flex; justify-content: flex-end; margin-right: 64px; margin-top: 20px;">
				<button type="submit" class="btn-primary"
					style="margin-right: 10px;">Transferir</button>
				<button type="submit" class="btn-secondary">Cancelar</button>
			</div>
		</form>
				<script>
            function confirmTrans() {
              return confirm("¿Está seguro que deseas realizar esta transacción?");
             }
          </script>

	</div>
</body>
</html>
