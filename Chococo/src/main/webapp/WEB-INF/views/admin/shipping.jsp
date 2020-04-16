<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<!-- 
배송준비 -> 배송중 -> 배송완료 순으로 정리.
배송준비, 배송중에선 market_order로 불러오고, 배송완료에선 order_member, order_nomember 두 탭으로 나눠서 볼 수 있게 처리.

이 페이지도 search, pageMaker 처리 해줘야 함. 2020.04.01 == 할꺼면 분할해야돼 화면 싹다.
 -->
<div class="container">

	<div align="center" style="margin-top: 50px; margin-bottom: 50px;">
		<h1 class="text-primary">Product Shipping Management Page</h1>
	</div>

	<div align="center">
		<ul class="nav nav-tabs">
			<li class="nav-item" style="width: 33%"><a
				class="nav-link active" href="#ShippingReady" data-toggle="tab">배송준비</a></li>
			<li class="nav-item" style="width: 33%"><a class="nav-link"
				href="#Shipping" data-toggle="tab">배송중</a></li>
			<li class="nav-item" style="width: 33%"><a class="nav-link"
				href="#ShippingComplate" data-toggle="tab">배송완료</a></li>
		</ul>
	</div>

	<div class="tab-content " id="myTabContent">
		<div class="tab-pane fade active show" id="ShippingReady">
			<h2 class="text-primary" style="margin: 60px 30px;">Shipping
				Ready</h2>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col" style="width: 15%">주문번호</th>
						<th scope="col" style="width: 15%">주문날짜</th>
						<th scope="col" style="width: 15%">이름</th>
						<th scope="col" style="width: 45%">이메일</th>
						<th scope="col" style="width: 10%">회원/비회원</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${shippingReadyList}" var="shippingReady">
						<tr class="table">
							<td><a
								href="/chococo/admin/shippingView?orderNo=${shippingReady.orderNo}&userName=${shippingReady.userName}">
									${shippingReady.orderNo} </a></td>
							<td><fmt:formatDate value="${shippingReady.orderDate}"
									pattern="yyyy-MM-dd" /></td>
							<td>${shippingReady.userName}</td>
							<td>${shippingReady.userEmail}</td>
							<td align="center"><c:if
									test="${shippingReady.memberExists == 0 }">
									<span class="badge badge-pill badge-light">비회원</span>
								</c:if> <c:if test="${shippingReady.memberExists == 1 }">
									<span class="badge badge-pill badge-success">회원</span>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- shipping -->
		<div class="tab-pane fade" id="Shipping">
			<h2 class="text-primary" style="margin: 60px 30px;">Shipping</h2>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col" style="width: 15%">주문번호</th>
						<th scope="col" style="width: 15%">주문날짜</th>
						<th scope="col" style="width: 10%">이름</th>
						<th scope="col" style="width: 20%">상품명</th>
						<th scope="col" style="width: 15%">송장번호</th>
						<th scope="col" style="width: 15%">회원/비회원</th>
						<th scope="col" style="width: 10%">배송완료</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${shippingList}" var="shipping">
						<tr class="table">
							<td>${shipping.orderNo}</td>
							<td><fmt:formatDate value="${shipping.orderDate}"
									pattern="yyyy-MM-dd" /></td>
							<td>${shipping.userName}</td>
							<td>${shipping.productName }</td>
							<td>${shipping.trackingNum}</td>
							<td align="center"><c:if
									test="${shipping.memberExists == 0 }">
									<span class="badge badge-pill badge-light">비회원</span>
								</c:if> <c:if test="${shipping.memberExists == 1 }">
									<span class="badge badge-pill badge-success">회원</span>
								</c:if></td>
							<td align="center">
							<input type="button" class="btn btn-success shippingTrackingOk" value="배송완료">
								<form>
									<input type="hidden" name="mainCategory"
										value="${shipping.mainCategory }"> <input
										type="hidden" name="productNo" value="${shipping.productNo }">
									<input type="hidden" name="orderNo"
										value="${shipping.orderNo }">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


		<!-- shipping complate -->
		<div class="tab-pane fade" id="ShippingComplate">
			<h2 class="text-primary" style="margin: 60px 30px;">Shipping
				Complate List</h2>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col" style="width: 10%">주문번호</th>
						<th scope="col" style="width: 10%">주문날짜</th>
						<th scope="col" style="width: 20%">이름</th>
						<th scope="col" style="width: 30%">이메일</th>
						<th scope="col" style="width: 15%">송장번호</th>
						<th scope="col" style="width: 15%">회원/비회원</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderSuccess}" var="success">
						<tr class="table">
							<td>${success.orderNo}</td>
							<td><fmt:formatDate value="${success.orderDate}"
									pattern="yyyy-MM-dd" /></td>
							<td>${success.userName}</td>
							<td>${success.userEmail}</td>
							<td>${success.trackingNum }</td>
							<td align="center"><c:if
									test="${success.memberExists == 0 }">
									<span class="badge badge-pill badge-light">비회원</span>
								</c:if> <c:if test="${success.memberExists == 1 }">
									<span class="badge badge-pill badge-success">회원</span>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>