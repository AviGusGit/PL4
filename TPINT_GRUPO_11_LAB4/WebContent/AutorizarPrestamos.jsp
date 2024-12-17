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
<title>Autorizar Préstamos</title>
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
		
		<form name="formulario" action="AutorizarPrestamos" method="post">
		
		<div class="form-item"
			style="margin-top: 10px; margin-left: 10px; display: flex; flex-direction: row;">
			<label for="numeroPres">Préstamos a Autorizar:</label> 
			
			<select id="numeroPres" name="numeroPres" style="margin-left: 10px; width: 15%;" required>
				<option value="">Seleccione un prestamo</option>
				<% 
				int numeroPres = 0;
						
					if(request.getAttribute("numeroPres") != null){
						numeroPres = (Integer)request.getAttribute("numeroPres");
					}
						
				if (listaPrestamos != null && !listaPrestamos.isEmpty()) {
					int num = 0;
			        for (Prestamo prestamo : listaPrestamos) { 
			        	num = prestamo.getNumeroPres();
		        %>
						<option value="<%= num %>" <%= num == numeroPres ? "selected" : "" %>><%= num %></option>
				<% 
		              }
		            } else { 
		        %>
				<option value="">No hay prestamos a autorizar</option>
				<% 
		            } 
		        %>
				</select>
		</div>

		
			<div class="form-item"
				style="display: flex; justify-content: flex-end; margin-right: 64px; margin-top: 20px;">
				<button type="submit" class="btn-primary" name="btnAutorizar" style="margin-right: 10px;">Autorizar</button>
				<button type="submit" class="btn-secondary" name="btnRechazar">Rechazar</button>
			</div>
		</div>
		</form>
	</div>
</body>
</html>