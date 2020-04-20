<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="container" style="margin-bottom: 300px;">
	<div class="row" style="margin-top: 150px;">
		<div class="col-md-6" align="left">
			<a style="margin-left: 30px;" href="/chococo/market/productList?mainCategory=${mpc.mainCategory}"><i class="fas fa-angle-left fa-3x"></i></a>
		</div>
		<div class="col-md-6" align="right">
			<c:if test="${isLogin != null }">
			<i style="margin-right: 50px;" class="text-warning fas fa-star fa-2x favoriteAddCtr"></i>
			</c:if>
		</div>
	</div>
	<div align="center" style="margin-bottom: 50px;">
		<h1 class="text-primary">${product.productName}</h1>
	</div>
	
	<div class="row">
		<div class="col-md-6" style="padding-right: 10px;">
			<img src="/chococo/img/loading?imageFileName=${product.imageFileName}&mainCategory=${mpc.mainCategory}" alt="Image" style="width: 550px; height: 550px;">
		</div>
	
		<div class="col-md-6">
			<div class="row" style="padding-top: 50px;">
				<div class="col-md-3" align="center">
					<strong>상품번호</strong>
				</div>
				<div class="col-md-9">
					${product.productNo}
				</div>
			</div>
			<div class="row" style="padding-top: 30px;">
				<div class="col-md-3" align="center">
					<strong>상품가격</strong>
				</div>
				<div class="col-md-9">
					<fmt:formatNumber value="${product.price}" type="currency" currencySymbol="￦"/>
					<input type="hidden" id="productPrice" value="${product.price}">
				</div>
			</div>

			<div class="row" style="padding-top: 30px;">
				<div class="col-md-3" align="center">
					<strong>상품옵션</strong>
				</div>
				<div class="col-md-9">
					<select class="form-control productSelectOption">
						<c:forEach items="${selectOption}" var="option">
							<option value="${option }">${option }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="row" style="padding-top: 30px;">
				<div class="col-md-3" align="center">
					<strong>구매개수</strong>
				</div>
				<div class="col-md-9">
					<input type="text" class="form-control productAmount" name="amount" id="checkAmount" value="1">
				</div>
			</div>
			
			<div align="right" style="padding-top: 100px;">
				<h3 class="text-danger" id="totalPrice">총 결제금액 : ${product.price} 원</h3>
			</div>
			
			
			<div class="row" style="padding-top: 30px;">
				<c:if test="${product.productStatus == 1 }">
				<div class="col-md-6" align="center">
					<form name="purchaseProductForm">
						<input type="hidden" name="price" value="${product.price }">
						<input type="hidden" name="productName" value="${product.productName}">				
						<input type="hidden" name="productNo" value="${product.productNo}">				
						<input type="hidden" name="mainCategory" value="${mpc.mainCategory}">
						<input type="hidden" name="imageFileName" value="${product.imageFileName}">
					</form>
					<input class="btn btn-primary purchaseProduct" style="width: 100%; height: 150%;" type="button" value="즉시구매하기">
				</div>
				<div class="col-md-6" align="center">
					<input class="btn btn-warning basketAddProduct" style="width: 100%; height: 150%;" type="button" value="장바구니담기">
				</div>
				</c:if>
				<c:if test="${product.productStatus == 0 }">
				<div style="margin-top: 50px;">
					<h5 class="text-danger">판매 중단된 상품입니다.</h5>
				</div>
				</c:if>
			</div>
		</div>
	</div>
	
	<div align="center" style="margin-top: 100px; margin-bottom: 50px;">
		<ul class="nav nav-tabs">
			<li class="nav-item" style="width: 33%"><a class="nav-link active" href="#detailView" data-toggle="tab">상세보기</a></li>
			<li class="nav-item" style="width: 33%"><a class="nav-link" href="#shippingInfo" data-toggle="tab">배송안내</a></li>
			<li class="nav-item" style="width: 33%"><a class="nav-link" href="#customerReview" data-toggle="tab">상품후기</a></li>
		</ul>
	</div>
	
	
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade active show" id="detailView">
			<div align="center">${product.content}</div>
		</div>
		
		<div class="tab-pane fade" id="shippingInfo" align="center">
			<img src="../resources/image/shippingInfo.jpg">
		</div>
		
		<div class="tab-pane fade" id="customerReview">
			<div style="margin-bottom: 50px;">
				<h2 class="text-primary">Customer Review</h2>
				<h6 class="text-muted">소중한 후기를 남겨주세요! 도움이 많이 되어요.</h6>
			</div>
			<div>
				<label class="text-muted">후기 작성하기</label>
				<form>
					<input type="hidden" name="productName" value="${product.productName }">
					<input type="hidden" name="writer" value="${isLogin.userName}">
					<input type="hidden" name="productNo" value="${product.productNo }">
					<input type="hidden" name="mainCategory" value="${mpc.mainCategory }">
					<textarea class="form-control" name="content" id="productReviewTextarea"></textarea>
					<script>
					$('#productReviewTextarea').summernote({
						placeholder: '리뷰를 입력해주세요.',
						lang: 'ko-KR',
						tabsize: 2,
						height: 120,
						toolbar: [
						['style', ['style']],
						['font', ['bold', 'underline', 'clear']],
						['color', ['color']],
						['para', ['ul', 'ol', 'paragraph']],
						['table', ['table']],
						['insert', ['link', 'picture', 'video']],
						['view', ['fullscreen', 'codeview', 'help']]
						]
					});
					</script>
				</form>
				<button type="button" class="btn btn-success reviewInsertCtr" style="margin-top: 10px;">Success</button>
				<hr>
			</div>
			
			<div style="margin-top: 100px;">
				<h2 class="text-primary">Review List</h2>
				<hr>
			</div>
			<c:if test="${reviewList == null}">
				<div align="center">
					<h2 class="text-danger" style="padding-top: 250px; padding-bottom: 250px;">작성된 글이 없습니다.</h2>
					<hr>
				</div>
			</c:if>
			<c:if test="${reviewList != null}">
				<c:forEach items="${reviewList}" var="review">
				<div class="row">
					<div class="col-md-6">
						<h6>작성자 : ${review.writer}</h6>
					</div>
					<div class="col-md-6" align="right">
						<h6>작성일자 : <fmt:formatDate value="${review.regDate}" pattern="yyyy-MM-dd"/></h6>
					</div>
				</div>
				
				<div class="Rebi_reviewPrint" style="display: inline;">
					<div style="padding-top: 20px;">
						${review.content}
						<div align="right" style="padding-top: 20px;">
							<c:if test="${isLogin.userName == review.writer }">
								<button type="button" class="btn btn-warning reviewFormOnOff">수정하기</button>
							</c:if>
							<c:if test="${isLogin.userName == review.writer || adminCheck != null}">
								<button type="button" class="btn btn-danger reviewDeleteCtr">삭제하기</button>
							</c:if>
								<form>
									<input type="hidden" name="mainCategory" value="${review.mainCategory }">
									<input type="hidden" name="productNo" value="${review.productNo}">
									<input type="hidden" name="boardNo" value="${review.boardNo }">
							</form>
							
						</div>
					</div>
				</div>
				
				<div class="Rebi_reviewModify" style="display: none;">
					<div style="padding-top: 20px;">
						<form>
							<input type="hidden" name="writer" value="${isLogin.userName}">
							<input type="hidden" name="mainCategory" value="${review.mainCategory }">
							<input type="hidden" name="productNo" value="${review.productNo}">
							<input type="hidden" name="boardNo" value="${review.boardNo }">
							<textarea class="form-control" name="content" id="productReviewModifyTextarea">${review.content}</textarea>
							<script>
							$('#productReviewModifyTextarea').summernote({
								placeholder: '리뷰를 입력해주세요.',
								lang: 'ko-KR',
								tabsize: 2,
								height: 120,
								toolbar: [
								['style', ['style']],
								['font', ['bold', 'underline', 'clear']],
								['color', ['color']],
								['para', ['ul', 'ol', 'paragraph']],
								['table', ['table']],
								['insert', ['link', 'picture', 'video']],
								['view', ['fullscreen', 'codeview', 'help']]
								]
							});
							</script>
							<div align="right" style="padding-top: 20px;">
								<button type="button" class="btn btn-warning reviewModifyCtr">리뷰수정</button>
								<button type="button" class="btn btn-danger reviewFormOnOff">취소하기</button>
							</div>
						</form>
					</div>
				</div>
				<hr>
				</c:forEach>
			</c:if>
		</div>
	</div>
</div>