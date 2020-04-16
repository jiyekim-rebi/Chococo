<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="container">

	<h1 style="padding-top: 100px;" class="text-primary">배송지 입력/확인</h1>
	<hr>
	<c:if test="${isLogin == null }">
		<h3 style="padding-top: 50px;" class="text-danger">비회원 주문하기</h3>
		<h5 style="padding-bottom: 20px;" class="text-success">아래 정보를 입력해주셔야 주문이 완료됩니다.</h5>
		
		<div class="row" style="padding-top: 20px; padding-left: 50px;">
			<div class="col-md-3">
				<label>이름</label>
			</div>
			<div class="col-md-9">
				<input type="text" style="width: 70%;" class="form-control checkOutName" name="userName">
			</div>
		</div>
		<div class="row" style="padding-top: 20px; padding-left: 50px;">
			<div class="col-md-3">
				<label>이메일</label>
			</div>
			<div class="col-md-9">
				<input type="text" style="width: 70%;" class="form-control checkOutEmail" name="userEmail">
			</div>
		</div>
		<div class="row" style="padding-top: 20px; padding-left: 50px;">
			<div class="col-md-3">
				<label>주소</label>
			</div>
			<div class="col-md-9">
				<input type="text" style="width: 70%;" class="form-control checkOutAddress" name="address">
			</div>
		</div>
		<div class="row" style="padding-top: 20px; padding-left: 50px;">
			<div class="col-md-3">
				<label>배송시 메모</label>
			</div>
			<div class="col-md-9">
				<input type="text" style="width: 70%;" class="form-control" name="shippingMemo">
			</div>
		</div>
	</c:if>
	
	<c:if test="${isLogin != null }">
		<h3 style="padding-top: 50px; padding-bottom: 20px;" class="text-danger">아래 정보를 확인해주세요.</h3>
		<div class="row" style="padding-top: 20px; padding-left: 50px;">
			<div class="col-md-3">
				<label>이름</label>
			</div>
			<div class="col-md-9">
				<input type="text" style="width: 70%;" class="form-control checkOutName" name="userName" value="${isLogin.userName }" readonly>
			</div>
		</div>
		<div class="row" style="padding-top: 20px; padding-left: 50px;">
			<div class="col-md-3">
				<label>이메일</label>
			</div>
			<div class="col-md-9">
				<input type="text" style="width: 70%;" class="form-control checkOutEmail" name="userEmail" value="${isLogin.userEmail }" readonly>
			</div>
		</div>
		<div class="row" style="padding-top: 20px; padding-left: 50px;">
			<div class="col-md-3">
				<label>주소</label>
			</div>
			<div class="col-md-9">
				<input type="text" style="width: 70%;" class="form-control checkOutAddress" name="address" value="${isLogin.address }">
			</div>
		</div>
		<div class="row" style="padding-top: 20px; padding-left: 50px;">
			<div class="col-md-3">
				<label>배송시 메모</label>
			</div>
			<div class="col-md-9">
				<input type="text" style="width: 70%;" class="form-control" name="shippingMemo">
			</div>
		</div>
	</c:if>

	<h2 style="padding-top: 200px;" class="text-primary">주문상품 정보</h2>
	<hr>

	<c:if test="${orderList != null }">
	<form name="productCheckOutForm">
		<input type="hidden" name="size" id="orderListSize" value="${fn:length(orderList)}" >
		<c:forEach items="${orderList}" var="orderList" varStatus="status">
			<div class="row">
			<div class="col-md-4" align="center" style="padding-top: 15px;">
				<img src="/chococo/img/loading?imageFileName=${orderList.imageFileName}&mainCategory=${orderList.mainCategory}" alt="Image" style="max-width:100%;">
			</div>
			<div class="col-md-6" style="padding-top: 15px;">
				<input type="hidden" name="basketLists[${status.index}].productNo" value="${orderList.productNo}">
				<input type="hidden" name="basketLists[${status.index}].productName" value="${orderList.productName}">
				<input type="hidden" name="basketLists[${status.index}].price" value="${orderList.price}">
				<input type="hidden" name="basketLists[${status.index}].selectOption" value="${orderList.selectOption}">
				<input type="hidden" name="basketLists[${status.index}].mainCategory" value="${orderList.mainCategory}">
				<input type="hidden" name="basketLists[${status.index}].imageFileName" value="${orderList.imageFileName }">

				<h4 class="text-success">${orderList.productName }</h4>
				<div class="row">
					<div class="col-md-3">
						<p style="padding-top: 5px;">상품 주문 개수</p> 
					</div>
					<div class="col-md-9">
						<input type="text" class="form-control" name="basketLists[${status.index}].amount" value="${orderList.amount }">
					</div>
				</div>
				<div>
					선택옵션 : ${orderList.selectOption}
				</div>
				<div style="padding-top: 15px;">
					<h5 class="text-primary">상품가격 : <fmt:formatNumber value="${orderList.price}" type="currency" currencySymbol="￦"/></h5>
				</div>
			</div>
			<div class="col-md-2">
				<input type="hidden" name="productNo" value="${orderList.productNo }">
				<input type="hidden" name="mainCategory" value="${orderList.mainCategory }">
				<input type="hidden" name="selectOption" value="${orderList.selectOption }">
				<button class="btn btn-danger checkOutProductDel" style="margin-top: 70px;">삭제하기</button>
			</div>
		</div>
		</c:forEach>
		<input type="hidden" name="couponNumber" value="${couponNumber}">
	</form>
	<hr>
	<form name="checkOutProductDelForm"></form>
	</c:if>
	
	<c:if test="${orderList == null }">
		<hr>
		<div class="row" style="margin-top: 150px; margin-bottom: 150px;">
			<h2 class="text-danger">상품이 없습니다. 상품을 담아주세요!</h2>
		</div>
		<hr>
	</c:if>
	<div style="margin-top: 200px;"></div>
	<c:if test="${isLogin != null}">
	<h3>사용할 수 있는 쿠폰</h3>
	<hr>
		<c:if test="${coupon != null }">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col" style="width: 40%">쿠폰명</th>
						<th scope="col" style="width: 20%">할인율</th>
						<th scope="col" style="width: 20%">사용기한</th>
						<th scope="col">쿠폰사용</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${coupon}" var="coupon">
					<tr class="table">
						<td>${coupon.content}</td>
						<td>${coupon.ratio}%</td>
						<td><fmt:formatDate value="${coupon.dateOfUse}" pattern="yyyy-MM-dd"/></td>
						<td>
						<c:if test="${coupon.couponNumber != couponNumber}">
						<form>
							<c:if test="${fn:length(orderList) == 1}">
								<c:forEach items="${orderList }" var="couponOrder">
									<input type="hidden" name="productNo" value="${couponOrder.productNo}">
									<input type="hidden" name="productName" value="${couponOrder.productName}">
									<input type="hidden" name="price" value="${couponOrder.price}">
									<input type="hidden" name="selectOption" value="${couponOrder.selectOption}">
									<input type="hidden" name="mainCategory" value="${couponOrder.mainCategory}">
									<input type="hidden" name="imageFileName" value="${couponOrder.imageFileName }">
								</c:forEach>
							</c:if>
							<input type="hidden" name="ratio" value="${coupon.ratio }">
							<input type="hidden" name="couponNumber" value="${coupon.couponNumber }">
							<input type="button" class="btn btn-warning checkOutUseCouponCtr" value="쿠폰 사용하기">
						</form>
						</c:if>
						<c:if test="${coupon.couponNumber == couponNumber}">
							<p class="text-danger">적용된 쿠폰</p>
						</c:if>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${coupon == null }">
			<div class="row" style="margin: 100px 0px;">
				<h5 class="text-danger">사용할 수 있는 쿠폰이 없습니다.</h5>
			</div>
		</c:if>
	</c:if>
	
	<div class="row" style="margin-top: 100px;">
		<div class="col-md-8">
		</div>		
		<c:if test="${orderList != null }">
		<div class="col-md-4">
			<div>
				<label id="basketPrice">상품금액 : ${basketPrice}원</label>
			</div>
			<div>
				<label class="text-success">쿠폰사용 : ${ratio}% 할인</label>
				<input type="hidden" name="useRatio" value="${ratio}">
			</div>
			<div>
				<label>배송비 : 3000 원</label>
			</div>
			<div>
			<hr>
				<h4 id="basketTotalPrice" class="text-danger">총 결제금액 : ${totalPrice} 원</h4>
				<input type="hidden" id="ctrTotalPrice" name="totalPrice" value="${totalPrice}">
			</div>
		</div>
		</c:if>
	</div>
	<hr>
	<div class="row" style="padding-top: 100px; padding-bottom: 300px;">
		<div class="col-md-8">
			<textarea class="form-control" style="resize: none;" rows="10" readonly>
				전자금융거래 기본약관
				
