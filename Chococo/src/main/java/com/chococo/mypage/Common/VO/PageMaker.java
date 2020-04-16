package com.chococo.mypage.Common.VO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	//게시글 전체 개수
	private int totalCount;
	//시작 페이지 (1-10 이면 1, 11-20이면 11 이런식?)
	private int startPage;
	//끝페이지(이게 꼭 10, 20, 30으로 끝나지 않음. 4나 7, 8등으로 끝날 수 있음.)
	private int endPage;
	//이전페이지
	private boolean prev;
	//다음페이지
	private boolean next;
	//페이지 블럭! 한번 보여줄때 블럭 몇개 생성할 것인가.
	//10이면 [이전] 1 2 3 4 5 6 7 8 9 10 [다음]
	//5면 [이전] 1 2 3 4 5 [다음]
	private int displayPageNum = 5;
	//Criteria :)
	private PageCriteria cri;

	public PageMaker()
	{

	}

	public void setCri(PageCriteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public PageCriteria getCri() {
		return cri;
	}

	private void calcData() {
		//현재 페이지에서 displayPageNum 값 만큼 나눈 다음 다시 곱해준걸 int형으로 깔쌈하게 처리
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	//게시글 아래부분 pageMaker 새로고침 하는 부분.
	public String makeQuery(int page) {
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();

		return uriComponents.toUriString();
	}

	//pageMaker 처리
	public String makeMaker(int page)
	{
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("categoryNo", cri.getCategoryNo())
				.queryParam("searchType", cri.getSearchType())
				.queryParam("keyword", encoding(cri.getKeyword()))
				.build(); 
		return uriComponents.toUriString();  
	}
	
	public String makeMakerReply(int page, int boardNo)
	{
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("boardNo", boardNo)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("categoryNo", cri.getCategoryNo())
				//얘... 다시 search로 돌아갈일 있을것 같아서 존속시킬려고.
				.queryParam("searchType", cri.getSearchType())
				.queryParam("keyword", encoding(cri.getKeyword()))
				.build(); 
		return uriComponents.toUriString();  
	}
	
	public String makeMakerMarketProduct(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("keyword", encoding(cri.getKeyword()))
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("mainCategory", cri.getMainCategory())
				.build();
		
		return uriComponents.toUriString();
	}
	
	public String makeMakerAdminCommunity(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("keyword", encoding(cri.getKeyword()))
				.build();
		
		return uriComponents.toUriString();
	}

	private String encoding(String keyword) {
		//빈칸이거나 null값 들어오면 인코딩 따로 안해도 됨.
		if(keyword == null || keyword.trim().length() == 0) { 
			return "";
		}		 
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch(UnsupportedEncodingException e) { 
			return ""; 
		}
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
	
}
