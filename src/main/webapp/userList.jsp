<%--
  Created by IntelliJ IDEA Ultimate.
  User: piotr
  Date: 6/8/2022
  Time: 1:02 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><s:message code="pages.userList.title"/></title>
</head>
<body>
<table>
    <thead>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td><a href="/user/deleteConfirmation/${user.id}"><s:message code="pages.userList.delete"/></a></td>
            <td><a href="/user/edit/${user.id}"><s:message code="pages.userList.edit"/></a></td>
        </tr>
    </c:forEach>
    </thead>
</table>
</body>
</html>
