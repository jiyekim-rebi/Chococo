<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

		<!-- 게시글 수정 form -->
		<div class="col-md-9" style="padding-top: 50px; padding-bottom: 50px; padding-right: 100px;">
			<div class="container">
			<h3>Article Modify</h3>
			<form name="ArticlemodifyForm">
				<div class="form-group">
					<!-- writer, boardNo는 변경할 수 없는 값이라서 hidden으로 넘겨줄 것. -->
					<input type="hidden" name="writer" value="${modifyForm.writer}">
					<input type="hidden" name="boardNo" value="${modifyForm.boardNo}">
					
					<c:if test="${modifyForm.parentBoardNo == 0 }">
					<label>Category Select</label>
					<select class="form-control boardModifySelect">
						<option value="2"<c:if test="${modifyForm.categoryNo == '2'}">selected</c:if>>Lecture</option>
						<option value="3"<c:if test="${modifyForm.categoryNo == '3'}">selected</c:if>>DIY MarketPlace</option>
						<option value="4"<c:if test="${modifyForm.categoryNo == '4'}">selected</c:if>>FreeTalk</option>
					</select>
					</c:if>
					<c:if test="${modifyForm.parentBoardNo != 0}">
						<input type="hidden" name="categoryNo" value="${modifyForm.categoryNo}">
					</c:if>
					
					<label class="control-label" for="disabledInput">Board title</label>
					<input class="form-control" name="title" type="text" value="${modifyForm.title}" required data-validation-required-message="Please write your Title!">
					<hr>
					<label class="control-label" for="disabledInput">Board Content</label>
					<textarea class="form-control" name="content" id="boardModifyTextarea">${modifyForm.content}</textarea>
					<script>
					$('#boardModifyTextarea').summernote({
						lang: 'ko-KR',
						tabsize: 2,
						height: 420,
						toolbar: [
						['style', ['style']],
						['font', ['bold', 'underline', 'clear']],
						['color', ['color']],
						['para', ['ul', 'ol', 'paragraph']],
						['table', ['table']],
						['insert', ['link', 'picture', 'video']],
						['view', ['fullscreen', 'codeview', 'help']]
						]
					});
					</script>
				</div>
				<hr>
			</form>
			<!-- searchType, keyword 값 있으면 search로, 없으면 그냥 board로. -->
			<c:if test = "${cri.searchType != '' && cri.keyword != ''}">
				<button type="button" class="btn btn-info" onClick="backSearchList('${cri.page}','${cri.searchType}', '${cri.keyword}')">List</button>
			</c:if>
			<c:if test = "${cri.searchType == '' && cri.keyword == ''}">
				<button type="button" class="btn btn-info" onClick="backList('${cri.page}','${modifyForm.categoryNo}')">List</button>
			</c:if>
			<button type="button" class="btn btn-warning articleModify">Modify Success</button>
			<br>
			</div>
		</div>
