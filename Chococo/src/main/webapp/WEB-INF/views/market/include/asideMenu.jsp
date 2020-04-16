<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- asideMenu -->
	<div class="col-md-2">
		<div class="marketAsideMenu">
			<h3 class="text-primary">Category Lists</h3>
			<hr>
			<ul style="list-style-type: none;">
			<!-- 여기 나중에 c:forEach로 각 list별로 다르게 불러올 필요가 있어요. -->
				<c:forEach items="${subCategoryList }" var="sub">
				<li>
					<a href="${sub.href }"><span class="fa fa-angle-double-right text-primary" style="padding: 0 15px 0 0;"></span>${sub.subCategoryName}</a>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>