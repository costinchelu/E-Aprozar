<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizare produs <c:out value="${product.name}"/>.</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">        
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Actualizare <c:out value="${product.name}"></c:out>.</h1>
		<div class="product-preview-container">
			<ul>
				<li><img class="product-preview-image"  src="${pageContext.request.contextPath}/resources/images/<c:out value="${product.idProduct}"/>.jpg"></li>
				<li class="product-preview-name"><c:out value="${product.name}"/></li>
				<li ><span class="product-preview-price">Preț: </span><c:out value="${product.price}"/><c:out value="${product.currency}"/></li>
			</ul>
		</div>
		<div class="product-container">
			<ul>
				<li><img class="product-image"  src="${pageContext.request.contextPath}/resources/images/<c:out value="${product.idProduct}"/>.jpg"></li>
				<li class="product-name"><c:out value="${product.name}"/></li>
				<li ><span class="product-price">Preț: </span><c:out value="${product.price}"/><c:out value="${product.currency}"/></li>
				<li><div class="product-description-container"><span class="product-description">Descriere: </span><c:out value="${product.description}"/></div></li>
				<c:forEach items="${product.specifications}" var="specification">
					<li style="list-style-type: circle"><c:out value= "${specification}"/></li>
				</c:forEach>
			</ul>		
		</div>
		<form:form action="updateProduct" method="post" modelAttribute="updProduct" enctype="multipart/form-data">
				<table>
					<tr>
						<th></th>
						<td><form:hidden path="idProduct"/></td>					
					</tr>
					<tr>						
						<th>Tip produs</th>
					    <td>
							<form:select path="department">	
						    	<form:options items="${listDepartments}"/>							       
						    </form:select>
					    </td>
					</tr>
					<tr>	
						<th></th>		                    
						<td><form:errors path="department" cssClass="error" /></td>
					</tr>
					<tr>
						<th>Nume</th>
						<td><form:input path="name"/></td>					
					</tr>
					<tr>	
						<th></th>		                    
						<td><form:errors path="name" cssClass="error" /></td>
					</tr>
					<tr>
						<th>Preț</th>
						<td><form:input path="price"/></td>					
					</tr>
					<tr>	
						<th></th>		                    
						<td><form:errors path="price" cssClass="error" /></td>
					</tr>
					<tr>
						<th>Monedă</th>
						<td>
							<form:radiobutton path="currency" value="lei" label="leu"/>
							<form:radiobutton path="currency" value="eur" label="euro"/>
						</td>					
					</tr>
					<tr>	
						<th></th>		                    
						<td><form:errors path="currency" cssClass="error" /></td>
					</tr>
					<tr>
						<th>Disponibilitate</th>
						<td>
							<form:radiobutton path="availability" value="Yes" label="Da"/>
							<form:radiobutton path="availability" value="No" label="Nu"/>
						</td>	
					</tr>
					<tr>	
						<th></th>		                    
						<td><form:errors path="availability" cssClass="error" /></td>
					</tr>
					<tr>
						<th>Descriere</th>
						<td><form:textarea path="description" rows="5" cols="40"/></td>				
					</tr>
					<tr>	
						<th></th>		                    
						<td><form:errors path="description" cssClass="error" /></td>
					</tr>
					<tr>
						<th>Specificații</th>
						<td><form:input path="specification1"/></td>		
					</tr>
					<tr>	
						<th></th>		                    
						<td><form:errors path="specification1" cssClass="error" /></td>
					</tr>
					<tr>
						<th></th>					
						<td><form:input path="specification2"/></td>		
					</tr>
					<tr>	
						<th></th>		                    
						<td><form:errors path="specification2" cssClass="error" /></td>
					</tr>
					<tr>
						<th></th>				
						<td><form:input path="specification3"/></td>		
					</tr>
					<tr>	
						<th></th>		                    
						<td><form:errors path="specification3" cssClass="error" /></td>
					</tr>
					<tr>
						<th></th>				
						<td><form:input path="specification4"/></td>		
					</tr>								
					<tr>
						<th></th>
						<td><form:input path="specification5"/></td>		
					</tr>
					<tr>
						<th>Imagine</th>
						<td><input type="file" name="image" accept="image/jpeg"/></td>
					</tr>
					<tr>	
						<th></th>		                    
						<td><form:errors path="image" cssClass="error" /></td>
					</tr>
					<tr>  
						<th></th>             
						<td><form:button>OK</form:button></td>
					</tr>
				</table>
			</form:form>			
	</body>
</html>