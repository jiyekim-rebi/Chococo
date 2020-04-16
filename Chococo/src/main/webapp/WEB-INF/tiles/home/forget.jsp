<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
	
<div class="forgotCard center-block Rebi_ForgotPw" style="width: 30rem; background: white;">
	<article class="card-body">
		<h4 class="card-title mb-4 mt-1">Forget Password?</h4>
		<form name="forgetPasswordForm">
			<div class="form-group">
				<label>Your Email</label>
				<input name="userEmail" class="form-control" placeholder="Email" type="email" required>
			</div>
			<!-- form-group// -->
			<div class="form-group">
				<button type="submit" class="btn btn-primary btn-block forgetPasswordCtr">Access</button>
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-warning btn-block ForgotPw">Cancel</button>
			</div>
		</form>
	</article>
</div>