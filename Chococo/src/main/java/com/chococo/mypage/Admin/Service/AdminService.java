package com.chococo.mypage.Admin.Service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chococo.mypage.Admin.VO.ProductVO;
import com.chococo.mypage.Common.VO.PageCriteria;
import com.chococo.mypage.Community.VO.CommunityVO;
import com.chococo.mypage.Market.VO.BasketVO;
import com.chococo.mypage.Member.VO.CouponVO;
import com.chococo.mypage.Member.VO.MemberVO;

public interface AdminService {
	//마켓에 상품 추가
	public void marketProductAdd(Map<String, Object> insert) throws Exception;
	//상품목록 불러오기
	public List<ProductVO> productList(PageCriteria cri) throws Exception;
	//상품목록 pageMaker totalCount setting
	public int productListCount(PageCriteria cri) throws Exception;
	//productView
	public ProductVO productView(Map<String, Object> setting) throws Exception;
	//productModify
	public void productModify(ProductVO product) throws Exception;
	//productDelete
	public void productDelete(ProductVO product) throws Exception;
	
	//shipping - orderNo에 따른 select
	public List<BasketVO> shippingList(String shippingStatus) throws Exception;
	//shipping - orderNo 누르면 나오는 주문 상세정보 보기 창
	public List<BasketVO> shippingReadyView(BasketVO shipping) throws Exception;
	//shipping - address, amount, selectOpton 변경시
	public void orderModify(BasketVO order) throws Exception;
	//송장번호 업데이트 + 배송중 상태 변경
	public void trackingUpdate(BasketVO order) throws Exception;
	//배송완료
	public void trackingSuccess(BasketVO order) throws Exception;
	//배송완료 리스트 출력
	public List<BasketVO> orderSuccessList() throws Exception;
	
	//커뮤니티 공지 불러오기
	public List<CommunityVO> noticeList(PageCriteria cri) throws Exception;
	//커뮤니티 전체 공지 갯수 설정 - pageMaker
	public int noticeListCount() throws Exception;
	//커뮤니티 공지 등록
	public void noticeInsert(CommunityVO notice) throws Exception;
	//CommunityView로 데이터 불러오기
	public CommunityVO noticeView(CommunityVO notice) throws Exception;
	//공지 수정
	public void noticeModify(CommunityVO notice) throws Exception;
	//공지 삭제
	public void noticeDelete(CommunityVO notice) throws Exception;
	
	//멤버 정보 가져오기 - memberList.jsp
	public MemberVO memberSelectResult(MemberVO member) throws Exception;
	//멤버 밴 유무 변경
	public void adminMemberBanUpdate(MemberVO member) throws Exception;
	//멤버 정보변경
	public void adminMemberInfoUpdate(MemberVO member) throws Exception;
	//멤버탈퇴
	public void adminMemberDelete(MemberVO member) throws Exception;
	
	//쿠폰 생성
	public void adminCouponCreate(CouponVO coupon) throws Exception;
	//쿠폰 발행 중단(재발행은 현재로썬 기획할 생각 없음.)
	public void adminCouponNotUse(CouponVO coupon) throws Exception;
	//쿠폰 번호들 불러오기 - pageMaker 처리 할꺼에요.
	public List<CouponVO> adminCouponList(PageCriteria cri) throws Exception;
	//pageMaker 처리를 위한 count 체킹
	public int adminCouponCount() throws Exception;
	//쿠폰 중복여부 체크
	public int adminCouponCheck(CouponVO coupon) throws Exception;
	
	//2020.04.16 상품삭제시 리뷰도 같이 제거
	public void productReviewDelete(ProductVO product) throws Exception;
	
}
