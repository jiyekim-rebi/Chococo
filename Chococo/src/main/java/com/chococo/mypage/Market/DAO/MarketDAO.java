package com.chococo.mypage.Market.DAO;

import java.util.List;
import java.util.Map;

import com.chococo.mypage.Market.VO.BasketVO;
import com.chococo.mypage.Market.VO.FavoriteVO;
import com.chococo.mypage.Market.VO.MarketPageCriteria;
import com.chococo.mypage.Market.VO.MarketVO;
import com.chococo.mypage.Market.VO.ReviewVO;
import com.chococo.mypage.Member.VO.MemberVO;

public interface MarketDAO {
	//메인 카테고리에 따라 모든 메인 카테고리에 등록된 정보 불러오기
	public List<MarketVO> selectListMainCategory(MarketPageCriteria mpc);
	//total of item
	public int totalOfitem(MarketPageCriteria mpc);
	//productDetail
	public MarketVO productDetail(Map<String, Object> detail);
	//member - order
	public void memberOrder(BasketVO basket);
	//order 주문 후 marketVO 부분 hit 처리
	public void orderHit(BasketVO basket);
	//orderNo Setting
	public int orderNoSetting();
	//detailView - 리뷰 불러오기
	public List<ReviewVO> reviewList(Map<String, Object> detail);
	//detailView - 리뷰 수정하기
	public void reviewModify(ReviewVO review);
	//detailView - 리뷰 삭제하기
	public void reviewDelete(ReviewVO review);
	//detailView - 리뷰 등록하기
	public void reviewInsert(ReviewVO review);
	//mypage - 주문한 상품 불러오기
	public List<BasketVO> mypageOrderList(MemberVO member);
	//mypage - 내가 작성한 리뷰 불러오기
	public List<ReviewVO> myProductReview(MemberVO member);
	//favorite mapper 관련 - 검색, 삭제, 등록
	public int favoriteSearch(FavoriteVO favorite);
	public void favoriteAdd(FavoriteVO favorite);
	public void favoriteDel(FavoriteVO favorite);
	//찜한 상품 불러오기
	public List<FavoriteVO> favoriteList(MemberVO member);
	
	//market-main 화면 부분
	public List<MarketVO> recommendProducts() throws Exception;
	public List<MarketVO> newProducts() throws Exception;
	public List<MarketVO> trandingProducts() throws Exception;
	
	//2020.04.22 상품 구매자만 리뷰 작성할 수 있게
	public int reviewProductCheck(ReviewVO review) throws Exception;
}
