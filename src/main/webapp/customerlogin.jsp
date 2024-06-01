<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${message}
    <form action="customerloginvalidate">
    Enter email : <input type="email" name="email"> <br>
    Enter password : <input type="password" name="password"> <br> 
    <input type="submit" value="login">
	</form>
</body>
</html>