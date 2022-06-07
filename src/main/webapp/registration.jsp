<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/6/2022
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><s:message code="pages.userRegistration.pageTitle"/></title>
</head>
<body>
<fmt:formatDate value="${user.drivingLicenseIssueDate}" var="dateString" pattern="yyyy-MM-dd"/>
<form:form modelAttribute="user" method="post" action="/registration">
    <s:message code="pages.userRegistration.firstName"/>: <form:input path="firstName"/> <form:errors path="firstName"/><br>
    <s:message code="pages.userRegistration.lastName"/>: <form:input path="lastName"/> <form:errors path="lastName"/> <br>
    <s:message code="pages.userRegistration.emailAddress"/>: <form:input path="email"/><br>
    <s:message code="pages.userRegistration.drivingLicenseIssueDate"/>: <form:input path="drivingLicenseIssueDate" type="date" value="${dateString}"/> <form:errors path="drivingLicenseIssueDate"/><br>
    <s:message code="pages.userRegistration.password"/>: <form:password path="password"/><br>
    <s:message code="pages.userRegistration.confirmPassword"/>: <form:password path="matchingPassword"/> <br>
    <s:message code="pages.userRegistration.role"/>:
    <s:message code="pages.userRegistration.customer"/> <form:radiobutton path="role" value="user"/>
    <s:message code="pages.userRegistration.admin"/> <form:radiobutton path="role" value="admin"/>
    <input type="submit" name="<s:message code="pages.userRegistration.submitButton"/>"/>
</form:form>
</body>
</html>
