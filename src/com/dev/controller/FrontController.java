package com.dev.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class FrontController extends HttpServlet {
// FrontController는 HttpServlet을 상속받고 있음 -> 따라서 서블릿객체
// 컨트롤러를 서블릿으로 개발하는 것은 MVC Model2의 구조로 개발한다는 의미!!
	
	private static final long serialVersionUID = 1L;

	String charset = null;
	HashMap<String, Controller> list = null;
	// 멤버변수 charset: 인코딩하려는 문자코드를 저장할 변수
	// list는 클라이언트의 요청에 대하여 실제 실행할 컨트롤러가 누구인지에 대한 정보를 저장할 변수
	
	@Override
	public void init(ServletConfig sc) throws ServletException {
		// init 메소드는 현재 서블릿 객체가 최초로 요청이 들어왔을 때 한번만 실행되는 메소드 -> 서블릿의 초기화 기능을 담당
		
		charset = sc.getInitParameter("charset");
		// sc.getInitParameter() 메소드는 web.xml의 <servlet> 태그의 정보중 
		//<init-param>의 <param-name>이 charset인 태그의 <param-value> 값을 추출 -> UFT-8을 추출했음
		
		list = new HashMap<String, Controller>();
		list.put("/memberInsert.do", new MemberInsertController()); //ex) key: /memberInsert.do value: MemberInsertController
		list.put("/memberSearch.do", new MemberSearchController());
		list.put("/memberUpdate.do", new MemberUpdateController());
		list.put("/memberDelete.do", new MemberDeleteController());
		list.put("/memberList.do", new MemberListController());
		
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트로부터 요청이 들어올 때마다 실행하는 메소드

		request.setCharacterEncoding(charset);
		// setCharacterEncoding() 메소드: 클라이언트로부터 POST 방식으로 전달된 질의 문자열을 인코딩 처리하는 메소드, 한글을 처리를 위한 코드
		//charset 변수에 web.xml 파일에 <init-param>에 지정한 값이 저장
		//소스에 직접 값을 지정하지 않고 외부에서 읽어들여 지정하는 것은 유지보수성이 좋은 개발 형태 !

		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		//클라이언트의 요청 URL에서 KEY를 추룰하는 코드
		//ex) 요청된 URL: http://localhost:9090/dev/memberInsert.do이면 각 메소드의 결과값
		// request.getRequestURI() -> /dev/memberInsert.do
		// request.getContextPath() -> /dev
		// contextPath.length() -> 4
		//url.subString(4) -> /memberInsert.do

		Controller subController = list.get(path);
		// String path= "memberInsert.do" 저장되어 있으면 list.get(path)에서 반환되는 객체는 MemberInsertController
		// 따라서, Controller subController 변수는 MemberInsertController의 주솟값이 저장
		
		subController.execute(request, response);
		//dev 애플리케이션의 Controller 객체는 Controller 인터페이스를 상속받아서 작성하도록 규정
		// 모든 Controller 객체에서는 execute() 메소드를 재정의하여 Controller 기능을 구현하므로 list에서 추출한 Controller 객체가 무엇이든간에 execute() 메소드가 실행되도록 인터페이스 통일
	}
}
