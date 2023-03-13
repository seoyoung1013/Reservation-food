<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과</title>

	
	<%
	    String result = (String)request.getAttribute("result");
	// request에 result 이름으로 등록된 정보를 추출
	// result 정보는 데이터베이스의 member 테이블에 추출된 회원정보가 없을 때 등록되는 정보
	
	    if(result!=null) {
	    	out.print(result+"<p>");
	    	// result가 null이 아니면 데이터베이스에서 검색한 id에 해당하는 정보가 없다는 의미
	    	// 이런경우 result값을 반환. result는 검색된 정보가 없습니다!라는 문자열
	    }else{
	%>
	<!-- result가 null이면 else에 해당
	DB의 member 테이블에서 추출된 회원정보가 있다는 의미
	검색한 회원정보는 request에 member 이름으로 등록 -->
		    <h3>
			${member.id } 님 로그인 성공 !
			<!-- request에 member 이름으로 등록된 MemberVO 객체의 getter 메소드를 호출하여
			반환값을 출력하는 코드
			${member.id}는 request.getAttribute("id")와 동일 명령문 -->
			</h3>
    <%} %>
    
    <%@ include file="home.jsp" %> 	
</head>
<body>

</body>
</html>