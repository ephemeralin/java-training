<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

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
            "<img src='data:image/gif;base64,"+photo+"'"+
            " height='100' alt='Cars photo'/>" +
            "</div>"
        }
        console.log(str);
        return str;
    }

    function showEditDeleteButtons(car, curLogin, curRole) {
        var pathUpdate = "'<%=pageContext.getServletContext().getContextPath()%>" + "/update_car'";
        var pathDelete = "'<%=pageContext.getServletContext().getContextPath()%>" + "/delete_car'";
        var str = "";
        if (curLogin === car.owner.login || curRole === "admin") {
            str ="<td>" +
                    "<form action="+ pathUpdate +" method='get'><input type='submit' value='Edit'>" +
                        "<input type='hidden' name='id' value='" +car.id+ "'>" +
                    "</form>" +
                "</td>" +
                "<td>" +
                    "<form action="+ pathDelete +" method='get'><input type='submit' value='Delete'>" +
                        "<input type='hidden' name='id' value='" +car.id+ "'>" +
                    "</form>" +
                "</td>";
        } else {
            str = "<td></td><td></td>";
        }
        return str;
    }

    function filterData(element) {
        var dataObject = {
            "filterName": element.options[element.selectedIndex].text
        };
        $.ajax({
            type: "POST",
            url: "filtercars",
            data: dataObject,
            cache: false,
            dataType:"json",
            success: function (data) {
                $('#tbody').empty();
                var options = {year: 'numeric', month: 'numeric', day: 'numeric', hour: 'numeric', minute: 'numeric' };
                for (var i = 0; i != data.length; i++) {
                    var Html =
                        "<tr>" +
                        "<td>"+new Date(data[i].date).toLocaleDateString("ru-RU", options)+"</td>" +
                        "<td>"+data[i].name+"</td>" +
                        "<td>"+data[i].make.name+"</td>" +
                        "<td>"+data[i].model.name+"</td>" +
                        "<td>"+data[i].body.name+"</td>" +
                        "<td>"+data[i].engine.name+"</td>" +
                        "<td>"+data[i].transmission.name+"</td>" +
                        "<td>" + showPhoto(data[i].base64imageFile) + "</td>" +
                        "<td>"+data[i].owner.login+"</td>" +
                        "<td><input type='checkbox' id="+data[i].id+" onclick='return false;'" +
                        isChecked(data[i].sold) +
                        "</td>" +showEditDeleteButtons(data[i], "${login}", "${role}") +
                        "</tr>";
                    $('#tbody').append(Html);
                }
            }
        });
    }


</script>

<body>
<c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>
<h1>Car Place</h1>
<table class="tlogin">
    <tr>
        <td>
            <form action='${requestScope.path}/login' method='post'><input type='submit'value='Logoff' style="width: auto">
                <input type='hidden' name='logoff' value="logoff">
            </form>
        </td>
        <td>
            Login: <c:out value="${login}"></c:out><br>
            Role: <c:out value="${role.name}"></c:out>
        </td>
        <td>
            Filter:
            <br>
                <select name="filter" class="tfilter" onchange="filterData(this)">
                    <option value="All">All</option>
                    <option value="OnlyWithPhoto">Only with photo</option>
                    <option value="OnlyToday">Only today</option>
                </select>
            <br>
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

            <c:choose>
                <c:when test="${(car.owner.login == login) || role == 'admin'}">
                    <td><form action='${requestScope.path}/update_car' method='get'><input type='submit' value='Edit'>
                        <input type='hidden' name='id' value="${car.id}">
                    </form>
                    </td>
                    <td><form action='${requestScope.path}/delete_car' method='post'><input type='submit' value='Delete'>
                        <input type='hidden' name='id' value="${car.id}">
                    </form>
                    </td>
                </c:when>
                <c:otherwise>
                    <td></td>
                    <td></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<form action='${requestScope.path}/add_car' method='get'>
    <input type='hidden' name='filterName' value="All">
    <input type='submit' value='Add car' style="width: auto">
</form>
</body>
</html>
