package com.chococo.mypage.Community.DAO;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.chococo.mypage.Common.VO.PageCriteria;
import com.chococo.mypage.Community.VO.CommunityVO;

@Repository
public class CommunityDAOImpl implements CommunityDAO {
	
	@Inject
	private SqlSession session;

	@Override
	public List<CommunityVO> searchArticle(PageCriteria cri) {
		return session.selectList("Chococo.community.searchArticle", cri);
	}

	@Override
	public int listCount(PageCriteria cri) {
		return session.selectOne("Chococo.community.listCount", cri);
	}

	@Override
	public List<CommunityVO> recentPost() {
		return session.selectList("Chococo.community.recentPost");
	}

	@Override
	public CommunityVO articleView(CommunityVO article) {
		return session.selectOne("Chococo.community.articleView", article);
	}

	@Override
	public void modifyArticle(CommunityVO modify) {
		session.update("Chococo.community.modifyArticle", modify);
	}

	@Override
	public void insertArticle(CommunityVO insert) {
		session.insert("Chococo.community.insertArticle", insert);
	}

	@Override
	public void deleteArticle(CommunityVO delete) {
		session.delete("Chococo.community.deleteArticle", delete);
	}

	@Override
	public List<CommunityVO> articleChild(CommunityVO article) {
		return session.selectList("Chococo.community.articleChild", article);
	}

	@Override
	public int articleChildCount(CommunityVO article) {
		return session.selectOne("Chococo.community.articleChildCount", article);
	}

	@Override
	public void insertParentArticle(CommunityVO article) {
		session.insert("Chococo.community.insertParentArticle", article);
	}

	@Override
	public List<CommunityVO> mypageSearchArticle(HashMap<String, Object> map) {
		return session.selectList("Chococo.community.mypageSearchArticle", map);
	}

	@Override
	public int mypageListCount(CommunityVO article) {
		return session.selectOne("Chococo.community.mypageListCount", article);
	}

}
