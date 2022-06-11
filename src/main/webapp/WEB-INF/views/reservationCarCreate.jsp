<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/11/2022
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><s:message code="pages.createReservation.carTitle"/></title>
</head>
<body>
<form:form modelAttribute="reservation" method="post" action="/reservation/chooseCar">
    <s:message code="pages.createReservation.availableCars"/>: <form:select path="car.id" items="${availableCars}" itemValue="id" itemLabel="fullCarName"/><br>
    <form:hidden path="id"/>
    <form:hidden path="startDate"/>
    <form:hidden path="endDate"/>
    <form:hidden path="user"/>
    <form:button><s:message code="pages.createReservation.finishButton"/></form:button>
</form:form>
</body>
</html>
