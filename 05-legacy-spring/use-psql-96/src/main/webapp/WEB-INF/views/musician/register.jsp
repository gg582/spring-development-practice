<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register Musician</title>
</head>
<body>
    <h2>Register New Musician</h2>
    <form action="${pageContext.request.contextPath}/musician/register" method="post">
        Name: <input type="text" name="name" required><br>
        Genre: <input type="text" name="genre"><br>
        <button type="submit">Register</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/musician/list">Back to List</a>
</body>
</html>
