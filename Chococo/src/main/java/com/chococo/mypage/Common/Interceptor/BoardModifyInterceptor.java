package com.chococo.mypage.Common.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chococo.mypage.Member.VO.MemberVO;



/*
 * <input type="hidden" name="writer" value="${articleView.writer}">
				<input type="hidden" name="boardNo" value="${articleView.boardNo }">
				<input type="hidden" name="categoryNo" value="${articleView.categoryNo}">
				
	http://localhost:8180/chococo/community/boardModify?writer=admin&boardNo=91&categoryNo=2
 * 
 */

public class BoardModifyInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("isLogin");
		String writer = request.getParameter("writer");
		
		if(member == null || writer.equals(member.getUserName()) == false) {
			response.sendRedirect("/chococo/community/main");
			return false;
		}
		

		return true;
	}
	
	

}
