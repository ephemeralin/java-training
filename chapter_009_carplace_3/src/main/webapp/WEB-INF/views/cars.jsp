<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <title>Car place</title>
    <style>
        <%@ include file="css/mainStyle.css"%>
        body {
            text-align: left;
        }

        h1 {
            text-align: center;
        }
    </style>

    <security:csrfMetaTags/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>

        function isChecked(flag) {
            if (flag) {
                return "checked = 'checked'";
            } else return "";
        }

        function showPhoto(photo) {
            var str = "No photo"
            if (photo != null && photo !== "") {
                str =
                    "<div class='zoom'>" +
                    "<img src='data:image/gif;base64," + photo + "'" +
                    " height='100' alt='Cars photo'/>" +
                    "</div>"
            }
            return str;
        }

        function showEditDeleteButtons(car, login, isAdmin) {
            var str = "";
            if (login === car.owner.username || isAdmin == 'true') {
                str = "<td>" + 
                    "<form action='update_car.do' method='get'>" +
                    "<input type='submit' value='Edit'>" +
                    "<input type='hidden' name='car_id' value='" + car.id + "'>" +
                    "</form>" +
                    "</td>" +
                    "<td>" +
                    "<form action='delete_car.do' method='post'>" +
                    "<input type='submit' value='Delete'>" +
                    "<input type='hidden' name='car_id' value='" + car.id + "'>" +
                    "</form>" +
                    "</td>";
            } else {
                str = "<td></td><td></td>";
            }
            return str;
        }

        function filterData() {
            var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
            var csrfToken = $("meta[name='_csrf']").attr("content");
            var csrfHeader = $("meta[name='_csrf_header']").attr("content");

            var e = document.getElementById("tfilter");
            var data = {};
            var headers = {};
            data[csrfParameter] = csrfToken;
            data["filter_name"] = e.options[e.selectedIndex].text;
            headers[csrfHeader] = csrfToken;

            var login = document.getElementById("loginValue").innerText;
            var isAdmin = document.getElementById("isAdminValue").innerText;

            $.ajax({
                type: "POST",
                url: "filterCars.do",
                headers: headers,
                data: data,
                cache: false,
                dataType: "json",
                success: function (data) {
                    $('#tbody').empty();
                    var options = {
                        year: 'numeric',
                        month: 'numeric',
                        day: 'numeric',
                        hour: 'numeric',
                        minute: 'numeric'
                    };
                    for (var i = 0; i != data.length; i++) {
                        var Html =
                            "<tr>" +
                            "<td>" + new Date(data[i].date).toLocaleDateString("ru-RU", options) + "</td>" +
                            "<td>" + data[i].name + "</td>" +
                            "<td>" + data[i].make.name + "</td>" +
                            "<td>" + data[i].model.name + "</td>" +
                            "<td>" + data[i].body.name + "</td>" +
                            "<td>" + data[i].engine.name + "</td>" +
                            "<td>" + data[i].transmission.name + "</td>" +
                            "<td>" + showPhoto(data[i].base64imageFile) + "</td>" +
                            "<td>" + data[i].owner.username + "</td>" +
                            "<td><input type='checkbox' id=" + data[i].id + " onclick='return false;'" +
                            isChecked(data[i].sold) +
                            "</td>" + showEditDeleteButtons(data[i], login, isAdmin) +
                            "</tr>";
                        $('#tbody').append(Html);
                    }
                }
            });
        }

    </script>
</head>

<body>

<p id="loginValue" hidden>${loginValue}</p>
<p id="isAdminValue" hidden>${isAdminValue}</p>

<h1>Car Place</h1>
<table class="tlogin">
    <tr>
        <td>
            Filter:
            <br>
            <form:select id='tfilter' class='tfilter' path='filter' onchange="filterData()">
                <form:option value="All">All</form:option>
                <form:option value="OnlyWithPhoto">Only with photo</form:option>
                <form:option value="OnlyToday">Only today</form:option>
            </form:select>
        </td>
        <td>
        </td>
        <td>
            User: <security:authentication property="principal.username"/>
            <br>
            Role(s): <security:authentication property="principal.authorities"/>
        </td>
    </tr>
</table>
<br>

<table>
    <thead>
    <th>Date</th>
    <th>Name</th>
    <th>Make</th>
    <th>Model</th>
    <th>Body</th>
    <th>Engine</th>
    <th>Transmission</th>
    <th>Photo</th>
    <th>Seller</th>
    <th>Sold out</th>
    <th>Edit</th>
    <th>Delete</th>
    </thead>
    <tbody id="tbody">
    <c:forEach items="${carsList}" var="car">
        <jsp:useBean id="dateValue" class="java.util.Date"/>
        <jsp:setProperty name="dateValue" property="time" value="${car.date}"/>
        <tr>
            <td><fmt:formatDate value="${dateValue}" pattern="dd.MM.yyyy, HH:mm"/></td>
            <td><c:out value="${car.name}"></c:out></td>
            <td><c:out value="${car.make}"></c:out></td>
            <td><c:out value="${car.model}"></c:out></td>
            <td><c:out value="${car.body}"></c:out></td>
            <td><c:out value="${car.engine}"></c:out></td>
            <td><c:out value="${car.transmission}"></c:out></td>
            <c:choose>
                <c:when test="${(car.base64imageFile != null) && car.base64imageFile != ''}">
                    <td>
                        <div class="zoom">
                            <img src="data:image/gif;base64,<c:out value="${car.base64imageFile}"/>"
                                 height="100" alt="Cars photo"/>
                        </div>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>No photo</td>
                </c:otherwise>
            </c:choose>

            <td><c:out value="${car.owner}"></c:out></td>

            <td>
                <input type="checkbox" id="<c:out value="${car.id}"></c:out>" onclick="return false;"
                       <c:if test="${car.sold == 'true'}">checked="checked"</c:if>/>
            </td>

            <td>
                <c:choose>
                    <c:when test="${(loginValue == car.owner.username) || isAdminValue == 'true'}">
                        <form:form action='${pageContext.request.contextPath}/update_car.do' method='get'>
                            <input type='submit' value='Edit'>
                            <input type='hidden' name='car_id' value='${car.id}'>
                        </form:form>
                    </c:when>
                </c:choose>
            </td>

            <td>
                <c:choose>
                    <c:when test="${(loginValue == car.owner.username) || isAdminValue == 'true'}">
                        <form:form action='${pageContext.request.contextPath}/delete_car.do' method='post'>
                            <input type='submit' value='Delete'>
                            <input type='hidden' name='car_id' value="${car.id}">
                        </form:form>
                    </c:when>
                </c:choose>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<table class="tlogin">
    <tr>
        <td>
            <form:form action='${pageContext.request.contextPath}/add_car.do' method='get'>
                <input type='hidden' name='filter_name' value="All">
                <input type='submit' value='Add car' style="width: auto">
            </form:form>
        </td>
        <td style="text-align: right">
            <form:form action="${pageContext.request.contextPath}/logout" method="post">
                <input class="cancelButton" type="submit" value="Logout" style="width: auto"/>
            </form:form>
        </td>
    </tr>
</table>

</body>
</html>
