package com.dev.dao;

import java.sql.*;
import java.util.ArrayList;

import com.dev.vo.MemberVO;

public class MemberDAO {

	private static MemberDAO dao = new MemberDAO();
	private MemberDAO(){}

	public static MemberDAO getInstance() {
		return dao;
	}
	// MemberDAO 객체를 하나만 생성하여 사용하기 위한 코드

	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		}
		return conn;
	}
	// DB에 접속하여 Connection 객체를 얻어내기 위한 코드
	//Class.forName()메소드를 사용하여 JDBC 드라이버로 로딩
	//DriverManager.getConnection() 메소드를 사용하여 DB에 접속
	//올바른 DB를 인자값으로 지정했다면 정상적으로 접속
	// Connection 객체가 반환
	//connect() 메소드는 DB와 연결된 Connection 객체를 반환하는 메소드

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : " + ex);				
			}
		}
		close(conn, ps);
	} // close
	// DB에 관한 작업이 마쳤으면 자원을 해제
	// 자원 해제 작업은 공통적으로 나오는 코드이므로 close() 메소드를 선언하여 재사용하도록 구현
	//Connection, PreparedStatement, ResultSet 객체를 인자로 받아 해제하는 메소드
	
	public void close(Connection conn, PreparedStatement ps) {
	// close 재정의. 인자로 Connection, PreparedStatement를 받아 자원을 해제
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : " + ex);				
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : " + ex);				
			}
		}
	} // close
	
	public void memberLogin(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select password from member where id=?");
			pstmt.setString(1, member.getId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(3).equals(member.getPasswd())) {
					member = new MemberVO();
					member.setId(rs.getString(2));
					member.setPasswd(rs.getString(3));
					member.setName(rs.getString(4));
					member.setMail(rs.getString(5));
					member.setaddress(rs.getString(6));
					member.setaddress(rs.getString(7));
				}
			}
		}catch(Exception ex) {
			System.out.println("오류발생 : " + ex);
		}finally {
			close(conn, pstmt);
		}
	}

	public void memberInsert(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			// memberInsert()는 MemberVO 객체를 인자로 받아 member 테이블에 삽입하는 메소드
			// conn = connect(); -> connect() 메소드를 실행하여 Connection 객체를 반환받음
			
			pstmt = conn.prepareStatement("insert into member values(member_seq.nextval,?,?,?,?,?,?)");
			// prepareStatement 객체를 사용하여 member테이블에 insert 명령문을 실행하기 위해 SQL 문장을 생성
			// ?는 나중에 별도로 값을 지정하기 위해 사용
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getMail());
			pstmt.setString(5, member.getaddress());
			pstmt.setString(6, member.getphone());
			
			
			// pstmt에 ?처리했던 부분에 값을 설정하는 부분
			// 1,2,3,4는 ?의 순서를 의미
			// 각 자리에 member의 getter 메소드를 사용하여 MemberVO 객체의 멤버변수들의 값들을 설정
			
			pstmt.executeUpdate();
			// pstmt가 가지고 있는 SQL 문장을 실행
			// member 테이블에 레코드가 삽입됨
		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	public MemberVO memberSearch(String id) {
		//인자로 받은 id에 해당하는 회원정보를 member테이블에서 select 하여 MemberVO 객체에 담아서 반환해주는 메소드
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberVO member = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			// 클라이언트가 입력한 id에 해당하는 레코드를 member 테이블에서 추출하는 SQL 명령문을 생성하는 코드
			// SQL 문의 ? 자리에 id 변수값을 설정
			
			rs = pstmt.executeQuery();
			// pstmt가 가지고 있는 SQL 문을 실행하는 코드
			// select문을 실행한 후의 결과값을 ResultSet rs에 저장
			
			if (rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString(2));
				member.setPasswd(rs.getString(3));
				member.setName(rs.getString(4));
				member.setMail(rs.getString(5));
				member.setaddress(rs.getString(6));
				member.setphone(rs.getString(7));
			}
			// rs.next()는 메소드는 추출된 레코드가 있을 때만 true값을 반환
			//true면, 즉. 클라이언트로부터 전달받은 id에 해당하는 레코드를 추출하였으면 MemberVO 객체 하나를 생성 후
			// 다음 ResultSet의 getter 메소드를 이용하여 칼럼값을 추출하고,
			//이것을 MemberVO의 setter 메소드의 인자값으로 전달함으로써 MemberVO 객체에 회원정보값을 바인딩

		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return member;
		// member은 MemberVO 객체로서 데이터베이스에서 select한 회원정보가 바인딩되어 있음
		// 현재 메소드를 호출한 memberSearch() 메소드에 member객체를 반환
	}

	public void memberUpdate(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("update member set password=?,name=?,email=?,address=?,phone=? where id=?");
			// 회원에 대한 정보를 수정하는 sql문
			//id를 제외한 모든 정보를 수정하며, 수정하는 레코드의 조건은 클라이언트가 입력한 id와 동일한 id를 찾아서 수정
			
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getMail());
			pstmt.setString(4, member.getaddress());
			pstmt.setString(5, member.getphone());
			pstmt.setString(6, member.getId());
			
			// update문장을 가지고 있는 pstmt의 ?자리에 member 객체의 멤버변수를 설정
			// 모든 ? 자리에 값을 설정해야만 실행 가능한 sql문이 된다.
			
			pstmt.executeUpdate();
			// 데이터베이스의 member 테이블에 pstmt가 가지고 있는 update문을 실행
			
		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}

	}

	public void memberDelete(String id) {
		// DB의 member 테이블에서 회원정보를 삭제하는 기능이 구현된 메소드
		//인자로 삭제할 회원정보의 id를 전달받음
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("delete from member where id=?");
			//회원정보를 삭제하는 SQL문. 삭제할 조건으로 id값을 지정
			
			pstmt.setString(1, id);
			//pstmt의 첫 번째 ?에 id 변숫값을 설정

			pstmt.executeUpdate();
			// pstmt의 SQL 문장(delete문)을 실행
			// 삭제할 조건으로 지정한 id와 동일한 레코드를 찾아서 삭제
			
		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	public ArrayList<MemberVO> memberList() {
		// member테이블에서 모든 회원에 대한 정보를 추출하는 SQL 기능이 구현된 메소드
		
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		// MemberVO 객체를 여러개 저장할 수 있는 ArrayList 객체 생성
		// 생성한 후 ArrayList에 add() 메소드를 이용하여 데이터를 등록하기 전까지는 비어있는 상태
		//생성 후 ArrayList의 isEmpty() 메소드를 실행하면 true반환. 아무것도 등록되어 있지 않음을 의미
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberVO member = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from member");
			// member 테이블의 모든 레코드를 추출하는 SQL 명령문
			rs = pstmt.executeQuery();
			// SQL문을 실행한 후 결과값을 rs에 저장
			
			while (rs.next()) {
				// rs.next()가 true를 반환하면 첫번째 레코드를 가리키던 커서는 다음 레코드를 가르킴
				// 커서가 현재 가리키는 레코드 다음에 또 다른 레코드가 존재하는지 판단해서
				// 존재하면 true 반환, 커서가 다름 레코드를 가르킴
				// false를 반환하면 가리킬 레코드가 없다는 의미로 while문 종료
				
				member = new MemberVO();
				// rs.next() 메소드에 의해 ResultSet의 커서가 새로운 레코드를 가리킬 때마다 새로운 MemberVO 객체를 생성
				
				member.setId(rs.getString(2));
				member.setPasswd(rs.getString(3));
				member.setName(rs.getString(4));
				member.setMail(rs.getString(5));
				member.setaddress(rs.getString(6));
				member.setphone(rs.getString(7));
				
				// 새로 생성한 MemberVO 객체에 rs의 칼럼값들을 getter 메소드로 추출
				// 추출한 값들을 MemberVO의 setter 메소드를 이용하여 MemberVO 객체에 정보를 바인딩
				
				list.add(member);
				// ArrayList 객체에 하나의 회원정보, 즉 하나의 레코드 값들이 저장된 MemberVO 객체인 member를 추가
				
			}

		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
		//현재 메소드를 호출한 MemberService의 memberList() 메소드에 list 반환
	}

}
