package com.chococo.mypage.Market.VO;

public class MarketPageCriteria {
	
	//Main Category
	private int mainCategory;
	//sub Category
	private int subCategory;
	//sub Category의 정렬 type 지정
	private int subCategoryOrder;
	
	public MarketPageCriteria() {

	}

	public MarketPageCriteria(int mainCategory, int subCategory, int subCategoryOrder) {
		super();
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
		this.subCategoryOrder = subCategoryOrder;
	}

	@Override
	public String toString() {
		return "MarketPageCriteria [mainCategory=" + mainCategory + ", subCategory=" + subCategory
				+ ", subCategoryOrder=" + subCategoryOrder + "]";
	}

	public int getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(int mainCategory) {
		this.mainCategory = mainCategory;
	}

	public int getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(int subCategory) {
		this.subCategory = subCategory;
	}

	public int getSubCategoryOrder() {
		return subCategoryOrder;
	}

	public void setSubCategoryOrder(int subCategoryOrder) {
		this.subCategoryOrder = subCategoryOrder;
	}
	
}
