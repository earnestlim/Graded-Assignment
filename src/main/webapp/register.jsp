<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="RegisterServlet" method="Post">
Name : <input type="text" name="userName"><br>
Password : <input type="password" name="password"><br>
Email : <input type="text" name="email"><br>
AR : <select name="AR">
<option>1</option>
<option>0</option>
</select>
<input type="submit" value = "Add User"/>
</form>
</body>
</html>