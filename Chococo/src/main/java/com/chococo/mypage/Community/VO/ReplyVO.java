package com.chococo.mypage.Community.VO;

import java.util.Date;

/*
create table chococo_reply (
	replyNo number not null, //리플 번호
	boardNo number not null, //리플이 달린 게시글의 번호
	parentReplyNo number, //리플의 답리플이 달린 경우. 몇번 리플에 리플답글 달렸니
	step number default 1, //1번째 답리플, 2번째 답리플 등등
	content VARCHAR2(1500),
	writer VARCHAR2(100) not null,
	regDate DATE DEFAULT SYSDATE,
    parentReplyNoWriter VARCHAR2(100),
	primary key(replyNo),
	Constraint Reply_FK foreign key(boardNo) references chococo_community(boardNo)
);
*/

public class ReplyVO {
	
	private int replyNo;
	private int boardNo;
	private int parentReplyNo;
	private int step;
	private String content;
	private String writer;
	private Date regDate;
	private String parentReplyNoWriter;
	
	public ReplyVO() {

	}

	public ReplyVO(int replyNo, int boardNo, int parentReplyNo, int step, String content, String writer, Date regDate,
			String parentReplyNoWriter) {
		super();
		this.replyNo = replyNo;
		this.boardNo = boardNo;
		this.parentReplyNo = parentReplyNo;
		this.step = step;
		this.content = content;
		this.writer = writer;
		this.regDate = regDate;
		this.parentReplyNoWriter = parentReplyNoWriter;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getParentReplyNo() {
		return parentReplyNo;
	}

	public void setParentReplyNo(int parentReplyNo) {
		this.parentReplyNo = parentReplyNo;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
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

	public String getParentReplyNoWriter() {
		return parentReplyNoWriter;
	}

	public void setParentReplyNoWriter(String parentReplyNoWriter) {
		this.parentReplyNoWriter = parentReplyNoWriter;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", boardNo=" + boardNo + ", parentReplyNo=" + parentReplyNo + ", step="
				+ step + ", content=" + content + ", writer=" + writer + ", regDate=" + regDate
				+ ", parentReplyNoWriter=" + parentReplyNoWriter + "]";
	}
	
}
