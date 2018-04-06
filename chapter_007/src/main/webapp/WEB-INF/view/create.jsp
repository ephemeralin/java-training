<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create user</title>
</head>

<body>
<c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>

<p>Please, fill all fields and submit...</p>

<form action="${requestScope.path}/create" method='post'>
    E-mail:<br>
    <input type='text' name='email' value=''><br>
    Name:<br>
    <input type='text' name='name' value=''><br>
    Login:<br>
    <input type='text' name='login' value=''><br>
    Role:<br>
    <select name="role">
        <c:forEach items="${rolesList}" var="roleFromList">
            <option value="${roleFromList.name}">${roleFromList.name}</option>
        </c:forEach>
    </select><br>
    Password:<br>
    <input type='password' name='password' value=''><br>
    <br>
    <input type='submit' value='Create'>
</form>

<form action="${requestScope.path}/main" method = 'get'>
    <input type='submit' value='Cancel'>
</form>