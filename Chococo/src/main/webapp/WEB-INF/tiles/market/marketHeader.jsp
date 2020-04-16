<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<!-- 2020.03.19 marketHeader tiles 설정 할 것. -->
<div class="MarketHeader">
	<div class="container">
		<div class="input-group" style="padding-top: 20px;">
			<span style="font-size: 40px; padding-right: 40px;" class="text-primary"><a href="/chococo/market/main">Market</a></span>
			<div class="input-group-append" style="padding-right: 30px; padding-top: 10px;">
				<input type="text" style="width: 600px;" class="form-control" placeholder="Search for...">
				<span class="input-group-btn"><button class="btn btn-primary ml-2" type="button">Search</button></span>
			</div>
			<!-- dropMenu -->
			<div class="MarketMenu">
				<span><a href="#"><i class="fas fa fa-list-ul fa-2x Rebi_dropdown" aria-label="info"></i></a></span>
				<div style="display: inline-block">
					<div class="rebi_dropdown_content">
						<a href="/chococo/market/productList?mainCategory=1">SET상품</a>
    					<a href="/chococo/market/productList?mainCategory=2">소품모음</a>
    					<a href="/chococo/market/productList?mainCategory=3">몰드</a>
    					<a href="/chococo/market/productList?mainCategory=4">작업도구</a>
					</div>
				</div>
				<span><a style="padding-left: 5px;" href="/chococo/market/cart"><i class="fas fa-shopping-cart fa-2x" aria-label="cart"></i></a></span>
				<span><a style="padding-left: 10px;" href="#"><i class="far fa-bell fa-2x" aria-label="bell"></i></a></span>
				<span><a style="padding-left: 10px;" href="#"><i class="fas fa-headset fa-2x" aria-label="center"></i></a></span>
			</div>
		</div>
	</div>
</div>