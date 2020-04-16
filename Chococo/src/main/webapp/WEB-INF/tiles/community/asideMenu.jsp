<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- 왼쪽 메뉴들 -->
<div class="col-md-3 col-sm-12" style="padding-left: 50px;">
	<div class="blog-sidebar" style="margin-top: 80px;">
		<div class="input-group searchbar">
			<div class="col-xs-2" style="padding-right: 5px;">
					<select name="searchType" class="form-control CommunitysearchBar">
						<option value="n"
							<c:out value="${cri.searchType == null ? 'selected' : ''}"/>>-----</option>
						<option value="t"
							<c:out value="${cri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
						<option value="c"
							<c:out value="${cri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
						<option value="w"
							<c:out value="${cri.searchType eq 'w' ? 'selected' : ''}"/>>작성자</option>
						<option value="tc"
							<c:out value="${cri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>	
					</select>
			</div>
			<input type="text" name="keyword" class="form-control searchbar" placeholder="Search for...">
			<span class="input-group-btn"> <button class="btn btn-primary ml-2" type="button" id="searchBtn">Search</button></span>
		</div>
	</div>
	
	<div class="blog-sidebar" style="padding-top: 100px;">
		<h4 class="sidebar-title"><i class="fa fa-list-ul"></i> Categories</h4>
		<hr>
		<ul class="sidebar-list">
			<li><a href="/chococo/community/main">Community Notice</a></li>
			<li><a href="/chococo/community/board?categoryNo=2">Lecture</a></li>
			<li><a href="/chococo/community/board?categoryNo=3">DIY MarketPlace</a></li>
			<li><a href="/chococo/community/board?categoryNo=4">FreeTalk</a></li>
		</ul>
	</div>

	<!-- Recent Posts -->
	<div class="blog-sidebar" style="padding-top: 200px; padding-bottom: 150px;">
		<h4 class="sidebar-title"><i class="fa fa-align-left"></i> Recent Posts </h4>
		<hr style="margin-bottom: 5px;">
		<c:forEach items="${recentPost}" var="recent">
			<div class="container">
			<div class="media mt-3">
				<div class="media-body">
					<h4 class="media-heading"><a href="/chococo/community/boardView?boardNo=${recent.boardNo}">${recent.title}</a></h4>
				</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>