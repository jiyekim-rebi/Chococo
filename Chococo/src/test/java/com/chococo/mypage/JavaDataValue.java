package com.chococo.mypage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JavaDataValue {

	public static void main(String[] args) {
		/*
		 * 2020.03.26 ArrayList를 이용한 최근에 본 상품 구현하기
		 * 
		 * vo에는 그냥 date 채로 넣고
		 * 이걸 정렬하고 나서 format을 씌워놓고 model로 보내는게 좋을 것도 같은데
		 */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		//2020-03-26
		System.out.println(date);
		
		
		RecentList recent1 = new RecentList();
		RecentList recent2 = new RecentList();
		RecentList recent3 = new RecentList();
		
		List<RecentList> list = new ArrayList<>();
		recent1.setRecentData(new Date());
		recent1.setProductNo(1);
		recent1.setProductName("첫번째");
		list.add(recent1);
		
		recent2.setRecentData(new Date());
		recent2.setProductNo(2);
		recent2.setProductName("두번째");
		list.add(recent2);
		
		recent3.setRecentData(new Date());
		recent3.setProductNo(3);
		recent3.setProductName("세번째");
		list.add(recent3);
		
		for(int i = 0; i<list.size(); i++) {
			System.out.println(list.get(i).getRecentData());
			System.out.println(list.get(i).getProductName());
			System.out.println(list.get(i).getProductNo());
		}
	}

}
