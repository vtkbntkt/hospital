<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h"%>

<c:set var="language" value="${not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="decor" />
<fmt:message key="404.message" var="msgValue" />

<!DOCTYPE html>
<html lang="${language}">
<body>
	<img src="resources/img/404.jpg" alt="" height="328" width="795" style="margin:0px auto;display:block">
	<h2 style="text-align:center">${msgValue}</h2>
</body>
</html>