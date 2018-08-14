<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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
                url: "models.do",
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
            // if (validate()) {
            //     document.getElementById('_title').innerText = "Please, fill all fields and submit...";
            //     document.getElementById('_title').style.color = "#949494";
            //     console.log("validated!")
                document.getElementById("create_form").submit();
            // } else {
            //     document.getElementById('_title').innerText = "Some fields are empty!";
            //     document.getElementById('_title').style.color = "red";
            //     console.log("NOT validated!")
            // }
        }

        function validate() {
            var isOk = true;
            var name = document.getElementById("name_").value;
            var make = document.getElementById("make_").value;
            var model = document.getElementById("model_").value;
            var engine = document.getElementById("engine_").value;
            var body = document.getElementById("body_").value;
            var transmission = document.getElementById("transmission_").value;

            if(!name || !make || !model || !engine || !body || !transmission || model === 'Please, select a model') {
                isOk = false;
            }
            return isOk;
        }

    </script>
</head>

<body>
<h1 id="_title" class="_title">Add car...</h1>
<div class="div1">

    <form:form id='create_form' action="add_car.do" method="post" enctype="multipart/form-data" modelAttribute="car">
        Make:
        <br>
        <form:select id="make_" class="make_" path="make" onchange="make_change()">
            <c:choose>
                <c:when test="${car.id != ''}">
                    <c:forEach items="${makesList}" var="makeFromList">
                        <c:choose>
                            <c:when test="${makeFromList.id == car.make.id}">
                                <form:option value='${makeFromList.id}' label="${makeFromList.name}" selected='true'> </form:option>
                            </c:when>
                            <c:otherwise>
                                <form:option value='${makeFromList.id}' label="${makeFromList.name}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${makesList}" var="makeFromList">
                        <form:option value='${makeFromList.id}' label="${makeFromList.name}"/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </form:select>
        <br>

        Model:
        <br>
        <form:select id="model_" class="model_" path="model">
            <c:choose>
                <c:when test="${car.id != ''}">
                    <c:forEach items="${modelsList}" var="modelFromList">
                        <c:choose>
                            <c:when test="${modelFromList.id == car.model.id}">
                                <form:option value='${modelFromList.id}' label="${modelFromList.name}" selected='true'> </form:option>
                            </c:when>
                            <c:otherwise>
                                <form:option value='${modelFromList.id}' label="${modelFromList.name}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${modelsList}" var="modelFromList">
                        <form:option value='${modelFromList.id}' label="${modelFromList.name}"/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </form:select>
        <br>

        Name:
        <br>
        <c:choose>
            <c:when test="${car.id != ''}">
                <input type='text' id='name_' name='name' value="${car.name}">
            </c:when>
            <c:otherwise>
                <input type='text' id='name_' name='name' value=''>
            </c:otherwise>
        </c:choose>
        <br>

        Engine:
        <br>
        <form:select id="engine_" class="engine_" path="engine">
            <c:choose>
                <c:when test="${car.id != ''}">
                    <c:forEach items="${enginesList}" var="engineFromList">
                        <c:choose>
                            <c:when test="${engineFromList.id == car.engine.id}">
                                <form:option value='${engineFromList.id}' label="${engineFromList.name}" selected='true'> </form:option>
                            </c:when>
                            <c:otherwise>
                                <form:option value='${engineFromList.id}' label="${engineFromList.name}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${enginesList}" var="engineFromList">
                        <form:option value='${engineFromList.id}' label="${engineFromList.name}"/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </form:select>
        <br>

        Body:
        <br>
        <form:select id="body_" class="body_" path="body">
            <c:choose>
                <c:when test="${car.id != ''}">
                    <c:forEach items="${bodiesList}" var="bodyFromList">
                        <c:choose>
                            <c:when test="${bodyFromList.id == car.body.id}">
                                <form:option value='${bodyFromList.id}' label="${bodyFromList.name}" selected='true'> </form:option>
                            </c:when>
                            <c:otherwise>
                                <form:option value='${bodyFromList.id}' label="${bodyFromList.name}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${bodiesList}" var="bodyFromList">
                        <form:option value='${bodyFromList.id}' label="${bodyFromList.name}"/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </form:select>
        <br>

        Transmission:
        <br>
        <form:select id="transmission_" class="transmission_" path="transmission">
            <c:choose>
                <c:when test="${car.id != ''}">
                    <c:forEach items="${transmissionsList}" var="transmissionFromList">
                        <c:choose>
                            <c:when test="${transmissionFromList.id == car.transmission.id}">
                                <form:option value='${transmissionFromList.id}' label="${transmissionFromList.name}" selected='true'> </form:option>
                            </c:when>
                            <c:otherwise>
                                <form:option value='${transmissionFromList.id}' label="${transmissionFromList.name}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${transmissionsList}" var="transmissionFromList">
                        <form:option value='${transmissionFromList.id}' label="${transmissionFromList.name}"/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </form:select>
        <br>

        Sold:
        <br>
        <c:choose>
            <c:when test="${car.id != ''}">
                <input type="checkbox" id="<c:out value="${car.id}"></c:out>" name="sold"
                       <c:if test="${car.sold == 'true'}">checked="checked"</c:if>/>
            </c:when>
            <c:otherwise>
                <input type="checkbox" id="_sold" name="sold"/>
            </c:otherwise>
        </c:choose>
        <br>

        <c:choose>
            <c:when test="${car.id != ''}">
                <input type='hidden' id='_id' name='carId' value="${car.id}" ><br>
            </c:when>
            <c:otherwise>
                <input type='hidden' id='_id' name='carId' value='' ><br>
            </c:otherwise>
        </c:choose>

        <input type="file" name="file"/>
        <%--<input type='hidden' id='_login_text' value="${login}" readonly><br>--%>

        <input type="button" value="Create" onclick="create()"/>
    </form:form>

    <form action="cars.do" method='get'>
        <input class="cancelButton" type='submit' value='Cancel'>
    </form>
</div>
</body>


