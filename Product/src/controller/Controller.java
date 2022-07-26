package controller;

import model.MemberDAO;
import model.MemberVO;
import model.ProductDAO;
import model.ProductVO;
import view.View;

public class Controller {
	ProductDAO pDAO;
	MemberDAO mDAO;
	View view;
	public Controller() {
		pDAO=new ProductDAO();
		mDAO=new MemberDAO();
		view=new View();
	}
	public void startApp() {
		while(true) { 
			MemberVO mVO=view.login();  // 로그인
			MemberVO mData=mDAO.login(mVO);
			if(mData==null) {
				continue;
			}
			
			ProductVO pVO=new ProductVO();
			int pid=view.showProduct(pDAO.selectAll(pVO)); // 메인페이지
			pVO.setPid(pid);
			ProductVO pData=pDAO.selectOne(pVO);
			if(pData==null) {
				continue;
			}
			
			String res=view.productInfo(pData); // 상세페이지
			if(res.equals("Y")) {
				pDAO.update(pVO);
				System.out.println("C 로그: 구매O");
				continue;
			}
			System.out.println("C 로그: 구매X");
		}
	}
}
