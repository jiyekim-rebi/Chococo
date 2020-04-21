package com.chococo.mypage.Market.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chococo.mypage.Market.Service.MarketService;
import com.chococo.mypage.Market.VO.BasketList;
import com.chococo.mypage.Market.VO.BasketVO;
import com.chococo.mypage.Market.VO.FavoriteVO;
import com.chococo.mypage.Market.VO.MarketPageCriteria;
import com.chococo.mypage.Market.VO.MarketVO;
import com.chococo.mypage.Market.VO.ReviewVO;
import com.chococo.mypage.Market.VO.SubCategory;
import com.chococo.mypage.Member.Service.MemberService;
import com.chococo.mypage.Member.VO.CouponVO;
import com.chococo.mypage.Member.VO.MemberVO;

@Controller
@RequestMapping("/market/*")
public class MarketController {
	
	@Inject
	private MarketService market;
	@Inject
	private MemberService member;
	
	private static final Logger logger = LoggerFactory.getLogger(MarketController.class);

	//Market- main화면
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main(Model model) throws Exception {
		logger.info("Market main 진입");
		
		//trand item등 model로 쏴주기.
		model.addAttribute("recommendProducts", market.recommendProducts());
		model.addAttribute("newProducts", market.newProducts());
		model.addAttribute("trandingProducts", market.trandingProducts());
		
		return "market/main";
	}
	
	@RequestMapping(value="/productList", method=RequestMethod.GET)
	public String productList(Model model, MarketPageCriteria mpc, MarketVO marketVO) throws Exception {
		logger.info("productList : " + mpc.getMainCategory() + "에 진입");
		logger.info("productList - " + mpc.toString());

		//첫진입일때는 서브카테고리 1번으로 처리.
		if(mpc.getSubCategory() == 0) {
			mpc.setSubCategory(1);
		}
		//신상품, 상품명, 낮은가격, 높은가격, 인기상품순으로 처리할 것.
		//첫 진입때는 신상품 순으로
		if(mpc.getSubCategoryOrder() == 0) {
			mpc.setSubCategoryOrder(1);
		}
		
		//나머지 부분은 서브 카테고리에 맞게끔 데이터 가져올 것.
		//mpc에서 메인 카테고리 sql문을 맞추고, where subCategory의 정보를 marketVO에서 가져와서 한번 검색한 후 view로 보낼 것.	
		model.addAttribute("AllProducts", market.selectListMainCategory(mpc));
				
		//mainCategory name, subCategory name 설정
		model.addAttribute("mainCategoryName", mainCategoryName(mpc.getMainCategory()));
		model.addAttribute("subCategoryName", subCategoryName(mpc.getMainCategory(), mpc.getSubCategory()));

		//model
		//mainCategory - subCategory에 등록된 모든 아이템들
		model.addAttribute("totalOfitem", market.totalOfitem(mpc));
		model.addAttribute("subCategoryList", subCategoryList(mpc.getMainCategory()));
		
		model.addAttribute("mpc", mpc);
		return "market/productList";
	}
	
