<%@page import="Entidades.Prestamo"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
<link href="CSS/table.css" rel="StyleSheet">
<title>Autorización Préstamos</title>
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
			<h1 class="title-container">PRÉSTAMOS</h1>
		</div>

		<div>
			<jsp:include page="HTML/MenuPrestamos.html"></jsp:include>
		</div>
		
		<% 
		ArrayList <Prestamo> listaPrestamos = (ArrayList<Prestamo>)request.getAttribute("listaP");
		%>
		

		
		<div class="form-item"
			style="margin-top: 10px; margin-left: 10px; display: flex; flex-direction: row;">
			<label for="filtro">Filtrar por:</label> 
			<select id="filtro" name="filtro" style="margin-left: 10px; width: 20%; height: 30px">
			<option value="numero">Número Préstamo</option>
			<option value="dni">DNI Cliente</option>
			<option value="fecha">Fecha</option>
			</select>
			<input id="inputFiltro" name="inputFiltro" style="margin-left: 10px; width: 20%; height: 30px">
			<button id="btnFiltrar" name="btnFiltrar" type="submit" class="btn-secondary" style="margin-left: 10px; height: 30px;">Filtrar</button>
		</div>
		<div>
			<table class="redTable">
				<thead>
					<tr>
						<th>N° Préstamo</th>
						<th>DNI Cliente</th>
						<th>Numero Cuenta</th>
						<th>Fecha</th>
						<th>Importe a Pagar</th>
						<th>Importe Pedido</th>
						<th>Plazo de Pago</th>
						<th>Monto por Mes</th>
						<th>Cuotas</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="8">
							<div class="links">
								<a href="#">&laquo;</a> <a class="active" href="#">1</a> <a
									href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">&raquo;</a>
							</div>
						</td>
					</tr>
				</tfoot>
				
		<tbody>
				
		<% if(listaPrestamos!=null)
    	
		for(Prestamo prestamo : listaPrestamos) 
		{
		%>
		<tr>
		<form name="formulario" action="ListarPrestamos" method="get">
				<td><%=prestamo.getNumeroPres() %></td>
				<td><%=prestamo.getDniCliente() %> </td>
				<td><%=prestamo.getNumeroCuenta() %> </td>
				<td><%=prestamo.getFecha() %></td>
				<td><%=prestamo.getImportePagar() %></td>
				<td><%=prestamo.getImportePedido() %></td>
				<td><%=prestamo.getPlazoPago() %></td>
				<td><%=prestamo.getMontoMes() %></td>
				<td><%=prestamo.getCuotas() %></td>
		</form>
		</tr>		
		<%
		}
		%>
		</tbody>
		</table>
		</div>
	</div>
</body>
</html>