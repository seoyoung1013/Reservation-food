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
		    <h3>
			${member.id} 님 로그인 성공 !
			</h3>
    <%} %>
    
    <%@ include file="home.jsp" %> 	
</head>
<body>

</body>
</html>