	//productDetail. get
	//여기서 장바구니로 넣는 controller, 바로 결제하는 창으로 가는 controller 로 연결할 것
	@RequestMapping(value="/productDetail", method=RequestMethod.GET)
	public String productDetail(Model model, HttpSession session, MarketVO marketVO, MarketPageCriteria mpc) throws Exception {
		logger.info("productDetail - 상품번호 : " + marketVO.getProductNo());
		
		Map<String, Object> detail = new HashMap<>();
		detail.put("mainCategory", mpc.getMainCategory());
		detail.put("marketVO", marketVO);
		MarketVO product = market.productDetail(detail);
		System.out.println(product.toString());
		
		model.addAttribute("product", product);
		
		//2020.03.23 select 부분 따로 model 설정할 것
		model.addAttribute("selectOption", product.getSelectOption().split(","));
		model.addAttribute("mpc", mpc);
		
		List<ReviewVO> reviewList = market.reviewList(detail);
		if(reviewList.size() > 0) {
			model.addAttribute("reviewList", reviewList);
		}
		
		//2020.03.26 최근에 본 상품
		MemberVO member = (MemberVO) session.getAttribute("isLogin");
		
		//로그인 세션이 있을 경우 최근에 본 상품 추가해주기.
		if(member != null) {
			BasketVO basket = new BasketVO();
			basket.setProductName(product.getProductName());
			basket.setMainCategory(mpc.getMainCategory());
			basket.setProductNo(product.getProductNo());
			basket.setImageFileName(product.getImageFileName());
			@SuppressWarnings("unchecked")
			List<BasketVO> recentList = (List<BasketVO>)session.getAttribute("recentList");
			
			//세션을 처음 생성할 때.
			if(recentList == null) {
				recentList = new ArrayList<BasketVO>();
				recentList.add(basket);
				session.setAttribute("recentList", recentList);
			} else {
				boolean temp = false;
				for(int i = 0; i<recentList.size(); i++) {
					if(recentList.get(i).getProductName().equals(basket.getProductName())
							&& recentList.get(i).getMainCategory() == basket.getMainCategory()
							&& recentList.get(i).getProductNo() == basket.getProductNo()){
						temp = true;
					}
				}
				//temp가 false 유지일 때 = 최근 본 상품에 등록되어 있지 않음.
				if(temp == false) {
					//최근에 본 상품 추가하는데 size가 5개가 넘어갈 경우 0번 인덱스 지우고 add
					//테스트용으로 5개까지만 하고 20개로 늘려주세요.
					if(recentList.size() == 5) {
						recentList.remove(0);
						recentList.add(basket);
					} else {
						recentList.add(basket);
					}
				}
				session.removeAttribute("recentList");
				session.setAttribute("recentList", recentList);
			}
		}
		return "market/productDetail";
	}
	
	//결제 form
	//2020.04.05 장바구니, 즉시구매 간소화 처리 
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/checkOut", method={RequestMethod.GET, RequestMethod.POST})
	public String checkOut(Model model, HttpSession session, BasketVO basketVO, CouponVO couponVO) throws Exception {
		logger.info("checkOut - 결제 view");
		logger.info("basketVO check : " + basketVO.toString());
		logger.info("coupon 등록 유무 확인하기 " + couponVO.toString());
	
		List<BasketVO> cartList = (List<BasketVO>)session.getAttribute("cartList");
		//장바구니 비어있는지 차있는지 모르겠는데 mypage에서 재주문으로 들어왔을 땐 orderNo값도 있을테니.
		//orderNo값이 0보다 클 경우, 즉 값이 있을 경우에는 mypage에서 재주문 버튼 눌러서 들어온것이니 단일주문 처리.
		if(cartList == null || basketVO.getOrderNo() > 0 || basketVO.getProductName() != null) {
			//cartList가 null일 경우
			//여기서 세션 등록을 해버려야 할 
			cartList = new ArrayList<BasketVO>();
			cartList.add(basketVO);
		}
		
		if(cartList != null && cartList.size() != 0) {
			model.addAttribute("orderList", cartList);
		}
		
		//회원일 경우 보유 쿠폰 리스트 불러오기
		MemberVO isLogin = (MemberVO)session.getAttribute("isLogin");
		if(isLogin != null) {
			List<CouponVO> coupon = member.mypageCouponList(isLogin);
			if(coupon.size()>0) {
				model.addAttribute("coupon", coupon);
			}
		}
		
		//상품 총 금액 가격 지정하기
		int basketPrice = 0;
		//totalPrice 정하기 (할인율 들어가는 경우, 배송비 포함하는 경우)
		int totalPrice = 0;
		//할인율 있을 때
		int ratio = 0;
		
		for(int i = 0; i<cartList.size(); i++) {
			basketPrice += cartList.get(i).getPrice() * cartList.get(i).getAmount();
		}
		
		
		totalPrice = basketPrice;
		model.addAttribute("basketPrice", basketPrice);
		
		//쿠폰 사용 유무 체킹
		//사용버튼 눌렀을 때 이 컨트롤러에서 model 설정 -> 적용
		if(couponVO.getRatio() > 0 ) {
			ratio = couponVO.getRatio();
			model.addAttribute("couponNumber", couponVO.getCouponNumber());
		}
		
		model.addAttribute("ratio", ratio);
		
		if(ratio > 0) {
			totalPrice -= totalPrice * (double)ratio/100;
		}
		
		totalPrice += 3000;
		model.addAttribute("totalPrice", totalPrice);
		
		return "market/checkOut";
	}
	
