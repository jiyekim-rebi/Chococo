package com.chococo.mypage.Market.VO;

import java.util.Date;

/*
 * 2020.03.20 상품정보 관련 로드할 수 있는 VO
 * 2020.03.30 content 추가!
 * 
 * 2020.04.06 market-main화면 때문에 mainCategory 늘어남...
 */
public class MarketVO {
	
	//상품 고유 번호
	private int productNo;
	//상품명
	private String productName;
	//상품 가격
	private int price;
	//상품 옵션 - String으로 길게 받아서 list로 만든 후 view에 뿌려줄 것.
	private String selectOption;
	//상품 등록일 - main화면에서 new Products 출력할 때 필요
	private Date regDate;
	//상품이미지파일 - 나중에 이미지파일 불러오는 작업할 때 연동
	private String imageFileName;
	//누적 판매회수
	private int hit;
	//서브 카테고리
	private int subCategory;
	//상품 상세 설명(feat. 이미지까지)
	private String content;
	//2020.04.06 메인화면 출력 부분 때문에
	private int mainCategory;
	//2020.04.20 상품 판매중단/판매가능 업데이트 유무
	private int productStatus;
	

	public int getMainCategory() {
		return mainCategory;
	}


	public void setMainCategory(int mainCategory) {
		this.mainCategory = mainCategory;
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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	public int getProductStatus() {
		return productStatus;
	}


	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}


	@Override
	public String toString() {
		return "MarketVO [productNo=" + productNo + ", productName=" + productName + ", price=" + price
				+ ", selectOption=" + selectOption + ", regDate=" + regDate + ", imageFileName=" + imageFileName
				+ ", hit=" + hit + ", subCategory=" + subCategory + ", mainCategory=" + mainCategory
				+ ", productStatus=" + productStatus + "]";
	}

	
}
