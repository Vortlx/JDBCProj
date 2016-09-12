<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Search</title>
</head>
<body>
    <form action="./StudentsSearch.jsp" method="POST">
        <input name="toStudents" type="submit" value="Find a student">
    </form>
    <form action="./TeachersSearch.jsp" method="POST">
        <input name="toTeachers" type="submit" value="Find a teacher">
    </form>
    <form action="./GroupsSearch.jsp" method="POST">
        <input name="toGroups" type="submit" value="Find a group">
    </form>
    <form action="../../" method="POST">
        <input name="back" type="submit" value="Back">
    </form>
</body>
</html>