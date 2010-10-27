<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
 <body>
<s:form method="post" namespace="/core" action="szukaj!prosta.action">
<s:select label="Miejscowość:" name="miejscid" list="miejscowosci" listKey="idmiejsc" listValue="nazwa" emptyOption="true"/>
<s:select label="Kategoria:" name="katid" list="kategorie" listKey="idkategorie" listValue="nazwa" emptyOption="true"/>
<s:textfield label="max metrow od stoku" name="obiekt.odlStok" size="15" />
<s:textfield label="max metrow od lasu" name="obiekt.odlLas" size="15"/>
<s:textfield label="max cena" name="obiekt.cenaMax" size="15"/>
<s:checkboxlist name="zaznaczone" list="dodatkiWszystkie"
		listKey="idobiektydodatki" listValue="nazwa" />
<sx:submit key="submit_szukaj" targets="wysz"/>
</s:form>

<div id=wysz>
	<s:iterator value="obiekty">
		<s:property value="nazwa"/>
	</s:iterator>
</div>