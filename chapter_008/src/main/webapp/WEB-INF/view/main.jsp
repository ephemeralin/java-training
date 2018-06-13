<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Todo list</title>

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
    <h1>Todo list</h1>
    <br>
    <form>
        <textarea id="todo" name="message" rows="10" cols="30"></textarea>
    </form>
    <input type='button' value='Add' id="add" onclick="add()">
    Show all: <input type="checkbox" onclick="showAll(this)" id="tShowAll" checked="checked"/>
    <br>
    <br>
    <table
            style='width:70%'>
        <thead>
            <th>Created</th>
            <th>Description</th>
            <th>Done</th>
        </thead>
        <tbody id="tbody">
            <c:forEach items="${itemsList}" var="item">
                <jsp:useBean id="dateValue" class="java.util.Date"/>
                <jsp:setProperty name="dateValue" property="time" value="${item.created}"/>
                <tr>
                    <td><fmt:formatDate value="${dateValue}" pattern="dd.MM.yyyy, HH:mm"/></td>
                    <td><c:out value="${item.description}"></c:out></td>
                    <td>
                        <input type="checkbox" onclick="changeStatus(this)" id="<c:out value="${item.id}"></c:out>"
                               <c:if test="${item.done == 'true'}">checked="checked"</c:if>
                        />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
