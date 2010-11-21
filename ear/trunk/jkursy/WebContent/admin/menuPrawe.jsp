<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<H2>menu Prawe</H2>
<ul>
	<li><s:url id="url1" namespace="/admin" action="users"
		method="formAdmin">
		<s:param name="userImp.user.username" value="''" />
	</s:url> <s:a href="%{url1}">
		<s:text name="dodaj admina" />
	</s:a></li>
	<li><s:url id="url1" namespace="/admin" action="users"
		method="formKursant">
		<s:param name="userImp.user.username" value="''" />
	</s:url> <s:a href="%{url1}">
		<s:text name="dodaj kursanta" />
	</s:a></li>
	<li><s:url id="url1" namespace="/admin" action="users"
		method="formWyklad">
		<s:param name="userImp.user.username" value="''" />
	</s:url> <s:a href="%{url1}">
		<s:text name="dodaj wykładowcę" />
	</s:a></li>
	<li><s:url id="url1" namespace="/admin" action="kursy"
		method="form"></s:url> <s:a href="%{url1}">
		<s:text name="dodaj kurs" />
	</s:a></li>
	<li><s:url id="url1" namespace="/admin" action="statyczne"
		method="form"></s:url> <s:a href="%{url1}">
		<s:text name="dodaj statyczna" />
	</s:a></li>
</ul>


