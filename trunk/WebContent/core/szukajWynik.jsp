<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<table border="2">
	<s:iterator value="obiekty" status="stat">
	<tr>
	<td>
	<s:property value="nazwa"/><br>
	<s:text name="ob.panstwo"/>:<s:property value="panstwo"/><br>
	<s:text name="ob.miejsc"/>:<s:property value="miejsc.nazwa"/><br>
	<s:text name="ob.kategoria"/>:<s:property value="obiektKategoria.nazwa"/><br>
	<s:text name="ob.tel1"/>:<s:property value="tel1"/><br>
	<s:text name="ob.tel2"/>:<s:property value="tel2"/><br>
	<s:text name="ob.www"/>:<s:property value="www"/><br>
	<s:text name="ob.email"/>:<s:property value="user.email"/><br>
	<s:text name="ob.tel1"/>:<s:property value="tel1"/><br>
	<s:text name="ob.cenaMin"/>:<s:property value="cenaMin"/><br>
	<s:text name="ob.cenaMax"/>:<s:property value="cenaMax"/><br>
	</td>
	<td>
	<s:url id="url1" namespace="/core" action="obiekt"><s:param name="obiekt.idobiekty" value="%{idobiekty}"/></s:url>
	<s:a href="%{url1}">wiecej...</s:a><br>
	<s:if test="foty.iterator.hasNext">
	<s:url id="url4" namespace="/core" action="obiekt" method="obrazMini" includeParams="none">
		<s:param name="fota.id_foty" value="%{foty.iterator.next.id_foty}" />
	</s:url>
	<img alt="fotaMini" src="<s:property value="%{url4}"/>">
	<br>
	</s:if>
	</td>
	</tr>
	</s:iterator>
	</table>