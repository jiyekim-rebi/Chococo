package com.chococo.mypage.Market.DAO;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.chococo.mypage.Market.VO.BasketVO;
import com.chococo.mypage.Market.VO.FavoriteVO;
import com.chococo.mypage.Market.VO.MarketPageCriteria;
import com.chococo.mypage.Market.VO.MarketVO;
import com.chococo.mypage.Market.VO.ReviewVO;
import com.chococo.mypage.Member.VO.MemberVO;

@Repository
public class MarketDAOImpl implements MarketDAO {
	
	@Inject
	private SqlSession session;

	@Override
	public List<MarketVO> selectListMainCategory(MarketPageCriteria mpc) {
		return session.selectList("Chococo.market.selectListMainCategory", mpc);
	}

	@Override
	public int totalOfitem(MarketPageCriteria mpc) {
		return session.selectOne("Chococo.market.totalOfitem", mpc);
	}

	@Override
	public MarketVO productDetail(Map<String, Object> detail) {
		return session.selectOne("Chococo.market.productDetail", detail);
	}

	@Override
	public void memberOrder(BasketVO basket) {
		session.insert("Chococo.market.memberOrder", basket);
	}

	@Override
	public void orderHit(BasketVO basket) {
		session.update("Chococo.market.orderHit", basket);
	}

	@Override
	public int orderNoSetting() {
		return session.selectOne("Chococo.market.orderNoSetting");
	}

	@Override
	public List<ReviewVO> reviewList(Map<String, Object> detail) {
		return session.selectList("Chococo.market.reviewList", detail);
	}

	@Override
	public void reviewModify(ReviewVO review) {
		session.update("Chococo.market.reviewModify", review);
	}

	@Override
	public void reviewDelete(ReviewVO review) {
		session.delete("Chococo.market.reviewDelete", review);
	}

	@Override
	public void reviewInsert(ReviewVO review) {
		session.insert("Chococo.market.reviewInsert", review);
	}

	@Override
	public List<BasketVO> mypageOrderList(MemberVO member) {
		return session.selectList("Chococo.market.mypageOrderList", member);
	}

	@Override
	public List<ReviewVO> myProductReview(MemberVO member) {
		return session.selectList("Chococo.market.myProductReview", member);
	}

	@Override
	public int favoriteSearch(FavoriteVO favorite) {
		return session.selectOne("Chococo.market.favoriteSearch", favorite);
	}

	@Override
	public void favoriteAdd(FavoriteVO favorite) {
		session.insert("Chococo.market.favoriteAdd", favorite);
	}

	@Override
	public void favoriteDel(FavoriteVO favorite) {
		session.delete("Chococo.market.favoriteDel", favorite);
	}

	@Override
	public List<FavoriteVO> favoriteList(MemberVO member) {
		return session.selectList("Chococo.market.favoriteList", member);
	}

	@Override
	public List<MarketVO> recommendProducts() throws Exception {
		return session.selectList("Chococo.market.recommendProducts");
	}

	@Override
	public List<MarketVO> newProducts() throws Exception {
		return session.selectList("Chococo.market.newProducts");
	}

	@Override
	public List<MarketVO> trandingProducts() throws Exception {
		return session.selectList("Chococo.market.trandingProducts");
	}

}
