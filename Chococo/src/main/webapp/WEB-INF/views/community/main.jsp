<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


		<!-- 게시글 상세보기 -->
		<div class="col-md-9" style="padding-top: 50px; padding-bottom: 50px; padding-right: 100px;">

		<div class="container">
			<!-- 커뮤니티 메인 공지 -->
			<h1 class="mt-2 mb-4" style="padding:50px 0px; font-size: 60px;">Community Notice</h1>
			
			<div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col" style="width: 20%">WriteDate</th>
						<th scope="col" style="width: 60%">Title</th>
						<th scope="col">writer</th>
					</tr>
				</thead>
			<c:forEach items="${communityNotice}" var="notice">
				<tr class="table-light">
					<td><fmt:formatDate value="${notice.regDate}" pattern="yyyy-MM-dd"/></td>
					<td><a class="text-danger" href="/chococo/community/boardView?boardNo=${notice.boardNo}&searchType=${cri.searchType}&keyword=${cri.keyword}&page=${cri.page}">${notice.title}</a></td>
					<td>${notice.writer }</td>				
				</tr>
			</c:forEach>
			</table>
			</div>	
			
			<!-- 메인 화면에 공지 관련된 pageMaker -->
			<div class="row">
				<div class="center-block" style="margin: 0 auto;">
					<ul class="pagination">
					<c:if test="${pageMaker.prev}">
						<li class="page-item"><a class="page-link" href="main${pageMaker.makeMaker(pageMaker.startPage - 1)}">&laquo;</a></li>
					</c:if>
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
						<li class="page-item" <c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
								<a class="page-link" href="main${pageMaker.makeMaker(idx)}">${idx}</a></li>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<li class="page-item"><a class="page-link" href="main${pageMaker.makeMaker(pageMaker.endPage + 1)}">&raquo;</a></li>
					</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>