	//cart - list
	@RequestMapping(value="/cart", method=RequestMethod.GET)
	public String cart(Model model, HttpSession session) throws Exception {
		
		//session 가져와서 뿌릴 것.
		@SuppressWarnings("unchecked")
		List<BasketVO> cartList = (List<BasketVO>)session.getAttribute("cartList");
		model.addAttribute("cartList", cartList);
		
		return "market/cart";
	}
	
	//결제처리 하기!
	//2020.03.26 결제처리 부분 로직 수정 - amount 변경값에 따라 다르게 받겠습니다.
	//2020.03.31 비회원, 회원 구분하는 DB를 통합함에 따라 try문안에 코드가 확 줄었음.
	//로그인 세션 유무에 따라 memberExists값을 for문을 돌려서 세팅하고(왜냐면 애시당초 받아올때 list로 받아오니까)
	//for문을 통해 장바구니, 단일주문 구분 없이 db로 전송함.
	@RequestMapping(value="/order", method=RequestMethod.POST)
	public String order(Model model, HttpSession session, BasketList basketLists, MemberVO memberVO, 
			CouponVO couponVO, @RequestParam("totalPrice") int totalPrice) throws Exception {
		logger.info("order - 결제처리하기");
		logger.info("결제 유저 정보 확인하기 : " + memberVO.toString());
		logger.info("coupon 정보 확인하기 : " + couponVO.toString());
		logger.info("총 결제금액 : " + totalPrice);
		
		//장바구니, 또는 즉시결제 정보 가져오기.
		List<BasketVO> order = basketLists.getBasketLists();
		MemberVO isLogin = (MemberVO)session.getAttribute("isLogin");
		
		//orderNo 지정
		int orderNoSetting = market.orderNoSetting();

		try {
			//isLogin이 비어있지 않은 경우 = 회원이 주문함.
			if(isLogin != null) {
				//회원 주문 - setMemberExists 1로 설정.
				for(int i = 0; i<order.size(); i++) {
					order.get(i).setMemberExists(1);
				}
			} else {
				//비회원 주문 - setMemberExists = 0 으로 설정
				for(int i = 0; i<order.size(); i++) {
					order.get(i).setMemberExists(0);
				}
			}
	
			for(int i = 0; i<order.size(); i++) {
				order.get(i).setOrderNo(orderNoSetting);
				order.get(i).setAddress(memberVO.getAddress());
				order.get(i).setUserName(memberVO.getUserName());
				order.get(i).setUserEmail(memberVO.getUserEmail());
				market.memberOrder(order.get(i));
				market.orderHit(order.get(i));
			}
			
			//장바구니 세션이 있는 경우 세션 제거해주기
			if(session.getAttribute("cartList") != null) {
				session.removeAttribute("cartList");
			}
			
			//coupon이 들어왔을 때.
			if(couponVO.getCouponNumber() != null) {
				member.mypageCouponUse(couponVO);
			}

			model.addAttribute("msg", "주문이 완료되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "시스템 오류가 발생했습니다. 관리자에게 문의해주세요.");
		}

		model.addAttribute("url", "/chococo/market/main");		
		return "include/Result";
	}
	
	//장바구니 추가
	@RequestMapping(value="/basketAdd", method=RequestMethod.POST)
	public String basketAdd(HttpSession session, Model model, BasketVO basketVO) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<BasketVO> cartList = (List<BasketVO>)session.getAttribute("cartList");
		
		//장바구니 처음 집어넣을 때.
		if(cartList == null) {
			cartList = new ArrayList<>();
			cartList.add(basketVO);
			session.setAttribute("cartList", cartList);
		} else {
			//일단 기존에 있는 세션 삭제 후 새로 추가해서 다시등록해야함.
			//이미 등록된 상품일 경우 amount만 추가해줘야 함.
			boolean check = false;
			for(int i = 0; i<cartList.size(); i++) {
				//amount 합치는 조건
				//1. mainCategory가 같아야함, 2. productNo가 같아야 함, 3. selectOption이 같아야 함!
				if(cartList.get(i).getMainCategory() == basketVO.getMainCategory()
						&& cartList.get(i).getProductNo() == basketVO.getProductNo()
						&& cartList.get(i).getSelectOption().equals(basketVO.getSelectOption())) {
					cartList.get(i).setAmount(cartList.get(i).getAmount() + basketVO.getAmount());
					check = true;
				}
			}
			if(check == false) {
				cartList.add(basketVO);
				session.removeAttribute("cartList");
				session.setAttribute("cartList", cartList);
			}
		}
		
		model.addAttribute("msg", "상품을 장바구니에 추가하였습니다!");
		model.addAttribute("url", "/chococo/market/productList?mainCategory=" + basketVO.getMainCategory());
				
		return "include/Result";
	}
	
