<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="CSS/estiloGeneral.css" rel="StyleSheet">
<link href="CSS/table.css" rel="StyleSheet">
<title>INICIO</title>
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
			<h1 class="title-container">INICIO</h1>
		</div>
		<div>
			<jsp:include page="HTML/MenuAdmin.html"></jsp:include>
		</div>
		
		<div style="flex: 1; display: flex; flex-direction: column; align-items: center; justify-content: center;">
			<h2 style="font-size: 3rem; text-align: center; color: #333;">Bienvenido al Inicio</h2>
		</div>
	
<form method="post" onsubmit="return cerrarSesion()" action="Logout" style="position: fixed; bottom: 60px; right: 100px;">
    <button type="submit" id="btnLogout" name="btnLogout" style="background: red; padding: 10px 20px; font-size: 1rem; color: white; border: none; border-radius: 5px; cursor: pointer;">
        CERRAR SESIÓN
    </button>
</form>

<script>
    function cerrarSesion() {
        return confirm("¿Está seguro que deseas cerrar sesión ?");
    }
</script>

	</div>
</body>
</html>