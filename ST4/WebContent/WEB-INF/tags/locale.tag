<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="language" required="true"%>
<%@ attribute name="path" required="true"%>
<style>
select {
    border: 2px solid #d4e4d5;
    border-radius: 2px;
}
</style>
<form action="Controller" method="post">
	<input type="hidden" name="command" value="changelang" /> <input
		type="hidden" name="path" value="${path}" /> <select id="language"
		name="language" onchange="submit()">
		<option style="background-image:url(male.ico);" value="en" ${language == 'en' ? 'selected' : ''}>English</option>
		<option style="background-image:url(male.png);" value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
	</select>
</form>
