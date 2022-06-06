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
<html>
<head>
    <title>Registration</title>
</head>
<body>
<fmt:formatDate value="${customer.drivingLicenseIssueDate}" var="dateString" pattern="yyyy-MM-dd"/>
<form:form modelAttribute="customer" method="post" action="/registration">
    First name: <form:input path="firstName"/> <form:errors path="firstName"/><br>
    Last name: <form:input path="lastName"/> <form:errors path="lastName"/> <br>
    E-mail address: <form:input path="email"/><br>
    Driving license issue date: <form:input path="drivingLicenseIssueDate" type="date" value="${dateString}"/> <form:errors path="drivingLicenseIssueDate"/><br>
    Password: <form:password path="password"/><br>
    Confirm password: <form:password path="matchingPassword"/> <br>
    Role:
    Customer <form:radiobutton path="role" value="customer"/>
    Admin <form:radiobutton path="role" value="admin"/>
    <input type="submit">
</form:form>
</body>
</html>
