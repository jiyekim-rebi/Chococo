<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<div class="col-md-2" style="margin-bottom: 20px;">
	<div class="col">
		<div class="card border-primary mb-3" style="margin-top: 40px; margin-left: 15px; max-width: 30rem;">
  			<div class="card-header">Chococo Mypage</div>
  			<div class="card-body">
   				 <h4 class="card-title">Welcome, ${isLogin.userName}</h4>
    			<p class="card-text">안녕하세요, ${isLogin.userName} 님. Mypage입니다.</p>
  			</div>
		</div>
	</div>

	<div style="margin-top: 50px;">
		<ul class="mypage_side_ul">
			<li><a class="mypage_side_ul_first" href="/chococo/mypage/main">Profile</a></li>
				<ul class="mypage_side_ul">
					<li>회원정보 보기/수정</li>
					<li>회원탈퇴</li>
				</ul>
		</ul>
		<ul class="mypage_side_ul">
			<li><a class="mypage_side_ul_first" href="/chococo/mypage/market">Market</a></li>
				<ul class="mypage_side_ul">
					<li>구매내역 보기</li>
					<li>장바구니</li>
					<li>찜한 상품</li>
					<li>최근에 본 상품</li>
					<li>내가 작성한 리뷰</li>
					<li>쿠폰등록/보유쿠폰</li>
				</ul>
			</ul>
			<ul class="mypage_side_ul">
			<li><a class="mypage_side_ul_first" href="/chococo/mypage/community">Community</a></li>
				<ul class="mypage_side_ul">
					<li>내가 쓴 게시글</li>
					<li>내가 쓴 댓글</li>
				</ul>
			</ul>
			<ul class="mypage_side_ul">
			<li><a class="mypage_side_ul_first" href="#">Service Center</a></li>
				<ul class="mypage_side_ul">
					<li>자주 묻는 질문</li>
					<li>1:1 문의</li>
				</ul>
			</ul>
	</div>
</div>