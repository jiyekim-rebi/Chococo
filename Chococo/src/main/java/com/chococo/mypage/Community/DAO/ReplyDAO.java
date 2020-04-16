package com.chococo.mypage.Community.DAO;

import java.util.HashMap;
import java.util.List;

import com.chococo.mypage.Community.VO.ReplyVO;

public interface ReplyDAO {
	//boardNo 에 등록되어있는 모든 리플 불러오기
	public List<ReplyVO> searchReplyAll(HashMap<String, Object> map);
	//boardNo의 리플 전체개수(pageMaker 처리)
	public int replyCount(int boardNo);
	//새 리플 등록하기
	public void newReply(ReplyVO replyVO);
	//댓글 답글 등록하기
	public void parentNewReply(ReplyVO replyVO);
	//댓글 삭제하기
	public void deleteReply(ReplyVO replyVO);
	//게시글 삭제 시 등록된 댓글들 다 지워버림
	public void deleteReplyByBoard(ReplyVO replyVO);
	//mypage - userName으로 등록된 모든 댓글 조회(페이징 처리)
	public List<ReplyVO> mypageSearchReplyAll(HashMap<String, Object> reply);
	//mypage - userName으로 등록된 모든 댓글 갯수
	public int mypageReplyCount(ReplyVO reply);
}
