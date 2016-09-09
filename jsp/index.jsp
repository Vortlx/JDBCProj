<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
    <form action="jsp/StudentsList.jsp" method="POST">
        <input name="back" type="submit" value="Find a student">
    </form>
    <form action="jsp/TeachersList.jsp" method="POST">
        <input name="back" type="submit" value="Find a teacher">
    </form>
    <form action="jsp/GroupsList.jsp" method="POST">
        <input name="back" type="submit" value="Find a group">
    </form>
</body>
</html>