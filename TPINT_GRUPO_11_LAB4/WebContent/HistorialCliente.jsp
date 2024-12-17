<%@page import="Entidades.Cliente"%>
<%@page import="Entidades.Cuenta"%>
<%@page import="Entidades.Movimiento"%>
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
<title>HISTORIAL</title>
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
    	float total = ((Float) session.getAttribute("SaldoTotal")).floatValue();
		
	    if (cli != null && session.getAttribute("cantCuentas") != null) {
	    	int cantCuentas = (Integer)session.getAttribute("cantCuentas");
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
					<strong>Saldo Total:</strong> $<%=total %>
				</p>
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
        
		<div>
			<jsp:include page="HTML/MenuCliente.html"></jsp:include>
		</div>

		<% 
		
        ArrayList<Integer> cuentasCliente = (ArrayList<Integer>) session.getAttribute("listaCuentas");
		ArrayList<Movimiento> listaMovs = (ArrayList<Movimiento>)request.getAttribute("listaMovs");
		
		%>

	<form action="HistorialCliente" method="get">
		<div class="form-item"
			style="margin-top: 10px; margin-left: 10px; display: flex; flex-direction: row;">
			<label for="numeroCuenta">Número de Cuenta</label>
			<select id="numeroCuenta" name="numeroCuenta" onchange="this.form.submit()" required>

						<option value="">Seleccione una cuenta</option>
			<% 
	        	int numSelec = 0;
				if(request.getAttribute("numCuentaSelec") != null){
					numSelec = (Integer) request.getAttribute("numCuentaSelec");
				}
					
	            if (cuentasCliente != null && !cuentasCliente.isEmpty()) {
	                for (int num : cuentasCliente) { 
        	%>
						<option value="<%= num %>" <%= num == numSelec ? "selected" : "" %>><%= num %></option>
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
		<div>
			<table class="redTable">
				<thead>
					<tr>
						<th>N° Cuenta</th>
						<th>Código Movimiento</th>
						<th>Fecha</th>
						<th>Detalle</th>
						<th>Importe</th>
						<th>Tipo Movimiento</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="6">
							<div class="links">
								<a href="#">&laquo;</a> <a class="active" href="#">1</a> <a
									href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">&raquo;</a>
							</div>
						</td>
					</tr>
				</tfoot>
				<tbody>

		<% if(listaMovs!=null)
    	
		for(Movimiento movimiento : listaMovs) 
			
		{
		%>
		<tr>
				
				<td><%=movimiento.getNumeroCuenta() %> </td>
				<td><%=movimiento.getCodigoMovimiento() %></td>
				<td><%=movimiento.getFecha() %></td>
				<td><%=movimiento.getDetalle() %></td>
				<td><%=movimiento.getImporte() %></td>
				<td><%=movimiento.getTipoMovimiento().getIdTipoMovimiento() %></td>
		</tr>		
		<%
		}
		%>
				</tbody>
			</table>
		</div>
	</form>
	</div>
</body>
</html>