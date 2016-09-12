<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Search Groups</title>
</head>
<body>
    <form action="FindGroupServ.jsp" method="POST">
        Name: <input name="name" type="text">
        <br>
        <input name="send" type="submit" value="Find">
    </form>
    <form action="Search.jsp" method="POST">
        <input name="back" type="submit" value="Back">
    </form>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Family Name</th>
            <th>Group</th>
        </tr>
        <c:forEach items="${groups}" var="group">
            <tr>
                <c:choose>
                    <c:when test="${group.students.size() > 0}">
                        <td rowspan="${group.students.size()}">${group.name}</td>
                    </c:when>
                    <c:when test="${group.students.size() == 0}">
                        <td>${group.name}</td>
                        <td> </td>
                        <td> </td>
                        </tr>
                    </c:when>
                </c:choose>
                <c:forEach items="${group.students}" var="student">
                    <td>${student.name}</td>
                    <td>${student.familyName}</td>
                    </tr>

                    <tr>
                </c:forEach>
        </c:forEach>
    </table>
</body>
</html>