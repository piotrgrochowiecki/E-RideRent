<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/11/2022
  Time: 12:04 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <c:forEach items="${reservationList}" var="reservation">
        <tr>
            <td>${reservation.id}</td>
            <td>${reservation.startDate}</td>
            <td>${reservation.endDate}</td>
<%--            <td><a href="/reservation/deleteConfirmation/${reservation.id}"><s:message code="pages.reservationList.delete"/></a></td>--%>
<%--            <td><a href="/reservation/edit/${reservation.id}"><s:message code="pages.reservationList.edit"/></a></td>--%>
        </tr>
    </c:forEach>
    </thead>
</table>
</body>
</html>
