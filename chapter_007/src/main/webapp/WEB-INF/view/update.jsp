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
            <input type='text' name='login' value="${login}"><br>
            Role:<br>
            <c:choose>
                <c:when test="${currentRole.name == 'admin'}">
                    <select name="role">
                        <c:forEach items="${rolesList}" var="roleFromList">
                            <c:choose>
                                <c:when test="${roleFromList.name == role}">
                                    <option value="${roleFromList.name}" selected>${roleFromList.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${roleFromList.name}">${roleFromList.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select><br>
                </c:when>
                <c:otherwise>
                    <input type='text' name='role' value="${role}" readonly><br>
                </c:otherwise>
            </c:choose>
            Password:<br>
            <input type='password' name='password' value="${password}"><br>
            <br>
            <input type='submit' value='Update'>
        </form>

        <form action="${requestScope.path}/main" method = 'get'>
            <input type='submit' value='Cancel'>
        </form>
    </body>
</html>