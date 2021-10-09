<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Afișează <c:out value="${department}"></c:out> produs (sterge/actualizeaza).</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">        
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Afișează <c:out value="${department}"/> produs (sterge/actualizeaza).</h1>
		<table border="1" class="table">
			<thead>
				<tr>
					<th>№</th>
					<th>Tip produs</th>
					<th>Nume</th>
					<th>Preț</th>
					<th>Monedă</th>
					<th>Disponibilitate</th>
					<th>Specificații</th>
					<th>Actualizare</th>
					<th>Stergere</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="product">
					<tr>				
						<td><c:out value= "${product.department}"/></td>
						<td><c:out value= "${product.name}"/></td>
						<td><c:out value= "${product.price}"/></td>
						<td><c:out value= "${product.currency}"/></td>
						<td><c:out value= "${product.availability}"/></td>
						<td><c:forEach items="${product.specifications}" var="specification">
								<ul><li class="specification"><c:out value= "${specification}"/></li></ul>
							</c:forEach>
						</td>
						<td><a href="${pageContext.request.contextPath}/managementProduct/update/<c:out value="${product.idProduct}"/>">update</a></td>
						<td><a href="${pageContext.request.contextPath}/managementProduct/delete/<c:out value="${department}"/>/<c:out value="${product.idProduct}"/>" class="delProduct">delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/deleteProduct.js"></script>
	</body>
</html>