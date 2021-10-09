<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizare profil</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Actualizare profil</h1>
		<h2>Formular introducere utilizator</h2>
		<div class="form-input">
		<fieldset>
			<legend>Actualizează informațiile contului de utilizator</legend>
					<form:form action="updateUser" method="post" modelAttribute="userUpdate">
			            <table>
			            	<tr>
			                    <th>Nume</th>
			                    <td><form:input path="surname" value="${currentUser.surname}"/></td>
			                </tr>
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="surname" cssClass="error" /></td>
			                </tr>			            	
			            	<tr>
			                    <th>Prenume</th>
			                    <td><form:input path="name" value="${currentUser.name}"/></td>
			                </tr> 
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="name" cssClass="error" /></td>
			                </tr>
			                <tr>
			                    <th>Detalii</th>
			                    <td><form:input path="details" value="${currentUser.details}"/></td>
			                </tr> 
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="details" cssClass="error" /></td>
			                </tr>
			                <tr>
			                    <th>Telefon</th>
			                    <td><form:input path="phone" placeholder="+40720000000" value="${currentUser.phone}"/></td>
			                </tr> 
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="phone" cssClass="error" /></td>
			                </tr>
			                <tr>
			                    <th>Parola</th>
			                    <td><form:password path="password" /></td>
			                </tr>  
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="password" cssClass="error" /></td>
			                </tr>
			                <tr>
			                    <th>Reintroducere parola</th>
			                    <td><form:password path="matchingPassword" /></td>
			                </tr>
			                <tr>	
			                	<th></th>		                    
			                    <td><form:errors path="matchingPassword" cssClass="error" /></td>
			                </tr>
			                <tr>               
			                    <td><form:button>OK</form:button></td>
			                </tr>
			            </table>            
			        </form:form>
	        </fieldset>
        </div>
	</body>
</html>