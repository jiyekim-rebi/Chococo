<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

	<!-- 등록된 상품 정보보기 -->
	<div class="container adminProductViewDiv">
		<div align="center" style="margin-top: 50px; margin-bottom: 100px;">
				<h2 class="text-primary">등록된 상품 정보보기</h2>
		</div>
		<div class="row">
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">상품 이름</h5>
			</div>
			<div class="col-md-10">
				<input type="text" name="productName" class="form-control" value="${product.productName}" readOnly>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">상품 가격</h5>
			</div>
			<div class="col-md-10">
				<input type="text" name="price" class="form-control" value="${product.price}" readOnly>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">	
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">상품 메인 카테고리</h5>
			</div>
			<div class="col-md-10">
				<input type="text" name="mainCategory" class="form-control" value="${cri.mainCategory }" readOnly>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">	
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">상품 서브 카테고리</h5>
			</div>
			<div class="col-md-10">
				<input type="text" name="subCategory" class="form-control" value="${product.subCategory }" readOnly>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">	
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">상품 선택 옵션</h5>
			</div>
			<div class="col-md-10">
				<input type="text" name="selectOption" class="form-control" value="${product.selectOption}" readOnly>
			</div>
		</div>
		<hr>
		<h5>상품 상세 설명</h5>
		<div>
			<pre>${product.content }</pre>
		</div>
		<hr>
		<div>
			<!-- 이미지 등록 구간 -->
			<h5>상품 미리보기 사진</h5>
			<img src="/chococo/img/loading?imageFileName=${product.imageFileName}&mainCategory=${cri.mainCategory}" alt="Image" style="width: 300px; height: 300px;"/>
		</div>
		<div class="row" style="margin-top: 50px; margin-bottom: 100px;">
			<div class="center-block" style="margin: 0 auto;">
				<input type="button" class="btn btn-success adminProductModifyOnOff" value="상품 수정하기">
				<c:if test="${product.productStatus == 1 }">
					<input type="button" class="btn btn-danger adminProductStatusUpdateCtr" value="판매중단하기">
				</c:if>
				<c:if test="${product.productStatus == 0 }">
					<input type="button" class="btn btn-warning adminProductStatusUpdateCtr" value="상품 판매하기">
				</c:if>
				<input type="button" class="btn btn-info adminProductBackToList" value="Back To List">
				<form name="adminBackToListForm">
					<input type="hidden" name="mainCategory" value="${cri.mainCategory }">
					<input type="hidden" name="keyword" value="${cri.keyword}">
				</form>
				<form name="adminProductStatusUpdateForm">
					<input type="hidden" name="mainCategory" value="${cri.mainCategory}">
					<input type="hidden" name="productNo" value="${product.productNo }">
					<input type="hidden" name="productStatus" value="${product.productStatus }">
				</form>

			</div>
		</div>
	</div>
	
	 <!-- 상품 정보 수정하기 -->
	 <div class="container adminProductModifyDiv">
		<div align="center" style="margin-top: 50px; margin-bottom: 100px;">
				<h2 class="text-primary">상품 등록 정보 수정하기</h2>
		</div>
		<form name="adminProductModifyForm" enctype="multipart/form-data">
		<div class="row">
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">상품 이름</h5>
			</div>
			<div class="col-md-10">
				<input type="text" name="productName" class="form-control" value="${product.productName}">
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">상품 가격</h5>
			</div>
			<div class="col-md-10">
				<input type="text" name="price" class="form-control" value="${product.price}">
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">	
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">상품 메인 카테고리</h5>
			</div>
			<div class="col-md-10">
				<select class="form-control adminMarketAddMainCategory">
					<option value="1">SET상품</option>
					<option value="2">소품모음</option>
					<option value="3">몰드</option>
					<option value="4">도구모음</option>
				</select>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">	
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">상품 서브 카테고리</h5>
			</div>
				<div class="col-md-10">
				<select class="form-control productSubCategory">
					<option value="1">DIY 세트</option>
					<option value="2">도구세트</option>
				</select>
			</div>
		</div>
		<div class="row" style="margin-top: 10px;">	
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">상품 선택 옵션</h5>
			</div>
			<div class="col-md-10">
				<input type="text" name="selectOption" class="form-control" value="${product.selectOption}">
			</div>
		</div>
		<hr>
		<h5>상품 상세 설명</h5>
		<textarea id="adminMarketModifyText" name="content">${product.content }</textarea>
		<script>
			$('#adminMarketModifyText').summernote({
				placeholder: '상품 정보를 입력해주세요!',
				lang: 'ko-KR',
				tabsize: 2,
				height: 400,
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
		<input type="hidden" name="productNo" value="${product.productNo }">
		<input type="hidden" name="imageFileName" value="${product.imageFileName }">
		<h5>상품 이미지 파일 업로드</h5>
			<input type="file" id="imgView" name="imageFile">
			<div class="uploadImg" style="margin-top: 20px;"><img src=""/></div>
			<script>
				$("#imgView").change(function(){
					if(this.files && this.files[0]){
						var reader = new FileReader;
						reader.onload = function(data){
							$(".uploadImg img").attr("src", data.target.result).width(300);
						}
						reader.readAsDataURL(this.files[0]);
					}
				});
			</script>
			<hr>
		</form>
		<div class="row" style="margin-top: 50px; margin-bottom: 100px;">
			<div class="center-block" style="margin: 0 auto;">
				<input type="button" class="btn btn-danger adminProductModifyCtr" value="수정 완료">
				<input type="button" class="btn btn-warning adminProductModifyOnOff" value="취소하기">
			</div>
		</div>
	</div>
