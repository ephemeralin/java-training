<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit user</title>
    <style>
        <%@ include file="css/mainStyle.css"%>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        function country_change() {
            var e = document.getElementById("country_");
            var country_id = e.options[e.selectedIndex].value;
            var currentCityId = '${cityId}';
            console.log(country_id);
            $.ajax({
                type: "POST",
                url: "cities",
                data: "country_id="+country_id,
                cache: false,
                dataType:"json",
                success: function (data) {
                    var selectedCity=$(".city_"), option="";
                    selectedCity.empty();
                    option = "<option value=''>Please, select a city</option>";
                    for (var i = 0; i != data.length; i++) {
                        if(data[i].id == currentCityId) {
                            option = option + "<option value='"+data[i].id + "' selected>"+data[i].name + "</option>";
                        } else {
                            option = option + "<option value='"+data[i].id + "'>"+data[i].name + "</option>";
                        }
                    }
                    selectedCity.append(option)
                }
            });
        }

        function update() {
            if (validate()) {
                document.getElementById('_title').innerText = "Please, fill all fields and submit...";
                document.getElementById('_title').style.color = "#949494";
                console.log("validated!")
                document.getElementById("update_form").submit();
            } else {
                document.getElementById('_title').innerText = "Some fields are empty!";
                document.getElementById('_title').style.color = "red";
                console.log("NOT validated!")
            }
        }

        function validate() {
            var isOk = true;
            var name = document.getElementById("_name").value;
            var email = document.getElementById("_email").value;
            var login = document.getElementById("_login").value;
            var role = document.getElementById("_role").value;
            var country = document.getElementById("country_").value;
            var city = document.getElementById("city_").value;
            if (name == ''  || email == '' || login == '' || role == '' ||
                country == '' || city == '' || city == 'Please, select a city') {
                isOk = false;
            }
            return isOk;
        }

    </script>
</head>

<body>
    <c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>
    <h1 id="_title" class="_title">Please, edit fields and submit...</h1>
    <div>
        <form id="update_form" action="${requestScope.path}/update" method='post'>
            E-mail<br>
            <input type='text' id='_email' name='email' value="${email}" readonly><br>
            Name<br>
            <input type='text' id='_name' name='name' value="${name}"><br>
            Login<br>
            <input type='text' id='_login' name='login' value="${login}"><br>
            Password<br>
            <input type='password' name='password' value="${password}"><br>
            Role<br>
            <c:choose>
                <c:when test="${currentRole.name == 'admin'}">
                    <select id='_role' name="role">

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
            Country:<br>
            <select id="country_" class="country_" name="country" onchange="country_change()" onload="country_change()" )>
                <c:forEach items="${countriesList}" var="countryFromList">
                    <c:choose>
                        <c:when test="${countryFromList.name == country}">
                            <option value="${countryFromList.id}" selected>${countryFromList.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${countryFromList.id}">${countryFromList.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select><br>
            City:<br>
            <select id = "city_" class="city_" name="city">
                <option value="">Please, select a city</option>
            </select><br>
            <script type="text/javascript">
                country_change();
            </script>
            <br>
            <input type='button' value='Update' id="_update" onclick="update()">
        </form>

        <form action="${requestScope.path}/main" method = 'get'>
            <input type='submit' value='Cancel'>
        </form>
    </div>

</body>
</html>