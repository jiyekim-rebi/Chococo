package com.chococo.mypage.Community.DAO;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.chococo.mypage.Community.VO.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession session;


	@Override
	public int replyCount(int boardNo) {
		return session.selectOne("Chococo.reply.replyCount", boardNo);
	}

	@Override
	public void newReply(ReplyVO replyVO) {
		session.insert("Chococo.reply.newReply", replyVO);
	}

	@Override
	public void parentNewReply(ReplyVO replyVO) {
		session.insert("Chococo.reply.parentNewReply", replyVO);
	}

	@Override
	public void deleteReply(ReplyVO replyVO) {
		session.delete("Chococo.reply.deleteReply", replyVO);
	}

	@Override
	public List<ReplyVO> searchReplyAll(HashMap<String, Object> map) {
		return session.selectList("Chococo.reply.searchReplyAll", map);
	}

	@Override
	public List<ReplyVO> mypageSearchReplyAll(HashMap<String, Object> reply) {
		return session.selectList("Chococo.reply.mypageSearchReplyAll", reply);
	}

	@Override
	public int mypageReplyCount(ReplyVO reply) {
		return session.selectOne("Chococo.reply.mypageReplyCount", reply);
	}



}
