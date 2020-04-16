<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<div class="col-md-9 container" style="margin-top: 50px;">
	
	<h1 class="text-danger">Chococo Mypage</h1>

		<div style="margin-top: 50px;">
			<div align="center">
				<ul class="nav nav-tabs" >
					<li class="nav-item" style="width: 33%"><a class="nav-link active" href="#MemberInfo" data-toggle="tab">회원정보 보기</a></li>
					<li class="nav-item" style="width: 33%"><a class="nav-link" href="#MemberModify" data-toggle="tab">회원정보 수정</a></li>
					<li class="nav-item" style="width: 33%"><a class="nav-link" href="#MemberDelete" data-toggle="tab">회원탈퇴</a></li>
				</ul>
			</div>
			
			<div class="tab-content " id="myTabContent">
				<div class="tab-pane fade active show" id="MemberInfo">
				<h2 class="text-primary" style="margin: 60px 30px;">내 정보</h2>
					<div class="container" style="margin-top: 100px;">
    					<div class="row" style="margin-bottom: 50px;">
    						<div class="col-md-6">
    							<div class="row">
      								<h4 class="col-md-3 text-primary">이메일</h4>
      								<label class="col-md-9">${isLogin.userEmail}</label>
      							</div>
      						</div>
      						<div class="col-md-6">
      							<div class="row">
      								<h4 class="col-md-3 text-primary">이름</h4>
      								<label class="col-md-9">${isLogin.userName}</label>
      							</div>
      						</div>
    					</div>
    					<div class="row" style="margin-bottom: 50px;">
    						<div class="col-md-6">
    							<div class="row">
      								<h4 class="col-md-3 text-primary">생년월일</h4>
      								<label class="col-md-9">
      								${isLogin.birth_year}
      								-
      								<c:if test="${isLogin.birth_month < 10}">
      								0${isLogin.birth_month}
      								</c:if>
      								<c:if test="${isLogin.birth_month >= 10}">
      								${isLogin.birth_month}
      								</c:if>
      								-
      								<c:if test="${isLogin.birth_day < 10}">
      								0${isLogin.birth_day}
      								</c:if>
      								<c:if test="${isLogin.birth_day >= 10}">
      								${isLogin.birth_day}
      								</c:if>
      								</label>
      							</div>
      						</div>
      						<div class="col-md-6">
      							<div class="row">
      								<h4 class="col-md-3 text-primary">성별</h4>
      								<label class="col-md-9">${isLogin.sex}</label>
      							</div>
      						</div>
    					</div>
    					<div class="row" style="margin-bottom: 50px;">
    						<div class="col-md-6">
    							<div class="row">
      								<h4 class="col-md-3 text-primary">가입일자</h4>
      								<label class="col-md-9"><fmt:formatDate value="${isLogin.joinDate}" pattern="yyyy - MM - dd"/></label>
      							</div>
      						</div>
      						<div class="col-md-6">
      							<div class="row">
      								<h4 class="col-md-3 text-primary">주소</h4>
      								<c:if test="${isLogin.address != null}">
      									<label class="col-md-9">${isLogin.address}</label>
      								</c:if>
      								<c:if test="${isLogin.address == null}">
      									<label class="col-md-9 text-danger">회원정보 수정 탭에서 주소를 설정해주세요.</label>
      								</c:if>
      							</div>
      						</div>
    					</div>
					</div>
				</div>
				
				
				
				<div class="tab-pane fade" id="MemberModify">
					<h2 class="text-primary" style="margin: 60px 30px;">회원정보 수정</h2>
					
					<!-- 비밀번호 check이 끝났을 때 수정 form으로 진입. -->
					<c:if test="${myPage_passCheck != null}">
					<div class="container" style="margin-top: 100px;">
    					<div class="row" style="margin-bottom: 50px;">
    						<div class="col-md-6">
    							<div class="row">
      								<h4 class="col-md-3 text-primary">암호변경</h4>
      								<input type="password" style="width: 80%;" class="form-control col-md-9 mypage_modify_userPass">
      							</div>
      						</div>
      						<div class="col-md-6">
      							<div class="row">
      								<h4 class="col-md-3 text-primary">이름</h4>
      								<label class="col-md-9">${isLogin.userName}</label>
      							</div>
      						</div>
    					</div>
    					<div class="row" style="margin-bottom: 50px;">
    						<div class="col-md-6">
    							<div class="row">
      								<h4 class="col-md-3 text-primary">생년월일</h4>
      							<div class="col-md-9">
     								<div class="row">
      								<div class="col-md-3" style="padding: 5px;" >
 										<select class="form-control mypage_modify_year">
                       						<c:forEach var="index" begin="1970" end="2020">
                        						<option value="${index}"
                        						<c:if test="${isLogin.birth_year == index}">
                        							selected
                        						</c:if>>${index}</option>
                        					</c:forEach>
                    					</select>
                    				</div>
                    				<div class="col-md-3" style="padding: 5px;" >
      									<select class="form-control mypage_modify_month">
                       						<c:forEach var="index" begin="1" end="12">
                        						<option value="${index}"
                        						<c:if test="${isLogin.birth_month == index}">
                        							selected
                        						</c:if>>${index}</option>
                        					</c:forEach>
                    					</select>
                    				</div>
                    				<div class="col-md-3" style="padding: 5px;" >
      									<select class="form-control mypage_modify_day">
                       						<c:forEach var="index" begin="1" end="31">
                        						<option value="${index}"
                        						<c:if test="${isLogin.birth_day == index}">
                        							selected
                        						</c:if>>${index}</option>
                        					</c:forEach>
                    					</select>
      								</div>
      								</div>
      							</div>
      							</div>
      						</div>
      						<div class="col-md-6">
      							<div class="row">
      								<h4 class="col-md-3 text-primary">성별</h4>
      								<div class="col-md-9">
      									<label class="radio-inline">
      									<input type="radio" class="mypage_modify_sex" name="sex" value="male" checked/> Male
      									</label>
										<label class="radio-inline" style="padding-left: 40px;">
										<input type="radio" class="mypage_modify_sex" name="sex" value="female" /> Female
										</label>
									</div>
      							</div>
      						</div>
    					</div>
    					<div class="row" style="margin-bottom: 50px;">
    						<div class="col-md-6">
    							<div class="row">
      								<h4 class="col-md-3 text-primary">가입일자</h4>
      								<label class="col-md-9"><fmt:formatDate value="${isLogin.joinDate}" pattern="yyyy-MM-dd"/></label>
      							</div>
      						</div>
      						<div class="col-md-6">
      							<div class="row">
      								<h4 class="col-md-3 text-primary">주소</h4>
      								<input type="text" class="col-md-9 form-control mypage_modify_address" value="${isLogin.address }">
      							</div>
      						</div>
    					</div>
    					<!-- form 동적생성 관련 btn -->
    					<div class="row">
    						<div class="center-block" style="margin:0 auto;">
    							<form class="form-group">
    								<input type="hidden" name="userEmail" value="${isLogin.userEmail}">
									<button type="button" class="btn btn-warning mypageProfileModifyCtr">수정하기</button>
								</form>
    						</div>
    					</div>		
					</div>
					</c:if>
					
					<c:if test="${myPage_passCheck == null}">
						<div class="container" style="margin-top: 100px;">
							<div class="row">
    							<div class="center-block" style="margin: 0 auto; padding-bottom: 30px; color: green;">
    								<i class="fas fa-check-square fa-4x"></i>
    							</div>
    						</div>
    						<div class="row">
    							<div class="center-block" style="margin: 0 auto; padding-bottom: 30px;">
    								<h6 class="text-info">회원정보 수정을 위해 비밀번호를 입력해주세요.</h6>
    							</div>
    						</div>
    						<form name="myPagePassCheckForm">
    							<div class="row">
    								<div class="center-block" style="margin: 0 auto;">
    									<div class="form-group">
    										<input class="form-control" type="text" value="${isLogin.userEmail}" readonly><br>
    										<input class="form-control userPassData" type="password" placeholder="******" name="userPass">
    									</div>
    								</div>
    							</div>
    							<div class="row" style="padding-top: 20px;">
    								<div class="center-block" style="margin: 0 auto;">
    									<div class="form-group">
    										<button type="button" class="btn btn-success myPagePassCheckFormCtr">Submit</button>
    									</div>
    								</div>
    							</div>
    						</form>
						</div>
					</c:if>			
				</div>
				
				
				
				<div class="tab-pane fade" id="MemberDelete">
					<h2 class="text-primary" style="margin: 60px 30px;">회원 탈퇴</h2>
					<div class="container" style="margin-top: 70px;">
    					<div class="row">
    						<div class="center-block" style="margin: 0 auto; color: red;">
    							<i class="fas fa-exclamation-triangle fa-7x"></i>
    						</div>
    					</div>
    					<div class="row">
    						<div class="text-center" style="margin: 0 auto; padding-top: 50px;">
    							한번 회원탈퇴를 진행할 경우 다시 복구하기 어려우며 재 가입을 해주셔야만 합니다.
    								<h3 style="color: red; padding-top: 20px;">정말 회원탈퇴를 하시겠어요?</h3>
    						</div>
    					</div>
    					<div class="row">
    						<div class="center-block" style="margin: 0 auto; padding-top: 100px;">
    							<label style="margin-right: 10px;">회원 탈퇴를 진행합니다. </label><input type="checkbox" class="MemberDeleteCheck">		
    						</div>
    					</div>
    					<div class="row">
    						<div class="center-block" style="margin: 0 auto; padding-top: 30px;">
    							<input type="button" value="탈퇴하기" class="nav_deleteMember btn btn-warning">		
    						</div>
    					</div>
					</div>
				</div>
				
				
				
			</div>
		</div>
	</div>


