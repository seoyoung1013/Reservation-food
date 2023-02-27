<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>생성 결과</title>
</head>
<body>
<h3> ${id} 님 가입이 완료되었습니다! </h3>
<!-- MemberInsertController의 execute() 메소드에서 출력 뷰 페이지로 이동하면서
 새회원으로 가입된 정보중 id를 request에 "id" 이르믕로 등록
${id}는 request에 setAttribute() 메소드로 저장된 id값을 추출하여 출력
 -->
<%@ include file="home.jsp" %>
<!-- home.jsp 파일을 포함
home.jsp 파일은 index.jsp 즉 첫 화면으로 이동하는 링크가 있는 페이지 --> 
</body>
</html>