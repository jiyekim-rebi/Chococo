package com.chococo.mypage.Community.Controller;

import java.util.HashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@RequestMapping("/community/*")
public class CommunityController {

	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

	@Inject
	private CommunityService community;

	@Inject
	private ReplyService reply;

	//Community 메인 화면 진입했을 때.
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main(Model model, PageCriteria cri) throws Exception {
		//공지니까 1번째 글
		cri.setCategoryNo(1);
		logger.info("main : " + cri.toString());

		//main화면은 community의 공지사항을 띄워줘야 하기 때문에 model 설정할 것.
		model.addAttribute("communityNotice", community.searchArticle(cri));

		//PageMaker 계산 과정
		PageMaker pageMaker = setPageMaker(cri);
		setPageView(model, cri, pageMaker);

		return "community/main";
	}

	//Community - 2 ~ 4번 카테고리 진입했을 때
	//왼쪽 상단에서 키워드 검색했을 때.
	@RequestMapping(value={"/board", "/search"}, method=RequestMethod.GET)
	public String board(Model model, PageCriteria cri) throws Exception {
		logger.info("board : " + cri.toString());

		if(cri.getCategoryNo() == 1) {
			return "redirect:/community/main";
		}

		model.addAttribute("boardList", community.searchArticle(cri));
		model.addAttribute("categoryName", categoryName(cri.getCategoryNo()));

		//PageMaker 계산 과정
		PageMaker pageMaker = setPageMaker(cri);
		setPageView(model, cri, pageMaker);

		return "community/board";
	}

	@RequestMapping(value="/boardView", method=RequestMethod.GET)
	public String boardView(Model model, PageCriteria cri, CommunityVO article) throws Exception {
		logger.info("boardView - article : " + article.toString());
		logger.info("boardView - cri : " + cri.toString());

		//보려는 게시글 불러오기
		model.addAttribute("articleView", community.articleView(article));

		//답글이 달려있을 경우 출력
		if(community.articleChildCount(article) > 0) {
			model.addAttribute("articleChild", community.articleChild(article));
		}

		//2020.03.11 리플 전체 불러오기
		//2020.03.16 리플 count 개수가 0일 경우는 model 설정 안해줄꺼임.
		int replyCount = reply.replyCount(article.getBoardNo());
		if(replyCount != 0) {

			//ReplyPageMaker 처리 필요함. 리플이라서 따로 정의.
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(replyCount);

			//2020.03.16 Reply 부분 pageMaker가 제대로 생성이 안되서 HashMap을 통해 mapper 수정
			HashMap<String, Object> map = new HashMap<>();
			map.put("article", article);
			map.put("cri", cri);
			model.addAttribute("articleReplys", reply.searchReplyAll(map));

			//Reply pageMaker
			model.addAttribute("pageMaker", pageMaker);
		}

		//Recent Posts - recentPost
		model.addAttribute("recentPost", community.recentPost());
		//PageCriteria setting
		model.addAttribute("cri", cri);

		return "community/boardView";
	}

	//ModifyView
	//2020.03.30 에디터로 인해 POST 방식으로 데이터를 받음.
	@RequestMapping(value="/boardModify", method=RequestMethod.POST)
	public String boardModify(Model model, CommunityVO article, PageCriteria cri) throws Exception {
		logger.info("boardModify - cri : " + cri.toString());
		logger.info("boardModify - article: " + article.toString());

		model.addAttribute("modifyForm", community.articleView(article));
		model.addAttribute("cri", cri);

		return "community/boardModify";
	}

