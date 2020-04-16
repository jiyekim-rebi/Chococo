<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

	<div class="col-md-9" style="padding-top: 50px; padding-bottom: 50px; padding-right: 100px;">
		<div class="container">
		<h3>New Article</h3>
		<form name="ArticleInsertForm">
			<div class="form-group">
				<!-- writer는 변경할 수 없는 값이라서 hidden으로 넘겨줄 것. -->
				<input type="hidden" name="writer" value="${isLogin.userName}">
				<input type="hidden" name="parentBoardNo" value="${insertForm.parentBoardNo}">

				<c:if test="${insertForm.parentBoardNo == 0 }">
				<label>Category Select</label>
				<select class="form-control boardInsertSelect">
					<option value="2"<c:if test="${insertForm.categoryNo == '2'}">selected</c:if>>Lecture</option>
					<option value="3"<c:if test="${insertForm.categoryNo == '3'}">selected</c:if>>DIY MarketPlace</option>
					<option value="4"<c:if test="${insertForm.categoryNo == '4'}">selected</c:if>>FreeTalk</option>
				</select>
				</c:if>
				<c:if test="${insertForm.parentBoardNo != 0}">
					<input type="hidden" name="categoryNo" value="${insertForm.categoryNo}">
				</c:if>

				<label class="control-label" for="disabledInput">Board title</label>
				<input class="form-control" name="title" type="text" required data-validation-required-message="Please write your Title!">
				<hr>
				<label class="control-label" for="disabledInput">Board Content</label>
				<textarea class="form-control" name="content" id="boardInsertTextarea"></textarea>
				<script>
					$('#boardInsertTextarea').summernote({
						placeholder: '내용을 입력해주세요!',
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
			<button type="button" class="btn btn-info" onClick="backList('${insertForm.categoryNo}')">Back list</button>
			<button type="button" class="btn btn-success articleInsert">Insert Success</button>
		</form>
		<br>
		</div>
	</div>

