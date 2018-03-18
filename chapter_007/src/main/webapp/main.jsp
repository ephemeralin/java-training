<%@ page import="ru.job4j.users.model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<br>
<table
        style='width:70%'>
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>E-mail</th>
        <th>Edit</th>
        <th>Delete</th>
    <%
        List<User> allUsers = (List<User>) request.getAttribute("usersList");
        for (User user : allUsers) { %>
            <tr>
                <td><%=user.getName()%></td>
                <td><%=user.getLogin()%></td>
                <td><%=user.getEmail()%></td>
                <td><form action='/users/updatej' method='get'><input type='submit' value='Edit'>
                    <input type='hidden' name='email' value=<%=user.getEmail()%>></form>
                </td>
                <td><form action='/users/deletej' method='post'><input type='submit'value='Delete'>
                    <input type='hidden' name='email' value=<%=user.getEmail()%>></form>
                </td>
            </tr>

    <%  }
    %>

</table>
<br>
    <form action='/users/createj' method='get'><input type='submit' value='Create new'>
</form>

</body>
</html>
