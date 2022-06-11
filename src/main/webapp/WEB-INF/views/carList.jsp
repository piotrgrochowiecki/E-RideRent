<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/8/2022
  Time: 1:02 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><s:message code="pages.carList.title"/></title>
</head>
<body>
<table>
    <thead>
    <c:forEach items="${carList}" var="car">
        <tr>
            <td>${car.brand}</td>
            <td>${car.model}</td>
            <td><a href="/car/deleteConfirmation/${car.id}"><s:message code="pages.carList.delete"/></a></td>
            <td><a href="/car/edit/${car.id}"><s:message code="pages.carList.edit"/></a></td>
        </tr>
    </c:forEach>
    </thead>
</table>
<a href="/dashboard"><s:message code="pages.link.backToDashboard"/></a>
</body>
</html>
