package com.chococo.mypage.Admin.Controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chococo.mypage.Admin.Service.AdminService;
import com.chococo.mypage.Admin.VO.ProductVO;
import com.chococo.mypage.Common.VO.PageCriteria;
import com.chococo.mypage.Common.VO.PageMaker;
import com.chococo.mypage.Community.VO.CommunityVO;
import com.chococo.mypage.Market.VO.BasketVO;
import com.chococo.mypage.Member.VO.CouponVO;
import com.chococo.mypage.Member.VO.MemberVO;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Inject
	private AdminService admin;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	//admin 메인화면 진입
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main(Model model) throws Exception {
		logger.info("admin Main 페이지 진입.");
		
		return "admin/main";
	}
	
	//상품 등록하기 form
	@RequestMapping(value="/productInsert", method=RequestMethod.GET)
	public String productInsert(Model model) throws Exception {
		logger.info("상품 등록 form 진입");
		
		return "admin/productInsert";
	}
	
	
	//2020.04.01 DB 명칭 매핑 안되길래 변경함.
	//2020.04.04 썸네일용 파일 업로드하기.
	@RequestMapping(value="/market/productAdd", method=RequestMethod.POST)
	public String marketProductAdd(Model model, ProductVO productVO, @RequestParam("imageFile") MultipartFile file) throws Exception {
		logger.info("상품 새로 추가 - productVO 값 체크 : " + productVO.toString());
		logger.info("2020.04.04 image Uplode ----- ");

		try {
			//메인 카테고리별로 나눠서 파일들을 저장함.
			String imgUploadPath = uploadPath + "\\" + productVO.getMainCategory();
			String fileName = null;

			File dirPath = new File(imgUploadPath);
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
			
			if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
				fileName = file.getOriginalFilename();
			} else {
				fileName = "none.png";
			}
			
			productVO.setImageFileName(fileName);
			File newImage = new File(imgUploadPath + "\\" + fileName);
			
			try {
				file.transferTo(newImage);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", "파일 업로드에 실패했습니다.");
			}
			
			Map<String, Object> insert = new HashMap<>();
			insert.put("mainCategory", getMainCategory(productVO.getMainCategory()));
			insert.put("product", productVO);
			
			admin.marketProductAdd(insert);
			model.addAttribute("msg", "상품이 정상적으로 등록되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "상품등록이 실패했습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/main");

		return "include/Result";
	};
	
	//상품 수정하기 form - post
	//수정 form jsp를 따로 만들지 않고 insert에서 묶어서 처리 후 db 연동만 이곳에서 처리
	//2020.04.04 이미지 등록한거 수정하기.
	@RequestMapping(value="/market/productModify", method=RequestMethod.POST)
	public String productModify(Model model, ProductVO product, @RequestParam("imageFile") MultipartFile file) throws Exception {
		logger.info("상품 정보 수정 - productVO 값 체크 : " + product.toString());
		logger.info("modify 정보 테스트 - " + product.getContent() + ", 상품번호 - " + product.getProductNo());
		
		try {
			//파일 업로드가 새로 될 경우.
			String imgUploadPath = uploadPath + "\\" + product.getMainCategory();
			if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
				//기존 이미지 제거
				File delFile = new File(imgUploadPath + "\\" + product.getImageFileName());
				if(delFile.delete()) {
					logger.info(product.getImageFileName() + " 파일 삭제처리");
				}
				
				//새로 첨부할 이미지
				String fileName = file.getOriginalFilename();
				product.setImageFileName(fileName);
				File newImage = new File(imgUploadPath + "\\" + fileName);
				
				try {
					file.transferTo(newImage);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("msg", "파일 업로드에 실패했습니다.");
				}
			}
			
			admin.productModify(product);
			model.addAttribute("msg", "상품내용이 정상적으로 수정되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "상품내용 수정이 실패했습니다.");
		}

		model.addAttribute("url", "/chococo/admin/productList");

		return "include/Result";
	}
	
	//상품 전체등록된 list
	//일단 모든 상품을 출력시킴 (pageMaker 적용해서!)
	@RequestMapping(value="/productList", method= {RequestMethod.GET, RequestMethod.POST})
	public String productList(Model model, PageCriteria cri) throws Exception {
		logger.info("상품 list 불러오기 : " );
		logger.info("cri값 체크 : " + cri.toString());
		
		//mainCategory, keyword
		//이 세개의 값이 유동적으로 바뀌면서 productList에서 검색하게.
		if(cri.getMainCategory() == 0) {
			cri.setMainCategory(1);
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(admin.productListCount(cri));
		
		List<ProductVO> productList = admin.productList(cri);
		model.addAttribute("productList", productList);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("cri", cri);
		
		return "admin/productList";
	}
	
	//상품 판매 on/off
	@RequestMapping(value="/market/productStatusUpdate", method=RequestMethod.POST)
	public String productStatusUpdate(Model model, ProductVO product) throws Exception {
		logger.info("productStatusUpdate - mainCategory : " + product.getMainCategory() +
					", productNo - "+ product.getProductNo() + ", productStatus - " + product.getProductStatus());
			
		//Controller에 올 때 마다 상품 판매 상태가 반전되어 update되어야 함.
		if(product.getProductStatus() == 0) {
			product.setProductStatus(1);
		} else {
			product.setProductStatus(0);
		}
		
		try {
			admin.productStatusUpdate(product);
			model.addAttribute("msg", "상품 판매상태를 업데이트 했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "시스템 오류가 발생했습니다.");
		}
			
		model.addAttribute("url", "/chococo/admin/productList");

		return "include/Result";
	}
	
	//등록된 상품 상세보기 view
	@RequestMapping(value="/productView", method=RequestMethod.GET)
	public String productView(Model model, PageCriteria cri, ProductVO product) throws Exception {
		logger.info("productView : mainCategory - " + product.getMainCategory() + ", productNo - " + product.getProductNo());
			
		Map<String, Object> setting = new HashMap<String, Object>();
		setting.put("mainCategory", cri.getMainCategory());
		setting.put("productNo", product.getProductNo());
		
		model.addAttribute("product", admin.productView(setting));
		//keyword 값
		model.addAttribute("cri", cri);
		
		return "admin/productView";
	}
	
	
	//Shipping Main
	@RequestMapping(value="/shipping", method={RequestMethod.GET, RequestMethod.POST})
	public String shipping(Model model) throws Exception {
		
		//2020.04.02
		//pageMaker 적용 하려면 세 페이지를 다 나눠줘야 하는데 나눌지 말지 생각해볼 것.
		
		logger.info("Shipping Management Page 진입 ");
		
		
		//1. 배송전, 상품들 정보 수정하는 공간
		model.addAttribute("shippingReadyList", admin.shippingList("배송준비"));
		
		//2. 송장번호가 입력되고 배송중 상태가 되었을 때.
		model.addAttribute("shippingList", admin.shippingList("배송중"));
		
		//3. 최종적으로 배송 완료 되었을 때
		model.addAttribute("orderSuccess", admin.orderSuccessList());
		
		return "admin/shipping";
	}
	
	//shippingView - 상세보기 / 요기서 modify, delete, 송장번호세팅 다 함.
	@RequestMapping(value="/shippingView", method=RequestMethod.GET)
	public String shippingView(Model model, BasketVO shipping) throws Exception {
		logger.info("shipping view Check - orderNo : " + shipping.getOrderNo());
		
		model.addAttribute("orderNo", shipping.getOrderNo());
		model.addAttribute("userName", shipping.getUserName());
		model.addAttribute("shippingViewList", admin.shippingReadyView(shipping));
		
		return "admin/shippingView";
	}
	
	@RequestMapping(value="/order/orderModify", method=RequestMethod.POST)
	public String orderDataModify(Model model, BasketVO order) throws Exception {
		logger.info("ShippingView에서 각 항목별로 데이터 수정 요청이 들어옴");
		logger.info("data Check : " + order.toString());
		
		try {
			//amount 같은 경우 여기 결제시스템이 있다면 환불을 해주거나 해야할 것 같은데...
			admin.orderModify(order);
			model.addAttribute("msg", "주문 정보를 수정하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "주문정보를 수정하지 못했습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/shipping");
		return "include/Result";
	}
	
	@RequestMapping(value="/order/trackingUpdate", method=RequestMethod.POST)
	public String trackingUpdate(Model model, BasketVO order) throws Exception {
		logger.info("tracking Update Data check : " + order.toString());
		
		try {
			admin.trackingUpdate(order);
			model.addAttribute("msg", "송장번호 업데이트가 완료되었습니다.");
		} catch (Exception e){
			e.printStackTrace();
			model.addAttribute("msg", "업데이트에 실패했습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/shipping");
		return "include/Result";
	}
	
	@RequestMapping(value="/order/trackingSuccess", method=RequestMethod.POST)
	public String trackingSuccess(Model model, BasketVO order) throws Exception {
		
		try {
			admin.trackingSuccess(order);
			model.addAttribute("msg", "송장번호 업데이트가 완료되었습니다.");
		} catch (Exception e){
			e.printStackTrace();
			model.addAttribute("msg", "업데이트에 실패했습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/shipping");
		return "include/Result";
	}
	
	@RequestMapping(value="/communityList", method=RequestMethod.GET)
	public String communityList(Model model, CommunityVO community, PageCriteria cri) throws Exception {
		logger.info("communityList 진입 - " + cri.toString());
		
		model.addAttribute("noticeList", admin.noticeList(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(admin.noticeListCount());
		
		model.addAttribute("cri", cri);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/communityList";
	}
	
	//공지 글 쓰는 창으로 감.
	@RequestMapping(value="/communityInsert", method=RequestMethod.GET)
	public String communityInsert(Model model) throws Exception {
		logger.info("공지 글 쓰는 form으로 이동");
		
		return "admin/communityInsert";
	}
	
	//글 등록하기
	@RequestMapping(value="/notice/insert", method=RequestMethod.POST)
	public String noticeInsert(Model model, CommunityVO notice) throws Exception {
		logger.info("community notice Insert - " + notice.toString());
				
		try {
			admin.noticeInsert(notice);
			model.addAttribute("msg", "커뮤니티 공지를 등록하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "공지 등록에 실패하였습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/communityList");
		return "include/Result";
		
	}
	
	//글 보기 & 수정 form
	@RequestMapping(value="/communityView", method=RequestMethod.GET)
	public String communityView(Model model, CommunityVO notice) throws Exception {
		logger.info("공지 글 보기 & 수정 form");
		
		model.addAttribute("noticeView", admin.noticeView(notice));
		
		return "admin/communityView";
	}
	
	//공지 수정
	@RequestMapping(value="/notice/modify", method=RequestMethod.POST)
	public String noticeModify(Model model, CommunityVO notice) throws Exception {
		
		try {
			admin.noticeModify(notice);
			model.addAttribute("msg", "공지 내용을 수정하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "공지 수정에 실패하였습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/communityList");
		return "include/Result";
		
	}
	
	//공지 삭제
	@RequestMapping(value="/notice/delete", method=RequestMethod.POST)
	public String noticeDelete(Model model, CommunityVO notice) throws Exception {
		
		try {
			admin.noticeDelete(notice);
			model.addAttribute("msg", "공지를 삭제하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "공지 삭제에 실패했습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/communityList");
		return "include/Result";
		
	}
	
	//2020.04.02 admin-member datalist
	@RequestMapping(value="/memberList", method= {RequestMethod.GET, RequestMethod.POST})
	public String memberList(Model model, MemberVO member) throws Exception {
		logger.info("memberList view 진입 - " + member.toString());
		
		//처음에 들어올땐 search를 안했을테니 getUserName의 값이 null임.
		//그러니 null이 아닐 때 model 설정을 하게 해줘야 함.
		if(member.getUserName() != null) {
			try {
				MemberVO select = admin.memberSelectResult(member);
				if(select != null) {
					if(select.getIsAdmin() == 1) {
						//같은 admin은 검색조차 안되게 튕겨버릴 것.
						model.addAttribute("msg", "같은 admin은 검색하실 수 없습니다.");
						model.addAttribute("url", "/chococo/admin/memberList");
						return "include/Result";
					} else {
						model.addAttribute("member", select);
					}
				} else {
					model.addAttribute("msg", "검색된 회원이 없습니다.");
					model.addAttribute("url", "/chococo/admin/memberList");
					return "include/Result";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "admin/memberList";
	}
	
	//밴 유무 변경
	@RequestMapping(value="/member/isBan", method={RequestMethod.GET, RequestMethod.POST})
	public String banUpdate(Model model, MemberVO member) throws Exception {
		logger.info("ban update 처리 유무 데이터 체크 - " + member.toString());
		
		try {
			admin.adminMemberBanUpdate(member);
			model.addAttribute("msg", "밴 유무 업데이트 완료.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "에러가 발생했습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/memberList");
		return "include/Result";
	}
	
	//멤버탈퇴처리
	@RequestMapping(value="/member/delete", method=RequestMethod.POST)
	public String adminMemberDelete(Model model, MemberVO member) throws Exception {
		logger.info("delete 데이터 체크 - " + member.toString());
		
		try {
			admin.adminMemberDelete(member);
			model.addAttribute("msg", "탈퇴처리 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "에러가 발생했습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/memberList");
		return "include/Result";
	}
	
	//멤버 정보 수정
	@RequestMapping(value="/member/modify", method=RequestMethod.POST)
	public String adminMemberModify(Model model, MemberVO member) throws Exception {
		logger.info("modify 데이터 체크 - " + member.toString());
		
		try {
			admin.adminMemberInfoUpdate(member);
			model.addAttribute("msg", "정보변경이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "에러가 발생했습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/memberList");
		return "include/Result";
	}
	
	//쿠폰 페이지
	@RequestMapping(value="/coupon", method={RequestMethod.GET, RequestMethod.POST})
	public String coupon(Model model, PageCriteria cri) throws Exception {
		logger.info("coupon page : " + cri.toString());

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(admin.adminCouponCount());
		
		//등록한 쿠폰 불러와서 model 설정
		model.addAttribute("couponLists", admin.adminCouponList(cri));
		
		model.addAttribute("cri", cri);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/coupon";
	}
	
	//쿠폰 생성하기.
	@RequestMapping(value="/coupon/add", method=RequestMethod.POST)
	public String couponAdd(Model model, CouponVO coupon) throws Exception {
		logger.info("쿠폰을 생성합니다. : " + coupon.toString());
		
		//일단 쿠폰을 만듬
		//이 쿠폰이 db에 등록되어있는지 체크 해야함
		//등록 안되어있으면 등록하고, 등록이 되어있다면 다시 랜덤으로 생성해서 검사 할 것.
		try {
			while(true) {
				coupon.setCouponNumber(getCouponNumber());
				System.out.println("생성된 쿠폰 번호 : " + coupon.getCouponNumber());
				if(admin.adminCouponCheck(coupon) == 0) {
					admin.adminCouponCreate(coupon);
					break;
				} else {
					System.out.println("쿠폰번호 중복 : " + coupon.getCouponNumber());
				}
			}
			model.addAttribute("msg", "쿠폰 생성이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "에러가 발생했습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/coupon");
		
		return "include/Result";
	}
	
	//쿠폰 발행중단!
	@RequestMapping(value="/coupon/notUse", method=RequestMethod.POST)
	public String couponNotUse(Model model, CouponVO coupon) throws Exception {
		logger.info("등록된 쿠폰 사용을 중단합니다 - " + coupon.toString());
		
		try {
			admin.adminCouponNotUse(coupon);
			model.addAttribute("msg", "쿠폰 발행을 중단했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "에러가 발생했습니다.");
		}
		
		model.addAttribute("url", "/chococo/admin/coupon");
		return "include/Result";
	}
	

	// ------------------------------
	
	
	
	private String getMainCategory(int main) {
		String category = "chococo_market_";
		
		switch(main) {
		case 1:
			category += "first";
			break;
		case 2:
			category += "second";
			break;
		case 3:
			category += "third";
			break;
		case 4:
			category += "fourth";
			break;
		}
		
		return category;
	}
	
	private String getCouponNumber() {
		String couponNumber = "";
		
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++) {
				Random rnd =new Random();
				if(rnd.nextBoolean()){
					couponNumber += (char)((int)(rnd.nextInt(26))+97);
			    } else {
			    	couponNumber += rnd.nextInt(10);
			    }
			}
			if(i < 3) {
				couponNumber += "-";
			}
		}

		return couponNumber.toUpperCase();
	}
	
}
