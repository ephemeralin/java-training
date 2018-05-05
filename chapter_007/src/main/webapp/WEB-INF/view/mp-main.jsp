<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Musicplace</title>

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
    <h1>Musicplace</h1>
    <p>
        Mock.
    </p>

    <form action='${requestScope.path}/mp-login' method='post'><input type='submit'value='Logoff' style="width: auto">
        <input type='hidden' name='logoff' value="logoff">
    </form>

</body>
</html>
