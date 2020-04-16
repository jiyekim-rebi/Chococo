package com.chococo.mypage.Common.VO;

public class PageCriteria {

	//현재 보고있는 페이지
	private int page;
	//한 페이지당 몇개의 게시글을 검색해서 model로 보낼지 정함
	private int perPageNum;
	//1~5, 6~10.. 등 전체 페이지 중 몇번째 구간 게시글 출력
	private int rowStart;
	private int rowEnd;
	//카테고리 넘버
	private int categoryNo;
	
	//검색창 - 검색 타입
	private String searchType = "";
	//검색창 - 검색하고자 하는 단어
	private String keyword = "";
	
	//메인 카테고리
	private int mainCategory;

	@Override
	public String toString() {
		return "PageCriteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd="
				+ rowEnd + ", categoryNo=" + categoryNo + ", searchType=" + searchType + ", keyword=" + keyword
				+ ", mainCategory=" + mainCategory + "]";
	}

	public int getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(int mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	//생성자
	public PageCriteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getRowStart() {
		rowStart = ((page - 1) * perPageNum) + 1;
		return rowStart;
	}
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	public int getRowEnd() {
		rowEnd = rowStart + perPageNum - 1;
		return rowEnd;
	}
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}
	
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}
	

}
