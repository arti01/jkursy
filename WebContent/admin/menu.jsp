<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<H2>menu</H2>
<ul>
	<li><s:url id="url1" namespace="/admin" action="users"
		method="listAdmin"></s:url> <s:a href="%{url1}">
		<s:text name="lista adminów" />
	</s:a></li>
	<li><s:url id="url1" namespace="/admin" action="users"
		method="listWyklad"></s:url> <s:a href="%{url1}">
		<s:text name="lista wykładowców" />
	</s:a></li>
	<li><s:url id="url1" namespace="/admin" action="users"
		method="listKursant"></s:url> <s:a href="%{url1}">
		<s:text name="lista kursantów" />
	</s:a></li>
	<li><s:url id="url1" namespace="/admin" action="kursy"
		method="listKursy"></s:url> <s:a href="%{url1}">
		<s:text name="lista kursów" />
	</s:a></li>
	<li><s:url id="url1" namespace="/admin" action="statyczne"
		method="list"></s:url> <s:a href="%{url1}">
		<s:text name="strony statyczne" />
	</s:a></li>
</ul>
<a href="<%=request.getContextPath()%>/">do strony głównej</a>

