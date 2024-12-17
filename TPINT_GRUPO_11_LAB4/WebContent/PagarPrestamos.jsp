<%@page import="Entidades.Cliente"%>
<%@page import="Entidades.Cuenta"%>
<%@page import="Entidades.Prestamo"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="CSS/estiloGeneral.css" rel="stylesheet">
    <link href="CSS/menu.css" rel="stylesheet">
    <link href="CSS/table.css" rel="stylesheet">
    <title>Pagar Préstamos</title>
</head>
<%
//VERIFICA QUE HAYA UN USUARIO LOGUEADO, SINO DEVUELVE AL LOGIN
int rol = Integer.parseInt(session.getAttribute("usuarioRol").toString());
if (session == null || session.getAttribute("usuarioLogueado") == null || rol == 1) {
%>
        <script>
        alert("Debe iniciar sesi�n como Cliente.");
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
            int cantCuentas = (Integer) session.getAttribute("cantCuentas");
            float total = ((Float) session.getAttribute("SaldoTotal")).floatValue();
        %>
        <div class="header" style="display: flex; justify-content: space-between;">
            <img src="image/logo.png" alt="logo MAZE BANK" width="125" height="90" style="margin-top: 7px;">
            <div style="margin-right: 20px; font-size: 15px; color: #555; line-height: 0.5;">
                <p><strong>Nombre:</strong> <%= cli.getNombre() + " " + cli.getApellido() %></p>
                <p><strong>Total Cuentas:</strong> <%= cantCuentas %></p>
                <p><strong>Saldo Total:</strong> $<%= total %></p>
            </div>
        </div>
        <% } else {
            response.sendRedirect("Login.jsp");
        } %>

        <div>
            <jsp:include page="HTML/MenuCliente.html"></jsp:include>
        </div>

        <h2 style="display: flex; justify-content: center; margin-bottom: 40px;">Pagar Pr�stamos</h2>

        <%
       
        ArrayList<Integer> cuentasCliente = (ArrayList<Integer>) session.getAttribute("listaCuentas");
        float importeCuota = session.getAttribute("importeCuota") != null ? (Float) session.getAttribute("importeCuota") : 0.0f;
       
        ArrayList<Prestamo> NumPrestamos = new  ArrayList<>();
       
        NumPrestamos = (ArrayList<Prestamo>) session.getAttribute("numeroDePrestamoPorUsuario");
       
       
       
       
        %>

        <form action="PagarPrestamos" method="post">
            <div class="form-container">
                <!-- Selección de cuenta -->
                <div class="form-item" style="margin-top: 10px; margin-left: 10px; display: flex; flex-direction: row;">
                    <label for="numeroCuenta">Número de Cuenta:</label>
                    <select id="numeroCuenta" name="numeroCuenta" required>
                        <option value="">Seleccione una cuenta</option>
                        <%
                        if (cuentasCliente != null && !cuentasCliente.isEmpty()) {
                            for (int num : cuentasCliente) {
                        %>
                        <option value="<%= num %>"><%= num %></option>
                        <%
                            }
                        } else {
                        %>
                        <option value="">No hay cuentas disponibles</option>
                        <%
                        }
                        %>
                    </select>
                   <label for="numeroCuenta">Numero de Prestamo:</label>
                    <select id="numeroPrestamo" name="numeroPrestamo" required>
                        <option value="">Seleccione un Prestamo</option>
                        <%
                        if (cuentasCliente != null && !cuentasCliente.isEmpty()) {
                            for (Prestamo num : NumPrestamos) {
                        %>
                        <option value="<%= num.getNumeroPres() %>"><%= num.getNumeroPres() %></option>
                        <%
                            }
                        } else {
                        %>
                        <option value="">No hay Prestamos disponibles</option>
                        <%
                        }
                        %>
                    </select>
                </div>

                <!-- Selección de cuotas -->
                <div class="form-item">
                    <label for="cuotas">Cuotas a Pagar:</label>
                    <select id="cuotas" name="cuotas" required>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                </div>

                <!-- Importe dinámico -->
                <div class="form-item">
                    <label>Importe por Cuota:</label>
                    <label>$<%= importeCuota %></label>
                </div>
            </div>

            <!-- Botones -->
            <div class="form-item" style="display: flex; justify-content: flex-end; margin-right: 64px; margin-top: 20px;">
                <button type="submit" class="btn-primary" style="margin-right: 10px;">Pagar</button>
                <button type="button" class="btn-secondary" onclick="window.location.href='MenuCliente.html';">Cancelar</button>
            </div>
        </form>
    </div>
</body>
</html>