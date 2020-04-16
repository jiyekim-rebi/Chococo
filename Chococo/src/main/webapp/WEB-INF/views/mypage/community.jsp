<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


	<div class="col-md-9 container" style="margin-top: 50px;">
		<h1 class="text-danger">Chococo Mypage</h1>

		<div style="margin-top: 50px;">
			<ul class="nav nav-tabs">
				<li class="nav-item"><a class="nav-link active"
					href="/chococo/mypage/community">내가 쓴 게시글</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/chococo/mypage/communityReply">내가 쓴 댓글</a></li>
			</ul>

			<!-- 내가 쓴 게시글 -->
			<div class="tab-content " id="myTabContent"
				style="padding-bottom: 30px;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th style="width: 10%;" scope="col">No</th>
							<th style="width: 50%;" scope="col">Title</th>
							<th style="width: 20%;" scope="col">WriteDate</th>
							<th style="width: 20%;" scope="col">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${boardList}" var="board">
							<tr class="table">
								<td>${board.boardNo}</td>
								<td><a
									href="/chococo/community/boardView?boardNo=${board.boardNo}">${board.title}</a></td>
								<td><fmt:formatDate value="${board.regDate}"
										pattern="yyyy-MM-dd" /></td>
								<td>
									<button type="button" class="btn btn-warning"
										onClick="mypageArticleDelete('${board.boardNo}')">삭제하기</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!-- pageMaker -->
				<div class="row">
					<div class="center-block" style="margin: 0 auto;">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li class="page-item"><a class="page-link"
									href="community${pageMaker.makeMaker(pageMaker.startPage - 1)}">&laquo;</a></li>
							</c:if>
							<c:forEach begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}" var="idx">
								<li class="page-item"
									<c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
									<a class="page-link"
									href="community${pageMaker.makeMaker(idx)}">${idx}</a>
								</li>
							</c:forEach>
							<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
								<li class="page-item"><a class="page-link"
									href="community${pageMaker.makeMaker(pageMaker.endPage + 1)}">&raquo;</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
