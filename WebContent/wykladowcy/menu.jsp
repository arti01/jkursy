<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<H2>menu</H2>
<ul>
	<li><s:url id="url1" namespace="/all" action="index">
	</s:url><s:a href="%{url1}">
		<s:text name="strona glowna" />
	</s:a></li>
	<li><s:url id="url1" namespace="/all" action="index"
		method="logout"></s:url> <s:a href="%{url1}">
		<s:text name="wyloguj" />
	</s:a></li>
</ul>


