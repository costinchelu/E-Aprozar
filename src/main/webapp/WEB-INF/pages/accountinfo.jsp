<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informații cont</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>informații cont.</h1>
		<h3><a href="${pageContext.request.contextPath}/user/profile">Profil.</a></h3>
		<h3><a href="${pageContext.request.contextPath}/user/orderList">Lista de comenzi.</a></h3>
		<c:if test="${orderNewSize > 0}"><h3><a href="${pageContext.request.contextPath}/user/choiceCart">Cos de cumparaturi.</a></h3></c:if>
		<sec:authorize access="hasRole('ADMIN')"><h3><a href="${pageContext.request.contextPath}/managementProduct">Gestiune produse.</a></h3></sec:authorize>
	</body>
</html>	