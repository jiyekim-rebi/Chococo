package com.chococo.mypage.Member.VO;

import java.util.Date;

//쿠폰 관련 VO
/*
 * create table chococo_coupon (
	userName varchar2(100) not null,
	ratio number not null,
    couponNumber clob not null,
	content clob not null,
	createDate date default sysdate,
	isUse number default 1
);

create table chococo_member_coupon (
	userName varchar2(100) not null,
	userEmail varchar2(200) not null,
    couponNumber clob not null,
	content clob not null,
	ratio number not null,
	createDate date default sysdate,
	usedDate date,
	dateOfUse date default sysdate+7
);
 * 
 */
public class CouponVO {
	
	private String userName;
	private String userEmail;
	private String couponNumber;
	private String content;
	private int ratio;
	private Date createDate;
	private Date usedDate;
	private Date dateOfUse;
	private int isUse;
	
	
	@Override
	public String toString() {
		return "CouponVO [userName=" + userName + ", userEmail=" + userEmail + ", couponNumber=" + couponNumber
				+ ", content=" + content + ", ratio=" + ratio + ", createDate=" + createDate + ", usedDate=" + usedDate
				+ ", dateOfUse=" + dateOfUse + ", isUse=" + isUse + "]";
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getCouponNumber() {
		return couponNumber;
	}


	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getRatio() {
		return ratio;
	}


	public void setRatio(int ratio) {
		this.ratio = ratio;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getUsedDate() {
		return usedDate;
	}


	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}


	public Date getDateOfUse() {
		return dateOfUse;
	}


	public void setDateOfUse(Date dateOfUse) {
		this.dateOfUse = dateOfUse;
	}


	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}
	
}
