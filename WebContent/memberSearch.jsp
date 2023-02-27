<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>조회</title>
</head>
<body>

<h3>회원 정보 검색 </h3>
${error}
<!-- id를 입력하지 않은 상태에서 검색을 시도했을 때 오류 메시지를 출력하는 코드 -->

<form action="memberSearch.do" method="post">
<!-- id로 회원정보 검색 처리를 요청하는 URI는 memberSearch.do이며 요청방식은 post -->

   ID : <input type="text"  name="id" />
   <!-- 회원정보 검색을 위한 ID를 받는 부분
   name은 id로 받음 -->
   
  <input type="submit"  value="검색" />
  
  <input type="hidden" name="job" value="search" />
  <!-- input type="hidden은 화면에는 출력되지 않지만, 서버에 특정한 값을 전달하고자 할 때 사용
  job=search로 값이 전달
  회원정보 검색, 수정, 삭제 작업시 먼저 작업하려는 회원정보를 DB에서 검색해야함
  id를 통해 검색하는 작업은 memberSerch.do 요청으로 공통적으로 처리
  
  그러나 검색 후 이동하는 뷰 페이지가 다르므로, 현재 진행중인 작업이 무엇인지를 나타내기 위해
  job 변수를 사용
  job = search 이면 검색 작업을 의미 -->
</form>
</body>
</html>