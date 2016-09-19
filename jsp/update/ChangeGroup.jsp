<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Add Group</title>
</head>
<body>
    <form action="./ChangeGroupServ" method="POST">
        <input name="studentName" type="hidden" value="${param.studentName}">
        <input name="studentFamilyName" type="hidden" value="${param.studentFamilyName}">
        Group Name: <input name="newGroupName" type="text" required>
        <br>
        <input name="add" type="submit" value="Change">
    </form>
    <form action="../search/StudentsSearch.jsp" method="POST">
        <input name="back" type="submit" value="Back">
    </form>
    <br>
    ${requestScope.message}
</body>
</html>