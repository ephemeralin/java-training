<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>Error</title>
    <style>
        <%@ include file="css/mainStyle.css"%>
    </style>
</head>

<body>
    <c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>

    <h1 style="color: red">${errorMsg}</h1>
    <div>
        <form action='${requestScope.path}/main' method = 'get'>
            <input type='submit' value='Main'>
        </form>
        <form action='${requestScope.path}/create' method='get'>
            <input type='submit' value='Create new'>
        </form>
    </div>

</body>

</html>
