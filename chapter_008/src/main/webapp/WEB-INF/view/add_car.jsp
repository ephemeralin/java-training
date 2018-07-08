<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add car</title>
    <style>
        <%@ include file="css/mainStyle.css"%>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>

        function make_change() {
            var e = document.getElementById("make_");
            var make_id = e.options[e.selectedIndex].value;
            $.ajax({
                type: "POST",
                url: "models",
                data: "make_id="+make_id,
                cache: false,
                dataType:"json",
                success: function (data) {
                    console.log(data);
                    var selectedModel=$(".model_"), option="";
                    selectedModel.empty();
                    option = "<option value=''>Please, select a model</option>";
                    for (var i = 0; i != data.length; i++) {
                        option = option + "<option value='"+data[i].id + "'>"+data[i].name + "</option>";
                    }
                    selectedModel.append(option)
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
            var name = document.getElementById("name_").value;
            var make = document.getElementById("make_").value;
            var model = document.getElementById("model_").value;
            var engine = document.getElementById("engine_").value;
            var body = document.getElementById("body_").value;
            var transmission = document.getElementById("transmission_").value;


            if (name === '' || make === '' || model === '' || engine === '' ||
                body === '' || transmission === '' || model === 'Please, select a model') {
                    isOk = false;
            }
            return isOk;
        }

    </script>
</head>

<body>
<c:set var="path" value="${pageContext.servletContext.contextPath}" scope="request"></c:set>

<h1 id="_title" class="_title">Add car...</h1>
<div class="div1">

    <form id='create_form' action="${requestScope.path}/add_car" method="post" enctype="multipart/form-data">

        Make:
        <br>
        <c:choose>
            <c:when test="${car != null}">
                <select id="make_" class="make_" name="make" onchange="make_change()">
                    <c:forEach items="${makesList}" var="makeFromList">
                        <c:choose>
                            <c:when test="${makeFromList.id == car.make.id}">
                                <option value="${makeFromList.id}" selected>${makeFromList.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${makeFromList.id}">${makeFromList.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <select id="make_" class="make_" name="make" onchange="make_change()">
                    <c:forEach items="${makesList}" var="makeFromList">
                        <option value="${makeFromList.id}">${makeFromList.name}</option>
                    </c:forEach>
                </select>
            </c:otherwise>
        </c:choose>
        <br>

        Model:
        <br>
        <c:choose>
            <c:when test="${car != null}">
                <select id="model_" class="model_" name="model">
                    <c:forEach items="${modelsList}" var="modelFromList">
                        <c:choose>
                            <c:when test="${modelFromList.id == car.model.id}">
                                <option value="${modelFromList.id}" selected>${modelFromList.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${modelFromList.id}">${modelFromList.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <select id = "model_" class="model_" name="model">
                    <option value="">Please, select a model</option>
                </select>
            </c:otherwise>
        </c:choose>
        <br>

        Name:
        <br>
        <c:choose>
            <c:when test="${car != null}">
                <input type='text' id='name_' name='name' value="${car.name}">
            </c:when>
            <c:otherwise>
                <input type='text' id='name_' name='name' value=''>
            </c:otherwise>
        </c:choose>
        <br>

        Engine:
        <br>
        <c:choose>
            <c:when test="${car != null}">
                <select id="engine_" class="engine_" name="engine">
                    <c:forEach items="${enginesList}" var="engineFromList">
                        <c:choose>
                            <c:when test="${engineFromList.id == car.engine.id}">
                                <option value="${engineFromList.id}" selected>${engineFromList.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${engineFromList.id}">${engineFromList.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <select id="engine_" class="engine_" name="engine">
                    <c:forEach items="${enginesList}" var="engineFromList">
                        <option value="${engineFromList.id}">${engineFromList.name}</option>
                    </c:forEach>
                </select>
            </c:otherwise>
        </c:choose>
        <br>

        Body:
        <br>
        <c:choose>
            <c:when test="${car != null}">
                <select id="body_" class="body_" name="body">
                    <c:forEach items="${bodiesList}" var="bodyFromList">
                        <c:choose>
                            <c:when test="${bodyFromList.id == car.body.id}">
                                <option value="${bodyFromList.id}" selected>${bodyFromList.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${bodyFromList.id}">${bodyFromList.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <select id="body_" class="body_" name="body">
                    <c:forEach items="${bodiesList}" var="bodyFromList">
                        <option value="${bodyFromList.id}">${bodyFromList.name}</option>
                    </c:forEach>
                </select>
            </c:otherwise>
        </c:choose>
        <br>

        Transmission:
        <br>
        <c:choose>
            <c:when test="${car != null}">
                <select id="transmission_" class="transmission_" name="transmission">
                    <c:forEach items="${transmissionsList}" var="transmissionFromList">
                        <c:choose>
                            <c:when test="${transmissionFromList.id == car.transmission.id}">
                                <option value="${transmissionFromList.id}" selected>${transmissionFromList.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${transmissionFromList.id}">${transmissionFromList.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </c:when>
            <c:otherwise>
                <select id="transmission_" class="transmission_" name="transmission">
                    <c:forEach items="${transmissionsList}" var="transmissionFromList">
                        <option value="${transmissionFromList.id}">${transmissionFromList.name}</option>
                    </c:forEach>
                </select>
            </c:otherwise>
        </c:choose>
        <br>

        Sold:
        <br>
        <c:choose>
            <c:when test="${car != null}">
                <input type="checkbox" id="<c:out value="${car.id}"></c:out>" name="sold"
                       <c:if test="${car.sold == 'true'}">checked="checked"</c:if>/>
            </c:when>
            <c:otherwise>
                <input type="checkbox" id="_sold"/>
            </c:otherwise>
        </c:choose>
        <br>

        <c:choose>
            <c:when test="${car != null}">
                <input type='hidden' id='_id' name='carId' value="${car.id}" ><br>
            </c:when>
            <c:otherwise>
                <input type='hidden' id='_id' name='carId' value='' ><br>
            </c:otherwise>
        </c:choose>


        <input type="file" name="file" />
        <input type='hidden' id='_login_text' value="${login}" readonly><br>
        <input type="button" value="Create" onclick="create()"/>
    </form>

    <form action="${requestScope.path}/cars" method='get'>
        <input class="cancelButton" type='submit' value='Cancel'>
    </form>
</div>
</body>


