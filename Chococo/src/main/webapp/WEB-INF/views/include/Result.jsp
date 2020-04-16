<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Chococo</title>
</head>
<body>
<script>
	alert('${msg}');
	//location.href='<c:out value="${pageContext.request.contextPath}"/>${url}';
	document.location.href = '${url}'; 
</script>
</body>
</html>