package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
	Connection conn;
	PreparedStatement pstmt;
	// CRUD
	// 제품추가
	// 제품목록보기
	// 제품상세보기
	// 검색하기
	// 재고추가
	// 가격변경
	// 구매
	// 제품삭제
	final String sql_selectAll="SELECT * FROM PRODUCT";
	final String sql_selectOne="SELECT * FROM PRODUCT WHERE PID=?";
	final String sql_update="UPDATE PRODUCT SET CNT=CNT-1 WHERE PID=?";
	public ArrayList<ProductVO> selectAll(ProductVO vo) {
		ArrayList<ProductVO> datas=new ArrayList<ProductVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAll);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				ProductVO data=new ProductVO();
				data.setCnt(rs.getInt("cnt"));
				data.setPid(rs.getInt("pid"));
				data.setPname(rs.getString("pname"));
				data.setPrice(rs.getInt("price"));
				datas.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}
	public ProductVO selectOne(ProductVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectOne);
			pstmt.setInt(1, vo.getPid());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				ProductVO data=new ProductVO();
				data.setCnt(rs.getInt("cnt"));
				data.setPid(rs.getInt("pid"));
				data.setPname(rs.getString("pname"));
				data.setPrice(rs.getInt("price"));
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return null;
	}
	public boolean update(ProductVO vo) {
		// 1. 빼기전에 재고<요청하는 양 xxx
		// 2. 빼고나서 재고<0 확인 -> 되돌리기 [트랜잭션]
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_update);
			pstmt.setInt(1, vo.getPid());
			int res=pstmt.executeUpdate();
			if(res<=0) {
				System.out.println("로그: 구매 실패...");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
}
