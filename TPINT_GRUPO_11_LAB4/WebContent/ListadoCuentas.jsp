<%@page import="Entidades.Cuenta"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
<link href="CSS/table.css" rel="StyleSheet">
<title>CUENTAS</title>
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
	ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaC");

 %>
		<div>
			<table class="redTable">
				<thead>
					<tr>
						<th>N° Cuenta</th>
						<th>DNI Cliente</th>
						<th>Fecha Creación</th>
						<th>Tipo</th>
						<th>CBU</th>
						<th>Saldo</th>
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
	<%  if(listaCuentas!=null)
		for(Cuenta user : listaCuentas) 
		{
	%>
					<tr>
						<form name="formulario" action="ListarCuentas" method="get">
							<td><%=user.getNumeroCuenta() %></td>
							<td><%=user.getDniCliente() %></td>
							<td><%=user.getFechaCreacion() %></td>
							<td><%=user.getTipoCuenta().getNombreTipo() %></td>
							<td><%=user.getCbu() %></td>
							<td><%=user.getSaldo() %></td>
						</form>
					</tr>
					<%  } %>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>