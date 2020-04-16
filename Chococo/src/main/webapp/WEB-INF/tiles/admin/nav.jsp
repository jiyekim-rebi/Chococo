<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="/chococo/">Chococo Home</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="/chococo/admin/main">Admin Home</a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<button class="btn btn-warning my-2 my-sm-0 nav_logout" type="button">Logout</button>
		</form>
	</div>
</nav>


