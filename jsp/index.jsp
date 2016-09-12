<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Welcome</title>
</head>
<body>
    <form action="jsp/search/Search.jsp" method="POST">
        <input name="toSearch" type="submit" value="Find">
    </form>
    <form action="jsp/add/Add.jsp" method="POST">
        <input name="toAdd" type="submit" value="Add">
    </form>
    <form action="jsp/delete/Delete.jsp" method="POST">
        <input name="toDelete" type="submit" value="Delete">
    </form>
</body>
</html>