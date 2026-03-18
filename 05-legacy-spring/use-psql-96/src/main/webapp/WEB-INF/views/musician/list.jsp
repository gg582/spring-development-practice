<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Musician List</title></head>
<body>
    <h2>Musician List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th><th>Name</th><th>Genre</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${musicianList}" var="m">
                <tr>
                    <td>${m.id}</td>
                    <td>${m.name}</td>
                    <td>${m.genre}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
