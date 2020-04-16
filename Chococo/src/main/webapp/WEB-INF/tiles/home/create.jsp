<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="createCard col-xs-12 col-sm-12 col-md-4 well well-sm Rebi_create" style="width: 30rem; background-color: white;">
        	<h4 class="card-title mb-2 mt-3">Sign Up</h4>
        	<br>
       <form name="createMemberForm">
       		<label>Your Name</label>
			<input class="form-control mb-2 isUserNameCheck" name="userName" placeholder="Name" type="text">
			<label class="isChkName"><i class="fas fa-times text-danger" style="padding-right: 10px;"></i>이름을 입력해주세요!</label>

			<br><label>Your Email</label>
			<input class="form-control mb-2 isUserEmailCheck" name="userEmail" placeholder="Email" type="email">
			<label class="isChkEmail"><i class="fas fa-times text-danger" style="padding-right: 10px;"></i>이메일을 입력해주세요!</label>
			
			<br><label>Your Password</label>
			<input class="form-control mb-2" name="userPass" placeholder="******" type="password">
			<label class="text-primary">Password Check, please</label>
			<input class="form-control mb-2" name="userPassCheck" placeholder="******" type="password">

            <label for="">Birth Date</label>
            <div class="row mb-2">
                <div class="col-xs-4 col-md-4">
                    <select name="birth_month" class="form-control">
                        <option value="Month">Month</option>
                        <c:forEach var="index" begin="1" end="12">
                        	<option value="${index}">${index}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-xs-4 col-md-4">
                    <select name="birth_day" class="form-control">
                        <option value="Day">Day</option>
                        <c:forEach var="index" begin="1" end="31">
                        	<option value="${index}">${index}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-xs-4 col-md-4">
                    <select name="birth_year" class="form-control">
                        <option value="Year">Year</option>
                        <c:forEach var="index" begin="1970" end="2020">
                        	<option value="${index}">${index}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            
            <div class="form-group create_sex_check">
            <!-- Male / Female -->
            <label class="radio-inline"><input type="radio" name="sex" value="male" />Male</label>
            <label class="radio-inline"><input type="radio" name="sex" value="female" />Female</label>
            </div>
            <div class="form-group">
            	<input type="hidden" class="createNameOk" value="">
            	<input type="hidden" class="createEmailOk" value="">
            	<button type="button" class="btn btn-primary btn-block createMemberDB">Sign up</button>
            </div>
            <div class="form-group">
				<button type="button" class="btn btn-warning btn-block createForm">Cancel</button>
			</div>
        </form>
</div>