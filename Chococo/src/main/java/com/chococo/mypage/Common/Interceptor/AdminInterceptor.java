package com.chococo.mypage.Common.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chococo.mypage.Member.VO.MemberVO;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		MemberVO admin = (MemberVO) session.getAttribute("adminCheck");
		
		if(admin == null) {
			response.sendRedirect("/chococo/");
			return false;
		}
		
		return true;
	}
	
}
