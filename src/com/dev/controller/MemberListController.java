package com.dev.controller;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberListController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		ArrayList<MemberVO> list = service.memberList();
		//member 테이블에서 하나의 레코드가 하나의 회원정보이며, 하나의 회원정보를 MemberVO객체를
		//생성하여 바인딩한 후 ArrayList에 담아서 반환하므로 ArrayList를 반환받고 있음
		//ArrayList에는 각각의 회원정보가 MemberVO로 담겨져있음

		request.setAttribute("list", list);
		// DB의 member테이블에서 추출된 정보를 출력 뷰 페이지에서 출력하기 위해 request에 list이름으로 ArrayList를 등록하여 전달
		HttpUtil.forward(request, response, "/result/memberListOutput.jsp");
	}
}
