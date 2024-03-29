<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>
        <s:message code="pages.createCarForm.title"/>
    </title>
</head>
<body>
    <form:form modelAttribute="carCreateRequestDto"
               method="post">
        <s:message code="pages.createCarForm.brand"/>
        <form:input path="brand"/>
        <form:errors path="brand"/>
        </br>
        <s:message code="pages.createCarForm.model"/>
        <form:input path="model"/>
        <form:errors path="model"/>
        </br>
        <s:message code="pages.createCarForm.accelerationSec"/>
        <form:input path="accelerationSec"
                    type="double"/>
        <form:errors path="accelerationSec"/>
        </br>
        <s:message code="pages.createCarForm.topSpeedKmh"/>
        <form:input path="topSpeedKmh"
                    type="number"/>
        <form:errors path="topSpeedKmh"/>
        </br>
        <s:message code="pages.createCarForm.rangeKm"/>
        <form:input path="rangeKm"
                    type="number"/>
        <form:errors path="rangeKm"/>
        </br>
        <s:message code="pages.createCarForm.fastChargeKmh"/>
        <form:input path="fastChargeKmh"
                    type="number"/>
        <form:errors path="fastChargeKmh"/>
        </br>
        <s:message code="pages.createCarForm.powerTrain"/>
        <select name="powerTrain">
            <option>
                AWD
            </option>
            <option>
                FWD
            </option>
            <option>
                RWD
            </option>
        </select>
        </br>
        <form:button>
            <s:message code="pages.createCarForm.submit"/>
        </form:button>
    </form:form>
</body>
</html>
