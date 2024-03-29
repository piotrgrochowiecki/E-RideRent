<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>
        <s:message code="pages.carDeleteConfirmation.title"/>
    </title>
</head>
<body>
    <s:message code="pages.carDeleteConfirmation.question"/>
    ${carResponseDto.brand} ${carResponseDto.model}
    </br>
    <a href="/car/delete/${carResponseDto.id}">
        <s:message code="pages.carDeleteConfirmation.yes"/>
    </a>
    </br>
    <a href="/car/findAll">
        <s:message code="pages.carDeleteConfirmation.no"/>
    </a>
    </br>
</body>
</html>
