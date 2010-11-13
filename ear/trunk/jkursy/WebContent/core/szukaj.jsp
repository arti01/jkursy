<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<body>

<s:form method="post" namespace="/core" action="szukaj!prosta.action">
<s:select label="Miejscowość:" name="miejscid" list="miejscowosci" listKey="idmiejsc" listValue="nazwa" emptyOption="true"/>
<s:select label="Kategoria:" name="katid" list="kategorie" listKey="idkategorie" listValue="nazwa" emptyOption="true"/>
<sx:submit key="submit_szukaj" targets="wysz"/>
</s:form>
<s:url id="url1" namespace="/core" action="szukaj" method="szukajZaaw"></s:url>
<s:a href="%{url1}">
	<s:text name="wyszukiwarka zaawansowana" />
</s:a>
<br>
<div id=wysz>
	<s:iterator value="obiekty">
		<s:property value="nazwa"/>
	</s:iterator>
</div>