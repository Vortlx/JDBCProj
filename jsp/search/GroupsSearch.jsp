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
    <form action="FindGroupServ" method="POST">
        Name: <input name="name" type="text">
        <br>
        <input name="send" type="submit" value="Find">
    </form>
    <form action="Search.jsp" method="POST">
        <input name="back" type="submit" value="Back">
    </form>
    <table border="1">
        <tr>
            <th>Group</th>
            <th>Name</th>
            <th>Family Name</th>
        </tr>
        <c:forEach items="${groups}" var="group">
            <tr>
                <c:choose>
                    <c:when test="${group.students.size() > 0}">
                        <td rowspan="${group.students.size()}">
                            <a name="toTeachers" href="./TeachersByGroupServ?groupName=${group.name}">${group.name}</a>
                        </td>
                    </c:when>
                    <c:when test="${group.students.size() == 0}">
                        <td>
                            <a name="toTeachers" href="./TeachersByGroupServ.jsp?groupName=${group.name}">${group.name}</a>
                        </td>
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