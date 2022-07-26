package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	// CRUD
	// 회원가입
	// 로그인
	// 로그아웃
	// 마이페이지에서 내 정보 확인
	// 회원정보변경
	// 회원탈퇴
	final String sql_login="SELECT * FROM MEMBER WHERE MID=?";
	public MemberVO login(MemberVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_login);
			pstmt.setString(1, vo.getMid());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("mpw").equals(vo.getMpw())) {
					MemberVO data=new MemberVO();
					data.setMid(rs.getString("mid"));
					data.setMname(rs.getString("mname"));
					data.setMpw(rs.getString("mpw"));
					System.out.println("로그: 로그인 성공!");
					return data;
				}
				System.out.println("로그: 비밀번호 불일치로 로그인 실패...");
				return null;
			}
			System.out.println("로그: 회원정보없음 | 로그인 실패...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		// 1. DB에 해당 아이디가 있나?
		// 2. DB에서 찾은 그 정보에 맞는 비밀번호인가?
		return null;
	}
}
