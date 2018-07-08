<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create user</title>
    <style>
        <%@ include file="css/mainStyle.css"%>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        function country_change() {
            var e = document.getElementById("country_");
            var country_id = e.options[e.selectedIndex].value;
            $.ajax({
                type: "POST",
                url: "cities",
                data: "country_id="+country_id,
                cache: false,
                dataType:"json",
                success: function (data) {
                    console.log(data);
                    var selectedCity=$(".city_"), option="";
                    selectedCity.empty();
                    option = "<option value=''>Please, select a city</option>";
                    for (var i = 0; i != data.length; i++) {
                        option = option + "<option value='"+data[i].id + "'>"+data[i].name + "</option>";
                    }
                    selectedCity.append(option)
                }
            });
        }

        function create() {
            if (validate()) {
                document.getElementById('_title').innerText = "Please, fill all fields and submit...";
                document.getElementById('_title').style.color = "#949494";
                console.log("validated!")
                document.getElementById("create_form").submit();
            } else {
                document.getElementById('_title').innerText = "Some fields are empty!";
                document.getElementById('_title').style.color = "red";
                console.log("NOT validated!")
            }
        }

        function validate() {
            var isOk = true;
            var name = document.getElementById("_name").value;
            var password = document.getElementById("_password").value;
            var email = document.getElementById("_email").value;
            var login = document.getElementById("_login").value;
            var role = document.getElementById("_role").value;
            var country = document.getElementById("country_").value;
            var city = document.getElementById("city_").value;
            if (name || password || email || login || role || country || city || city === 'Please, select a city') {
                    isOk = false;
            }
            return isOk;
        }

    </script>
</head>

<body>
<c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>

<h1 id="_title" class="_title">Please, fill all fields and submit...</h1>
<div>
    <form id='create_form' action="${requestScope.path}/create" method='post'>
        E-mail:<br>
        <input type='text' id='_email' name='email' value=''><br>
        Name:<br>
        <input type='text' id='_name' name='name' value=''><br>
        Login:<br>
        <input type='text' id='_login' name='login' value=''><br>
        Password:<br>
        <input type='password' id='_password' name='password' value=''><br>
        Role:<br>
        <select id='_role' name="role">
            <c:forEach items="${rolesList}" var="roleFromList">
                <option value="${roleFromList.name}">${roleFromList.name}</option>
            </c:forEach>
        </select><br>
        Country:<br>
        <select id="country_" class="country_" name="country" onchange="country_change()">
            <c:forEach items="${countriesList}" var="countryFromList">
                <option value="${countryFromList.id}">${countryFromList.name}</option>
            </c:forEach>
        </select><br>
        City:<br>
        <select id = "city_" class="city_" name="city">
            <option value="">Please, select a city</option>
        </select><br>
        <br>
        <input type='button' value='Create' id="_create" onclick="create()">
    </form>

    <form action="${requestScope.path}/main" method='get'>
        <input type='submit' value='Cancel'>
    </form>
</div>
</body>