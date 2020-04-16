<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<div class="container" style="margin-bottom: 100px;">
	<div align="center" style="margin-top: 50px; margin-bottom: 100px;">
		<h1 class="text-primary">Product Shipping Management Page</h1>
		<h3 class="text-success">${userName}님의 주문서입니다.</h3>
	</div>

	<hr>
<c:if test="${shippingViewList!=null}">
	<!-- shippingViewList -->
	<c:forEach items="${shippingViewList}" var="shipping">
	<form>
		<div class="row" style="padding: 50px 0;">
			<div class="col-md-3">
				<img src="/chococo/img/loading?imageFileName=${shipping.imageFileName}&mainCategory=${shipping.mainCategory}" alt="Image" style="width: 180px; height: 180px;">
			</div>
			<div class="col-md-6">
				<h3 style="margin: 20px 0;">${shipping.productName}</h3>
				<div class="row">
					<div class="col-md-3" style="margin-top: 5px;">
						<h5>선택옵션</h5>
					</div>
					<div class="col-md-9">
						<input type="text" class="form-control shippingViewinput" name="selectOption" value="${shipping.selectOption}" readOnly>
					</div>
					<div class="col-md-3" style="margin-top: 5px;">
						<h5>수량</h5>
					</div>
					<div class="col-md-9" style="margin-top: 5px;">
						<%-- 수량 변경 불가능 하게 변경 --%>
						<h5>${shipping.amount}</h5>
					</div>
					<div class="col-md-3" style="margin-top: 5px;">
						<h5>배송주소</h5>
					</div>
					<div class="col-md-9">
						<input type="text" class="form-control shippingViewinput" name="address" value="${shipping.address}" readOnly>
					</div>
				</div>
			</div>
			<div class="col-md-3" align="center" style="padding-top: 100px;">
				<input type="button" class="btn btn-warning shippingViewOn" value="옵션변경">
				<input type="button" class="btn btn-danger shippingViewDelCtr" value="주문취소">
			</div>
			<div class="col-md-3" align="center" style="padding-top: 100px; display: none;">
				<input type="button" class="btn btn-success shippingViewModCtr" value="변경하기">
				<input type="button" class="btn btn-secondary shippingViewOff" value="취소하기">
			</div>
		</div>
		<hr>
		<div class="row" align="center">
			<div class="col-md-3">
				<h5 class="text-primary">송장번호 입력 </h5>
			</div>
			<div class="col-md-6">
				<input type="text" class="form-control" name="trackingNum" value="${shipping.trackingNum}">
			</div>
			<div class="col-md-3">
				<input type="button" class="btn btn-success settingTrackingNum" value="입력완료">
			</div>
		</div>
		<hr>
		<input type="hidden" name="orderNo" value="${orderNo}">
		<input type="hidden" name="productNo" value="${shipping.productNo}">
		<input type="hidden" name="mainCategory" value="${shipping.mainCategory}">
	</form>
	</c:forEach>
</c:if>

</div>