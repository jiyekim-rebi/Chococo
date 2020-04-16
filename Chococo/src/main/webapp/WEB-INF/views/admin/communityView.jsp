<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="container">
	<div align="center" style="margin-top: 50px; margin-bottom: 100px;">
			<h1 class="text-primary">Community Notice</h1>
		</div>
	<div class="adminNoticeView">
		<div class="row">
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">title</h5>
			</div>
			<div class="col-md-10">
				<input type="text" name="title" value="${noticeView.title}" class="form-control" readOnly>
			</div>
		</div>
		<hr>
		<h5>Contents</h5>
		<div>
			${noticeView.content}
		</div>
		<div class="row" style="margin-top: 50px; margin-bottom: 100px;">
			<div class="center-block" style="margin: 0 auto;">
				<input type="button" class="btn btn-success adminNoticeModifyOnOff" value="공지 수정하기">
				<input type="button" class="btn btn-danger adminNoticeDeleteCtr" value="공지 삭제하기">
			</div>
		</div>
		<form name="adminNoticeDeleteForm">
			<input type="hidden" name="mainCategory" value="1">
			<input type="hidden" name="writer" value="${noticeView.writer }">
			<input type="hidden" name="boardNo" value="${noticeView.boardNo }">
		</form>
	</div>
	
	<div class="adminNoticeModifyView">
		<form name="adminNoticeModifyForm">
			<input type="hidden" name="categoryNo" value="1">
			<input type="hidden" name="boardNo" value="${noticeView.boardNo}">
			<div class="row">
				<div class="col-md-2">
					<h5 style="margin-top: 2px;">title</h5>
				</div>
				<div class="col-md-10">
					<input type="text" name="title" class="form-control" value="${noticeView.title }">
				</div>
			</div>
			<hr>
			<h5>Contents</h5>
			<textarea name="content" id="adminCommunityNoticeText">${noticeView.content }</textarea>
			<script>
			$('#adminCommunityNoticeText').summernote({
		       placeholder: '상품 정보를 입력해주세요!',
		       lang: 'ko-KR',
		       tabsize: 2,
		       height: 600,
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
			<div class="row" style="margin-top: 50px; margin-bottom: 100px;">
				<div class="center-block" style="margin: 0 auto;">
					<input type="button" class="btn btn-success adminNoticeModifyCtr" value="내용 수정완료">
					<input type="button" class="btn btn-warning adminNoticeModifyOnOff" value="취소하기">
				</div>
			</div>
		</form>
	</div>
</div>