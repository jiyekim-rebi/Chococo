package com.chococo.mypage.Common.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chococo.mypage.Member.VO.MemberVO;

public class BoardInsertInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("isLogin");
		
		//1. 글쓰기나 글 수정할때 session 없을 경우 back
		if(member == null) {
			response.sendRedirect("/chococo/community/main");
			return false;
		}
		
		return true;
	}

}
