<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestiune produse</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Gestiune produse.</h1>
		<h3><a href="${pageContext.request.contextPath}/managementProduct/addProduct">Adaugă produs.</a></h3>
		<h3><a href="${pageContext.request.contextPath}/managementProduct/showProduct/All">Afișează produsele (ștergere/actualizare).</a></h3>
		<c:forEach items="${listDepartments}" var="department">
		<h3><a href="${pageContext.request.contextPath}/managementProduct/showProduct/${department}">Afișează ${department} produs (ștergere/actualizare).</a></h3>
		</c:forEach>
	</body>
</html>