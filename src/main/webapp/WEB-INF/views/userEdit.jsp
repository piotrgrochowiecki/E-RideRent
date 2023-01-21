<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/8/2022
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><s:message code="pages.editUser.title"/> ${user.firstName} ${user.lastName}</title>
</head>
<body>
<form:form modelAttribute="user" action="/user/editConfirmation">
    <s:message code="pages.userRegistration.firstName"/>: <form:input path="firstName"/> <form:errors path="firstName"/><br>
    <s:message code="pages.userRegistration.lastName"/>: <form:input path="lastName"/> <form:errors path="lastName"/><br>
    <s:message code="pages.userRegistration.emailAddress"/>: <form:input path="email"/> <form:errors path="email"/><br>
    <s:message code="pages.userRegistration.drivingLicenseIssueDate"/>: <form:input path="drivingLicenseIssueDate" type="date"/> <form:errors path="drivingLicenseIssueDate"/><br>
    <a href="/user/${user.id}/changePassword">Change password</a>
    <form:hidden path="id"/>
    <form:hidden path="password"/>
    <form:hidden path="reservationList"/>
    <form:hidden path="reviewList"/>
    <form:hidden path="roleList"/>
    <form:button><s:message code="pages.editUser.submit"/></form:button>
</form:form>
</body>
</html>
