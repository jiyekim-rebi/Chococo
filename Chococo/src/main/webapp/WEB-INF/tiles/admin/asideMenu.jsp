<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>


<div style="margin-left: 20px; margin-bottom: 80px;">
	<div class="center-block" style="margin: 0 auto;">
		<div align="center" style="margin-top: 70px; margin-bottom: 70px;">
			<h3 class="text-primary">Chococo Admin Page</h3>
			<h5 class="text-muted">Menu Navigation</h5>
		</div>
	
		<div style="margin-top: 30px;">
			<h4 style="margin-left: 30px;" class="text-info">Member</h4>
		</div>
		<div class="list-group">
			<a href="/chococo/admin/memberList" class="list-group-item list-group-item-action">Member Management</a>
			<a href="/chococo/admin/coupon" class="list-group-item list-group-item-action">Coupon</a>
		</div>
		
		<div style="margin-top: 30px;">
			<h4 style="margin-left: 30px;" class="text-info">Community</h4>
		</div>
		<div class="list-group">
			<a href="/chococo/admin/communityList" class="list-group-item list-group-item-action">Community Notice</a>
		</div>
		
		<div style="margin-top: 30px;">
			<h4 style="margin-left: 30px;" class="text-info">Market</h4>
		</div>
		<div class="list-group">
			<a href="/chococo/admin/productList" class="list-group-item list-group-item-action">Product List</a>
			<a href="/chococo/admin/productInsert" class="list-group-item list-group-item-action">Product Add</a>
			<a href="/chococo/admin/shipping" class="list-group-item list-group-item-action">Order / Shipping</a>
		</div>
		
		<div style="margin-top: 30px;">
			<h4 style="margin-left: 30px;" class="text-info">Service</h4>
		</div>
		<div class="list-group">
			<a href="#" class="list-group-item list-group-item-action">Q n A</a>
			<a href="#" class="list-group-item list-group-item-action">1:1</a>
		</div>
	</div>
</div>
