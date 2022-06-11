<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/11/2022
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="S" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><s:message code="pages.dashboard.title"/></title>
</head>
<body>
E-ride Rent
<a href="/reservation/chooseDates"><s:message code="pages.dashboard.newReservation"/></a><br>

<a href="/car/findAll"><s:message code="pages.links.allCars"/></a><br>

<a href="/user/findAll"><s:message code="pages.links.allUsers"/></a><br>

<a href="/logout"><s:message code="pages.links.logout"/></a><br>
</body>
</html>