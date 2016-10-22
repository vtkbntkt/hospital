<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:set var="language" value="${not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="decor" />
<fmt:message key="account.title" var="titleValue" />
<fmt:message key="account.greeting" var="greetingValue" />

<!DOCTYPE html>
<html lang="${language}">
<head>
<title>${titleValue}</title>
<script src="resources/js/jquery.js"></script>
 <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>         
       <script src="bootstrap/js/bootstrap.min.js"></script>
       
       
</head>
<body>
  
<c:forEach var="dbError" items="${dbErrors}">
<h:locwarnmessage2 key="${dbError.key}"/>
</c:forEach>

<h:locale language = "${language}" path = "${pageContext.request.servletPath}" />



<c:choose>
    <c:when test="${sessionScope.userInfo.roleId==0}">
       <%@ include file="/WEB-INF/jspf/admin/adminPanel.jspf" %>
    </c:when>
    <c:when test="${sessionScope.userInfo.roleId==2}">
        <%@ include file="/WEB-INF/jspf/doctor/docPanel.jspf" %>
    </c:when>
    <c:when test="${sessionScope.userInfo.roleId==3}">
        <%@ include file="/WEB-INF/jspf/nurse/nursePanel.jspf" %>
    </c:when>
     <c:when test="${sessionScope.userInfo.roleId==1}">
        <%@ include file="/WEB-INF/jspf/patient/patientPanel.jspf" %>
    </c:when>
    <c:otherwise>
        <c:redirect url="?command=getlogin"/>
    </c:otherwise>
</c:choose>


</body>
</html>