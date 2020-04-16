package com.chococo.mypage.Market.Service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chococo.mypage.Market.DAO.MarketDAO;
import com.chococo.mypage.Market.VO.BasketVO;
import com.chococo.mypage.Market.VO.FavoriteVO;
import com.chococo.mypage.Market.VO.MarketPageCriteria;
import com.chococo.mypage.Market.VO.MarketVO;
import com.chococo.mypage.Market.VO.ReviewVO;
import com.chococo.mypage.Member.VO.MemberVO;

@Service
public class MarketServiceImpl implements MarketService {
	
	@Inject
	private MarketDAO marketDAO;

	@Override
	public List<MarketVO> selectListMainCategory(MarketPageCriteria mpc) {
		return marketDAO.selectListMainCategory(mpc);
	}

	@Override
	public int totalOfitem(MarketPageCriteria mpc) {
		return marketDAO.totalOfitem(mpc);
	}

	@Override
	public MarketVO productDetail(Map<String, Object> detail) {
		return marketDAO.productDetail(detail);
	}

	@Override
	@Transactional
	public void memberOrder(BasketVO basket) {
		marketDAO.memberOrder(basket);
		marketDAO.orderHit(basket);
	}

	@Override
	public int orderNoSetting() {
		return marketDAO.orderNoSetting();
	}

	@Override
	public void orderHit(BasketVO basket) {
		marketDAO.orderHit(basket);
	}

	@Override
	public List<ReviewVO> reviewList(Map<String, Object> detail) {
		return marketDAO.reviewList(detail);
	}

	@Override
	public void reviewModify(ReviewVO review) {
		marketDAO.reviewModify(review);
	}

	@Override
	public void reviewDelete(ReviewVO review) {
		marketDAO.reviewDelete(review);
	}

	@Override
	public void reviewInsert(ReviewVO review) {
		marketDAO.reviewInsert(review);
	}

	@Override
	public List<BasketVO> mypageOrderList(MemberVO member) {
		return marketDAO.mypageOrderList(member);
	}

	@Override
	public List<ReviewVO> myProductReview(MemberVO member) {
		return marketDAO.myProductReview(member);
	}

	@Override
	public int favoriteSearch(FavoriteVO favorite) {
		return marketDAO.favoriteSearch(favorite);
	}

	@Override
	public void favoriteAdd(FavoriteVO favorite) {
		marketDAO.favoriteAdd(favorite);
	}

	@Override
	public void favoriteDel(FavoriteVO favorite) {
		marketDAO.favoriteDel(favorite);
	}

	@Override
	public List<FavoriteVO> favoriteList(MemberVO member) {
		return marketDAO.favoriteList(member);
	}

	@Override
	public List<MarketVO> recommendProducts() throws Exception {
		return marketDAO.recommendProducts();
	}

	@Override
	public List<MarketVO> newProducts() throws Exception {
		return marketDAO.newProducts();
	}

	@Override
	public List<MarketVO> trandingProducts() throws Exception {
		return marketDAO.trandingProducts();
	}

}
