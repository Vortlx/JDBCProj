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
    <form action="./TeachersSearch.jsp" method="POST">
        <input name="back" type="submit" value="Back">
    </form>
    <table border="1">
        <tr>
            <th colspan="2">${group.name}</th>
        </tr>
        <tr>
            <th>Name</th>
            <th>Family Name</th>
        </tr>
        <c:forEach items="${group.students}" var="student">
            <tr>
                <td>${student.name}</td>
                <td>${student.familyName}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>