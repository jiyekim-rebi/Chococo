package com.chococo.mypage.Admin.VO;

import com.chococo.mypage.Market.VO.MarketVO;

//MarketVO
//productNo, productName, price, selectOption, regDate, imageFileName, hit, subCategory, content
public class ProductVO extends MarketVO {
	
	private int mainCategory;
	private String keyword;
	
	@Override
	public String toString() {
		return "ProductVO [mainCategory=" + mainCategory + ", keyword=" + keyword + "]";
	}

	public int getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(int mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
