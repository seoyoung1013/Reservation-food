package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberLoginController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// Parameter 추출
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		// 클라이언트가 보낸 질의 문자열들을 각각 추출하여 지역변수들에 저장
		
		// 유효성 체크
		if (id.isEmpty() || passwd.isEmpty()) {
			// isEmpty() 메소드는 입력값이 없을 때 true반환

			request.setAttribute("error", "ID를 입력해주시기 바랍니다!");
			// 오류가 발생하여 입력페이지로 이동했을 때 보여줄 오류 메시지를 request에 저장
			
			HttpUtil.forward(request, response, "/memberLogin.jsp");
			return;
			// 입력 페이지인 /memberInsert.jsp로 이동한 후 return을 만나면 현재 메소드를 종료
		}
		MemberService service = MemberService.getInstance();
		MemberVO member = service.memberSearch(id);
		// Service 객체의 메소드 호출
		
		// Output View 페이지로 이동
		if (member == null) request.setAttribute("result", "검색된 아이디가 없습니다!");
		
		else if (member.getPasswd() != passwd) request.setAttribute("result", "비밀번호가 틀렸습니다!");
		
		request.setAttribute("member", member);
		HttpUtil.forward(request, response, "/result/memberLoginOutput.jsp");
		
		// service.memberInsert(member) 메소드가 종료된 후 실행
		// 이 메소다가 종료되었다는 것은 서비스 처리가 완료되었다는 의미
		// 요청된 서비스가 완료되면, 처리결과 페이지(출력 뷰 페이지)로 이동
		// 이동전, request.setAttribute() 메소드를 실행하여
		// id값을 저장한 후 "/result/memberInsertOutput.jsp"페이지로 이동
		
		
	}
	
	
	
}
