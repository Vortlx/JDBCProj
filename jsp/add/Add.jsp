<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
    <form action="AddStudent.jsp" method="POST">
        <input name="toStudentsSearch" type="submit" value="Add a student">
    </form>
    <form action="AddTeacher.jsp" method="POST">
        <input name="toTeachersSearch" type="submit" value="Add a teacher">
    </form>
    <form action="AddGroup.jsp" method="POST">
        <input name="toGroupsSearch" type="submit" value="Add a group">
    </form>
    <form action="../../" method="POST">
        <input name="back" type="submit" value="Back">
    </form>
</body>
</html>