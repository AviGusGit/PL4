<%@page import="Entidades.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
<link href="CSS/table.css" rel="StyleSheet">
<title>CLIENTES</title>
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
		ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) request.getAttribute("listaC");

		 %>
		<div>
			<table class="redTable">
				<thead>
					<tr>
						<th>DNI</th>
						<th>CUIL</th>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Correo</th>
						<th>T&eacute;lefono</th>
						<th>Dirección</th>
						<th>Localidad</th>
						<th>Provincia</th>
						<th>Nacionalidad</th>
						<th>Sexo</th>
						<th>Fecha Nacimiento</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="12">
							<div class="links">
								<a href="#">&laquo;</a> <a class="active" href="#">1</a> <a
									href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">&raquo;</a>
							</div>
						</td>
					</tr>
				</tfoot>
				
				<tbody>

		<%  if(listaClientes!=null)
    	
		for(Cliente user : listaClientes) 
		{
		%>
					<tr>
						<form name="formulario" action="ListarClienteServlet" method="get">
							<td><%=user.getDni() %></td>
							<td><%=user.getCuil() %></td>
							<td><%=user.getNombre() %></td>
							<td><%=user.getApellido() %></td>
							<td><%=user.getCorreo() %></td>
							<td><%=user.getTelefono() %></td>
							<td><%=user.getDireccion() %></td>
							<td><%=user.getLocalidad() %></td>
							<td><%=user.getProvincia() %></td>
							<td><%=user.getNacionalidad() %></td>
							<td><%=user.getSexo() %></td>
							<td><%=user.getFechaNacimiento() %></td>
						</form>
					</tr>
					<%  } %>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>