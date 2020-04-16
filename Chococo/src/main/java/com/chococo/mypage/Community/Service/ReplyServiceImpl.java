package com.chococo.mypage.Community.Service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chococo.mypage.Community.DAO.ReplyDAO;
import com.chococo.mypage.Community.VO.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO replyDAO;

	@Override
	public int replyCount(int boardNo) {
		return replyDAO.replyCount(boardNo);
	}

	@Override
	public void newReply(ReplyVO replyVO) {
		replyDAO.newReply(replyVO);
	}

	@Override
	public void parentNewReply(ReplyVO replyVO) {
		replyDAO.parentNewReply(replyVO);
	}

	@Override
	public void deleteReply(ReplyVO replyVO) {
		replyDAO.deleteReply(replyVO);
	}

	@Override
	public void deleteReplyByBoard(ReplyVO replyVO) {
		replyDAO.deleteReplyByBoard(replyVO);
	}

	@Override
	public List<ReplyVO> searchReplyAll(HashMap<String, Object> map) {
		return replyDAO.searchReplyAll(map);
	}

	@Override
	public List<ReplyVO> mypageSearchReplyAll(HashMap<String, Object> reply) {
		return replyDAO.mypageSearchReplyAll(reply);
	}

	@Override
	public int mypageReplyCount(ReplyVO reply) {
		return replyDAO.mypageReplyCount(reply);
	}

}
