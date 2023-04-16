<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/8/2022
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="S" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><s:message code="pages.login.title"/></title>
</head>
<body>
<form method="post">
    <div><label> <s:message code="pages.login.login"/>: <input type="text" name="username"/> </label></div>
    <div><label> <s:message code="pages.login.password"/>: <input type="password" name="password"/> </label></div>
    <c:if test="${param.error == 'true'}">
        <div><s:message code="pages.login.authenticationFailure"/></div>
    </c:if>
    <div><input type="submit" value="<s:message code="pages.login.button"/>"/></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<a href="/registration"><s:message code="pages.home.registrationLink"/></a>
<a href="/"><s:message code="pages.afterRegistration.homePageLink"/></a>
</body>
</html>