<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

	<div class="col-md-9 container" style="margin-top: 50px;">
		<h1 class="text-danger">Chococo Mypage</h1>

		<div style="margin-top: 50px;">
			<ul class="nav nav-tabs">
				<li class="nav-item"><a class="nav-link"
					href="/chococo/mypage/community">내가 쓴 게시글</a></li>
				<li class="nav-item"><a class="nav-link active"
					href="/chococo/mypage/communityReply">내가 쓴 댓글</a></li>
			</ul>

			<!-- 내가 쓴 댓글 -->
			<div class="tab-content " id="myTabContent"
				style="padding-bottom: 30px;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th style="width: 40%;" scope="col">Content</th>
							<th style="width: 20%;" scope="col">작성일</th>
							<th style="width: 20%;" scope="col">원글보기</th>
							<th style="width: 20%;" scope="col">삭제하기</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${replyList}" var="reply">
							<tr class="table">
								<td>${reply.content}</td>
								<td><fmt:formatDate value="${reply.regDate}" pattern="yyyy-MM-dd" /></td>
								<!-- 원글이 삭제된 게시글일 경우? 처리할 것. 2020.03.19 -->
								<td><a href="/chococo/community/boardView?boardNo=${reply.boardNo}">원글보기</a></td>
								<td>
									<%-- 
									이곳에 form을 동적으로 생성해서 보내버릴 것.
									
									댓글 삭제는 form으로, post타입으로만 진행함(댓글번호 따로 view로 뿌릴거 아니라서 절대 주소창에 보여지면 안됨!
									이때 form은 jquery로 절대 동적으로 생성시킬 것.
									 --%>
									<form>
										<input type="hidden" name="replyNo" value="${reply.replyNo}">
										<button type="button"
											class="btn btn-warning mypageReplyDelete">댓글삭제</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!-- ReplyPageMaker -->
				<div class="row">
					<div class="center-block" style="margin: 0 auto;">
						<ul class="pagination">
							<c:if test="${replyPageMaker.prev}">
								<li class="page-item"><a class="page-link"
									href="communityReply${replyPageMaker.makeMaker(pageMaker.startPage - 1)}">&laquo;</a></li>
							</c:if>
							<c:forEach begin="${replyPageMaker.startPage}"
								end="${replyPageMaker.endPage}" var="idx">
								<li class="page-item"
									<c:out value="${replyPageMaker.cri.page == idx ? 'class=info' : ''}" />>
									<a class="page-link"
									href="communityReply${replyPageMaker.makeMaker(idx)}">${idx}</a>
								</li>
							</c:forEach>
							<c:if test="${replyPageMaker.next && replyPageMaker.endPage > 0}">
								<li class="page-item"><a class="page-link"
									href="communityReply${replyPageMaker.makeMaker(pageMaker.endPage + 1)}">&raquo;</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
