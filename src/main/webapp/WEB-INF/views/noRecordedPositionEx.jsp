<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 7/3/2022
  Time: 8:18 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><s:message code="pages.noRecordedPosition.title"/></title>
</head>
<body>
<s:message code="pages.noRecordedPosition.text"/><br>
<a href="/dashboard"><s:message code="pages.link.backToDashboard"/></a><br>
<a href="/car/findAll"><s:message code="pages.links.allCars"/></a><br>
</body>
</html>