이 약관에서 정하는 용어의 정의는 다음과 같습니다. 
1. '전자금융거래'라 함은 회사가 전자적 장치를 통하여 전자지급결제대행서비스 및 결제대금예치서비스(이하 '전자금융거래 서비스'라고 합니다)를 제공하고, 이용자가 회사의 종사자와 직접 대면하거나 의사소통을 하지 아니하고 자동화된 방식으로 이를 이용하는 거래를 말합니다. 

2. '전자지급결제대행서비스'라 함은 전자적 방법으로 재화의 구입 또는 용역의 이용에 있어서 지급결제정보를 송신하거나 수신하는 것 또는 그 대가의 정산을 대행하거나 매개하는 서비스를 말합니다.

3. '결제대금예치서비스'라 함은 이용자가 재화의 구입 또는 용역의 이용에 있어서 그 대가(이하 '결제대금'이라 한다)의 전부 또는 일부를 재화 또는 용역(이하 '재화 등'이라 합니다)을 공급받기 전에 미리 지급하는 경우, 회사가 이용자의 물품수령 또는 서비스 이용 확인 시점까지 결제대금을 예치하는 서비스를 말합니다.
  
4. '이용자'라 함은 이 약관에 동의하고 회사가 제공하는 전자금융거래 서비스를 이용하는 자를 말합니다. 

