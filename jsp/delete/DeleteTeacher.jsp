<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Delete Teacher</title>
</head>
<body>
    <form action="DeleteTeacherServ" method="POST">
        Name: <input name="name" type="text" required>
        <br>
        Family Name: <input name="familyName" type="text" required>
        <br>
        <input name="back" type="submit" value="Delete">
    </form>
    <form action="./Delete.jsp" method="POST">
        <input name="back" type="submit" value="Back">
    </form>
    <br>
    ${requestScope.message}
</body>
</html>