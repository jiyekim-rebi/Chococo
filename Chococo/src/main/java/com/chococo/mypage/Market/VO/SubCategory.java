package com.chococo.mypage.Market.VO;

public class SubCategory {
	
	private String href;
	private String subCategoryName;
	
	public SubCategory() {
		
	}
	
	public SubCategory(String href, String subCategoryName) {
		super();
		this.href = href;
		this.subCategoryName = subCategoryName;
	}
	
	@Override
	public String toString() {
		return "SubCategory [href=" + href + ", subCategoryName=" + subCategoryName + "]";
	}
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}


}
