<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Coș de cumpărături salvat</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">        
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Coș de cumpărături salvat.</h1>
		<h2>Selectați coșul de cumpărături care trebuie adăugat.</h2>
		<div class="choice-cart-user">
		<form:form action="mixCart" method="post" modelAttribute="choiceCartList">
			<c:forEach items="${orderUserNew}" var="orderNew">	
				<table border="1">
					<thead>
						<tr>							
							<th>Data comenzii</th>
							<th width="60px">Stare</th>
							<th width="250px">Produs</th>
							<th>Preț total</th>
							<th><form:checkbox path="orderedDate" value="${orderNew.orderedDate}"/></th>
						</tr>
					</thead>
					<tbody>					
						<tr>				
							<td><c:out value= "${orderNew.orderedDate}"/></td>							
							<td><c:out value= "${orderNew.status}"/></td>
							<td><c:forEach items="${orderNew.lineItem}" var="lineItemList">
									<ul><li class="specification"><a href="${pageContext.request.contextPath}/buy/details/<c:out value="${lineItemList.product.idProduct}"/>"><c:out value= "${lineItemList.product.name}"/></a> <c:out value= "${lineItemList.quantity}"/> 
									<c:choose>
										<c:when test="${lineItemList.quantity == 1}">produs </c:when>
										<c:otherwise>produse</c:otherwise>
									</c:choose>
									</li></ul>
								</c:forEach>
							</td>
							<td><c:out value= "${orderNew.totalPrice}"/></td>
						</tr>					
					</tbody>
				</table>				
				<h1></h1>			
			</c:forEach>			
			<br/>	
			<form:button>Adaugă coș de cumpărături salvat</form:button>
		</form:form>
		</div>
		<c:choose>
			<c:when test="${empty order.lineItem}"><h2>Coșul de cumpărături este gol.</h2></c:when>
			<c:otherwise>
				<h2>Coș de cumpărături.</h2>
				<table border="1">
					<thead>
						<tr>							
							<th>Data comenzii</th>
							<th width="60px">Stare</th>
							<th width="250px">Produs</th>
							<th>Preț total</th>
						</tr>
					</thead>
					<tbody>					
						<tr>				
							<td><c:out value= "${order.orderedDate}"/></td>							
							<td><c:out value= "${order.status}"/></td>
							<td><c:forEach items="${order.lineItem}" var="lineItemList">
								<ul><li class="specification"><a href="${pageContext.request.contextPath}/buy/details/<c:out value="${lineItemList.product.idProduct}"/>"><c:out value= "${lineItemList.product.name}"/></a> <c:out value= "${lineItemList.quantity}"/> 
								<c:choose>
								<c:when test="${lineItemList.quantity == 1}">produs </c:when>
								<c:otherwise>produse</c:otherwise>
								</c:choose>
								</li></ul>
								</c:forEach>
							</td>
							<td><c:out value= "${order.totalPrice}"/></td>
						</tr>				
					</tbody>
				</table>
			</c:otherwise>		
		</c:choose>
		<h2><a href="${pageContext.request.contextPath}/welcome" class="profile">Continuă cu coșul de cumpărături actual</a></h2>
	</body>
</html>