<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>
        <s:message code="pages.createReservation.title"/>
    </title>
</head>
<body>
<form:form modelAttribute="reservation"
           method="post">
    <s:message code="pages.createReservation.startDate"/>
        <form:input path="startDate"
                    type="date"
                    value="${startDate}"/>
        <form:errors path="startDate"/>
        <br>
    <s:message code="pages.createReservation.endDate"/>
    <form:input path="endDate"
                type="date"
                value="${endDate}"/>
    <form:errors path="endDate"/>
    <br>
    <form:button>
        <s:message code="pages.createReservation.datesButton"/>
    </form:button>
</form:form>
</body>
</html>
