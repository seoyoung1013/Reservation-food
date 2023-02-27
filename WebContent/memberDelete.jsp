<%@page import="com.dev.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>삭제</title>
</head>
<body>
<h3>삭제 정보 검색 </h3>
${error}
<form action="memberSearch.do" method="post">
   ID : <input type="text"  name="id" />
  <input type="submit"  value="검색" />
    <input type="hidden" name="job" value="delete" />
</form>
<!-- 삭제를 위해 검색된 회원정보를 삭제하기 위한 태그
삭제하기 위한 회원정보를 id로 사용하기 위해 <input type="hidden" name="id" value="${member.id}"> 태그를 사용하여
숨겨진 변수로 id에 현재 회원 id를 담아서 전달 -->

<% MemberVO member = (MemberVO)request.getAttribute("member");
   if(member != null) { %>
		<h3>검색 정보 결과</h3>
		${member.id } / ${member.passwd } / ${member.name } / ${member.mail } <p>
	
	    <form action="memberDelete.do" method="post">
	       <input type="hidden" name="id"  value="${member.id}" />
	       <input type="submit"  value="삭제" />
	    </form>

<%}else{ %>
     ${result} <p>

<%} %>
</body>
</html>