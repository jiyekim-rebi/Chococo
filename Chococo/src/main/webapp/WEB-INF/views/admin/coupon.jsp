<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="container">
	<div align="center" style="margin-top: 50px; margin-bottom: 100px;">
		<h1 class="text-primary">Coupon Page</h1>
	</div>
	
	<div class="row">
		<div class="col-md-6 form-inline">
			<input type="button" class="btn btn-warning couponAddOnOff" value="쿠폰생성">
		</div>
		<div class="col-md-6">
			<form name="adminCouponSearchForm" class="form-inline">
				<input style="margin-left: 20px;" type="text" name="keyword" class="form-control" placeholder="content search for...">
				<button class="btn btn-primary ml-2 adminCouponSearchCtr" type="button">Search</button>
			</form>
		</div>
	</div>
	
	<%@ include file ="include/couponAdd.jsp" %>
	
	<form name="adminCouponNotUseForm"></form>
	
	<table class="table table-hover" style="margin: 30px 0 50px 0;">
		<thead>
			<tr>
				<th scope="col">발행인</th>
				<th scope="col">쿠폰이름</th>
				<th scope="col">쿠폰번호</th>
				<th scope="col">퍼센트</th>
				<th scope="col">발행날짜</th>
				<th scope="col">발행중단</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${couponLists }" var="coupon">
			<tr class="table">
				<td>${coupon.userName}</td>
				<td>${coupon.content}</td>
				<td>${coupon.couponNumber}</td>
				<td>${coupon.ratio}</td>
				<td><fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd"/></td>
				<td>
				<c:if test="${coupon.isUse == 1}">
					<input type="button" class="btn btn-danger adminCouponNotUseCtr" value="발행중단하기">
					<input type="hidden" name="couponNumber" value="${coupon.couponNumber }">
				</c:if>
				<c:if test="${coupon.isUse == 0}">
					<h6 class="text-danger">발행중단</h6>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<!-- pageMaker -->
	<div class="row">
		<div class="center-block" style="margin: 0 auto;">
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li class="page-item"><a class="page-link" href="coupon${pageMaker.makeMakerAdminCommunity(pageMaker.startPage - 1)}">&laquo;</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
					<li class="page-item" <c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>								<a class="page-link" href="coupon${pageMaker.makeMakerAdminCommunity(idx)}">${idx}</a></li>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li class="page-item"><a class="page-link" href="coupon${pageMaker.makeMakerAdminCommunity(pageMaker.endPage + 1)}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	
</div>