<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Custom Login page</title>
    <style>
        .failed {
            color:red;
        }
    </style>
    <style>
        <%@ include file="css/mainStyle.css"%>
    </style>
</head>

<body>
    <h1>Please, login</h1>
    <div class="div1">
        <form:form action="${pageContext.request.contextPath}/authenticateUser" method="post">
            <c:if test="${param.error != null}">
                <b class="failed">Sorry! You entered invalid username/password</b>
            </c:if>
            <c:if test="${param.logout != null}">
                <b class="failed">You have been logged out.</b>
            </c:if>
            <p>
                User name: <input type="text" name="username"/>
            </p>
            <p>
                Password: <input type="password" name="password"/>
            </p>
            <input type="submit" value="Login"/>
        </form:form>
    </div>

</body>

</html>
