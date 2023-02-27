package com.dev.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public interface Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	// execute() 메소드는 모든 Controller 클래스들이 반드시 재정의해야함
	// 컨트롤러 기능을 구현

}
