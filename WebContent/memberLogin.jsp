<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
</head>
<body>
<h3>로그인</h3>
${error}

<form action="memberLogin.do" method="post">


	아이디: <input type="text" name="id"> <br>
	비밀번호: <input type="password" name="passwd"><br>
	
	
	<input type="submit" value="로그인">
	
</form>
</body>
</html>