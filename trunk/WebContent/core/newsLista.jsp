<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
    <s:iterator value="newsy">
    <s:url id="url1" namespace="/core" action="news"><s:param name="news.idnews" value="%{idnews}"/></s:url>
		<s:a href="%{url1}">
			<h3><s:property value="nazwa"/></h3>
		</s:a>
		data wydarzenia:<s:property value="dataWydarzenia"/><br>
		miejscowość:<s:property value="miejsc.nazwa"/><br>
		dodany przez:<s:property value="user.nazwaWyswietlana"/><br>
		<s:property value="opisShort"/>
		<s:a href="%{url1}">
			wiecej...
		</s:a>
		<br><br>
    </s:iterator>