<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<h3>lista miejscowosci</h3>
<s:iterator value="miejscowosci">
	<s:url id="url1" namespace="/core" action="miejsc">
		<s:param name="miejsc.idmiejsc" value="%{idmiejsc}" />
	</s:url>
	<s:a href="%{url1}">
		<s:property value="nazwa" />
	</s:a>
	<br />
</s:iterator>