package com.chococo.mypage.Admin.Service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chococo.mypage.Admin.DAO.AdminDAO;
import com.chococo.mypage.Admin.VO.ProductVO;
import com.chococo.mypage.Common.VO.PageCriteria;
import com.chococo.mypage.Community.VO.CommunityVO;
import com.chococo.mypage.Market.VO.BasketVO;
import com.chococo.mypage.Member.VO.CouponVO;
import com.chococo.mypage.Member.VO.MemberVO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Inject
	private AdminDAO admin;

	@Override
	public void marketProductAdd(Map<String, Object> insert) throws Exception {
		admin.marketProductAdd(insert);
	}

	@Override
	public List<ProductVO> productList(PageCriteria cri) throws Exception {
		return admin.productList(cri);
	}

	@Override
	public int productListCount(PageCriteria cri) throws Exception {
		return admin.productListCount(cri);
	}

	@Override
	public ProductVO productView(Map<String, Object> setting) throws Exception {
		return admin.productView(setting);
	}

	@Override
	public void productModify(ProductVO product) throws Exception {
		admin.productModify(product);
	}

	@Override
	public void productDelete(ProductVO product) throws Exception {
		admin.productDelete(product);
	}

	@Override
	public List<BasketVO> shippingList(String shippingStatus) throws Exception {
		return admin.shippingList(shippingStatus);
	}

	@Override
	public List<BasketVO> shippingReadyView(BasketVO shipping) throws Exception {
		return admin.shippingReadyView(shipping);
	}

	@Override
	public void orderModify(BasketVO order) throws Exception {
		admin.orderModify(order);
	}

	@Override
	public void trackingUpdate(BasketVO order) throws Exception {
		admin.trackingUpdate(order);
	}

	@Override
	public void trackingSuccess(BasketVO order) throws Exception {
		admin.trackingSuccess(order);
	}

	@Override
	public List<BasketVO> orderSuccessList() throws Exception {
		return admin.orderSuccessList();
	}

	@Override
	public List<CommunityVO> noticeList(PageCriteria cri) throws Exception {
		return admin.noticeList(cri);
	}

	@Override
	public void noticeInsert(CommunityVO notice) throws Exception {
		admin.noticeInsert(notice);
	}

	@Override
	public CommunityVO noticeView(CommunityVO notice) throws Exception {
		return admin.noticeView(notice);
	}

	@Override
	public void noticeModify(CommunityVO notice) throws Exception {
		admin.noticeModify(notice);
	}

	@Override
	public void noticeDelete(CommunityVO notice) throws Exception {
		admin.noticeDelete(notice);
	}

	@Override
	public int noticeListCount() throws Exception {
		return admin.noticeListCount();
	}

	@Override
	public MemberVO memberSelectResult(MemberVO member) throws Exception {
		return admin.memberSelectResult(member);
	}

	@Override
	public void adminMemberBanUpdate(MemberVO member) throws Exception {
		admin.adminMemberBanUpdate(member);
	}

	@Override
	public void adminMemberInfoUpdate(MemberVO member) throws Exception {
		admin.adminMemberInfoUpdate(member);
	}

	@Override
	public void adminMemberDelete(MemberVO member) throws Exception {
		admin.adminMemberDelete(member);
	}

	@Override
	public void adminCouponCreate(CouponVO coupon) throws Exception {
		admin.adminCouponCreate(coupon);
	}

	@Override
	public void adminCouponNotUse(CouponVO coupon) throws Exception {
		admin.adminCouponNotUse(coupon);
	}

	@Override
	public List<CouponVO> adminCouponList(PageCriteria cri) throws Exception {
		return admin.adminCouponList(cri);
	}

	@Override
	public int adminCouponCount() throws Exception {
		return admin.adminCouponCount();
	}

	@Override
	public int adminCouponCheck(CouponVO coupon) throws Exception {
		return admin.adminCouponCheck(coupon);
	}

	@Override
	public void productReviewDelete(ProductVO product) throws Exception {
		admin.productReviewDelete(product);
	}

}
