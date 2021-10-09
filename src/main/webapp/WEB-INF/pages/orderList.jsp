<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Afacere electronică</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h3>Listă comenzi.</h3>
		<table border="1" class="table">
			<thead>
				<tr>
					<th>№</th>
					<th>Data comenzii</th>
					<th>Data plății</th>
					<th>Stare</th>
					<th>Detalii livrare</th>
					<th>Listă</th>
					<th>Preț total</th>
					<th>Plătit</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${currentUser.orders}" var="orderList">
					<tr>				
						<td><c:out value= "${orderList.orderedDate}"/></td>
						<td>
							<c:choose>
								<c:when test="${orderList.orderedDate == orderList.paidDate}"></c:when>
								<c:otherwise><c:out value= "${orderList.paidDate}"/></c:otherwise>
							</c:choose>						
						</td>
						<td><c:out value= "${orderList.status}"/></td>
						<td><c:out value= "${orderList.customerAddress}"/></td>
						<td><c:forEach items="${orderList.lineItem}" var="lineItemList">
								<ul><li class="specification"><a href="${pageContext.request.contextPath}/buy/details/<c:out value="${lineItemList.product.idProduct}"/>"><c:out value= "${lineItemList.product.name}"/></a> <c:out value= "${lineItemList.quantity}"/> 
								<c:choose>
									<c:when test="${lineItemList.quantity == 1}">buc. </c:when>
									<c:otherwise>buc.</c:otherwise>
								</c:choose>
								</li></ul>
							</c:forEach>
						</td>
						<td><c:out value= "${orderList.totalPrice}"/></td>
						<td><c:out value= "${orderList.paid}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>