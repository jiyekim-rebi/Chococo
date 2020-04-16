package com.chococo.mypage.Market.VO;

import java.util.Date;

import com.chococo.mypage.Member.VO.MemberVO;

/*
 * create table chococo_market_order (
    productNo number not null,
    orderNo number not null,
    productName varchar2(250) not null,
    mainCategory number not null,
    price number not null,
    amount number default 1,
    selectOption varchar2(100) not null,
    orderDate date default sysdate,
    userName varchar2(100) not null,
    userEmail varchar2(200) not null,
    address varchar2(300),
    trackingNum varchar2(150) default '-',
    shippingStatus varchar2(30) default '배송준비',
    memberExists number default 1 //회원이 디폴트값으로
);
 */

public class BasketVO extends MemberVO {
	
	//주문하는 상품 번호
	private int productNo;
	//주문번호
	private int orderNo;
	//상품이름
	private String productName;
	//mainCategory :) 이거로 db구분
	private int mainCategory;
	//가격
	private int price;
	//주문갯수
	private int amount;
	//주문옵션
	private String selectOption;
	//주문날짜
	private Date orderDate;
	//송장번호
	private String trackingNum;
	//배송상태
	private String shippingStatus;
	//회원, 비회원 구분 유무
	private int memberExists;
	//2020.04.05 이미지 파일 불러오기
	public String imageFileName;
	
	
	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(int mainCategory) {
		this.mainCategory = mainCategory;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSelectOption() {
		return selectOption;
	}

	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getTrackingNum() {
		return trackingNum;
	}

	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}

	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public int getMemberExists() {
		return memberExists;
	}

	public void setMemberExists(int memberExists) {
		this.memberExists = memberExists;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	@Override
	public String toString() {
		return "BasketVO [productNo=" + productNo + ", orderNo=" + orderNo + ", productName=" + productName
				+ ", mainCategory=" + mainCategory + ", price=" + price + ", amount=" + amount + ", selectOption="
				+ selectOption + ", orderDate=" + orderDate + ", trackingNum=" + trackingNum + ", shippingStatus="
				+ shippingStatus + ", memberExists=" + memberExists + ", imageFileName=" + imageFileName + "]";
	}

	
}
