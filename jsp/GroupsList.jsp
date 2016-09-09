<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Groups</title>
</head>
<body>
    <form action="FindGroup.jsp" method="POST">
        Name: <input name="name" type="text">
        <br>
        <input name="send" type="submit" value="Find">
    </form>
    <form action="../index.jsp" method="POST">
        <input name="back" type="submit" value="Back">
    </form>
    <table border="1">
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach items="${groups}" var="problem">
           <tr>      
               <td>${groups.name}</td>
           </tr>
        </c:forEach>
    </table>
</body>
</html>