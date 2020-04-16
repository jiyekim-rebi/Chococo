<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div id="header">
<div class="container">
	<div class="text-center" id="index_title">
		<div id="index_title1">Miniature Community</div>
		<div id="index_title2">Chococo</div>
	</div>
</div>
</div>

<div id="about">
	<div class="container">
		<div class="row">
		<div class="col-md-6 text-center" id="about_title">
			<h1>Welcome to Chococo</h1>
			<div>
				The European languages are members of the same family. Their separate existence is a myth. For science, music, sport, etc, Europe uses the same vocabulary. The languages only differ in their grammar, their pronunciation and their most common words. Everyone realizes why a new common language would be desirable: one could refuse to pay expensive translators. To achieve this, it would be necessary to have uniform grammar, pronunciation and more common words. If several languages coalesce, the grammar of the resulting language is more simple and regular than that of the individual languages. The new common language will be more
			</div>
			<br>
			<h3>Welcome to Chococo</h3>
		</div>
		<div class="col-md-6" id="about_image">
			<img src="resources/image/index_about.jpg" width="600px" height="500px">
		</div>
		</div>
	</div>
</div>

<div id="services">
		<div class="container">
			<div class="text-center" id="about_title">
				<h1 class="mb-5">Services</h1>
			
			<div class="row Rebi_serviceSet">
				<div class="col-md-3">
						<a href="#"><i class="fa-4x far fa-calendar Rebi_iconSize"></i></a>
						<h3>
							<a href="#">Regular meeting</a>
						</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Nam viverra euismod odio, gravida pellentesque urna varius vitae.
							Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
				</div>
				<div class="col-md-3">
						<a href="#"><i class="fa-4x far fa-handshake Rebi_iconSize"></i></a>
						<h3>
							<a href="#">Community</a>
						</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Nam viverra euismod odio, gravida pellentesque urna varius vitae.
							Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
				</div>
				<div class="col-md-3">
						<a href="#"><i class="fa-4x fas fa-cart-plus Rebi_iconSize"></i></a>
						<h3><a href="#">Chococo Market</a></h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Nam viverra euismod odio, gravida pellentesque urna varius vitae.
							Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
				</div>
				<div class="col-md-3">
						<a href="#"><i class="fa-4x fas fa-pencil-alt Rebi_iconSize"></i></a>
						<h3>
							<a href="#">Miniature Lecture</a>
						</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Nam viverra euismod odio, gravida pellentesque urna varius vitae.
							Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
				</div>
			</div>
		</div>
	</div>
</div>
		
<div id="contact">
		<div class="container" id="contact_setting">
			<div class="text-center">
				<h1 id="contact_title">Contact Us</h1>
			</div>
				<div class="row">
					<div class="ml-3 col-lg-6 col-md-6">
							<form name="contactUsForm">
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<label>Name</label><input type="text" name="mailName" class="form-control custom-labels" id="name" placeholder="이름" required data-validation-required-message="Please write your name!"/>
											<p class="help-block"></p>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<label>Email</label><input type="email" name="mailEmail" class="form-control custom-labels" id="email" placeholder="이메일" required data-validation-required-message="Please write your email!"/>
											<p class="help-block"></p>
										</div>
									</div>
									<div class="col-sm-12">
										<div class="form-group">
											<label>Message</label><input type="text" name="mailMessage" class="form-control custom-labels" id="message" placeholder="내용을 적어주세요." required data-validation-required-message="Please write a message!"/>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
											<input type="button" class="btn btn-success Rebi_ContactBtn mailSendCtr" value="Send It"/>
											<input type="reset" class="btn btn-warning Rebi_ContactBtn" name="reset" value="RESET">
										</div>
									</div>
									<div class="col-sm-3 col-xs-6 height-contact-element">
										<div class="form-group">
											<div id="response_holder"></div>
										</div>
									</div>
									<div class="col-sm-12 contact-msg">
									<div id="success"></div>
									</div>
								</div>
							</form>
					</div>
				<div class="col-lg-5 col-md-3 col-lg-offset-1 col-md-offset-1">
					<div class="row">
						<div class="col-md-12 height-contact-element">
							<div class="form-group">
								<i class="fa fa-1x fa-map-marker"></i>
								<span class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</span>
						</div>
					</div>
						<div class="col-md-12 height-contact-element">
							<div class="form-group">
								<i class="fa fa-1x fa-phone"></i>
								<span class="text">010-1234-1234</span>
							</div>
						</div>
					<div class="col-md-12 height-contact-element">    
						<div class="form-group">
							<i class="fa fa-1x fa-envelope"></i>
							<span class="text">rebianne03@naver.com</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 최상단으로 올려주는 버튼 -->
<a class="Rebi_top"><i class="fa-4x fas fa-angle-up" style="color: #e95420;"></i></a>
