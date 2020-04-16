<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="container">
	<div align="center" style="margin-top: 50px; margin-bottom: 100px;">
		<h1 class="text-primary">Chococo Member List</h1>
	</div>
	
	<div class="row" style="margin-bottom: 30px;">
		<div class="col-md-6" align="center">
			<h4 class="text-primary">찾고자 하는 멤버를 검색해주세요.</h4>
		</div>
		<div class="col-md-6" align="center">
			<form class="form-inline" name="adminCommunityForm">
				<input style="margin-left: 20px;" type="text" name="userName" class="form-control" placeholder="userName search for...">
				<button class="btn btn-primary ml-2 adminSearchMember" type="button">Search</button>
			</form>
		</div>
	</div>
	
	<hr>
	
	<c:if test="${member != null }">
	<div align="center" style="margin-top: 50px; margin-bottom: 50px;">
		<h5 class="text-success">${member.userName}님의 회원정보</h5>
	</div>
	
	
	<div class="row">
		<div class="col-md-6">
		<form name="adminMemberForm">
			<div class="row">
				<div class="col-md-3">
					<h6>이메일</h6>
				</div>
				<div class="col-md-9">
					<label>${member.userEmail}</label>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-3">
					<h6>생년월일</h6>
				</div>
				<div class="col-md-9 form-inline">
					<input type="text" style="width: 20%; margin: 0 5px;" class="form-control adminMemberListInputRead" value="${member.birth_year}" name="birth_year" readOnly>
					 - 
					<input type="text" style="width: 20%; margin: 0 5px;" class="form-control adminMemberListInputRead" value="${member.birth_month}" name="birth_month" readOnly>
					 - 
					<input type="text" style="width: 20%; margin: 0 5px;" class="form-control adminMemberListInputRead" value="${member.birth_day}" name="birth_day" readOnly>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-3">
					<h6>가입일자</h6>
				</div>
				<div class="col-md-9">
					<label><fmt:formatDate value="${member.joinDate}" pattern="yyyy-MM-dd"/></label>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-3">
					<h6>주소</h6>
				</div>
				<div class="col-md-9">
					<input type="text" id="addressData" class="form-control adminMemberListInputRead" name="address" 
					value="${member.address}" readOnly>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-3">
					<h6>회원등급</h6>
				</div>
				<div class="col-md-9">
					<c:choose>
						<c:when test="${member.isAdmin == 1}">
							<label class="text-primary"><strong>Admin</strong></label>
						</c:when>
						<c:when test="${member.isAdmin == 0 && member.isBan == 0}">
							<label class="text-success">Chococo Members</label>
						</c:when>
   						<c:otherwise>
							<label class="text-danger"><strong>정지된 회원</strong></label>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<input type="hidden" name="userName" value="${member.userName}">
			</form>
		</div>

		<div class="col-md-6 memberListBtn" align="center" style="margin-top: 40px;">
			<div>
				<input type="button" class="btn btn-info memberModifyOnOff" value="정보변경">
				<hr>
			</div>
			<div>
				<!-- 여기 이거, isBan 값에 따라서 버튼 다르게 나오게 할 것. -->
				<c:if test="${member.isBan == 1 }">
					<input type="button" class="btn btn-danger adminMemberBanCtr" value="밴 해제">
					<input type="hidden" name="isBan" value="0">
				</c:if>
				<c:if test="${member.isBan == 0 }">
					<input type="button" class="btn btn-warning adminMemberBanCtr" value="이용불가">
					<input type="hidden" name="isBan" value="1">
				</c:if>
				<hr>
			</div>
			<div>
				<input type="button" class="btn btn-danger adminMemberDeleteCtr" value="탈퇴처리">
			</div>
		</div>
		
		<div class="col-md-6 memberListModifyBtn" align="center" style="margin-top: 70px;">
			<div>
				<input type="button" class="btn btn-success adminMemberModifyCtr" value="변경완료">
				<hr>
			</div>
			<div>
				<input type="button" class="btn btn-secondary memberModifyOnOff" value="취소하기">
			</div>
		</div>
	</div>
	</c:if>
	
</div>