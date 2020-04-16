<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	
<div class="card center-block Rebi_login" style="width: 30rem;">
	<article class="card-body">
		<a class="float-right btn btn-outline-primary createForm">Sign up</a>
		<h4 class="card-title mb-4 mt-1">Login</h4>
		<form action="/chococo/member/login" method="post">
			<div class="form-group">
				<label>Your Email</label>
				<input name="userEmail" class="form-control" placeholder="Email" type="email" required>
			</div>
			<div class="form-group">
				<a class="float-right ForgotPw" href="#">Forgot Password?</a>
				<label>Your Password</label>
				<input name="userPass" class="form-control" placeholder="******" type="password" required>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary btn-block">Login</button>
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-warning btn-block loginForm">Cancel</button>
			</div>
		</form>
	</article>
</div>