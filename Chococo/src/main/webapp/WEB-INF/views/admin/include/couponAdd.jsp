<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="Rebi_create Rebi_CouponCreate col-xs-12 col-sm-12 col-md-4 well well-sm">
	<div align="center" style="margin-top: 100px; margin-bottom: 50px;">
		<h1 class="text-primary">Chococo Coupon Create</h1>
		<h6 class="text-secondary">쿠폰은 발행일로부터 7일간 사용하실 수 있습니다.</h6>
	</div>
	<form name="adminCouponCreateForm">
	<div class="row" style="margin-bottom: 70px;">
		<div class="col-md-8">
			<h6 class="text-danger">쿠폰내용 입력</h6>
			<input type="text" name="content" class="form-control" placeholder="생성할 쿠폰 내용을 입력해주세요.">
		</div>
		<div class="col-md-4">
			<h6 class="text-danger">적용할 퍼센트</h6>
			<input type="text" name="ratio" class="form-control" placeholder="숫자로 입력해주세요.">
		</div>
	</div>
	
	<div class="row" style="margin-bottom: 50px;">
		<div class="center-block" style="margin: 0 auto;">
			<input type="button" class="btn btn-success adminCouponAddCtr" value="쿠폰 등록하기">
			<input type="button" class="btn btn-warning couponAddOnOff" value="입력취소">
		</div>
	</div>
	<input type="hidden" name="userName" value="${adminCheck.userName}">
	</form>
</div>