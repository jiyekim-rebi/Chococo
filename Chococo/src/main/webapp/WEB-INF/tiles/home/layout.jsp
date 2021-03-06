<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title" /></title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/rebianne.css"></link>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://kit.fontawesome.com/c8572bd716.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/rebianne.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/rebianne_member.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/rebianne_community.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote-lite.min.js"></script>

</head>
<body>

<!-- navigation -->
<tiles:insertAttribute name="nav" />

<!-- Login.jsp file -->
<tiles:insertAttribute name="login" />
		
<!-- create.jsp file -->
<tiles:insertAttribute name="create" />
		
<!-- forget.jsp file -->
<tiles:insertAttribute name="forget" />

<!-- 내용 -->
<tiles:insertAttribute name="body"/>

<!-- footer -->
<tiles:insertAttribute name="footer" />

</body>
</html>