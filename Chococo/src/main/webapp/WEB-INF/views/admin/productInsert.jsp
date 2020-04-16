<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!--Market - 상품 등록, 카테고리 등록, 상품정보 수정, 상품삭제, 배송관련 설정, 1:1 문의(환불, 교환), 리뷰관리 -->

	<div class="container">
		<form name="adminMarketProductAddForm" action="/chococo/admin/market/productAdd" method="post" enctype="multipart/form-data">
			<div align="center" style="margin-top: 50px; margin-bottom: 100px;">
				<h1 class="text-primary">New Product Insert</h1>
			</div>
			<div class="row">
				<div class="col-md-2">
					<h5 style="margin-top: 2px;">상품 이름</h5>
				</div>
				<div class="col-md-10">
					<input type="text" name="productName" class="form-control">
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-2">
					<h5 style="margin-top: 2px;">상품 가격 설정</h5>
				</div>
				<div class="col-md-10">
					<input type="text" name="price" class="form-control">
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
					<input type="text" name="selectOption" class="form-control">
				</div>
			</div>
			<hr>
			<h5>상품 상세 설명</h5>
			<textarea name="content" id="adminMarketAddText"></textarea>
			<script>
			$('#adminMarketAddText').summernote({
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
			<hr>
			<h5>상품 이미지 파일 업로드</h5>
			<input type="file" id="imgView" name="imageFile" class="form-control">
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
			<div class="row" style="margin-top: 50px; margin-bottom: 100px;">
				<div class="center-block" style="margin: 0 auto;">
					<input type="button" class="btn btn-success adminMarketProductAdd" value="상품 등록하기">
					<input type="reset" class="btn btn-warning" value="다시 입력하기">
				</div>
			</div>
		</form>
	</div>