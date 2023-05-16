<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><s:message code="pages.userRegistration.pageTitle"/></title>
</head>
<body>
<form:form modelAttribute="user" method="post" action="/registration">
    <s:message code="pages.userRegistration.firstName"/>:
        <form:input path="firstName"/>
        </br>
        <form:errors path="firstName"/>
        </br>
    <s:message code="pages.userRegistration.lastName"/>:
        <form:input path="lastName"/>
        <br>
        <form:errors path="lastName"/>
        </br>
    <s:message code="pages.userRegistration.emailAddress"/>:
        <form:input path="email"/>
        </br>
        <form:errors path="email"/>
        </br>
    <s:message code="pages.userRegistration.drivingLicenseIssueDate"/>:
        <form:input path="drivingLicenseIssueDate"
                    type="date"
                    value="${dateString}"/>
        </br>
        <form:errors path="drivingLicenseIssueDate"/>
        </br>
    <s:message code="pages.userRegistration.password"/>:
    <form:password path="password"/>
    </br>
    <s:message code="pages.userRegistration.confirmPassword"/>:
        <form:password path="matchingPassword"/>
        </br>
        <form:errors path="matchingPassword"/>
        </br>
    <s:message code="pages.userRegistration.chooseRole"/>
        <select name="role">
            <option>USER</option>
            <option>ADMIN</option>
        </select>
    <form:button>
        <s:message code="pages.userRegistration.submitButton"/>
    </form:button>
</form:form>
</body>
</html>
