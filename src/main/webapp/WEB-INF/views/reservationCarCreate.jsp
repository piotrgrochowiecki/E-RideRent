<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>
        <s:message code="pages.createReservation.carTitle"/>
    </title>
</head>
<body>
<form:form modelAttribute="reservation"
           method="post"
           action="/reservation/chooseCar">
    <s:message code="pages.createReservation.availableCars"/>
        <form:select path="carResponseDto.id"
                        itemValue="id"
                     items="${availableCars}"
                     itemLabel="fullCarName"/>
        <br>
        <form:hidden path="startDate"/>
        <form:hidden path="endDate"/>
    <form:button>
        <s:message code="pages.createReservation.finishButton"/>
    </form:button>
</form:form>
</body>
</html>
