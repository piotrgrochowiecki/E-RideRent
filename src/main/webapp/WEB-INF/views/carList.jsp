<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>
        <s:message code="pages.carList.title"/>
    </title>
</head>
<body>
    <table>
        <th>
            <c:forEach items="${carList}" var="car">
                <tr>
                    <td>${car.brand}</td>
                    <td>${car.model}</td>
                    <td>
                        <a href="/car/deleteConfirmation/${car.id}">
                            <s:message code="pages.carList.delete"/>
                        </a>
                    </td>
                    <td>
                        <a href="/car/edit/${car.id}">
                            <s:message code="pages.carList.edit"/>
                        </a>
                    </td>
                    <td>
                        <a href="/car/position/${car.id}">
                            <s:message code="pages.carList.position"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </th>
    </table>
    <form action="/car/addUUIDtoAllCars">
        <input type="submit" value="<s:message code="pages.carList.addUUIDtoAllCars"/>"/>
    </form>
    <a href="/dashboard">
        <s:message code="pages.link.backToDashboard"/>
    </a>
</body>
</html>
