<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Search Teachers</title>
</head>
<body>
    <form action="../search/FindTeacherServ" method="POST">
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
            <th> </th>
        </tr>
        <c:forEach items="${teachers}" var="teacher">
           <tr>
               <c:choose>
                   <c:when test="${teacher.groups.size() == 0}">
                        <td>${teacher.name}</td>
                        <td>${teacher.familyName}</td>
                   </c:when>
                   <c:when test="${teacher.groups.size() > 0}">
                        <td rowspan="${teacher.groups.size() + 1}">${teacher.name}</td>
                        <td rowspan="${teacher.groups.size() + 1}">${teacher.familyName}</td>
                   </c:when>
               </c:choose>

               <c:forEach items="${teacher.groups}" var="group">
                    
                    <td>
                        <a name="toGroup" href="./FindGroupByTeacherServ?groupName=${group.name}">${group.name}</a>
                    </td>
                    <td>
                        <form action="../delete/DeleteCuratorServ" method="POST">
                            <input name="teacherID" type="hidden" value="${teacher.id}">
                            <input name="groupName" type="hidden" value="${group.name}">
                            <input name="delete" type="submit" value="Delete"/>
                        </form>
                    </td>
                    </tr>

                    <tr>
               </c:forEach>
               <td>
                   <form action="../add/AddCurator.jsp" method="POST">
                       <input name="teacherID" type="hidden" value="${teacher.id}">
                       <input name="add" type="submit" value="Add"/>
                   </form>
               </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>