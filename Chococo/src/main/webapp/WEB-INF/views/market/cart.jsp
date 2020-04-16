<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<!-- ${cartList} -->
<div class="container">

	<h1 style="padding-top: 100px;" class="text-primary">나의 장바구니</h1>
	<hr>

	<c:if test="${cartList != null }">
	<c:forEach items="${cartList }" var="cart">
		<div class="row">
			<div class="col-md-4" align="center" style="padding-top: 15px;">
				<img src="/chococo/img/loading?imageFileName=${cart.imageFileName}&mainCategory=${cart.mainCategory}" alt="Image" style="width: 150px; height: 150px;">
			</div>
			<div class="col-md-6" style="padding-top: 15px;">
				<div>
					<label>상품명 : </label>${cart.productName }
				</div>
				<div>
				<label>주문개수 : ${cart.amount } </label>
				</div>
				<div>
					<label>상품가격 : ${cart.price} </label>
				</div>
				<div>
					<label>선택옵션 : ${cart.selectOption } </label>
				</div>
			</div>
			<div class="col-md-2">
				<form>
					<input type="hidden" name="productNo" value="${cart.productNo }">
					<input type="hidden" name="mainCategory" value="${cart.mainCategory }">
					<input type="hidden" name="selectOption" value="${cart.selectOption }">
					<button class="btn btn-danger cartProductDel" style="margin-top: 70px;">삭제하기</button>
				</form>
			</div>
		</div>
		<hr>
	</c:forEach>
	
	<div class="row" style="margin: 150px 0;">
		<div class="center-block" style="margin: 0 auto;">
			<input type="button" onClick="checkOutForm();" class="btn btn-danger btn-lg" value="결제하기">
			<input type="button" onClick="backToMarket();" class="btn btn-primary btn-lg" value="쇼핑 계속하기">
		</div>
	</div>
	</c:if>
	<c:if test="${cartList == null }">
		<div align="center" style="margin: 200px;">
			<h2 class="text-danger">현재 장바구니가 비어있습니다!</h2>
		</div>
		<hr>
		<div class="row">
			<div class="center-block" style="margin: 0 auto;">
				<input type="button" style="margin: 50px;" onClick="backToMarket();" class="btn btn-primary btn-lg" value="쇼핑하러 가기">
			</div>
		</div>
	</c:if>

</div>