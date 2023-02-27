<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>생성</title>
</head>
<body>
<h3>회원 가입</h3>

${error} <!-- 오류가 발생하여 현재 페이지로 다시 돌아올때 오류메시지를 출력, request.getAttribute("error")와 동일한 실행문 -->

<form action="memberInsert.do"  method="post">
<!-- 폼에서 입력받은 값을 처리할 서버 프로그램. memberInsert.do를 지정, 요청방식: post -->

	ID : <input type="text"  name="id" > <br>
	비밀번호 : <input type="password"  name="passwd" ><br>
	이름 : <input type="text" name="name"> <br>
	E-Mail : <input type="text" name="mail" > <br>

	<input type="submit"  value="가입" >

</form>
</body>
</html>