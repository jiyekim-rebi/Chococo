<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

	<div class="col-md-9 container" style="margin-top: 50px;">
		<h1 class="text-danger">Chococo Mypage</h1>

		<div style="margin-top: 50px;">
			<div align="center">
				<ul class="nav nav-tabs">
					<li class="nav-item" style="width: 16%"><a class="nav-link active" href="#purchase" data-toggle="tab">구매내역 보기</a></li>
					<li class="nav-item" style="width: 16%"><a class="nav-link" href="/chococo/market/cart">장바구니</a></li>
					<li class="nav-item" style="width: 16%"><a class="nav-link" href="#favorite" data-toggle="tab">찜한 상품</a></li>
					<li class="nav-item" style="width: 16%"><a class="nav-link" href="#recent" data-toggle="tab">최근에 본 상품</a></li>
					<li class="nav-item" style="width: 16%"><a class="nav-link" href="#myReview" data-toggle="tab">내가 작성한 리뷰</a></li>
					<li class="nav-item" style="width: 16%"><a class="nav-link" href="#coupon" data-toggle="tab">쿠폰등록/보유쿠폰</a></li>
				</ul>
			</div>
			<div class="tab-content " id="myTabContent">
			<!-- 구매내역 보기 -->
				<div class="tab-pane fade active show" id="purchase">
					<h2 class="text-primary" style="margin: 60px 30px 15px 30px;">구매내역 보기</h2>
					
					<div class="container" style="padding-top: 50px; margin-bottom: 150px;">
					<c:if test="${basketList != null}">
						<c:forEach items="${basketList }" var="order">
						<div class="row" style="padding: 50px 0;">
							<div class="col-md-3">
								<img src="/chococo/img/loading?imageFileName=${order.imageFileName}&mainCategory=${order.mainCategory}" alt="Image" style="width: 200px; height: 200px;">
							</div>
							<div class="col-md-6">
								<h2 class="text-success" style="margin: 20px 0;">${order.productName}</h2>
								<div>
									<span style="font-size: 24px;">${order.price * order.amount}원</span>
									<span style="padding-left: 15px; border-right: 0.1em solid gray;"></span>
									<span class="text-secondary" style="margin-left: 20px; font-size: 18px;"><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd" /></span>
								</div>
								<p style="margin-top: 10px;">${order.selectOption }</p>
							</div>
							<div class="col-md-3" align="center" style="padding-top: 30px;">
								<div style="margin-bottom: 20px;">
									<i style="margin-right: 10px;" class="fas fa-truck fa-lg"></i>
									<c:if test="${order.shippingStatus == '배송준비' }">
										<span class="badge badge-primary" style="font-size: 16px;">${order.shippingStatus}</span>
									</c:if>
									<c:if test="${order.shippingStatus == '배송중' }">
										<span class="badge badge-warning" style="font-size: 16px;">${order.shippingStatus}</span>
									</c:if>
									<c:if test="${order.shippingStatus == '배송완료' }">
										<span class="badge badge-info" style="font-size: 16px;">${order.shippingStatus}</span>
									</c:if>
									<div style="margin-top: 5px;">
										송장번호 : ${order.trackingNum }
									</div>
								</div>		
								<input type="button" class="btn btn-warning" onClick=" mypageProductReview(${order.productNo }, ${order.mainCategory});" value="사용후기쓰기"><br><br>
								<form>
									<input type="hidden" name="productNo" value="${order.productNo }">
									<input type="hidden" name="orderNo" value="${order.orderNo }">
									<input type="hidden" name="productName" value="${order.productName}">
									<input type="hidden" name="selectOption" value="${order.selectOption}">
									<input type="hidden" name="price" value="${order.price}">
									<input type="hidden" name="amount" value="${order.amount}">
									<input type="hidden" name="userName" value="${order.userName}">
									<input type="hidden" name="userEmail" value="${order.userEmail}">
									<input type="hidden" name="address" value="${order.address}">
									<input type="hidden" name="imageFileName" value="${order.imageFileName }">
									<input type="button" class="btn btn-secondary mypageProductReOrder" value="상품 재구매">
								</form>
							</div>
						</div>
						<hr>
						</c:forEach>
					</c:if>
					<c:if test="${basketList == null}">
						<div class="container" style="padding: 200px 0;" align="center">
							<h2 class="text-danger" style="margin: 60px 30px 15px 30px;">구매하신 상품이 없습니다!</h2>
						</div>
						<hr>
					</c:if>
					</div>
				</div>
				

				<!-- 찜한 상품 -->
				<div class="tab-pane fade" id="favorite">
					<h2 class="text-primary" style="margin: 60px 30px 15px 30px;">Favorite Products</h2>
					
					<c:if test="${favoriteList != null}">
						<div class="container" style="padding-top: 50px;">
							<div class="row">
								<c:forEach items="${favoriteList }" var="favorite">
									<div class="col-md-3" style="padding-top: 30px;">
										<img src="/chococo/img/loading?imageFileName=${favorite.imageFileName}&mainCategory=${favorite.mainCategory}" alt="Image" style="width: 120px; height: 120px;">
									</div>
									<div class="col-md-6">
										<h4 style="margin: 40px 0;">${favorite.productName}</h4>
									</div>
									<div class="col-md-3" align="center" style="padding-top: 30px;">
										<input type="button" class="btn btn-warning" onClick=" mypageProductReview(${favorite.productNo }, ${favorite.mainCategory});" value="상품구매하기">
									</div>
									<hr>
								</c:forEach>
							</div>
						</div>
					</c:if>
					
					<c:if test="${favoriteList == null}">
						<div class="container" style="padding: 200px 0;" align="center">
							<h2 class="text-danger" style="margin: 60px 30px 15px 30px;">찜한 상품이 없습니다!</h2>
							<h6 class="text-muted">마음에 드는 상품을 찜해주세요 :)</h6>
						</div>
					</c:if>
				</div>
				
				
				<!-- 최근에 본 상품 -->
				<!-- mypage니까 일단 최근의 본 상품은 로그인 했을 때만 적용하는 걸로. -->
				<div class="tab-pane fade" id="recent">
					<h2 class="text-primary" style="margin: 60px 30px 15px 30px;">Recent Products</h2>
					<div class="container" style="margin-top: 150px; margin-bottom: 150px;">
						<!-- 최근에 본 상품 세션이 없을 때. -->
						<c:if test="${recentProductList == null}">
							<div style="padding: 40px 0;" align="center">
								<h2 class="text-danger" style="margin: 60px 30px 15px 30px;">최근에 본 상품이 없습니다!</h2>
							</div>
							<hr>
						</c:if>
						<!-- 최근에 본 상품 세션이 존재할 때 -->
						<c:if test="${recentProductList != null}">
							<div class="row">
								<c:forEach items="${recentProductList }" var="recentProduct">
									<div class="col-md-3" style="padding-top: 30px;">
										<img src="/chococo/img/loading?imageFileName=${recentProduct.imageFileName}&mainCategory=${recentProduct.mainCategory}" alt="Image" style="width:120px; height:120px;">
									</div>
									<div class="col-md-6">
										<h4 style="margin: 40px 0;">${recentProduct.productName}</h4>
									</div>
									<div class="col-md-3" align="center" style="padding-top: 30px;">
										<input type="button" class="btn btn-warning" onClick=" mypageProductReview(${recentProduct.productNo }, ${recentProduct.mainCategory});" value="상품구매하기">
									</div>
									<hr>
								</c:forEach>
							</div>
						</c:if>
					</div>
				</div>
								
				<!-- 내가 쓴 상품 리뷰 -->
				<div class="tab-pane fade" id="myReview">
					<h2 class="text-primary" style="margin: 60px 30px 15px 30px;">My Product Reviews</h2>
					<c:if test="${myProductReview != null}">
						<div class="container" style="margin-bottom: 150px;">
						<c:forEach items="${myProductReview }" var="review">
							<div class="row" style="padding-top: 100px;">
								<div class="col-md-2">
									<fmt:formatDate value="${review.regDate}" pattern="yyyy-MM-dd"/>
								</div>
								<div class="col-md-10">
									<a href="/chococo/market/productDetail?mainCategory=${review.mainCategory }&productNo=${review.productNo}">${review.productName}</a>
								</div>
							<hr>
							</div>
						</c:forEach>
						</div>
					</c:if>
					
					<c:if test="${myProductReview == null}">
						<div class="container" style="padding: 200px 0;" align="center">
							<h2 class="text-danger" style="margin: 60px 30px 15px 30px;">남기신 리뷰가 없습니다!</h2>
							<h6 class="text-muted">고객님의 소중한 리뷰를 기다리고 있어요.</h6>
						</div>
						<hr>
					</c:if>
				</div>		
				
				<!-- 쿠폰! -->
				<div class="tab-pane fade" id="coupon">
					<h2 class="text-primary" style="margin: 60px 30px 15px 30px;">My Coupons</h2>
					<div class="container" style="margin-top: 150px; margin-bottom: 250px;">
					<form name="mypageCouponAddForm">
						<div class="row">
								<div class="col-md-3" align="right" style="margin-top: 8px;">
									<h5 class="text-info">쿠폰 등록하기</h5>
								</div>
								<div class="col-md-6">
									<input type="text" name="couponNumber" class="form-control">
									<h6 class="text-secondary" style="margin-top: 5px;">※ 쿠폰번호는 '-' 표시까지 전부 입력해주세요.</h6>
								</div>
								<div class="col-md-3">
									<input type="button" class="btn btn-success mypageCouponAddCtr" value="success">
								</div>
								<input type="hidden" name="userName" value="${isLogin.userName}">
								<input type="hidden" name="userEmail" value="${isLogin.userEmail}">
							</div>
						</form>
						<div class="row" style="margin-top: 100px;">
							<h3 class="text-success">등록한 쿠폰 목록</h3>
						</div>
						<hr>
						<!-- c:if -->
						<div class="row" style="margin-top: 50px;">
						<c:if test="${mypageCouponList != null}">
							<h5 class="text-info">사용 가능한 쿠폰</h5>
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col" style="width: 20%;">등록날짜</th>
										<th scope="col" style="width: 60%;">쿠폰명</th>
										<th scope="col">사용기한</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${mypageCouponList}" var="coupon">
									<tr>
										<td><fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd"/></td>
										<td>${coupon.content}</td>
										<td><fmt:formatDate value="${coupon.dateOfUse}" pattern="yyyy-MM-dd"/></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<hr>
						</c:if>
						<!-- c:if model 없을 때 부분 -->
						<c:if test="${mypageCouponList == null }">
							<div class="container" style="padding: 200px 0;" align="center">
								<h2 class="text-danger" style="margin: 60px 30px 15px 30px;">등록된 쿠폰이 없습니다!</h2>
							</div>
						</c:if>
						<br><br>

						<h5 class="text-secondary" style="margin-top: 30px;">이미 사용한 쿠폰</h5>
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col" style="width: 20%;">등록날짜</th>
										<th scope="col" style="width: 60%;">쿠폰명</th>
										<th scope="col">사용한 날짜</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${mypageCouponListUsed}" var="couponUsed">
									<tr>
										<td><fmt:formatDate value="${couponUsed.createDate}" pattern="yyyy-MM-dd"/></td>
										<td>${couponUsed.content}</td>
										<td><fmt:formatDate value="${couponUsed.usedDate}" pattern="yyyy-MM-dd"/></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




	<!-- 최상단으로 올려주는 버튼 -->
	<a class="Rebi_top"><i class="fa-4x fas fa-angle-up" style="color: #e95420;"></i></a>
	
	