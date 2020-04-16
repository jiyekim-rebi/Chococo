<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="container">
	<div align="center" style="margin-top: 50px; margin-bottom: 100px;">
		<h1 class="text-primary">Community Notice List</h1>
	</div>
	<div class="row" style="margin-bottom: 30px;">
		<div class="col-md-6" align="left">
			<button type="button" class="btn btn-warning" onClick="noticeInsertView()">Notice Insert</button>
		</div>
		<div class="col-md-6" align="right">
			<form class="form-inline" name="adminCommunityForm">
				<input style="margin-left: 20px;" type="text" name="keyword" class="form-control" placeholder="title or content Search for...">
				<button class="btn btn-primary ml-2 adminCommunityListSearch" type="button">Search</button>
			</form>
		</div>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th scope="col" style="width: 15%">Writer</th>
				<th scope="col" style="width: 15%">regDate</th>
				<th scope="col">title</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${noticeList}" var="notice">
			<tr class="table">
				<td>${notice.writer}</td>
				<td><fmt:formatDate value="${notice.regDate}" pattern="yyyy-MM-dd"/></td>
				<td><a href="/chococo/admin/communityView?boardNo=${notice.boardNo}">${notice.title}</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- pageMaker -->
	<div class="row">
		<div class="center-block" style="margin: 0 auto;">
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li class="page-item"><a class="page-link" href="communityList${pageMaker.makeMakerAdminCommunity(pageMaker.startPage - 1)}">&laquo;</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
					<li class="page-item" <c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
							<a class="page-link" href="communtyList${pageMaker.makeMakerAdminCommunity(idx)}">${idx}</a></li>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li class="page-item"><a class="page-link" href="communityList${pageMaker.makeMakerAdminCommunity(pageMaker.endPage + 1)}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</div>


</div>