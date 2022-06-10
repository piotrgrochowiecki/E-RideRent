<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/9/2022
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title><s:message code="pages.createReservation.title"/></title>
</head>
<body>
<form:form modelAttribute="reservation" action="/reservation/chooseCar">
    <s:message code="pages.createReservation.startDate"/>: <form:input path="startDate" type="date"/><br>
    <s:message code="pages.createReservation.endDate"/>: <form:input path="endDate" type="date"/>
    <form:hidden path="id"/>
</form:form>
</body>
</html>
