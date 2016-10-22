<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="key" required="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>

</style>

<c:set var="lang" value="${not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="decor" />

<c:if test="${not empty key}">
   <c:choose>
    <c:when test="${key == 'TAKEN'}">
    <small style="color:red; "><fmt:message key="${key}"/></small>
    </c:when>
    <c:when test="${key == 'PERFORMED'}">
      <small style="color:blue; "><fmt:message key="${key}"/></small>  
    </c:when>
    
    <c:otherwise>
        <small style=" font-family:courier;"><fmt:message key="${key}"/></small>
    </c:otherwise>
</c:choose>
   
   
</c:if>



