<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Search Students</title>
</head>
<body>
    <form action="../search/FindStudentServ" method="POST">
        Name: <input name="name" type="text">
        <br>
        Family name: <input name="familyName" type="text">
        <br>
        <input name="send" type="submit" value="Find">
    </form>
    <form action="../search/Search.jsp" method="POST">
        <input name="back" type="submit" value="Back">
    </form>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Family Name</th>
            <th>Group</th>
        </tr>
        <c:forEach items="${students}" var="student">
           <tr>      
               <td>${student.name}</td>
               <td>${student.familyName}</td>
               <td>
                   ${student.group.name}
                   <br>
                   <form action="../update/ChangeGroup.jsp" method="POST">
                       <input name="studentID" type="hidden" value="${student.id}">
                       <input name="change" type="submit" value="Change">
                   </form>
               </td>
           </tr>
        </c:forEach>
    </table>
</body>
</html>