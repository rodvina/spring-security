<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Spring MVC Security POC
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p>Your principal object is....: <%= request.getUserPrincipal() %></p>
<table>
	<tr>
		<td><strong>CustomerId:</strong></td>
		<td><sec:authentication property="principal.customerId"/></td>
	</tr>
	<tr>
		<td><strong>Email:</strong></td>
		<td><sec:authentication property="principal.email"/></td>
	</tr>
	<tr>
		<td><strong>PrimaryGat:</strong></td>
		<td><sec:authentication property="principal.primaryGat"/></td>
	</tr>
	<tr>
		<td><strong>SecondaryGat:</strong></td>
		<td><sec:authentication property="principal.secondaryGat"/></td>
	</tr>
	<tr>
		<td><strong>FirstName:</strong></td>
		<td><sec:authentication property="principal.firstName"/></td>
	</tr>	
	<tr>
		<td><strong>LastName:</strong></td>
		<td><sec:authentication property="principal.lastName"/></td>
	</tr>	
	<tr>
		<td><strong>Initial:</strong></td>
		<td><sec:authentication property="principal.initial"/></td>
	</tr>	
	<tr>
		<td><strong>Title:</strong></td>
		<td><sec:authentication property="principal.title"/></td>
	</tr>
	<tr>
		<td><strong>Nickname:</strong></td>
		<td><sec:authentication property="principal.nickname"/></td>
	</tr>	
	<tr>
		<td><strong>BranchCode:</strong></td>
		<td><sec:authentication property="principal.branchCode"/></td>
	</tr>	
	<tr>
		<td><strong>AgencyName:</strong></td>
		<td><sec:authentication property="principal.agencyName"/></td>
	</tr>	
</table>

<p><sec:authorize access="hasRole('ROLE_KB01')">
	If you have role KB01, you can see this:  <a href="http://www.cnn.com">CNN</a>
</sec:authorize></p>
<p><sec:authorize access="hasRole('ROLE_BLA')">
	You have role BLA
</sec:authorize></p>
</body>
</html>
