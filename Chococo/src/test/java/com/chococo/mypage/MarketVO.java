package com.chococo.mypage;

import java.util.Date;

/*
 * 2020.03.20 상품정보 관련 로드할 수 있는 VO
 * 
 */
public class MarketVO {
	
	//상품 고유 번호
	public int productNo;
	//상품명
	public String productName;
	//상품 가격
	public int price;
	//상품 옵션 - String으로 길게 받아서 list로 만든 후 view에 뿌려줄 것.
	public String selectOption;
	//상품 등록일 - main화면에서 new Products 출력할 때 필요
	public Date regDate;
	//상품이미지파일 - 나중에 이미지파일 불러오는 작업할 때 연동
	public String imageFileName;
	//누적 판매회수
	public int hit;
	//서브 카테고리
	public int subCategory;
	
	public MarketVO() {

	}

	public MarketVO(int productNo, String productName, int price, String selectOption, Date regDate,
			String imageFileName, int hit, int subCategory) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.price = price;
		this.selectOption = selectOption;
		this.regDate = regDate;
		this.imageFileName = imageFileName;
		this.hit = hit;
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "MarketVO [productNo=" + productNo + ", productName=" + productName + ", price=" + price
				+ ", selectOption=" + selectOption + ", regDate=" + regDate + ", imageFileName=" + imageFileName
				+ ", hit=" + hit + ", subCategory=" + subCategory + "]";
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSelectOption() {
		return selectOption;
	}

	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(int subCategory) {
		this.subCategory = subCategory;
	}
	

}
