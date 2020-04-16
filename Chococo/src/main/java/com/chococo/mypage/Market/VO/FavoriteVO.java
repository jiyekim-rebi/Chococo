package com.chococo.mypage.Market.VO;

import java.util.Date;

public class FavoriteVO {
	
	private int mainCategory;
	private int productNo;
	private String productName;
	private Date regDate;
	private String userEmail;
	private String imageFileName;
	
	
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	@Override
	public String toString() {
		return "FavoriteVO [mainCategory=" + mainCategory + ", productNo=" + productNo + ", productName=" + productName
				+ ", regDate=" + regDate + ", userEmail=" + userEmail + ", imageFileName=" + imageFileName + "]";
	}
	
}
