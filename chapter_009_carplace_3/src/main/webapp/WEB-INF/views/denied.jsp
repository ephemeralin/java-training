<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Access denied</title>
    <style>
        <%@ include file="css/mainStyle.css"%>
    </style>
</head>
<body>
<div class="div1">
    <h3 style="color:#ff0000;">Access denied: you are not authorized to access this resource.</h3>
    User: <security:authentication property="principal.username" />
    <br>
    Role(s): <security:authentication property="principal.authorities" />
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/cars.do" method='get'>
        <input type='submit' value='Home'>
    </form>
</div>

</body>
</html>
