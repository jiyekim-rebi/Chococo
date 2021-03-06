package com.chococo.mypage.Community.Service;

import java.util.HashMap;
import java.util.List;

import com.chococo.mypage.Community.VO.ReplyVO;

public interface ReplyService {
	//boardNo 에 등록되어있는 모든 리플 불러오기
	public List<ReplyVO> searchReplyAll(HashMap<String, Object> map);
	//boardNo의 리플 전체개수(pageMaker 처리)
	public int replyCount(int boardNo);
	//새 리플 등록하기
	public void newReply(ReplyVO replyVO);
	//댓글 삭제하기
	public void deleteReply(ReplyVO replyVO);
	//mypage - userName으로 등록된 모든 댓글 조회(페이징 처리)
	public List<ReplyVO> mypageSearchReplyAll(HashMap<String, Object> reply);
	//mypage - userName으로 등록된 모든 댓글 갯수
	public int mypageReplyCount(ReplyVO reply);
	//댓글 내용 수정
	public void modifyReply(ReplyVO reply);
}
