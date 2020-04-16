<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<div class="MarketBanner">
	<ul style="padding-left: 0px; margin-bottom: 0px;">
		<li><img src="../resources/image/market1.jpg"></li>
		<li><img src="../resources/image/market2.jpg"></li>
		<li><img src="../resources/image/market3.jpg"></li>
		<li><img src="../resources/image/market4.jpg"></li>
	</ul>
</div>

<div class="container" style="margin-bottom: 300px;">
	
	<!-- Trending Products  -->
	<div align="center" style="padding-top: 100px; padding-bottom: 50px;">
		<hr>
			<h2>Trending Products</h2>
		<hr>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div id="TrendingCarousel" class="carousel slide" data-ride="carousel" style="padding-bottom: 10px;">
				<ul class="carousel-indicators">
					<li data-target="#TrendingCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#TrendingCarousel" data-slide-to="1"><label class="slideLabel"></label></li>
				</ul>
				<div class="carousel-inner">
					<div class="carousel-item active">
 						<div class="row">
 							<c:forEach items="${trandingProducts }" var="tranding" begin="0" end="3">
							<div class="col-md-3">
								<a href="/chococo/market/productDetail?mainCategory=${tranding.mainCategory}&productNo=${tranding.productNo}">
									<img src="/chococo/img/loading?imageFileName=${tranding.imageFileName}&mainCategory=${tranding.mainCategory}" alt="Image" 
									style="width: 280px; height: 280px;">
								</a>
							</div>
							</c:forEach>
						</div>
					</div>
					<div class="carousel-item">
						<div class="row">
							<c:forEach items="${trandingProducts }" var="tranding" begin="4" end="7">
							<div class="col-md-3">
								<a href="/chococo/market/productDetail?mainCategory=${tranding.mainCategory}&productNo=${tranding.productNo}">
									<img src="/chococo/img/loading?imageFileName=${tranding.imageFileName}&mainCategory=${tranding.mainCategory}" alt="Image" 
									style="width: 280px; height: 280px;">
								</a>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Favorite Products -->
	<div align="center" style="padding-top: 100px; padding-bottom: 50px;">
		<hr>
			<h2>Recommend Products</h2>
		<hr>
	</div>
	

	<div class="row">
		<div class="col-md-12">
			<div id="FavoriteCarousel" class="carousel slide" data-ride="carousel" style="padding-bottom: 10px;">
				<ul class="carousel-indicators">
					<li data-target="#FavoriteCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#FavoriteCarousel" data-slide-to="1"></li>
				</ul>
				<div class="carousel-inner">
					<div class="carousel-item active">
 						<div class="row">
							<c:forEach items="${recommendProducts }" var="recommend" begin="0" end="3">
							<div class="col-md-3">
								<a href="/chococo/market/productDetail?mainCategory=${recommend.mainCategory}&productNo=${recommend.productNo}">
									<img src="/chococo/img/loading?imageFileName=${recommend.imageFileName}&mainCategory=${recommend.mainCategory}" alt="Image" 
									style="width: 280px; height: 280px;">
								</a>
							</div>
							</c:forEach>
						</div>
					</div>
					<div class="carousel-item">
					 	<div class="row">
							<c:forEach items="${recommendProducts }" var="recommend" begin="4" end="7">
							<div class="col-md-3">
								<a href="/chococo/market/productDetail?mainCategory=${recommend.mainCategory}&productNo=${recommend.productNo}">
									<img src="/chococo/img/loading?imageFileName=${recommend.imageFileName}&mainCategory=${recommend.mainCategory}" alt="Image" 
									style="width: 280px; height: 280px;">
								</a>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- New Products -->
	<div align="center" style="padding-top: 100px; padding-bottom: 50px;">
		<hr>
			<h2>New Products</h2>
		<hr>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="NewCarousel" class="carousel slide" data-ride="carousel" style="padding-bottom: 10px;">
				<ul class="carousel-indicators">
					<li data-target="#NewCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#NewCarousel" data-slide-to="1"></li>
				</ul>
				<div class="carousel-inner">
					<div class="carousel-item active">
 						<div class="row">
							<c:forEach items="${newProducts }" var="newProducts" begin="0" end="3">
							<div class="col-md-3">
								<a href="/chococo/market/productDetail?mainCategory=${newProducts.mainCategory}&productNo=${newProducts.productNo}">
									<img src="/chococo/img/loading?imageFileName=${newProducts.imageFileName}&mainCategory=${newProducts.mainCategory}" alt="Image" 
									style="width: 280px; height: 280px;">
								</a>
							</div>
							</c:forEach>
						</div>
					</div>
					<div class="carousel-item">
					 	<div class="row">
							<c:forEach items="${newProducts }" var="newProducts" begin="4" end="7">
							<div class="col-md-3">
								<a href="/chococo/market/productDetail?mainCategory=${newProducts.mainCategory}&productNo=${newProducts.productNo}">
									<img src="/chococo/img/loading?imageFileName=${newProducts.imageFileName}&mainCategory=${newProducts.mainCategory}" alt="Image" 
									style="width: 280px; height: 280px;">
								</a>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>