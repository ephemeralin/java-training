<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Users database</title>
</head>

<body>
<c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>
<p>Please, edit fields and submit...</p>

<form action="${requestScope.path}/update" method='post'>
    E-mail:<br>
    <input type='text' name='email' value="${email}" readonly><br>
    Name:<br>
    <input type='text' name='name' value="${name}"><br>
    Login:<br>
    <input type='text' name='login' value="${login}"><br><br>
    <input type='submit' value='Update'>
</form>

<form action="${requestScope.path}/main" method = 'get'>
    <input type='submit' value='Cancel'>
</form>