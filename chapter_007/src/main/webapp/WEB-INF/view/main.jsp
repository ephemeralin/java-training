<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Users database</title>

    <style>
        <%@ include file="css/mainStyle.css"%>
        body {
            text-align: left;
        }
        h1 {
            text-align: center;
        }
    </style>

</head>

<body>
    <c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>
    <h1>Users database</h1>
    <p>
        Login: <c:out value="${login}"></c:out><br>
        Role: <c:out value="${role.name}"></c:out>
    </p>

    <form action='${requestScope.path}/login' method='post'><input type='submit'value='Logoff' style="width: auto">
        <input type='hidden' name='logoff' value="logoff">
    </form>

    <table
            style='width:70%'>
        <tr>
            <th>Name</th>
            <th>Login</th>
            <th>E-mail</th>
            <th>Role</th>
            <th>Country</th>
            <th>City</th>
            <th>Edit</th>
            <th>Delete</th>
            <c:forEach items="${usersList}" var="user">
                <tr>
                    <td><c:out value="${user.name}"></c:out></td>
                    <td><c:out value="${user.login}"></c:out></td>
                    <td><c:out value="${user.email}"></c:out></td>
                    <td><c:out value="${user.role}"></c:out></td>
                    <td><c:out value="${user.country}"></c:out></td>
                    <td><c:out value="${user.city}"></c:out></td>
                    <c:choose>
                        <c:when test="${role.name == 'admin'}">
                            <td><form action='${requestScope.path}/update' method='get'><input type='submit' value='Edit'>
                                <input type='hidden' name='email' value="${user.email}"></form>
                            </td>
                            <td><form action='${requestScope.path}/delete' method='post'><input type='submit'value='Delete'>
                                <input type='hidden' name='email' value="${user.email}"></form>
                            </td>
                        </c:when>
                        <c:when test="${role.name != 'admin' and login == user.login}">
                            <td><form action='${requestScope.path}/update' method='get'><input type='submit' value='Edit'>
                                <input type='hidden' name='email' value="${user.email}"></form>
                            </td>
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>


                </tr>
            </c:forEach>
    </table>
    <br>
    <c:if test="${role.name == 'admin'}">
        <form action='${requestScope.path}/create' method='get'>
            <input type='submit' value='Create new' style="width: auto">
        </form>
    </c:if>
</body>
</html>
