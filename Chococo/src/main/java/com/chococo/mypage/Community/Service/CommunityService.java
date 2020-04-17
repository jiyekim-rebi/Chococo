package com.chococo.mypage.Community.Service;

import java.util.HashMap;
import java.util.List;

import com.chococo.mypage.Common.VO.PageCriteria;
import com.chococo.mypage.Community.VO.CommunityVO;

public interface CommunityService {
	//카테고리별 Article 가져오기
	public List<CommunityVO> searchArticle(PageCriteria cri);
	//전체 게시글 개수 계산
	public int listCount(PageCriteria cri);
	//최신 포스트
	public List<CommunityVO> recentPost();
	//게시글 상세보기
	public CommunityVO articleView(CommunityVO article);
	//게시글 수정하기
	public void modifyArticle(CommunityVO modify);
	//게시글 등록하기
	public void insertArticle(CommunityVO insert);
	//게시글 삭제하기
	public void deleteArticle(CommunityVO delete);
	//게시글에 답글이 있을 경우
	public List<CommunityVO> articleChild(CommunityVO article);
	//답글 개수 체크 후 boardView에 출력하는지 아닌지 체크할꺼임.
	public int articleChildCount(CommunityVO article);
	//mypage - 내가 쓴 글 모아보기
	public List<CommunityVO> mypageSearchArticle(HashMap<String, Object> map);
	//mypage - pageMaker를 위한 게시글 수 체크
	public int mypageListCount(CommunityVO article);
}