	//장바구니 삭제 - cart page에서.
	@RequestMapping(value="/basketDel", method=RequestMethod.POST)
	public String basketDel(HttpSession session, Model model, BasketVO basketVO) throws Exception {
		logger.info("cart에서 아이템 삭제 - " + basketVO.toString());
		
		@SuppressWarnings("unchecked")
		List<BasketVO> cartList = (List<BasketVO>)session.getAttribute("cartList");
		
		//mainCategory, productNo, selectOption 세가지가 다 같아야 삭제함.
		for(int i = 0; i<cartList.size(); i++) {
			if(cartList.get(i).getMainCategory() == basketVO.getMainCategory()
					&& cartList.get(i).getProductNo() == basketVO.getProductNo()
					&& cartList.get(i).getSelectOption().equals(basketVO.getSelectOption())) {
				cartList.remove(i);
			}
		}
		
		session.removeAttribute("cartList");
		session.setAttribute("cartList", cartList);
		
		model.addAttribute("msg", "장바구니에서 상품을 제거하였습니다.");
		model.addAttribute("url", "/chococo/market/cart");
		
		return "include/Result";
	}
	
	//결제 form에서 물품삭제가 들어왔을 때.
	@RequestMapping(value="/checkOutDelete", method=RequestMethod.POST)
	public String checkOutDelete(HttpSession session, BasketVO basketVO) throws Exception {
		//2020.03.23 이부분 수정할 것.
		logger.info("CheckOut에서 물품삭제 요청 들어옴 - " + basketVO.toString());
		System.out.println("basketVO : " + basketVO.toString());
		
		@SuppressWarnings("unchecked")
		List<BasketVO> cartList = (List<BasketVO>)session.getAttribute("cartList");
		
		//mainCategory, productNo, selectOption 세가지가 다 같아야 삭제함.
		for(int i = 0; i<cartList.size(); i++) {
			if(cartList.get(i).getMainCategory() == basketVO.getMainCategory()
					&& cartList.get(i).getProductNo() == basketVO.getProductNo()
					&& cartList.get(i).getSelectOption().equals(basketVO.getSelectOption())) {
				cartList.remove(i);
			}
		}
		
		session.removeAttribute("cartList");
		session.setAttribute("cartList", cartList);
		
		return "redirect:/market/checkOut";
	}
	
