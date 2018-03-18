<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Error</title>
</head>

<body>
    <p><%=request.getAttribute("errorMsg")%></p>

    <form action='/users/mainj' method = 'get'>
        <input type='submit' value='Main'>
    </form>
</body>

</html>
