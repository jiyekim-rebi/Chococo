<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

		<!-- 게시글 상세보기 articleView -->
		<div class="col-md-9" style="padding-top: 50px; padding-bottom: 50px; padding-right: 100px;">
			<div class="container">
			<h2>${articleView.title}</h2>
			<div class="row">
				<div class="col-md-6">
					<h6>작성자 : ${articleView.writer}</h6>
				</div>
				<div class="col-md-6">
					<h6 class="text-right"><fmt:formatDate value="${articleView.regDate}" pattern="yyyy-MM-dd"/></h6>
				</div>
			</div>
			<hr>
			<div>
				${articleView.content}
			</div>
			<hr>
			<div class="row">
				<div class="col-md-6">
				<c:if test = "${cri.searchType != '' && cri.keyword != ''}">
					<button type="button" class="btn btn-info" onClick="backSearchList('${cri.page}', '${cri.searchType}', '${cri.keyword}')">List</button>
				</c:if>
				<c:if test = "${cri.searchType == '' && cri.keyword == ''}">
					<button type="button" class="btn btn-info" onClick="backList('${cri.page}','${articleView.categoryNo}')">List</button>
				</c:if>
				</div>
				<div class="col-md-6" align="right">
				<!-- 공지사항 관련된 수정은 admin page에서 처리 -->
				<c:if test="${articleView.categoryNo != 1 }">
					<!-- 사용자가 로그인 한 상태라면 답글달기 활성화 -->
					<c:if test="${isLogin != null }">
						<button type="button" class="btn btn-success articleParentInsert">답글달기</button>
					</c:if>
					<!-- 로그인된 계정의 userName과 글쓴 작성자가 동일할 경우 게시글 modify, delete 활성화. -->
					<c:if test="${isLogin.userName == articleView.writer}">
						<button type="button" class="btn btn-warning articleModifyCtr">Modify</button>
					</c:if>
					<c:if test="${isLogin.userName == articleView.writer || adminCheck != null}">
						<button type="button" class="btn btn-danger articleDelete">Delete</button>
					</c:if>
				</c:if>
				</div>

				<form name="deleteArticleForm">
					<input type="hidden" name="boardNo" value="${articleView.boardNo }">
					<input type="hidden" name="categoryNo" value="${articleView.categoryNo}">
				</form>
				<form name="modifyArticleFormCtr">
					<input type="hidden" name="writer" value="${articleView.writer}">
					<input type="hidden" name="boardNo" value="${articleView.boardNo }">
					<input type="hidden" name="content" value='${articleView.content }'>
					<input type="hidden" name="categoryNo" value="${articleView.categoryNo}">
				</form>
				<form name="parentArticleForm">
					<input type="hidden" name="writer" value="${articleView.writer}">
					<input type="hidden" name="parentBoardNo" value="${articleView.boardNo}">
					<input type="hidden" name="categoryNo" value="${articleView.categoryNo}">
				</form>
			</div>
			<hr>
			
			<!-- 게시글의 답글이 있을 경우 출력함 -->
			<c:if test="${articleChild != null}">
			<h5 style="padding-top: 80px;">이 게시글에 등록된 답글</h5>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">No</th>
						<th scope="col">Title</th>
						<th scope="col">Writer</th>
						<th scope="col">WriteDate</th>
					</tr>
				</thead>
				<tbody>
					<tr class="table">
						<td>${articleView.boardNo}</td>
						<td><a href="/chococo/community/boardView?boardNo=${articleView.boardNo}&searchType=${cri.searchType}&keyword=${cri.keyword}&page=${cri.page}">${articleView.title}</a></td>
						<td>${articleView.writer}</td>
						<td><fmt:formatDate value="${articleView.regDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
				</tbody>
				<tbody>
					<c:forEach items="${articleChild}" var="child">
					<tr class="table">
						<td>${child.boardNo}</td>
						<td><a href="/chococo/community/boardView?boardNo=${child.boardNo}&searchType=${cri.searchType}&keyword=${cri.keyword}&page=${cri.page}">${child.title}</a></td>
						<td>${child.writer}</td>
						<td><fmt:formatDate value="${child.regDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:if>
			<h4 style="padding-top: 80px;">Reply List</h4>
			<hr>
			<div>
			<!-- 게시글의 리플이 있는 경우 출력함. -->
			<c:if test="${articleReplys != null}">
			<c:forEach items="${articleReplys}" var="reply">
				<div class="row">
					<div class="col-md-6">
						<c:if test="${reply.step == 2}">
							<span><i class="fas fa-angle-double-right" style="color: #e95420; padding-left: 10px;"></i></span>
						</c:if>
						<span>댓글 작성자 : ${reply.writer}</span>
					</div>
					<div class="col-md-6">
						<h6 class="text-right">작성일자 : <fmt:formatDate value="${reply.regDate}" pattern="yyyy-MM-dd"/></h6>
					</div>
				</div>
				<div class="row" style="padding-bottom: 10px;">
					<div class="col-md-8">
						<c:if test="${reply.step == 2}">
							<i class="fas fa-angle-double-right" style="color: #e95420; padding-left: 20px; padding-right: 10px;"></i><strong>[${reply.parentReplyNoWriter}]</strong>
						</c:if>
						<pre>${reply.content}</pre>
					</div>
					<div class="col-md-4" align="right">
						<!-- 로그인 한 사람이 쓴 리플일 경우 리플 삭제, 리플 수정 버튼 활성화 시킬 것. -->
						<div style="padding-top: 5px;">
							<!-- 로그인된 계정의 userName과 댓글 작성자가 동일할 경우 게시글 modify, delete 활성화. -->					
							<c:if test="${isLogin.userName == reply.writer}">
								<button type="button" class="btn btn-warning replyModifyCtr">댓글수정</button>
							</c:if>	
							<c:if test="${isLogin.userName == reply.writer || adminCheck != null}">
								<button type="button" class="btn btn-danger" onClick="replyDelete('${reply.replyNo}', '${articleView.boardNo }')">댓글삭제</button>
							</c:if>
						</div>
					</div>
				</div>
				<!-- 사용자가 로그인 한 상태라면 댓글 답글달기 활성화 -->
				<form>
					<c:if test="${isLogin != null}">
						<button type="button" class="btn btn-info parentReplyFormOpen">답글달기</button>
					</c:if>
					<input type="hidden" name="writer" value="${isLogin.userName}">
					<input type="hidden" name="boardNo" value="${articleView.boardNo}">
					<input type="hidden" name="parentReplyNo" value="${reply.replyNo}">
					<input type="hidden" name="parentReplyNoWriter" value="${reply.writer}">
				</form>
				<hr>
			</c:forEach>
			
			<!-- pageMaker by reply -->
			<div class="row">
				<div class="center-block" style="margin: 0 auto;">
					<ul class="pagination">
					<c:if test="${pageMaker.prev}">
						<li class="page-item"><a class="page-link" href="boardView${pageMaker.makeMakerReply(pageMaker.startPage - 1, articleView.boardNo)}">&laquo;</a></li>
					</c:if>
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
							<li class="page-item" <c:out value="${pageMaker.cri.page == idx ? 'class=info' : ''}" />>
							<a class="page-link" href="boardView${pageMaker.makeMakerReply(idx, articleView.boardNo)}">${idx}</a></li>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<li class="page-item"><a class="page-link" href="boardView${pageMaker.makeMakerReply(pageMaker.endPage + 1, articleView.boardNo)}">&raquo;</a></li>
					</c:if>
					</ul>
				</div>
			</div>
			</c:if>
			<c:if test="${articleReplys == null }">
			<div align="center">
				<h5>등록된 댓글이 없습니다!</h5>
			</div>
			</c:if>
			<hr>
			</div>
			
			<!-- newReply : 로그인한 유저만 접근 가능 -->
			<c:if test="${isLogin != null }">
				<form name="newReplyForm">
					<div class="form-group">
						<textarea class="form-control" id="newReplyContent" name="content" rows="5" style="resize: none;" required></textarea>
						<button type="button" class="btn btn-success newReplyBtn" style="margin-top: 5px;">Reply Submit</button>
						<input type="hidden" name="writer" value="${isLogin.userName}">
						<input type="hidden" name="boardNo" value="${articleView.boardNo}">
					</div>
				</form>
			</c:if>
			
			</div>
		</div>
