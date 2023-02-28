<%@page import="com.dev.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>수정</title>
</head>
<body>

<h3>수정 정보 검색 </h3>
${error}
<!-- id를 입력하지 않은 상태에서 검색을 시도했을 때 오류 메시지를 출력하는 코드 -->

<form action="memberSearch.do" method="post">
   ID : <input type="text"  name="id" />
  <input type="hidden" name="job" value="update" />
  <input type="submit"  value="검색" />
</form>
<!-- 회원정보를 수정하기 위해 수정할 회원정보를 id를 이용해 검색하기 위한 <form> 태그
현재는 회원정보 수정을 위한 검색 작업이므로 job=update로 지정 -->



<% MemberVO member = (MemberVO)request.getAttribute("member");
// 회원정보 수정을 위해 검색 작업이 완료된 후 현재 memberUpdate.jsp로 이동된 경우
// 검색된 회원 정보가 MemberVO 객체에 담겨져 request에 member이름으로 등록되어 전달
// request에 등록된 회원정보를 가지고 있는 member를 추출하여 
//MemberVO로 타입을 변경한 후 member 지역변수에 저장

   if(member != null) { %>
   <!-- DB의 member테이블에서 추출한 회원정보가 존재한다는 의미  -->
    
		<h3>회원 정보 수정 </h3>
		<form action="memberUpdate.do"  method="post">
		<!-- 검색한 회원정보를 화면에 출력한 후 값을 변경하여 회원정보를 수정하기 위한 <form>태그  -->
		
			ID : <input type="text"  name="id" readonly  value="${member.id}"  > <br>
			<!--  <input>태그의 readonly는 입력상자의 값을 변경하지 못하고 읽기 전용으로만 사용할 수 있도록 하는 속성
			value는 <input type="text">의 입력상자에 초깃값을 지정하기 위한 속성
			초깃값으로 ${member.id}은 즉. request에 등록된 member의 getId() 메소드를 실행하여 반환값을 지정  -->
			
			비밀번호 : <input type="password"  name="passwd" value="${member.passwd}" ><br>
			이름 : <input type="text" name="name" value="${member.name}"> <br>
			E-Mail : <input type="text" name="mail" value="${member.mail}"> <br>
			<!-- 비밀번호, 이름, E-mail 입력상자를 만들고 각각 초깃값을 member 객체의 멤버변수로 지정 -->
			주소 : <input type="text"  name="address" value="${member.address}" ><br>
			전화번호 : <input type="text" name="phone" value="${member.phone}"> <br>

			<input type="submit"  value="수정" >
		</form>

<%}else{ %>
     ${result} <p>
<!--member가 null일 때 실행되는 코드. member가 null이면 DB의 member테이블에서 추출된 회원정보가 없다는 의미 -->
<%} %>

</body>
</html>