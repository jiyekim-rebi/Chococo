<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 카테고리 변경 만들어야함 1, 2, 3, 4 -->
<div class="container">
	<div align="center" style="margin-top: 50px; margin-bottom: 100px;">
		<h1 class="text-primary">Market Product List</h1>
	</div>
	<div class="row" style="margin-bottom: 30px;">
		<div class="col-md-6"></div>
		<div class="col-md-6">
			<form class="form-inline" name="adminProductSearchForm">
				<label>MainCategory : </label>
					<select style="margin-left: 20px;" class="form-control productMainCategory">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select>
				<input style="margin-left: 20px;" type="text" name="keyword" class="form-control" placeholder="ProductName Search for...">
				<button class="btn btn-primary ml-2 adminProductSearch" type="button">Search</button>
			</form>
		</div>		
	</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">productNo</th>
					<th scope="col">productName</th>
					<th scope="col">price</th>
					<th scope="col">hit</th>
					<th scope="col">subCategory</th>
					<th scope="col">selectOption</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${productList}" var="product">
				<tr class="table">
					<td>${product.productNo}</td>
					<td><a href="/chococo/admin/productView?mainCategory=${cri.mainCategory}&productNo=${product.productNo}&keyword=${cri.keyword}">${product.productName}</a></td>
					<td>${product.price}</td>
					<td>${product.hit}</td>
					<td>${product.subCategory}</td>
					<td>${product.selectOption}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- pageMaker -->
		<div class="row">
			<div class="center-block" style="margin: 0 auto;">
				<ul class="pagination">
					<c:if test="${pageMaker.prev}">
						<li class="page-item"><a class="page-link" href="productList${pageMaker.makeMakerMarketProduct(pageMaker.startPage - 1)}">&laquo;</a></li>
					</c:if>
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
						<li class="page-item" <c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
								<a class="page-link" href="productList${pageMaker.makeMakerMarketProduct(idx)}">${idx}</a></li>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<li class="page-item"><a class="page-link" href="productList${pageMaker.makeMakerMarketProduct(pageMaker.endPage + 1)}">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>

