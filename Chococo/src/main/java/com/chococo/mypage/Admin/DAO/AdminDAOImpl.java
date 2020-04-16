package com.chococo.mypage.Admin.DAO;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.chococo.mypage.Admin.VO.ProductVO;
import com.chococo.mypage.Common.VO.PageCriteria;
import com.chococo.mypage.Community.VO.CommunityVO;
import com.chococo.mypage.Market.VO.BasketVO;
import com.chococo.mypage.Member.VO.CouponVO;
import com.chococo.mypage.Member.VO.MemberVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Inject
	private SqlSession session;

	@Override
	public void marketProductAdd(Map<String, Object> insert) {
		session.insert("Chococo.admin.marketProductAdd", insert);
	}

	@Override
	public List<ProductVO> productList(PageCriteria cri) throws Exception {
		return session.selectList("Chococo.admin.productList", cri);
	}

	@Override
	public int productListCount(PageCriteria cri) throws Exception {
		return session.selectOne("Chococo.admin.productListCount", cri);
	}

	@Override
	public ProductVO productView(Map<String, Object> setting) throws Exception {
		return session.selectOne("Chococo.admin.productView", setting);
	}

	@Override
	public void productModify(ProductVO product) throws Exception {
		session.update("Chococo.admin.productModify", product);
	}

	@Override
	public void productDelete(ProductVO product) throws Exception {
		session.delete("Chococo.admin.productDelete", product);
	}

	@Override
	public List<BasketVO> shippingList(String shippingStatus) throws Exception {
		return session.selectList("Chococo.admin.shippingList", shippingStatus);
	}

	@Override
	public List<BasketVO> shippingReadyView(BasketVO shipping) throws Exception {
		return session.selectList("Chococo.admin.shippingReadyView", shipping);
	}

	@Override
	public void orderModify(BasketVO order) throws Exception {
		session.update("Chococo.admin.orderModify", order);
	}

	@Override
	public void trackingUpdate(BasketVO order) throws Exception {
		session.update("Chococo.admin.trackingUpdate", order);
	}

	@Override
	public void trackingSuccess(BasketVO order) throws Exception {
		session.update("Chococo.admin.trackingSuccess", order);
	}

	@Override
	public List<BasketVO> orderSuccessList() throws Exception {
		return session.selectList("Chococo.admin.orderSuccessList");
	}

	@Override
	public List<CommunityVO> noticeList(PageCriteria cri) throws Exception {
		return session.selectList("Chococo.admin.noticeList", cri);
	}

	@Override
	public void noticeInsert(CommunityVO notice) throws Exception {
		session.insert("Chococo.admin.noticeInsert", notice);
	}

	@Override
	public CommunityVO noticeView(CommunityVO notice) throws Exception {
		return session.selectOne("Chococo.admin.noticeView", notice);
	}

	@Override
	public void noticeModify(CommunityVO notice) throws Exception {
		session.update("Chococo.admin.noticeModify", notice);
	}

	@Override
	public void noticeDelete(CommunityVO notice) throws Exception {
		session.delete("Chococo.admin.noticeDelete", notice);
	}

	@Override
	public int noticeListCount() throws Exception {
		return session.selectOne("Chococo.admin.noticeListCount");
	}

	@Override
	public MemberVO memberSelectResult(MemberVO member) throws Exception {
		return session.selectOne("Chococo.admin.memberSelectResult", member);
	}

	@Override
	public void adminMemberBanUpdate(MemberVO member) throws Exception {
		session.update("Chococo.admin.adminMemberBanUpdate", member);
	}

	@Override
	public void adminMemberInfoUpdate(MemberVO member) throws Exception {
		session.update("Chococo.admin.adminMemberInfoUpdate", member);
	}

	@Override
	public void adminMemberDelete(MemberVO member) throws Exception {
		session.delete("Chococo.admin.adminMemberDelete", member);
	}

	@Override
	public void adminCouponCreate(CouponVO coupon) throws Exception {
		session.insert("Chococo.admin.adminCouponCreate", coupon);
	}

	@Override
	public void adminCouponNotUse(CouponVO coupon) throws Exception {
		session.update("Chococo.admin.adminCouponNotUse", coupon);
	}

	@Override
	public List<CouponVO> adminCouponList(PageCriteria cri) throws Exception {
		return session.selectList("Chococo.admin.adminCouponList", cri);
	}

	@Override
	public int adminCouponCount() throws Exception {
		return session.selectOne("Chococo.admin.adminCouponCount");
	}

	@Override
	public int adminCouponCheck(CouponVO coupon) throws Exception {
		return session.selectOne("Chococo.admin.adminCouponCheck", coupon);
	}

}
