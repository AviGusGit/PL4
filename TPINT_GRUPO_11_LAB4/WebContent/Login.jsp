<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/login.css" rel="StyleSheet">
<title>MAZE BANK</title>
</head>
<body>

	<div class="container">
		<img src="image/logo.png" alt="logo MAZE BANK" width="125" height="90"
			style="margin-top: 7px;">

		<form method="post" action="Login">
			<div class="log">

				<p>Usuario:</p>
				<input id="usuario" name="usuario" type="text">

				<p>Contraseña:</p>

				<input id="contrasena" name="contrasena" type="password">

				<button type="submit" id="btnAceptarLogin" name="btnAceptarLogin" class="btnIngresar">INGRESAR</button>
			</div>
		</form>
		
		<%if (session.getAttribute("usuarioLogueado") != null && !"true".equals(session.getAttribute("usuarioLogueado")))
		{
		%><p>NO SE HA ENCONTRADO EL USUARIO</p>
		<%	
		} 
		%>
		
	</div>

</body>
</html>