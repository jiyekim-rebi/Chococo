package com.chococo.mypage.Community.Service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chococo.mypage.Common.VO.PageCriteria;
import com.chococo.mypage.Community.DAO.CommunityDAO;
import com.chococo.mypage.Community.VO.CommunityVO;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Inject
	private CommunityDAO community;

	@Override
	public List<CommunityVO> searchArticle(PageCriteria cri) {
		return community.searchArticle(cri);
	}

	@Override
	public int listCount(PageCriteria cri) {
		return community.listCount(cri);
	}

	@Override
	public List<CommunityVO> recentPost() {
		return community.recentPost();
	}

	@Override
	public CommunityVO articleView(CommunityVO article) {
		return community.articleView(article);
	}

	@Override
	public void modifyArticle(CommunityVO modify) {
		community.modifyArticle(modify);
	}

	@Override
	public void insertArticle(CommunityVO insert) {
		community.insertArticle(insert);
	}

	@Override
	public void deleteArticle(CommunityVO delete) {
		community.deleteArticle(delete);
	}

	@Override
	public List<CommunityVO> articleChild(CommunityVO article) {
		return community.articleChild(article);
	}

	@Override
	public int articleChildCount(CommunityVO article) {
		return community.articleChildCount(article);
	}

	@Override
	public void insertParentArticle(CommunityVO article) {
		community.insertParentArticle(article);
	}

	@Override
	public List<CommunityVO> mypageSearchArticle(HashMap<String, Object> map) {
		return community.mypageSearchArticle(map);
	}

	@Override
	public int mypageListCount(CommunityVO article) {
		return community.mypageListCount(article);
	}

}
