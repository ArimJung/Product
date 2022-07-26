package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.MemberVO;
import model.ProductVO;

public class View {
	Scanner sc=new Scanner(System.in);
	
	public MemberVO login() { // [로그인 페이지]
		System.out.print("아이디 입력: ");
		String mid=sc.next();
		System.out.print("비밀번호 입력: ");
		String mpw=sc.next();
		
		MemberVO vo=new MemberVO();
		vo.setMid(mid);
		vo.setMpw(mpw);
		return vo;
	}
	public int showProduct(ArrayList<ProductVO> datas) { // [메인 페이지]
		System.out.println("상품목록");
		for(ProductVO v:datas) {
			System.out.println(v);
		}
		System.out.print("상품번호 입력: ");
		int pid=sc.nextInt();
		return pid;
	}
	public String productInfo(ProductVO data) { // [상세 페이지]
		System.out.println("상품명: "+data.getPname());
		System.out.println("상품가격: "+data.getPrice());
		System.out.println("재고: "+data.getCnt());
		System.out.println("구매하시겠습니까?(Y/N): ");
		String ans=sc.next();
		return ans;
	}
}
