<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/11/2022
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><s:message code="pages.createReservation.successTitle"/></title>
</head>
<body>
    <s:message code="pages.createReservation.successText"/>.<br>
    <a href="/dashboard"><s:message code="pages.link.backToDashboard"/></a>
</body>
</html>
