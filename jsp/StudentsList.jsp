<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Students</title>
</head>
<body>
    <form action="jsp/FindStudent.jsp" method="POST">
        Name: <input name="name" type="text">
        <br>
        Family name: <input name="name" type="text">
        <br>
        <input name="send" type="submit" value="Find">
    </form>
    <form action="jsp/index.jsp" method="POST">
        <input name="back" type="submit" value="Back">
    </form>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Family Name</th>
            <th>Group</th>
        </tr>
        <c:forEach items="${students}" var="problem">
           <tr>      
               <td>${students.name}</td>
               <td>${students.familyName}</td>
               <td>${students.group}</td>
           </tr>
        </c:forEach>
    </table>
</body>
</html>