	//Modify - POST
	@RequestMapping(value="/boardModifyPost", method=RequestMethod.POST)
	public String boardModifyPost(Model model, CommunityVO article) throws Exception {
		logger.info("boardModifyPost : " + article.toString());

		//1. 수정한 후 수정 완료되었다는 메세지
		//DB등의 오류가 났을 경우 안됐다는 메세지.
		try {
			community.modifyArticle(article);
			model.addAttribute("msg", "게시글 수정이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "서버 오류입니다. 관리자에게 문의하세요.");
		}

		//2. Community Main으로 redirect
		model.addAttribute("url", "/chococo/community/boardView?boardNo=" + article.getBoardNo());

		return "include/Result";
	}

	//insert - get
	@RequestMapping(value="/boardInsert", method=RequestMethod.GET)
	public String boardInsert(Model model, CommunityVO article) throws Exception {
		logger.info("boardInsert : " + article.toString());

		//categoryNo 가지고 가야함.
		model.addAttribute("insertForm", article);

		model.addAttribute("recentPost", community.recentPost());
		return "community/boardInsert";
	}

	//insert - post
	@RequestMapping(value="/boardInsertPost", method=RequestMethod.POST)
	public String boardInsertPost(Model model, CommunityVO article) throws Exception {
		logger.info("boardInsertPost : " + article.toString());

		//1. DB로 집어넣기.
		//2020.03.17 parentBoardNo에 값이 있을 경우 답글 게시글이란 의미니까 if문으로 구별할 것.
		try {
			community.insertArticle(article);
			model.addAttribute("msg", "게시글 등록이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "오류가 발생했습니다. 시스템 관리자에게 문의하세요.");
		}

		//2. Community Main으로 redirect
		model.addAttribute("url", "/chococo/community/board?categoryNo=" + article.getCategoryNo());

		return "include/Result";
	}

	//게시글 삭제 - POST
	@RequestMapping(value="/deleteArticle", method=RequestMethod.POST)
	public String deleteArticle(Model model, CommunityVO article, ReplyVO replyVO) throws Exception {
		logger.info("1. deleteArticle - article : " + article.toString());
		logger.info("2. deleteArticle - reply : " + replyVO.toString());

		//게시글 삭제
		try {
			community.deleteArticle(article);
			model.addAttribute("msg", "게시글을 삭제하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "서버 오류입니다. 관리자에게 문의하세요.");
		}
		model.addAttribute("url", "/chococo/community/board?categoryNo=" + article.getCategoryNo());

		return "include/Result";
	}

	//게시글 삭제 - GET
	//2020.03.19 logger가 두개 찍히는 걸 보아하니 뭔가 꼬인듯 해서 일단 나눔.
	//2020.04.16 url 차이때문에 컨트롤러를 두개로 나눠놓기는 했는데 하나로 합칠 순 없나?
	@RequestMapping(value="/mypageDeleteArticle", method=RequestMethod.GET)
	public String mypageDeleteArticle(Model model, CommunityVO article, ReplyVO replyVO) throws Exception {
		logger.info("1. mypageDeleteArticle - article : " + article.toString());
		logger.info("2. mypageDeleteArticle - reply : " + replyVO.toString());

		community.deleteArticle(article);

		model.addAttribute("msg", "게시글을 삭제하였습니다.");
		model.addAttribute("url", "/chococo/mypage/community");

		return "include/Result";
	}


	/*
	 * 이하 리플 관련 Controller
	 */

	//newReply
	@RequestMapping(value= {"/newReply", "/parentNewReply"}, method=RequestMethod.POST)
	public String newReply(ReplyVO replyVO, Model model) throws Exception {
		logger.info("newReply : " + replyVO.toString());
		
		try {
			reply.newReply(replyVO);
			model.addAttribute("msg", "댓글을 등록하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "서버 오류입니다. 관리자에게 문의하세요.");
		}

		model.addAttribute("url", "/chococo/community/boardView?boardNo="+replyVO.getBoardNo());
		return "include/Result";
	}

	//deleteReply - POST
	@RequestMapping(value="/deleteReply", method=RequestMethod.POST)
	public String deleteReply(ReplyVO replyVO, CommunityVO article, Model model) throws Exception {
		logger.info("deleteReply" + replyVO.toString());

		try {
			reply.deleteReply(replyVO);
			model.addAttribute("msg", "댓글을 삭제하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "서버 오류입니다. 관리자에게 문의하세요.");
		}
		
		model.addAttribute("url", "/chococo/community/boardView?boardNo="+replyVO.getBoardNo());
		return "include/Result";
	}
	
	//modifyReply - POST
	@RequestMapping(value="/modifyReply", method=RequestMethod.POST)
	public String modifyReply(ReplyVO replyVO, Model model) throws Exception {
		logger.info(replyVO.toString());
		
		try {
			reply.modifyReply(replyVO);
			model.addAttribute("msg", "댓글 내용을 수정하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "서버 오류입니다. 관리자에게 문의하세요.");
		}
		
		model.addAttribute("url", "/chococo/community/boardView?boardNo="+replyVO.getBoardNo());
		return "include/Result";
	}

	//2020.03.19 mypage에서 리플 삭제
	@RequestMapping(value="/mypageReplyDelete", method=RequestMethod.POST)
	public String mypageReplyDelete(ReplyVO replyVO, Model model) throws Exception {
		logger.info("mypageReplyDelete : " + replyVO.toString());

		try {
			reply.deleteReply(replyVO);
			model.addAttribute("msg", "댓글을 삭제하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "서버 오류입니다. 관리자에게 문의하세요.");
		}
		
		model.addAttribute("url", "/chococo/mypage/communityReply");
		return "include/Result";
	}


	// -------------------------------------------------------------------
	// -------------------------------------------------------------------

	private void setPageView(Model model, PageCriteria cri, PageMaker pageMaker) {
		//Recent Posts - recentPost
		model.addAttribute("recentPost", community.recentPost());

		//pageMaker, PageCriteria
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("cri", cri);
	}

	private PageMaker setPageMaker(PageCriteria cri) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(community.listCount(cri));

		System.out.println("getTotalCount! : " + pageMaker.getTotalCount());

		return pageMaker;
	}

	public String categoryName(int categoryNo) {
		String boardName = "Search Result";
		switch(categoryNo) {
		case 2:
			boardName = "Lecture";
			break;
		case 3:
			boardName = "DIY MarketPlace";
			break;
		case 4:
			boardName = "FreeTalk";
			break;
		}
		return boardName;
	}

}
