<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>
        <s:message code="pages.carDeletionEx.title"/>
    </title>
</head>
<body>
    <s:message code="pages.carDeletionEx.text"/>
    <br>
    <a href="/dashboard">
        <s:message code="pages.link.backToDashboard"/>
    </a>
    <br>
    <a href="/car/findAll">
        <s:message code="pages.links.allCars"/>
    </a>
    <br>
</body>
</html>