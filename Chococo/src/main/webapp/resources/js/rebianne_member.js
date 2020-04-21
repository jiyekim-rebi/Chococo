$(document).ready(function(){

	//회원가입 data checking후 Controller로 전송
	$(".createMemberDB").click(function(){
		var formObj=$("form[name='createMemberForm']");
		
		if($("input:radio[name=sex]:checked").val() == null || $("select[name='birth_month']").val() == "Month"
	|| $("select[name='birth_day']").val() == "Day" || $("select[name='birth_year']").val() == "Year"){
			alert("빈칸이 있습니다. 채워주세요.");
			return;
		}
		
		//자꾸 userPass값을 못 긁어오는 오류가 생겨서, form에서 find를 찾아서 접근함.
		var userPass = formObj.find("input[name='userPass']").val();
		var userPassCheck = formObj.find("input[name='userPassCheck']").val();
		if (userPass !== userPassCheck) {
			alert("비밀번호가 다릅니다. 다시 확인해주세요.");
			$("input[name='userPw']").focus();
			return;
		}
		
		var createNameOk = $(".createNameOk").val();
		var createEmailOk = $(".createEmailOk").val();
		var createPasswordOk = $(".createPasswordOk").val();
		
		if(createNameOk != "OK" || createEmailOk != "OK" || createPasswordOk != "OK"){
			alert("입력 정보를 확인해주세요!");
			return ;
		}
		
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/member/create");
		formObj.submit();

	});
	
	//로그아웃 버튼 눌렀을 때.
	$(".nav_logout").click(function(){
		if(confirm("정말 로그아웃 하시겠습니까?")){
			location.href="/chococo/member/logout";	
		}
	});
	
	//로그인 form ON/OFF
	$(".loginForm").click(function(){
		if($(".Rebi_login").css("display") == "none"){
			$(".Rebi_login").show();
		} else {
			$(".Rebi_login").hide();
		}
		$(".card").toggleClass("emphasized");
	});

	//회원가입 form ON/OFF
	$(".createForm").click(function(){
		if($(".Rebi_create").css("display") == "none"){
			$(".Rebi_create").show();
		} else {
			$(".Rebi_create").hide();
		}
		$(".createCard").toggleClass("emphasized");
	});
	
	//회원탈퇴 버튼
	$(".nav_deleteMember").click(function(){
		//체크박스 체크 완료됐는지 확인
		//아닐 경우 체크박스를 체크해달라고 alert 후 return
		var deleteCheck = $(".MemberDeleteCheck").is(":checked");
		if(deleteCheck == true){
			location.href="/chococo/member/deleteMember";
		} else {
			alert("회원 탈퇴 체크박스를 누르신 후 다시 시도해주세요.");
		}
	});
	
	//회원정보 수정 ON/OFF 관련 비밀번호 체크 Controller
	$(".myPagePassCheckFormCtr").click(function(){
		var formObj=$("form[name='myPagePassCheckForm']");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/mypage/main");
		formObj.submit();
	});
	
	//mypage - 회원정보 변경 Controller 연결
	$(".mypageProfileModifyCtr").click(function(){
		//생년월일
		var birth_year = $(".mypage_modify_year option:selected").val();
		var birth_month = $(".mypage_modify_month option:selected").val();
		var birth_day = $(".mypage_modify_day option:selected").val();
		//성별
		var sex = $(".mypage_modify_sex:checked").val();
		//주소
		var address = $(".mypage_modify_address").val();
		//비밀번호 변경일 경우
		var userPass = $(".mypage_modify_userPass").val();
		
		if(address == ""){
			alert("주소창이 비어있습니다. 채워주세요!");
		} else {
			var formObj = $(this).parent();
			formObj.append("<input type='hidden' name='birth_year' value='"+birth_year+"'>");
			formObj.append("<input type='hidden' name='birth_month' value='"+birth_month+"'>");
			formObj.append("<input type='hidden' name='birth_day' value='"+birth_day+"'>");
			formObj.append("<input type='hidden' name='sex' value='"+sex+"'>");
			formObj.append("<input type='hidden' name='address' value='"+address+"'>");
			formObj.append("<input type='hidden' name='userPass' value='"+userPass+"'>");
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/member/modifyMember");
			formObj.submit();
		}
	});
	
	//2020.03.27 forget Password?
	$(".forgetPasswordCtr").click(function(){
		var formObj = $("form[name='forgetPasswordForm']");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/member/forgetPw");
		formObj.submit();
	});
	
	//2020.03.27 이름, 이메일 중복 체크 관련 세팅
	//2020.04.21 이름 4글자 미만도 가입 못하게 :)
	$(".isUserNameCheck").keyup(function(){
		if($(".isUserNameCheck").val().length < 4){
			$(".isChkName").html("<i class='fas fa-times text-danger' style='padding-right: 10px;'></i>이름은 4글자 이상 입력해주세요!");
			$(".createNameOk").val("NO");
		//2020.04.03 이름 10글자 이상 가입 못하게 막기.
		} else if ($(".isUserNameCheck").val().length < 11) {
			$.ajax({
	            type: "POST",
	            url: "/chococo/member/nameChk",
	            dataType: "json",
	            data: {"userName" : $(".isUserNameCheck").val()},
	            success: function(result) {
	               if(result == 1) {
	            	   $(".isChkName").html("<i class='fas fa-times text-danger' style='padding-right: 10px;'></i>중복된 이름입니다."); 
	            	   $(".createNameOk").val("NO");
	               } else {
	            	   $(".isChkName").html("<i class='fas fa-check text-success' style='padding-right: 10px;'></i>사용 가능한 이름입니다.");
	            	   $(".createNameOk").val("OK");
	               }               
	            },
	        });
		} else {
			$(".isChkName").html("<i class='fas fa-times text-danger' style='padding-right: 10px;'></i>10글자 이하로 입력해주세요!");
			$(".createNameOk").val("NO");
		};
	});
	
	//2020.03.27 이메일 중복 체크 관련 세팅
	//2020.04.21 이메일 정규식에 맞게.
	$(".isUserEmailCheck").keyup(function(){
		if($(".isUserEmailCheck").val().length == 0 || isEmail($(".isUserEmailCheck").val()) == false){
			$(".isChkEmail").html("<i class='fas fa-times text-danger' style='padding-right: 10px;'></i>정상적인 이메일이 아닙니다!");
			$(".createEmailOk").val("NO");
		} else {
			$.ajax({
	            type: "POST",
	            url: "/chococo/member/emailChk",
	            dataType: "json",
	            data: {"userEmail" : $(".isUserEmailCheck").val()},
	            success: function(result) {
	               if(result == 1) {
	            	   $(".isChkEmail").html("<i class='fas fa-times text-danger' style='padding-right: 10px;'></i>중복된 이메일 입니다.");
	            	   $(".createEmailOk").val("NO");
	               } else {
	            	   $(".isChkEmail").html("<i class='fas fa-check text-success' style='padding-right: 10px;'></i>사용 가능한 이메일 입니다.");
	            	   $(".createEmailOk").val("OK");
	               }               
	            },
	        });
		}
	});
	
	//2020.04.21 비밀번호 8-15글자 사이로 숫자&영문조합
	$(".isUserPasswordCheck").keyup(function(){
		var userPass = $(".isUserPasswordCheck").val();
		if(isPassword(userPass) == false){
			$(".isChkPassword").html("<i class='fas fa-times text-danger' style='padding-right: 10px;'></i>영문, 숫자 조합으로 8-15글자여야 합니다!");
			$(".createPasswordOk").val("NO");
		} else {
			$(".isChkPassword").html("<i class='fas fa-check text-success' style='padding-right: 10px;'></i>입력하신 비밀번호는 사용할 수 있습니다.");
			$(".createPasswordOk").val("OK");
		}
	});
	
	//2020.04.03 mypage - coupon Add
	$(".mypageCouponAddCtr").click(function(){
		var formObj = $("form[name='mypageCouponAddForm']");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/mypage/couponAdd");
		formObj.submit();
	});
	
})


//2020.04.21 이메일 양식 정규식
//형식에 맞으면 true 반환
function isEmail(userEmail) {
		var emailTest = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		return emailTest.test(userEmail);
}

//2020.04.21 비밀번호 8-10글자 영문&숫자 조합
function isPassword(userPass) {
	var passwordTest = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,15}$/;
	return passwordTest.test(userPass);
}
