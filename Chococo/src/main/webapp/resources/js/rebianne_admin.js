/*
 * by Rebianne
 * Admin page scripts
 */

$(document).ready(function(){
	//상품 등록 controller 연결
	$(".adminMarketProductAdd").click(function(){
		var formObj = $("form[name='adminMarketProductAddForm']");
		var mainCategory = $(".adminMarketAddMainCategory option:selected").val();
		var subCategory = $(".productSubCategory option:selected").val();
		formObj.append("<input type='hidden' name='mainCategory' value='"+mainCategory+"'>");
		formObj.append("<input type='hidden' name='subCategory' value='"+subCategory+"'>");
		formObj.submit();
	});
	
	
	
	var subList = [["DIY 세트", "도구세트"], ["가구", "인테리어", "주방", "그릇/도자기"],
			["실리콘 몰드", "플라스틱 몰드"], ["레진", "점토", "도구"]];

	//2020.04.06
	//상품등록 - 카테고리 변경 눌렀을 때 서브카테고리 자동으로 추가
	//productInsert, productModify 둘다 사용함.
	$(".adminMarketAddMainCategory").change(function(){
		var mainCategory = $(".adminMarketAddMainCategory option:selected").val();
		var selectForm = $(".productSubCategory");
		selectForm.find("option").remove();

		for(var i = 0; i<subList[mainCategory-1].length; i++){
			selectForm.append("<option value="+(i+1)+">" + subList[mainCategory-1][i] + "</option>");
		}
		
	});
	
	
	
	//상품 이름검색 controller
	//productList로 redirect 되는 형식으로 출력함.
	$(".adminProductSearch").click(function(){
		var formObj = $("form[name='adminProductSearchForm']");
		//productMainCategory
		var mainCategory = $(".productMainCategory option:selected").val();
		formObj.append("<input type='hidden' name='mainCategory' value='"+mainCategory+"'>");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/productList");
		formObj.submit();
	});
	
	
	//productView 에서 modify, info 관련 on/off css 처리
	$(".adminProductModifyOnOff").click(function(){
		if($(".adminProductModifyDiv").css("display") == "none"){
			$(".adminProductModifyDiv").show();
			$(".adminProductViewDiv").hide();
		} else {
			$(".adminProductModifyDiv").hide();
			$(".adminProductViewDiv").show();
		}
	});
	
	//productView -> modify Post
	///market/productModify
	$(".adminProductModifyCtr").click(function(){
		var formObj = $("form[name='adminProductModifyForm']");
		var mainCategory = $(".adminMarketAddMainCategory option:selected").val();
		var subCategory = $(".productSubCategory option:selected").val();
		formObj.append("<input type='hidden' name='mainCategory' value='"+mainCategory+"'>");
		formObj.append("<input type='hidden' name='subCategory' value='"+subCategory+"'>");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/market/productModify");
		formObj.submit();
	});
	
	//productView -> backToList
	$(".adminProductBackToList").click(function(){
		var formObj = $("form[name='adminBackToListForm']");
		formObj.attr("method", "get");
		formObj.attr("action", "/chococo/admin/productList");
		formObj.submit();
	});
	
	//productStatusUpdate - controller
	$(".adminProductStatusUpdateCtr").click(function(){
		var formObj = $("form[name='adminProductStatusUpdateForm']");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/market/productStatusUpdate");
		formObj.submit();
	});
	
	//$("input:not([readonly]),select:not([readonly])") >
	//shippingView - modify On
	$(".shippingViewOn").click(function(){
		$(this).parent().prev().find("input").prop("readonly", false);
		
		$(this).parent().next().show();
		$(this).parent().hide();
	});
	
	//shippingView - modify Off
	$(".shippingViewOff").click(function(){
		$(this).parent().prev().prev().find("input").prop("readonly", true);
		
		$(this).parent().prev().show();
		$(this).parent().hide();
	});
	
	
	
	//shippingView - modify Controller!
	$(".shippingViewModCtr").click(function(){
		var formObj = $(this).parent().parent().parent();
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/order/orderModify");
		formObj.submit();
	});
	
	
	
	//트래킹번호 업데이트
	$(".settingTrackingNum").click(function(){
		var formObj = $(this).parent().parent().parent();
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/order/trackingUpdate");
		formObj.submit();
	});
	
	//배송완료 처리
	$(".shippingTrackingOk").click(function(){
		var formObj = $(this).next();
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/order/trackingSuccess");
		formObj.submit();
	});
	
	//커뮤니티 공지 등록 controller
	///notice/insert
	$(".adminNoticeInsertCtr").click(function(){
		var formObj = $("form[name='adminNoticeInsertForm']");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/notice/insert");
		formObj.submit();
	});
	
	//커뮤니티 공지 수정 controller
	$(".adminNoticeModifyCtr").click(function(){
		var formObj = $("form[name='adminNoticeModifyForm']");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/notice/modify");
		formObj.submit();
	});
	
	//커뮤니티 수정 form on/off
	$(".adminNoticeModifyOnOff").click(function(){
		if($(".adminNoticeModifyView").css("display") == "none"){
			$(".adminNoticeModifyView").show();
			$(".adminNoticeView").hide();
		} else {
			$(".adminNoticeView").show();
			$(".adminNoticeModifyView").hide();
		}
	});
	
	//커뮤니티 공지 삭제 controller
	$(".adminNoticeDeleteCtr").click(function(){
		if(confirm("정말로 삭제하시겠어요?")){
			var formObj = $("form[name='adminNoticeDeleteForm']");
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/admin/notice/delete");
			formObj.submit();
		}
	});
	
	
	//communityList - 검색
	//게시판 - 게시글 검색 관련
	$(".adminCommunityListSearch").click(function() {
		var formObj = $("form[name='adminCommunityForm']");
		formObj.attr("method", "get");
		formObj.attr("action", "/chococo/admin/communityList");
		formObj.submit();
	});
	
	//adminSearchMember
	$(".adminSearchMember").click(function(){
		var formObj = $("form[name='adminCommunityForm']");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/memberList");
		formObj.submit();
	})
	
	//memberList modifyForm on/off
	$(".memberModifyOnOff").click(function(){
		if($(".memberListModifyBtn").css("display") == "none"){
			$(".memberListBtn").hide();
			$(".memberListModifyBtn").show();
			$(".adminMemberListInputRead").prop("readonly", false);
		} else {
			$(".memberListBtn").show();
			$(".memberListModifyBtn").hide();
			$(".adminMemberListInputRead").prop("readonly", true);
		}
	});
	
	//memberList에서 회원정보 modify하는 controller 연결
	$(".adminMemberModifyCtr").click(function(){
		var formObj = $("form[name='adminMemberForm']");
		
		var address = $("#addressData").val();
		if(address == "") {
			alert("주소창이 비어있습니다. 채워주세요.");
			return ;
		}
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/member/modify");
		formObj.submit();
	});
	
	//밴처리
	$(".adminMemberBanCtr").click(function(){
		var formObj = $("form[name='adminMemberForm']");
		var isBan = $(this).next().val();
		formObj.append("<input type='hidden' name='isBan' value='"+isBan+"'>");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/member/isBan");
		formObj.submit();
	});
	
	//admin에서 탈퇴처리
	$(".adminMemberDeleteCtr").click(function(){
		if(confirm("정말 회원탈퇴 처리를 하시겠어요?")){
			var formObj = $("form[name='adminMemberForm']");
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/admin/member/delete");
			formObj.submit();
		}
	});
	
	//coupon create form on/off
	$(".couponAddOnOff").click(function(){
		if($(".Rebi_CouponCreate").css("display") == "none"){
			$(".Rebi_create").show();
		} else {
			$(".Rebi_create").hide();
		}
		$(".Rebi_CouponCreate").toggleClass("emphasized");
	});
	
	//coupon add Controller
	$(".adminCouponAddCtr").click(function(){
		var formObj = $("form[name='adminCouponCreateForm']");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/coupon/add");
		formObj.submit();
	});
		
	//쿠폰 content 검색 controller
	$(".adminCouponSearchCtr").click(function(){
		var formObj = $("form[name='adminCouponSearchForm']");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/coupon");
		formObj.submit();
	});
	
	//coupon notUse Controller
	$(".adminCouponNotUseCtr").click(function(){
		var formObj = $("form[name='adminCouponNotUseForm']");
		var couponNumber = $(this).next().val();
		formObj.append("<input type='hidden' name='couponNumber' value='"+couponNumber+"'>");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/admin/coupon/notUse");
		formObj.submit();
	});
	
	
	
})

function noticeInsertView(){
	location.href="/chococo/admin/communityInsert";
}
