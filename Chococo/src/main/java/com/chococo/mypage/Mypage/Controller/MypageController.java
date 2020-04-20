package com.chococo.mypage.Mypage.Controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chococo.mypage.Common.VO.PageCriteria;
import com.chococo.mypage.Common.VO.PageMaker;
import com.chococo.mypage.Community.Service.CommunityService;
import com.chococo.mypage.Community.Service.ReplyService;
import com.chococo.mypage.Community.VO.CommunityVO;
import com.chococo.mypage.Community.VO.ReplyVO;
import com.chococo.mypage.Market.Service.MarketService;
import com.chococo.mypage.Market.VO.BasketVO;
import com.chococo.mypage.Market.VO.FavoriteVO;
import com.chococo.mypage.Market.VO.ReviewVO;
import com.chococo.mypage.Member.Service.MemberService;
import com.chococo.mypage.Member.VO.CouponVO;
import com.chococo.mypage.Member.VO.MemberVO;

//내 정보, 장바구니, 회원정보변경 등등

@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	//패스워드 암호화 관련 처리로직
	@Inject
	private BCryptPasswordEncoder pwdEncoder;
	
	@Inject
	private CommunityService community;
	@Inject
	private ReplyService reply;
	@Inject
	private MarketService market;
	@Inject
	private MemberService member;
	
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	//mypage Main
	@RequestMapping(value="/main", method= {RequestMethod.GET, RequestMethod.POST})
	public String main(Model model, MemberVO member, HttpSession session) {
		
		//2020.03.30 LoginInterceptor로 인해 isLogin 세션이 없으면 메인으로 자동 리다이렉트처리.
		logger.info("/mypage/main");
	
		MemberVO isLogin = (MemberVO)session.getAttribute("isLogin");
		
		//회원정보 수정탭에서 비밀번호를 입력했을 경우.
		if(member.getUserPass() != null) {
			boolean pwdMatch = pwdEncoder.matches(member.getUserPass(), isLogin.getUserPass());		
			if(pwdMatch == true) {
				//session으로 설정할 경우 계속 남아있을거라서 한번 설정하고 redirect 될 경우 다시 인증받는 방향으로.
				model.addAttribute("myPage_passCheck", "success");
			} else {
				//로그인 한 당사자가 패스워드가 틀렸을 경우
				model.addAttribute("msg", "입력하신 비밀번호가 다릅니다.");
				model.addAttribute("url", "/chococo/mypage/main");
				return "include/Result";
			}
		}	
		return "mypage/main";
	}
	
	//Market View
	@RequestMapping(value="/market", method={RequestMethod.GET, RequestMethod.POST})
	public String market(Model model, HttpSession session, CouponVO coupon) throws Exception {
		logger.info("/mypage/market");
		MemberVO memberVO = (MemberVO)session.getAttribute("isLogin");
		
		//1. 주문상품 불러오기.
		model.addAttribute("basketList", market.mypageOrderList(memberVO));
		
		//2. 최근 본 상품 불러오기
		@SuppressWarnings("unchecked")
		List<BasketVO> recentList = (List<BasketVO>)session.getAttribute("recentList");
		if(recentList != null && recentList.size() > 0) {
			model.addAttribute("recentProductList", recentList);
		}
		
		//3. 내가 작성한 리뷰 불러오기
		List<ReviewVO> myProductReview = market.myProductReview(memberVO);
		if(myProductReview != null && myProductReview.size() > 0) {
			model.addAttribute("myProductReview", myProductReview);
		}
		
		//4. 찜한 상품 불러오기
		List<FavoriteVO> favoriteList = market.favoriteList(memberVO);
		if(favoriteList != null && favoriteList.size() > 0) {
			model.addAttribute("favoriteList", favoriteList);
		}
		
		//5. 쿠폰 불러오기
		List<CouponVO> mypageCouponList = member.mypageCouponList(memberVO);
		if(mypageCouponList != null && mypageCouponList.size() > 0) {
			model.addAttribute("mypageCouponList", mypageCouponList);
		}
		
		//5-2. 사용한 쿠폰 불러오기
		List<CouponVO> mypageCouponListUsed = member.mypageCouponListUsed(memberVO);
		if(mypageCouponListUsed != null && mypageCouponListUsed.size() > 0) {
			model.addAttribute("mypageCouponListUsed", mypageCouponListUsed);
		}
				
		return "mypage/market";
	}
	
	//Community View
	//2020.03.18 페이지마커 Board, Reply용 두개 정의할 것
	//categoryNo가 아닌 userName으로 search해서 가져와야 함.
	@RequestMapping(value="/community", method={RequestMethod.GET, RequestMethod.POST})
	public String community(HttpSession session, Model model, PageCriteria cri) throws Exception {
		logger.info("/mypage/community");
		//isLogin name으로 써진 값들 가져올 것!
		MemberVO isLogin = (MemberVO)session.getAttribute("isLogin");
		
		//1. boardList
		HashMap<String, Object> searchArticle = new HashMap<>();
		CommunityVO article = new CommunityVO();
		article.setWriter(isLogin.getUserName());
		searchArticle.put("cri", cri);
		searchArticle.put("article", article);
		model.addAttribute("boardList", community.mypageSearchArticle(searchArticle));
		
		//게시글용 페이지마커
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(community.mypageListCount(article));
		
		//pageMaker, PageCriteria
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("cri", cri);
			
		return "mypage/community";
	}
	
	@RequestMapping(value="/communityReply", method={RequestMethod.GET, RequestMethod.POST})
	public String communityReply (HttpSession session, Model model, PageCriteria cri) throws Exception {
		logger.info("/mypage/communityReply");
		//isLogin name으로 써진 값들 가져올 것!
		MemberVO isLogin = (MemberVO)session.getAttribute("isLogin");
		
		//2. replyList
		ReplyVO replyVO = new ReplyVO();
		replyVO.setWriter(isLogin.getUserName());
				
		HashMap<String, Object> searchReply = new HashMap<>();
		searchReply.put("reply", replyVO);
		searchReply.put("cri", cri);
		model.addAttribute("replyList", reply.mypageSearchReplyAll(searchReply));
				
		//replyPageMaker 댓글 페이지마커
		PageMaker replyPageMaker = new PageMaker();
		replyPageMaker.setCri(cri);
		replyPageMaker.setTotalCount(reply.mypageReplyCount(replyVO));
				
		model.addAttribute("replyPageMaker", replyPageMaker);
		model.addAttribute("cri", cri);
		
		return "mypage/communityReply";
	}
	
	@RequestMapping(value="/couponAdd", method=RequestMethod.POST)
	public String couponAdd(Model model, CouponVO coupon) throws Exception {
		logger.info("mypage - 쿠폰등록하기 : " + coupon.toString());
		try {
			//쿠폰 번호가 존재하는지 체크하기
			CouponVO search = member.mypageCouponExist(coupon);
			if(search != null) {
				//이미 등록한 쿠폰인지 체크하기
				if(member.mypageCouponSearch(coupon) == 0) {
					coupon.setContent(search.getContent());
					coupon.setRatio(search.getRatio());
					member.mypageCouponAdd(coupon);
					model.addAttribute("msg", "쿠폰을 등록하였습니다.");
				} else {
					model.addAttribute("msg", "이미 등록한 쿠폰입니다.");
				}
			} else {
				model.addAttribute("msg", "존재하지 않거나 발행이 중단된 쿠폰입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "에러가 발생했습니다.");
		}
		
		model.addAttribute("url", "/chococo/mypage/market");
		return "include/Result";
	}
}
