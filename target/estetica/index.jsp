<%-- 
    Document   : index-jsp
    Created on : 11/07/2024, 00:27:10
    Author     : SFX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World desde HTML!</h1>
        <% String hola = "Hola mundo desde Java"; %>
        <%=hola %>
        
        <form action="tratamiento" method="POST">
            <p><label>Nombre: </label><input type="text" name="nombre"></p>
            <p><label>Cantidad de sesiones: </label><input type="numeric" name="cantidadSesiones"></p>
            <p><label>Tiempo: </label><input type="text" name="tiempo"></p>
            <p><label>Tiempo superpuesto: </label><input type="text" name="tiempoSuperpuesto"></p>
            <p><label>Precio: </label><input type="number" name="precio"></p>
            <button type="submit"> Enviar</button>
            
        </form>
    </body>
</html>
