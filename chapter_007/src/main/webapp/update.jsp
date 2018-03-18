<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users database</title>
</head>

<body>
<p>Please, edit fields and submit...</p>

<form action='/users/updatej' method='post'>
    E-mail:<br>
    <input type='text' name='email' value=<%=request.getAttribute("email")%> readonly><br>
    Name:<br>
    <input type='text' name='name' value="<%=request.getAttribute("name")%>"><br>
    Login:<br>
    <input type='text' name='login' value="<%=request.getAttribute("login")%>"><br><br>
    <input type='submit' value='Update'>
</form>

<form action='/users/mainj' method = 'get'>
    <input type='submit' value='Cancel'>
</form>