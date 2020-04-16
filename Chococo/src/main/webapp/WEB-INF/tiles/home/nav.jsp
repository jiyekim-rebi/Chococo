<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<a class="navbar-brand" href="/chococo/">Chococo</a>
	
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="/chococo/">Home<span
					class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="/chococo/community/main">Community</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="/chococo/market/main">Market</a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<!-- 로그인 세션이 없을 경우 로그인, 가입 버튼이 활성화 -->
			<c:if test="${isLogin == null}">
				<button class="btn btn-info my-2 my-sm-0 mr-3 loginForm" type="button">Login</button>
				<button class="btn btn-success my-2 my-sm-0 createForm" type="button">SignUp</button>
			</c:if>
			<!-- 로그인 되어있다면 Mypage와 Logout으로. -->
			<c:if test="${isLogin != null}">
				<button class="btn btn-secondary my-2 my-sm-0 mr-3 nav_mypage" type="button">Mypage</button>
				<button class="btn btn-warning my-2 my-sm-0 nav_logout" type="button">Logout</button>
			</c:if>
			<!-- 접속한 계정이 admin계정일 경우 -->
			<c:if test="${adminCheck != null }">
				<button class="btn btn-danger my-2 my-sm-0 ml-3 nav_admin" type="button">Admin</button>
			</c:if>
		</form>
	</div>
</nav>


