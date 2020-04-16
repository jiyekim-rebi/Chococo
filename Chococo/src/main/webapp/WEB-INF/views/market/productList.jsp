<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="row" style="margin-top: 50px; margin-bottom: 300px;">

	<%@ include file="include/asideMenu.jsp" %>
	
	
	
	<!-- productList -->
	<div class="col-md-10">
		<div class="container">
			<div align="center" style="padding-top: 50px;">
				<hr>
				<h3 style="padding-top: 20px;">${mainCategoryName }</h3>
				<h4 style="padding-top: 5px; padding-bottom: 30px;">${subCategoryName }</h4>
				<hr>
			</div>
			<div class="row" style="margin-top: 100px;">
				<div align="left" class="col-md-6">
					<input type="button" class="btn btn-danger btn-sm" onClick="productListOrder(${mpc.mainCategory},${mpc.subCategory},1)" value="신상품">
					<input type="button" class="btn btn-secondary btn-sm" onClick="productListOrder(${mpc.mainCategory},${mpc.subCategory},2)" value="상품명">
					<input type="button" class="btn btn-info btn-sm" onClick="productListOrder(${mpc.mainCategory},${mpc.subCategory},3)" value="낮은가격">
					<input type="button" class="btn btn-success btn-sm" onClick="productListOrder(${mpc.mainCategory},${mpc.subCategory},4)" value="높은가격">
					<input type="button" class="btn btn-warning btn-sm" onClick="productListOrder(${mpc.mainCategory},${mpc.subCategory},5)" value="인기순">
				</div>
				<div align="right" class="col-md-6">
					<h6>total of <strong>[ ${totalOfitem} ]</strong> items</h6>
				</div>
			</div>
			<hr>
			<div class="row" style="margin-top: 50px;">
				<c:forEach items="${AllProducts}" var="products">
					<div class="col-md-3" style="padding: 5px;" align="center">
						<a href="/chococo/market/productDetail?mainCategory=${mpc.mainCategory}&productNo=${products.productNo}">
						<img src="/chococo/img/loading?imageFileName=${products.imageFileName}&mainCategory=${mpc.mainCategory}" alt="Image" style="width: 200px; height: 200px;">
						</a>
						<h5 class="text-primary" style="margin-top: 30px;">${products.productName}</h5>
						<h6 class="text-muted"><fmt:formatNumber value="${products.price}" type="currency" currencySymbol="￦"/></h6>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>