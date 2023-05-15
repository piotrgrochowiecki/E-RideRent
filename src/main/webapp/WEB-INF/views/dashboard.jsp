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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title><s:message code="pages.dashboard.title"/></title>
</head>
<body>
E-ride Rent<br>
<a href="/reservation/chooseDates"><s:message code="pages.dashboard.newReservation"/></a><br>

<sec:authorize access="hasAuthority('ADMIN')">
    <a href="/car/findAll"><s:message code="pages.links.allCars"/></a><br>

    <a href="/car/add"><s:message code="pages.car.addCar"/></a><br>

    <a href="/user/findAll"><s:message code="pages.links.allUsers"/></a><br>
</sec:authorize>

<a href="/logout"><s:message code="pages.links.logout"/></a><br>
</body>
</html>
