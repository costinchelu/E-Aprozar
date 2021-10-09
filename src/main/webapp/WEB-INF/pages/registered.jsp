<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inreistrat</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Inregistrare efectuată cu succes.</h1>
		<h2><a href="${pageContext.request.contextPath}/login">Acum puteți să vă logați.</a></h2>
		<h2><a href="${pageContext.request.contextPath}/welcome">Apasă pentru întoarcere la ecranul principal.</a></h2>
	</body>
</html>