	//2020.03.25 리뷰 수정하기
	@RequestMapping(value="/reviewModify", method=RequestMethod.POST)
	public String reviewModify(ReviewVO review, Model model) throws Exception {
		logger.info("reviewModify 데이터 체크 : " + review.toString());
		
		try {
			market.reviewModify(review);
			model.addAttribute("msg", "작성하신 리뷰를 수정하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "오류가 발생했습니다. 시스템 관리자에게 문의하세요.");
		}
		
		model.addAttribute("url", "/chococo/market/productDetail?productNo=" + review.getProductNo() + "&mainCategory=" + review.getMainCategory());
		
		return "include/Result";
	}
	
	@RequestMapping(value="/reviewDelete", method=RequestMethod.POST)
	public String reviewDelete(ReviewVO review, Model model) throws Exception {
		logger.info("reviewDelete 데이터 체크 : " + review.toString());
		
		try {
			market.reviewDelete(review);
			model.addAttribute("msg", "작성하신 리뷰를 수정하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "오류가 발생했습니다. 시스템 관리자에게 문의하세요.");
		}
		
		model.addAttribute("msg", "작성하신 리뷰를 삭제하였습니다.");
		model.addAttribute("url", "/chococo/market/productDetail?productNo=" + review.getProductNo() + "&mainCategory=" + review.getMainCategory());
		
		return "include/Result";
	}
	
	@RequestMapping(value="/reviewInsert", method=RequestMethod.POST)
	public String reviewInsert(ReviewVO review, Model model) throws Exception {
		logger.info("reviewInsert 데이터 체크 : " + review.toString());

		try {
			market.reviewInsert(review);
			model.addAttribute("msg", "작성하신 리뷰를 수정하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "오류가 발생했습니다. 시스템 관리자에게 문의하세요.");
		}
		
		model.addAttribute("msg", "리뷰가 등록되었습니다.");
		model.addAttribute("url", "/chococo/market/productDetail?productNo=" + review.getProductNo() + "&mainCategory=" + review.getMainCategory());
		
		return "include/Result";
	}
	
	
	//2020.03.29 DB를 이용한 찜하기 구현
	//isLogin 세션에서 userEmail 가져와서 db로 보낼 것.
	@ResponseBody
	@RequestMapping(value="/favoriteSearch", method=RequestMethod.POST)
	public int favoriteSearch(FavoriteVO favorite, HttpSession session) throws Exception {
		logger.info("favoriteSearch : " + favorite.toString());
		MemberVO member = (MemberVO) session.getAttribute("isLogin");
		favorite.setUserEmail(member.getUserEmail());
		
		return market.favoriteSearch(favorite);
	}
	
	@ResponseBody
	@RequestMapping(value="/favoriteAdd", method=RequestMethod.POST)
	public void favoriteAdd(FavoriteVO favorite, HttpSession session) throws Exception {
		logger.info("favoriteAdd : " + favorite.toString());
		MemberVO member = (MemberVO) session.getAttribute("isLogin");
		favorite.setUserEmail(member.getUserEmail());
		
		market.favoriteAdd(favorite);
	}
	
	@ResponseBody
	@RequestMapping(value="/favoriteDel", method=RequestMethod.POST)
	public void favoriteDel(FavoriteVO favorite, HttpSession session) throws Exception {
		logger.info("favoriteDel : " + favorite.toString());
		MemberVO member = (MemberVO) session.getAttribute("isLogin");
		favorite.setUserEmail(member.getUserEmail());
		
		market.favoriteDel(favorite);
	}
	
	//2020.04.22 상품 구매자만 리뷰 작성할 수 있게 처리
	@ResponseBody
	@RequestMapping(value="/reviewProductCheck", method=RequestMethod.POST)
	public int reviewProductCheck(ReviewVO review) throws Exception {
		logger.info("상품 구매유무 확인 Data : " + review.toString());
		return market.reviewProductCheck(review);
	}
	
	/*
	 * 
	 * ------------------------------------------------
	 * 
	 */
	
	
	
	//SubCategory 늘릴때마다 for문 i수치 조절해줘야 함.
	//좀더 획기적인 방법 없나...?
	private List<SubCategory> subCategoryList(int mainCategory){
		List<SubCategory> categoryList = new ArrayList<>();

		switch(mainCategory) {
			case 1:
				for(int i = 1; i<=2; i++) {
					categoryList.add(new SubCategory("productList?mainCategory="+mainCategory+"&subCategory="+i, subCategoryName(mainCategory, i)));
				}
				break;
			case 2:
				for(int i = 1; i<=4; i++) {
					categoryList.add(new SubCategory("productList?mainCategory="+mainCategory+"&subCategory="+i, subCategoryName(mainCategory, i)));
				}
				break;
			case 3:
				for(int i = 1; i<=2; i++) {
					categoryList.add(new SubCategory("productList?mainCategory="+mainCategory+"&subCategory="+i, subCategoryName(mainCategory, i)));
				}
				break;
			case 4:
				for(int i = 1; i<=3; i++) {
					categoryList.add(new SubCategory("productList?mainCategory="+mainCategory+"&subCategory="+i, subCategoryName(mainCategory, i)));
				}
				break;
		}
		
		
		return categoryList;
	}
	
	private String mainCategoryName(int mainCategory) {
		String mainCategoryName = "";
		switch(mainCategory) {
			case 1:
				mainCategoryName = "SET상품";
				break;
			case 2:
				mainCategoryName = "소품모음";
				break;
			case 3:
				mainCategoryName = "몰드";
				break;
			case 4:
				mainCategoryName = "작업도구";
				break;
		}
		
		return mainCategoryName;
	}
	
	private String subCategoryName(int mainCategory, int subCategory) {
		String[][] list = {
				{"DIY 세트", "도구세트"},
				{"가구", "인테리어", "주방", "그릇/도자기"},
				{"실리콘 몰드", "플라스틱 몰드"},
				{"레진", "점토", "도구"}
				};
		
		return list[mainCategory-1][subCategory-1];
	}
}
