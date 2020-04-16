<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="container">
	<form name="adminNoticeInsertForm">
		<input type="hidden" name="categoryNo" value="1">
		<input type="hidden" name="writer" value="${adminCheck.userName }">
		<div align="center" style="margin-top: 50px; margin-bottom: 100px;">
			<h1 class="text-primary">New Community Notice Insert</h1>
		</div>
		<div class="row">
			<div class="col-md-2">
				<h5 style="margin-top: 2px;">title</h5>
			</div>
			<div class="col-md-10">
				<input type="text" name="title" class="form-control">
			</div>
		</div>
		<hr>
		<h5>Contents</h5>
		<textarea name="content" id="adminCommunityNoticeText"></textarea>
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
				<input type="button" class="btn btn-success adminNoticeInsertCtr" value="공지 등록하기">
				<input type="reset" class="btn btn-warning" value="다시 입력하기">
			</div>
		</div>
	</form>
</div>