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
    <script>
        function add() {
            var dataObject = {
                "description": $('#todo').val()
            };
            var data = JSON.stringify(dataObject);
            $.ajax({
                type: "POST",
                url: "create",
                data: data,
                cache: false,
                dataType:"json",
                success: function (data) {
                    var options = {year: 'numeric', month: 'numeric', day: 'numeric', hour: 'numeric', minute: 'numeric' };
                    console.log(data);
                    for (var i = 0; i != data.length; i++) {
                        var Html =
                            "<tr>" +
                                "<td>"+new Date(data[i].created).toLocaleDateString("ru-RU", options)+"</td>" +
                                "<td>"+data[i].description+"</td>" +
                                "<td><input type='checkbox' onclick='changeStatus(this)' id= " + data[i].id +"/></td>" +
                            "</tr>";
                        $('#tbody').append(Html);
                    }
                    $('#todo').val("");
                }
            });
        }

        function changeStatus(done_element) {
            console.log(done_element);
            console.log(done_element.id);
            console.log(done_element.checked);
            var dataObject = {
                "description" : "",
                "id" : done_element.id,
                "done" : done_element.checked
            };
            var data = JSON.stringify(dataObject);
            $.ajax({
                type: "POST",
                url: "update",
                data: data,
                cache: false,
                dataType:"json",
                success: function (data) {
                    console.log("change status set " + done_element.checked);
                    console.log("change status return " + data);
                    if (data != done_element.checked) {
                        console.log("change status return <> set status!");
                    }
                }
            });
        }

        function showAll(element) {
            var dataObject = {
                "showAll": element.checked
            };
            $.ajax({
                type: "POST",
                url: "showAll",
                data: dataObject,
                cache: false,
                dataType:"json",
                success: function (data) {
                    $('#tbody').empty();
                    var options = {year: 'numeric', month: 'numeric', day: 'numeric', hour: 'numeric', minute: 'numeric' };
                    var done;
                    for (var i = 0; i != data.length; i++) {
                        done = (data[i].done == true ? " checked = 'checked'" : "");
                        var Html =
                            "<tr>" +
                            "<td>"+new Date(data[i].created).toLocaleDateString("ru-RU", options)+"</td>" +
                            "<td>"+data[i].description+"</td>" +
                            "<td><input type='checkbox' onclick='changeStatus(this) id = '" + data[i].id + "'"+done+"/></td>" +
                            "</tr>";
                        $('#tbody').append(Html);
                    }
                }
            });
        }
    </script>
</head>

<body>
    <c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>
    <h1>Car Place</h1>
    <p>
        Login: <c:out value="${login}"></c:out><br>
        Role: <c:out value="${role.name}"></c:out>
    </p>
    <form action='${requestScope.path}/login' method='post'><input type='submit'value='Logoff' style="width: auto">
        <input type='hidden' name='logoff' value="logoff">
    </form>
    <%--Show all: <input type="checkbox" onclick="showAll(this)" id="tShowAll" checked="checked"/>--%>
    <table
            style='width:70%'>
        <thead>
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
                <tr>
                    <td><c:out value="${car.name}"></c:out></td>
                    <td><c:out value="${car.make}"></c:out></td>
                    <td><c:out value="${car.model}"></c:out></td>
                    <td><c:out value="${car.body}"></c:out></td>
                    <td><c:out value="${car.engine}"></c:out></td>
                    <td><c:out value="${car.transmission}"></c:out></td>
                    <td>
                        <div class="zoom">
                            <img src="data:image/gif;base64,<c:out value="${car.base64imageFile}"/>"
                                 height="100" alt="Cars photo"/>
                        </div>
                    </td>

                    <td><c:out value="${car.owner}"></c:out></td>

                    <td>
                        <input type="checkbox" id="<c:out value="${car.id}"></c:out>" onclick="return false;"
                           <c:if test="${car.sold == 'true'}">checked="checked"</c:if>/>
                    </td>

                    <c:choose>
                        <c:when test="${(car.owner.login == login) || login == 'admin'}">
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
        <input type='submit' value='Add car' style="width: auto">
    </form>
</body>
</html>
