package com.chococo.mypage.Community.VO;

import java.util.Date;

/*
create table chococo_community(
categoryNo NUMBER NOT NULL,
boardNo NUMBER NOT NULL,
title VARCHAR2(200)  NOT NULL,
content VARCHAR2(3000),
writer VARCHAR2(100)    NOT NULL,
regDate DATE DEFAULT SYSDATE,
PRIMARY KEY(boardNo)
);
 */

public class CommunityVO {
	
	private int categoryNo;
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private int step;
	private int parentBoardNo;
	
	public CommunityVO() {

	}

	public CommunityVO(int categoryNo, int boardNo, String title, String content, String writer, Date regDate, int step,
			int parentBoardNo) {
		super();
		this.categoryNo = categoryNo;
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regDate = regDate;
		this.step = step;
		this.parentBoardNo = parentBoardNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getParentBoardNo() {
		return parentBoardNo;
	}

	public void setParentBoardNo(int parentBoardNo) {
		this.parentBoardNo = parentBoardNo;
	}

	@Override
	public String toString() {
		return "CommunityVO [categoryNo=" + categoryNo + ", boardNo=" + boardNo + ", title=" + title + ", writer="
				+ writer + ", regDate=" + regDate + ", step=" + step + ", parentBoardNo=" + parentBoardNo + "]";
	}

	
}
