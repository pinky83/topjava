<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2 align="center">Meal list</h2>

<table align="center">
        <c:forEach items="${requestScope.mealList}" var="item">
                <c:set var="cleanedDateTime" value="${fn:replace(item.getDateTime(), 'T', ' ')}" />
            <tr style="${item.isExceed()? 'color:red' : 'color:green'}">
                <td style="border: double">${cleanedDateTime}</td>
                <td style="border: double">${item.getDescription()}</td>
                <td style="border: double">${item.getCalories()}</td>
                <td style="border: double"><a href="users">Edit</a></td>
                <td style="border: double"><a href="meal?action=delete&id=${item.getId()}">Delete</a></td>
            </tr>
        </c:forEach>
            <tr>
                <td>
                    <a href="users">Add meal</a>
                </td>
            </tr>
</table>

</body>
</html>
