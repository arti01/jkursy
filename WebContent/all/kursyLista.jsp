<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<ul>
<s:iterator value="kursy">
	<li>
		<s:url id="url1" namespace="/all" action="kursy" method="detale"><s:param name="kurs.idkursy" value="idkursy"/></s:url>
		<s:a href="%{url1}">
			<s:property value="nazwa"/>
		</s:a>
		od:<s:date name="dataOd" format="yyyy-MM-dd"/>
		do:<s:date name="dataDo" format="yyyy-MM-dd"/>
		<s:iterator value="wykladowcy">
			<s:property value="imieNazwisko"/>
		</s:iterator>
	</li>
</s:iterator>
</ul>