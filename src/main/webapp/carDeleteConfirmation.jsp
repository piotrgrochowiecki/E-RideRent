<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/8/2022
  Time: 12:55 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><s:message code="pages.carDeleteConfirmation.title"/> </title>
</head>
<body>
<s:message code="pages.carDeleteConfirmation.question"/> ${car.brand} ${car.model} <br>
<a href="/car/delete/${car.id}"><s:message code="pages.carDeleteConfirmation.yes"/></a> <br>
<a href="/car/findAll"><s:message code="pages.carDeleteConfirmation.no"/></a> <br>
</body>
</html>
