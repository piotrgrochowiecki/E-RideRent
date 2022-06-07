<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/6/2022
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><s:message code="pages.afterRegistration.title"/></title>
</head>
<body>
<s:message code="pages.afterRegistration.text"/><br>
<a href="/login"><s:message code="pages.home.loginLink"/></a><br>
<a href="/"><s:message code="pages.afterRegistration.homePageLink"/></a><br>
</body>
</html>
