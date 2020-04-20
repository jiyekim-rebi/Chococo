$(document).ready(function(){

	//최상단으로 올라가는 button
	//일정 스크롤 내려가면 나타나게, top에 위치했을땐 display:none
	$(window).scroll(function(){
		if($(this).scrollTop() > 200) {
			$('.Rebi_top').fadeIn();
		} else {
			$('.Rebi_top').fadeOut();
		}
	})

	$(".Rebi_top").click(function(){
		$('html, body').animate({ scrollTop:0 }, 1000);
	})

	//Mypage 버튼 눌렀을 때.
	$(".nav_mypage").click(function(){
		location.href="/chococo/mypage/main";
	})

	//admin 버튼 눌렀을 때
	$(".nav_admin").click(function(){
		location.href="/chococo/admin/main";
	})

	//Forgot Password?
	$(".ForgotPw").click(function(){
		if($(".Rebi_ForgotPw").css("display") == "none"){
			$(".Rebi_ForgotPw").show();
		} else {
			$(".Rebi_ForgotPw").hide();
		}
		$(".forgotCard").toggleClass("emphasized");
	})

	//Market Category on/off
	$(".Rebi_dropdown").click(function(){
		if($(".rebi_dropdown_content").css("display") == "none"){
			$(".rebi_dropdown_content").show();
		} else {
			$(".rebi_dropdown_content").hide();
		}
	})

	//2020.03.20
	//Market - productDetail 에서 amount값 변경에 따라 실시간으로 총 결제금액 계산하기
	var oldAmount = 1;
	var productPrice = $("#productPrice").val();
	$("#checkAmount").on("propertychange input", function(){
		var newAmount = $(this).val();
		if(oldAmount == newAmount || newAmount == ""){
			return ;
		}
		oldAmount = newAmount;
		$("#totalPrice").text("총 결제금액 : " + productPrice * oldAmount + "원");
	});
	
	
	//바로결제 관련 FORM 생성 후 전송
	///chococo/market/checkOut
	$(".purchaseProduct").click(function(){
		var formObj = $("form[name='purchaseProductForm']");
		var amount = $(".productAmount").val();
		var selectOption = $(".productSelectOption option:selected").val();
		formObj.append("<input type='hidden' name='amount' value='" + amount + "'>");
		formObj.append("<input type='hidden' name='selectOption' value='" + selectOption + "'>");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/market/checkOut");
		formObj.submit();
	});
	
	//장바구니에 상품 추가하기
	$(".basketAddProduct").click(function(){
		var formObj = $("form[name='purchaseProductForm']");
		var amount = $(".productAmount").val();
		var selectOption = $(".productSelectOption option:selected").val();
		formObj.append("<input type='hidden' name='amount' value='" + amount + "'>");
		formObj.append("<input type='hidden' name='selectOption' value='" + selectOption + "'>");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/market/basketAdd");
		formObj.submit();
	})
	
	//장바구니에서 상품 삭제하기
	$(".cartProductDel").click(function(){
		var formObj = $(this).parent();
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/market/basketDel");
		formObj.submit();
	});
	
	//결제하기 form에서 물품삭제가 들어왔을 때.
	//2020.03.26 form data 동적 생성 후 submit
	$(".checkOutProductDel").click(function(){
		if(confirm("정말 삭제하시겠어요?")){
			var selectOption = $(this).prev().val();
			var mainCategory = $(this).prev().prev().val();
			var productNo = $(this).prev().prev().prev().val();
			var formObj = $("form[name='checkOutProductDelForm'");
			formObj.append("<input type='hidden' name='productNo' value='" + productNo + "'>");
			formObj.append("<input type='hidden' name='mainCategory' value='" + mainCategory + "'>");
			formObj.append("<input type='hidden' name='selectOption' value='" + selectOption + "'>");
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/market/checkOutDelete");
			formObj.submit();
		}
	});
	
	//결제하기 전 빈칸 체크
	//빈칸 없으면 컨트롤러로 보냄
	$(".checkOutProductCtl").click(function(){
		var formObj = $("form[name='productCheckOutForm']");
		
		var userName = $(".checkOutName").val();
		var userEmail = $(".checkOutEmail").val();
		var address = $(".checkOutAddress").val();
		var totalPrice = $("#ctrTotalPrice").val();
		
		if(userName == ""){
			alert("이름을 입력해주세요!");
		} else if(userEmail == ""){
			alert("이메일을 입력해주세요!");
		} else if(address == ""){
			alert("주소를 입력해주세요!");
		} else if($(".checkOutCheck").is(":checked") == false) {
			alert("결제 약관에 동의해주세요!");
		} else {
			formObj.append("<input type='hidden' name='userName' value='" + userName + "'>");
			formObj.append("<input type='hidden' name='userEmail' value='" + userEmail + "'>");
			formObj.append("<input type='hidden' name='address' value='" + address + "'>");
			formObj.append("<input type='hidden' name='totalPrice' value='" + totalPrice + "'>");
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/market/order");
			formObj.submit();
		}
	});
	
	//productDetail - 댓글 수정 on/off
	//display: none;
	$(".reviewFormOnOff").click(function(){
		if($(".Rebi_reviewPrint").css("display") == "none"){
			$(".Rebi_reviewPrint").show();
			$(".Rebi_reviewModify").hide();
		} else {
			$(".Rebi_reviewPrint").hide();
			$(".Rebi_reviewModify").show();
		}
	});
	
	//리뷰 수정 form에서 정보 입력 후 수정완료 할 때.
	$(".reviewModifyCtr").click(function(){
		var formObj = $(this).parent().parent();
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/market/reviewModify");
		formObj.submit();
	});
	
	//리뷰 삭제하고 싶을 때
	$(".reviewDeleteCtr").click(function(){
		if(confirm("정말로 리뷰를 삭제하시겠어요?")){
			var formObj = $(this).next();
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/market/reviewDelete");
			formObj.submit();
		}
	});
	
	//리뷰 추가하기.
	$(".reviewInsertCtr").click(function(){
		var formObj = $(this).prev();
		if(formObj.children("input[name='writer']").val() == ""){
			alert("비회원은 리뷰를 남기실 수 없습니다.");
		} else {
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/market/reviewInsert");
			formObj.submit();
		}
	});
	
	//2020.03.26 상품 재구매!
	$(".mypageProductReOrder").click(function(){
		if(confirm("이 상품을 재주문 하시겠어요?")){
			var formObj = $(this).parent();
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/market/checkOut");
			formObj.submit();
		}	
	});
	
	//2020.03.27 ContactUs Controller
	$(".mailSendCtr").click(function(){
		if(confirm("메일을 전송하시겠어요?")){
			var formObj = $("form[name='contactUsForm']");
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/contactUs");
			formObj.submit();
		}
	});
	
	//2020.03.29 favorite Add/delete
	//1. DB에 데이터 가져가서 일단 있는지 체크
	//2. 없으면 DB에 등록
	//3. 있으면 confirm = 있는데 삭제할래?
	//4. ㅇㅇ 라고 하면 지워주기.
	$(".favoriteAddCtr").click(function(){
		var formObj = $("form[name='purchaseProductForm']");
		var mainCategory = formObj.children("input[name='mainCategory']").val();
		var productNo = formObj.children("input[name='productNo']").val();
		var productName = formObj.children("input[name='productName']").val();
		var imageFileName = formObj.children("input[name='imageFileName']").val();
		
		$.ajax({
			type:"post",
			url:"/chococo/market/favoriteSearch",
			data:{"mainCategory" : mainCategory,
				"productNo" : productNo,
				"productName" : productName,
				"imageFileName" : imageFileName},
			success: function(result){
				if(result == 0){
					$.ajax({
						type:"post",
						url:"/chococo/market/favoriteAdd",
						data:{"mainCategory" : mainCategory,
							"productNo" : productNo,
							"productName" : productName,
							"imageFileName" : imageFileName},
						success: function(result){
							alert("상품을 찜했습니다.");
						},
						error: function(result){
							alert("오류가 발생했습니다. 시스템 관리자에게 문의하세요.");
						}
					});
				} else if(result == 1){
					if(confirm("이미 찜한 상품입니다. 찜하기를 취소하시겠어요?")){
						$.ajax({
							type:"post",
							url:"/chococo/market/favoriteDel",
							data:{"mainCategory" : mainCategory,
								"productNo" : productNo,
								"productName" : productName,
								"imageFileName" : imageFileName},
							success: function(result){
								alert("상품 찜하기를 취소했습니다.");
							},
							error: function(result){
								alert("오류가 발생했습니다. 시스템 관리자에게 문의하세요.");
							}
						});
					}
				}
			},
			error: function(result){
				alert("오류가 발생했습니다. 시스템 관리자에게 문의하세요.");
			}
		});
	});
	
	//2020.04.03 checkOut page
	//amount값에 따라 최종적인 값 계산 다시해주기.
	$("form[name='productCheckOutForm']").on("propertychange input", function(){
		var orderListSize = $("#orderListSize").val();
		
		var basketTotalPrice = 0;
		var price = 0;
		var amount = 0;
		
		for(var i = 0; i<orderListSize; i++){
			price = parseInt($("input[name='basketLists["+i+"].price']").val());
			if($("input[name='basketLists["+i+"].amount']").val() == ""){
				amount = 1;
			} else {
				amount = parseInt($("input[name='basketLists["+i+"].amount']").val());
			}
			basketTotalPrice += price * amount;
		}
		
		$("#basketPrice").text("상품금액 : " + basketTotalPrice + "원");
		
		//할인쿠폰 적용되었을 때
		var Ratio = parseInt($("input[name='useRatio']").val());
		if(Ratio != 0){
			basketTotalPrice -= (basketTotalPrice / Ratio);
		}
		//배송비 추가
		basketTotalPrice += 3000;
		$("#basketTotalPrice").text("총 결제금액 : " + basketTotalPrice + "원");
		$("#ctrTotalPrice").val(basketTotalPrice);
	});
	
	//쿠폰적용
	$(".checkOutUseCouponCtr").click(function(){
		var formObj = $(this).parent();
		amount = parseInt($("input[name='basketLists[0].amount']").val());
		formObj.append("<input type='hidden' name='amount' value='" + amount + "'>");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/market/checkOut");
		formObj.submit();
	});
	
})

function productListOrder(mainCategory, subCategory, subCategoryOrderBy){
	location.href="/chococo/market/productList?mainCategory="+mainCategory+"&subCategory="+subCategory+"&subCategoryOrder=" + subCategoryOrderBy;
}

function checkOutForm(){
	location.href="/chococo/market/checkOut";
}

function backToMarket(){
	location.href="/chococo/market/main";
}

function mypageProductReview(productNo, mainCategory){
	location.href="/chococo/market/productDetail?mainCategory="+mainCategory+"&productNo="+productNo;
}
