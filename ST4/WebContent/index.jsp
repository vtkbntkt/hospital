<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:if test="${not empty sessionScope.userInfo}">
	<c:redirect url="Controller">
		<c:param name="command" value="getaccount"></c:param>
	</c:redirect>
</c:if>


<c:set var="language" value="${not empty language ? language : pageContext.request.locale.language}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="decor" />

<fmt:message key="login.label.email" var="emailValue" />
<fmt:message key="login.label.password" var="passwordValue" />
<fmt:message key="login.button.submit" var="buttonValue" />
<fmt:message key="login.title" var="titleValue" />
<fmt:message key="login.form.title" var="formTitleValue" />


<!DOCTYPE html>
<html lang="${language}">
<head>
<title>${titleValue}</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>         
<script src="bootstrap/js/bootstrap.min.js"></script>

<style type='text/css'>
fieldset {
	font-size: 18px;
	padding: 10px;
	width: 250px;
	line-height: 1.8;
	margin: auto;
}

.button {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 1px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

.button1 {
	background-color: white;
	color: black;
	border: 2px solid #4CAF50;
}
</style>
</head>
<body>

	<c:forEach var="dbError" items="${dbErrors}">
		<h:locwarnmessage2 key="${dbError.key}" />
	</c:forEach>

	<h:locale language="${language}" path="${pageContext.request.servletPath}" />





	<form action="Controller" method="post">
		<fieldset>
			<legend style="float: top">${formTitleValue}</legend>
			<input type="hidden" name="command" value="signin" />
			<h:locwarnmessage key="${loginErrors['emailpassword']}" />
			<p>
			<p>${emailValue}<input type="email" value="${loginUserBean.email}" name="email">
				<h:locwarnmessage key="${loginErrors['email']}" />
			<p>
			<p>${passwordValue}<input type="password" name="password">
			<p>
				<h:locwarnmessage key="${loginErrors['password']}" />
			<p>
				<input type="submit" value="${buttonValue}" class="button button1">
		</fieldset>
	</form>
</body>
</html>