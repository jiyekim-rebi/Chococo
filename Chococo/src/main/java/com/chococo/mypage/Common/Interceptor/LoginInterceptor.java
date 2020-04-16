package com.chococo.mypage.Common.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chococo.mypage.Member.VO.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	//1. 관리자인지 아닌지 구분하는 인터셉터
	//2. 로그인 한 사람인지 아닌지 구분하는 인터셉터
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("isLogin");
		
		if(member == null) {
			//로그인 세션 없으면 그냥 메인으로 보내버림
			response.sendRedirect("/chococo/");
			return false;
		}
		
		return true;
	}

}
