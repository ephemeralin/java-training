<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Musicplace. Please, login...</title>

    <style>
        <%@ include file="css/mainStyle.css"%>
    </style>

</head>

    <body>
        <c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>
        <h1>Musicplace. Please, login...</h1>
        <div>
            <p style="color: red;font-size: 22px;font-weight: bold">
                <c:if test="${error != ''}">
                    <c:out value="${error}"/>
                </c:if>
            </p>

            <form action="${requestScope.path}/mp-login" method='post'>
                Login<br>
                <input type='text' name='login' value="${login}"><br>
                Password<br>
                <input type='password' name='password' value="${password}"><br>
                <br>
                <input type='submit' value='OK'>
            </form>
        </div>
    </body>
</html>