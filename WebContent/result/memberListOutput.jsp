<%@page import="com.dev.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>회원 정보</title>
</head>
<body>

<%  ArrayList<MemberVO> list = (ArrayList<MemberVO>) request.getAttribute("list");
// 모든 회원정보가 담겨 있는 ArrayList 추출하는 코드
// 모든 회원정보 보기 서비스를 요청하면 MemberListController에서 서비스를 처리한 다음, 
// 출력 뷰 페이지로 이동하면서 서비스 처리 메소드에서 반환받은 ArrayList 객체를 list 이름으로 request 등록

    if(!list.isEmpty()) {  %>
    <!-- 회원정보가 하나도 없으면 ArrayList가 비어있는 상태로 반환. 비어있는지 확인하는 조건,
    하나라도 존재하면 이후의 코드가 실행 -->
    
	   <table border="1">
	   		<tr><th>ID</th><th>비밀번호</th><th>이름</th><th>이메일</th></tr>
			
			<%   for(int i=0; i<list.size(); i++){
				// 회원의 수만큼 반복 실행하라는 의미
			       MemberVO member = list.get(i);   %>
			       <!-- list에 등록된 MemberVO 객체 중 i 번지에 해당하는 객체를 추출하여 member 변수에 저장 -->
			        <tr><td><%=member.getId() %></td>
			            <td><%=member.getPasswd() %></td>
			            <td><%=member.getName() %></td>
			            <td><%=member.getMail() %></td>
			       </tr>
			       <!-- ArrayList에서 추출한 MemberVO  객체의 멤버변수들을 getter 메소드로 추출하여 출력하는 코드 -->
			       
			<%   }
       }else{
    	   out.print("<h3>등록된 회원 정보가 없습니다.</h3>");
       }
	// ArrayList가 비어있을때, 즉 데이터베이스의 member 테이블에 회원정보가 하나도 없을때 실행
	%>
	  </table>

<%@ include file="home.jsp" %> 
</body>
</html>