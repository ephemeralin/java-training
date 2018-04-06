<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Please, login...</title>
</head>

    <body>
        <c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>
        <p>Please, login...</p>
        <c:if test="${error != ''}">
            <div style="background: darkred">
                <c:out value="${error}"/>
            </div>
        </c:if>
        <form action="${requestScope.path}/login" method='post'>
            Login:<br>
            <input type='text' name='login' value="${login}"><br>
            Password:<br>
            <input type='password' name='password' value="${password}"><br>
            <br>
            <input type='submit' value='OK'>
        </form>
    </body>
</html>