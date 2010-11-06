<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<H3>menu</H3>
<ul>
	<li><s:url id="url1" namespace="/admin" action="index">
	</s:url><s:a href="%{url1}">
		<s:text name="administracja" />
	</s:a></li>
	<li><s:url id="url1" namespace="/wykladowcy" action="index">
	</s:url><s:a href="%{url1}">
		<s:text name="wykladowcy" />
	</s:a></li>
	<li><s:url id="url1" namespace="/all" action="index"
		method="logout"></s:url> <s:a href="%{url1}">
		<s:text name="wyloguj" />
	</s:a></li>
	<li><s:url id="url1" namespace="/all" action="index"
		method="zalozKonto"></s:url> <s:a href="%{url1}">
		<s:text name="załóż konto" />
	</s:a></li>
</ul>
<s:url id="url1" namespace="/all" action="kursy" method="list"></s:url>
<H3><s:a href="%{url1}">
	<s:text name="'kursy'" />
</s:a></H3>
<H3>statyczne</H3>
<ul>
	<s:iterator value="statyczne">
		<li><s:url id="url1" namespace="/all" action="index"
			method="statyczne">
			<s:param name="strona.idStatyczne" value="idStatyczne" />
		</s:url> <s:a href="%{url1}">
			<s:property value="tytul" />
		</s:a></li>
	</s:iterator>
</ul>
<s:if test="zalogowany.username!=null">
Jesteś zalogowany jako:<s:property value="zalogowany.username"/>(<s:property value="zalogowany.imieNazwisko"/>)
</s:if>



