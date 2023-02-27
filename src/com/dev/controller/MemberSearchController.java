package com.dev.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberSearchController implements Controller {
	// Controller을 상속받고 있음
	// dev 애플리케이션에서는 컨트롤러 기능을 구현하는 객체를 작성할 때
	// Controller 인터페이스를 상속받아 execute() 메소드에 구현
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// execute메소드는  FrontController 객체의 service() 메소드에서 호출
		// 회원정보 검색 서비스에 관한 컨트롤러 기능을 구현
		
		// Parameter 추출
		String id = request.getParameter("id");
		String job = request.getParameter("job");
		// 컨트롤러 기능 중 하나는 클라이언트로부터 전송된 파라미터값을 추출하는 것
		// id와 job 변수의 값을 추출하여 지역변수에 저장
		
		String path = null;
		if (job.equals("search"))
			path = "/memberSearch.jsp";
		else if (job.equals("update"))
			path = "/memberUpdate.jsp";
		else if (job.equals("delete"))
			path = "/memberDelete.jsp";	
		// id를 통해 회원정보를 검색한 후 이동할 페이지에 대한 정보를 얻기 위해 회원정보 검색을 어떤 작업(검색,수정,삭제)에서 했는지를 파악하는 코드
		//id를 입력하지 않고 검색요청시 이동할 페이지를 현재 작업에 따라 다르게 지정
		// 즉, path 변수에는 이동할 페이지 경로가 저장
		
		// 유효성 체크
		if (id.isEmpty()) {
			request.setAttribute("error", "ID를 입력해주시기 바랍니다!");
			HttpUtil.forward(request, response, path);
			return;
		}
		//id를 입력하지 않았을 때
		//request에 error라는 이름으로 오류 메시지를 등록하고, path에 지정된 페이지로 이동하는 코드
		//isEmpty()는 입력된 id값이 존재하지 않으면 true를 반환
		//페이지 이동 명령문을 실행 후 현재 메소드는 return을 만나 실행 종료
		
		// Service 객체의 메소드 호출
		MemberService service = MemberService.getInstance();
		MemberVO member = service.memberSearch(id);
		// 회원정보 검색 서비스를 구현한 MemberService 객체의 MemberSearch() 메소드를 호출하면서 입력받은 id 전달
		//service.memberSearch(id) 메소드는 입력받은 id에 해당하는 정보를 DB에 추출하여 MemberVO 객체에 담아 반환

		// Output View 페이지로 이동
		if (member == null) request.setAttribute("result", "검색된 정보가 없습니다!");
		request.setAttribute("member", member); //출력 뷰 페이지로 이동하면서 request에 "member"라는 이름으로 데이터베이스에서 추출된 정보 값을 또는 null값을 가지고 있는 member객체를 등록
		// member 변수가 null이면 클라이언트가 입력한 id와 일치하는 id 정보가 DB에 없는 경우
		//request에 result라는 이름으로 검색된 정보가 없습니다라는 문자열을 등록

		if(job.equals("search")) path="/result/memberSearchOutput.jsp";
		HttpUtil.forward(request, response, path);
		// 회원정보 검색, 수정, 삭제 작업을 하면서 공통적으로 id 정보를 가지고 회원정보를 검색
		// id 값으로 회원정보 검색이 끝난 다음에는 회원정보 검색일때만 "/result/memberSearchOutput.jsp"로 이동
		//회원정보 수정, 삭제 작업이면 입력 페이지로 이동

	}
}

