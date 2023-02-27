package com.dev.service;

import java.util.ArrayList;

import com.dev.dao.MemberDAO;
import com.dev.vo.MemberVO;

public class MemberService {

	private static MemberService service = new MemberService();
	// @ MemberService 객체를 한개만 생성해서 사용하려는 디자인 패턴의 코드 (@으로 표시)
	//MemberService 객체를 생성해서 service 멤버변수에 저장
	//service 변수의 접근자는 private
	// 따라서 현재 클래스 내에서만 접근 o, 외부에서는 접근 x
	// 외부에서 값을 수정하는 것을 막기 위해 private 선언
	
	public MemberDAO dao = MemberDAO.getInstance();
	// DB에 저장된 데이터에 대한 처리는 DAO 객체에서 구현
	//getInstance() 메소드로 추출되는 MemberDAO 객체가 DB에 관한 기능을 담당하는 DAO 모델 객체
	
	private MemberService(){}
	//@
	// 생성자의 접근자도 private로 선언
	//외부에서 생성자에 접근할 수 없다는 것은 객체를 생성 할 수 없다는 것 의미
	
	public static MemberService getInstance() {
		return service;
	}
	//@
	// MemberService 객체를 외부에서 사용하기 위해 선언한 메소드
	// getInstance() 메소드는 public static으로 선언
	// 외부에서 MemberServie.getInstance()로 사용 가능
	// MemberServie 객체가 필요할 때 getInstance() 메소드를 사용해 이미 만들어진 객체를 추출하여 사용

	public void memberInsert(MemberVO member) {
		dao.memberInsert(member);
	}
	// 인자로 넘어온 MemberVO 객체를 member로 받음
	// dao.memberInsert(member); 메소드를 호출하여 MemberVO 객체 안에 저장된 회원 정보들을 member 테이블에 삽입

	public MemberVO memberSearch(String id) {
	// 회원정보 검색에 대한 서비스를 처리하는 메소드, 인자값은 겁색할 회원의 id를 전달받음
		
		MemberVO member = dao.memberSearch(id);
		return member;
		//DB에서 정보를 검색하여 추출하는 기능이 구현된 메소드 : dao의 memberSearch()
		// 추출된 정보를 MemberVO 객체에 담아서 반환
		//memberService 객체의 memberSearch() 메소드에서는 반환받은 MemberVO 객체를 컨트롤러쪽에 반환
	}
	//MemberService 객체는 서비스를 처리하기 위한 기능만 구현하는 서비스 모델
	

	public void memberUpdate(MemberVO member) {
		dao.memberUpdate(member);
	}
	// 수정된 회원정보를 가지고 잇는 MemberVO 객체를 인자로 받은 다음, DB 처리 해주는 dao.memberUpdate() 메소드를 호출하면서 인자로 다시 전달
	

	public void memberDelete(String id) {
		dao.memberDelete(id);
	}
	// 회원정보를 삭제 서비스를 처리하는 메소드
	// DB의 member 테이블에서 회원정보를 삭제하는 기능이 구현된 dao.memberDelete() 메소드를 호출
	// 호출하면서 삭제할 회원의 id를 인자로 전달

	public ArrayList<MemberVO> memberList() {
		
		ArrayList<MemberVO> list = dao.memberList();
		return list;
	}
	// member 테이블에서 모든 회원에 대한 정보를 추출하여 ArrayList 객체에 담아서 컨트롤러에 전달하는 서비스 처리 메소드
	// 모든 회원에 대한 정보를 추출하는 SQL 기능이 구현된 dao.memberList() 메소드를 호출
	// 현재 memberList() 메소드는 컨트롤러인 MemberListController에서 호출
	//호출한 쪽으로 ArrayList 객체인 list를 반환
	

}
