package com.dev.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberInsertController implements Controller {
	//Controller를 상속받음. dev 애플리케이션에서는 컨트롤러 기능을 구현하는 객체를 작성할 때
	// Controller 인터페이스를 상속받아 execute() 메소드에 구현하도록 하고있음
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// execute() 메소드는 FrontController 객체의 service() 메소드에서 호출되며 회원정보 생성 서비스에 관한 컨트롤러 기능 구현
		
		
		// Parameter 추출
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		// 클라이언트가 보낸 질의 문자열들을 각각 추출하여 지역변수들에 저장
		
		// 유효성 체크
		if (id.isEmpty() || passwd.isEmpty() || name.isEmpty() || mail.isEmpty() || address.isEmpty() || phone.isEmpty()) {
			// isEmpty() 메소드는 입력값이 없을 때 true반환
			// 하나라도 입력하지 않았으면 id, passwd, name, mail 중 하나라도 입력하지 않았으면 true 반환

			request.setAttribute("error", "모든 항목을 빠짐없이 입력해주시기 바랍니다!");
			// 오류가 발생하여 입력페이지로 이동했을 때 보여줄 오류 메시지를 request에 저장
			
			HttpUtil.forward(request, response, "/memberInsert.jsp");
			return;
			// 입력 페이지인 /memberInsert.jsp로 이동한 후 return을 만나면 현재 메소드를 종료
		}

		
		// VO 객체에 데이터 바인딩
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPasswd(passwd);
		member.setName(name);
		member.setMail(mail);
		member.setaddress(address);
		member.setphone(phone);
		// MemberVO 객체를 생성한 후 클라이언트로부터 전달된 입력값들을 setter 메소드를 호출하여 MemberVO 객체의 멤버변수에 저장
		//이런 작업을 데이터 바인딩이라고 함

		// Service 객체의 메소드 호출
		MemberService service = MemberService.getInstance();
		service.memberInsert(member);
		// 회원 관리 서비스를 처리하는 모델인 MemberService 객체를 추출한 후 회원정보 생성 서비스를 처리하는 memberInsert() 메소드를 호출하면서
		// 클라이언트 회원정보가 바인딩된 MemberVO 객체 member를 인자로 전달

		// Output View 페이지로 이동
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "/result/memberInsertOutput.jsp");
		// service.memberInsert(member) 메소드가 종료된 후 실행
		// 이 메소다가 종료되었다는 것은 서비스 처리가 완료되었다는 의미
		// 요청된 서비스가 완료되면, 처리결과 페이지(출력 뷰 페이지)로 이동
		// 이동전, request.setAttribute() 메소드를 실행하여
		// id값을 저장한 후 "/result/memberInsertOutput.jsp"페이지로 이동
		
	}
}
