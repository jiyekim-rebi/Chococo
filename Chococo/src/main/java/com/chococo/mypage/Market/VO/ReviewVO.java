package com.chococo.mypage.Market.VO;

import java.util.Date;

/*
 * create table chococo_market_review (
    productNo number not null,
    boardNo number not null,
    writer varchar2(100) not null,
    content long,
    regDate date default sysdate,
    mainCategory number not null,
    productName varchar2(250) not null,
    primary key(boardNo)
);
 */
public class ReviewVO {
	
	private int productNo;
	private int boardNo;
	private String writer;
	private String content;
	private Date regDate;
	private int mainCategory;
	private String productName;
	
	public ReviewVO(int productNo, int boardNo, String writer, String content, Date regDate, int mainCategory,
			String productName) {
		super();
		this.productNo = productNo;
		this.boardNo = boardNo;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.mainCategory = mainCategory;
		this.productName = productName;
	}

	public ReviewVO() {

	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(int mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ReviewVO [productNo=" + productNo + ", boardNo=" + boardNo + ", writer=" + writer + ", regDate="
				+ regDate + ", mainCategory=" + mainCategory + ", productName=" + productName + "]";
	}

	
}