5. '접근매체'라 함은 전자금융거래에 있어서 거래지시를 하거나 이용자 및 거래내용의 진실성과 정확성을 확보하기 위하여 사용되는 수단 또는 정보로서 전자식 카드 및 이에 준하는 전자적 정보(신용카드번호를 포함한다), '전자서명법'상의 인증서, 회사에 등록된 이용자번호, 이용자의 생체정보, 이상의 수단이나 정보를 사용하는데 필요한 비밀번호 등 전자금융거래법 제2조 제10호에서 정하고 있는 것을 말합니다.

6. '거래지시'라 함은 이용자가 본 약관에 의하여 체결되는 전자금융거래계약에 따라 회사에 대하여 전자금융거래의 처리를 지시하는 것을 말합니다. 

7. '오류'라 함은 이용자의 고의 또는 과실 없이 전자금융거래가 전자금융거래계약 또는 이용자의 거래지시에 따라 이행되지 아니한 경우를 말합니다.   
			</textarea>
		</div>
		<div class="col-md-4">
			<div style="padding-top: 100px;" align="center">
				<label style="padding-right: 15px;">결제 약관에 동의합니다. </label><input type="checkbox" class="checkOutCheck">
			</div>
			<div style="padding-top: 20px;">
				<button class="btn btn-warning btn-lg checkOutProductCtl" style="width: 100%; margin-bottom: 10px;">결제하기</button>
				<button class="btn btn-secondary btn-lg" onClick="backToMarket();" style="width: 100%;">취소하기</button>
			</div>
		</div>
	</div>
	
	
	
</div>