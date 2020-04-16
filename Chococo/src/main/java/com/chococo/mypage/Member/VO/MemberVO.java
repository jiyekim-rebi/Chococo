package com.chococo.mypage.Member.VO;

import java.util.Date;

/*
 * 
create table chococo_member (
	userName varchar2(100) not null,
	userEmail varchar2(200) primary key not null,
	userPass varchar2(200) not null,
	birth_year varchar2(10) not null,
	birth_month varchar2(10) not null,
	birth_day varchar2(10) not null,
	sex varchar2(10) not null,
	joinDate Date default sysdate
	address varchar2(300),
	isAdmin number default 0,
	isBan number default 0
);

 * 
 */

public class MemberVO {
	
	private String userName;
	//이메일을 아이디 대용으로 씀.
	private String userEmail;
	private String userPass;
	private String birth_year;
	private String birth_month;
	private String birth_day;
	private String sex;
	private Date joinDate;
	private String address;
	//어드민 유무 - 1이면 어드민계정
	private int isAdmin;
	//로그인 금지 계정 유무 - 1이면 밴먹음
	private int isBan;
	
	@Override
	public String toString() {
		return "MemberVO [userName=" + userName + ", userEmail=" + userEmail + ", userPass=" + userPass
				+ ", birth_year=" + birth_year + ", birth_month=" + birth_month + ", birth_day=" + birth_day + ", sex="
				+ sex + ", joinDate=" + joinDate + ", address=" + address + ", isAdmin=" + isAdmin + ", isBan=" + isBan
				+ "]";
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

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getBirth_year() {
		return birth_year;
	}

	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}

	public String getBirth_month() {
		return birth_month;
	}

	public void setBirth_month(String birth_month) {
		this.birth_month = birth_month;
	}

	public String getBirth_day() {
		return birth_day;
	}

	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getIsBan() {
		return isBan;
	}

	public void setIsBan(int isBan) {
		this.isBan = isBan;
	}
			
}
