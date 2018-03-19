<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Users database</title>

    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 7px;
        }
    </style>
</head>

<body>
    <c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>
    <br>
    <table
            style='width:70%'>
        <tr>
            <th>Name</th>
            <th>Login</th>
            <th>E-mail</th>
            <th>Edit</th>
            <th>Delete</th>
            <c:forEach items="${usersList}" var="user">
                <tr>
                    <td><c:out value="${user.name}"></c:out></td>
                    <td><c:out value="${user.login}"></c:out></td>
                    <td><c:out value="${user.email}"></c:out></td>
                    <td><form action='${requestScope.path}/update' method='get'><input type='submit' value='Edit'>
                        <input type='hidden' name='email' value="${user.email}"></form>
                    </td>
                    <td><form action='${requestScope.path}/delete' method='post'><input type='submit'value='Delete'>
                        <input type='hidden' name='email' value="${user.email}"></form>
                    </td>
                </tr>
            </c:forEach>
    </table>
    <br>
    <form action='${requestScope.path}/create' method='get'>
        <input type='submit' value='Create new'>
    </form>
</body>
</html>
