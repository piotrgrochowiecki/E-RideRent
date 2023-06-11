<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>
        <s:message code="pages.createReservation.successTitle"/>
    </title>
</head>
<body>
    <s:message code="pages.createReservation.successText"/>.<br>
    <a href="/dashboard">
        <s:message code="pages.link.backToDashboard"/>
    </a>
</body>
</html>
