package com.dev.controller;

import javax.servlet.*;
import javax.servlet.http.*;

public class HttpUtil {
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) {
		// forward()는 사용자가 정의한 메소드
		// 다른 페이지로 이동하기 위한 기능을 가지며, 호출시 인자값으로 HttpServletRequest, HttpServletResponse, 이동할 페이지에 대한 정보 path변수에서 받도록 선언
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			// 이동할 페이지에 대한 URI 정보는 path 변수에 저장
			// path에 지정된 페이지의 경로를 가지는 RequestDispatcher객체를 생성
			
			dispatcher.forward(request, response);
			// dispatcher가 가지고 있는 경로의 페이지로 이동
		} catch (Exception ex) {
			System.out.println("forward 오류: " + ex);
		}
	}
}
