$(document).ready(function(){
	
	//게시판 - 게시글 검색 관련
	$('#searchBtn').click(function() {
		var searchType = $(".CommunitysearchBar option:selected").val();
		var keyword = $("input[name='keyword'").val();
		if(searchType != 'n' && keyword != ""){
			location.href = "search?"
				+ "&searchType=" + searchType
				+ "&keyword=" + encodeURIComponent(keyword);
				
		} else {
			alert('검색창에 빈칸이 있습니다. 채워주세요.');
		}
	});
	
	//게시판 - 글 수정 form 진입 전 데이터 전송
	//search, board에 따라 넘겨줄 값이 달라서 부득이하게 이렇게 처리함.
	//boardModify
	$(".articleModifyCtr").click(function(){
		var formObj=$("form[name='modifyArticleFormCtr']");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/community/boardModify");
		formObj.submit();
	})
	
	//게시판 - 글 수정 controller
	$('.articleModify').click(function(){
		var formObj=$("form[name='ArticlemodifyForm']");
		var categoryNo = $(".boardModifySelect option:selected").val();
		formObj.append("<input type='hidden' name='categoryNo' value='"+categoryNo+"'>");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/community/boardModifyPost");
		formObj.submit();
	});
	
	//게시판 - 글 등록 Controller
	$(".articleInsert").click(function(){
		var formObj=$("form[name='ArticleInsertForm']");
		var categoryNo=$(".boardInsertSelect option:selected").val();
		formObj.append("<input type='hidden' name='categoryNo' value='"+categoryNo+"'>");
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/community/boardInsertPost");
		formObj.submit();
	});
	
	//게시판 - 글 삭제 Controller
	$(".articleDelete").click(function(){
		if(confirm('정말로 삭제하시겠어요?')){
			var formObj=$("form[name='deleteArticleForm']");
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/community/deleteArticle");
			formObj.submit();
		}
	});
		
	//2020.03.17 댓글 답글 달기 - Controller로 form 연결하기
	$(".articleParentInsert").click(function(){
		var formObj = $("form[name='parentArticleForm']");
		formObj.attr("method", "get");
		formObj.attr("action", "/chococo/community/boardInsert");
		formObj.submit();
	})
	
	//새 리플 달기 - controller로 보내기
	$(".newReplyBtn").click(function(){
		if($("#newReplyContent").val() == ""){
			alert('내용을 입력해주세요!');
		} else {
			var formObj = $("form[name='newReplyForm']");
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/community/newReply");
			formObj.submit();
		}
		
	});
	
	//리플 수정창 on
	$(".replyModifyOn").click(function(){
		$(this).parent().parent().next().show();
		$(this).parent().parent().prev().show();
		$(this).parent().parent().prev().prev().hide();
		$(this).parent().parent().hide();
	});
	
	//리플 수정창 off
	$(".replyModifyOff").click(function(){
		$(this).parent().parent().prev().show();
		$(this).parent().parent().prev().prev().prev().show();
		$(this).parent().parent().prev().prev().hide();
		$(this).parent().parent().hide();
	});
	
	//리플 수정 Controller
	$(".replyModifyCtr").click(function(){
		var formObj = $(this).parent().parent().prev().prev().children();
		formObj.attr("method", "post");
		formObj.attr("action", "/chococo/community/modifyReply");
		formObj.submit();
	});
	
	//2020.03.14
	//append로 class 명 지정하면 들어가기는 하는데 이벤트 발생이 안되서. 이렇게 처리.
	//parentReply 댓글 form ON
	var parentFormOnOff = false;
	$(document).on('click', '.parentReplyFormOpen', function(){
		if(parentFormOnOff == false){			
			$(this).parent().append('<textarea id="parentForm" class="form-control" name="content" rows="5" style="resize: none; margin-top: 5px;" required></textarea>')
			.find("#parentForm").addClass("parentTextarea");
			$(this).parent().append('<button type="button" class="btn btn-warning parentReplySubmit" style="margin-top: 5px; margin-right: 5px;">Reply Submit</button>');
			$(this).parent().append('<button type="button" class="btn btn-danger parentReplyCancel" style="margin-top: 5px;">리플 답글취소</button>');
			$(this).remove();
			parentFormOnOff = true;
		} 
	});
	
	//parentReply 댓글 form OFF
	$(document).on('click', '.parentReplyCancel', function(){
		$(this).siblings().not("input").remove();
		$(this).parent().append('<button type="button" class="btn btn-info parentReplyFormOpen">답글달기</button>');
		$(this).remove();
		parentFormOnOff = false;
	});
	
	//parentReplyController
	$(document).on('click', '.parentReplySubmit', function(){
		if($(".parentTextarea").val() == ''){
			alert('내용을 입력해주세요!');
		} else {
			var formObj = $(this).parent();
			formObj.attr("method", "post");
			formObj.attr("action", "/chococo/community/parentNewReply");
			formObj.submit();
		}
	});
	
	//2020.03.19 mypage에서 댓글 삭제하기
	//boardNo 필요없고, mypage로 다시 돌아올꺼라 replyNo만 받아와서 post로 처리해주세요.
	$(".mypageReplyDelete").click(function(){
		var formObj = $(this).parent();
		formObj.attr("method","post");
		formObj.attr("action", "/chococo/community/mypageReplyDelete");
		formObj.submit();
	});
	
})


function GotomodifyForm(boardNo){
	location.href="/chococo/community/boardModify?boardNo=" + boardNo;
}

//search 데이터가 있을 경우 search로, 아닐 경우 board로.
//둘다 공통적으로 cri의 page, perPageNum값은 공통으로 넘겨줌.
function backList(page, categoryNo) {
	location.href="/chococo/community/board?page="+page+"&categoryNo="+categoryNo;
}

function backSearchList(page, searchType, keyword){
	location.href="/chococo/community/search?page="+page+"&searchType=" + searchType +"&keyword=" + keyword;
}

function insertArticle(categoryNo){
	location.href="/chococo/community/boardInsert?categoryNo="+categoryNo;
}

//댓글 삭제
function replyDelete(replyNo, boardNo){
	if(confirm("정말로 댓글을 삭제하시겠어요?")){
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", "/chococo/community/deleteReply");

		var reply = document.createElement("input");
		reply.setAttribute("name", "replyNo");
		reply.setAttribute("value", replyNo);

		form.appendChild(reply);

		var board = document.createElement("input");
		board.setAttribute("name", "boardNo");
		board.setAttribute("value", boardNo);

		form.appendChild(board);

		document.body.appendChild(form);
		form.submit();
	}
}

//2020.03.19 글 삭제 Controller from mypage
//다시 마이페이지로 돌아가야 해서.
function mypageArticleDelete(boardNo) {
	if(confirm('정말로 삭제하시겠어요?')){
		location.href="/chococo/community/mypageDeleteArticle?boardNo="+boardNo; 
	}
}
