<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
kursy detale:)
<br>
nazwa:
<H2><s:property value="kurs.nazwa"/></H2>
krótki opis:
<H3><s:property value="kurs.opisKrotki"/></H3>
od:<s:property value="kurs.dataOd"/>do:<s:property value="kurs.dataDo"/><br>
wykładowcy:<br>
<s:iterator value="kurs.wykladowcy">
	<s:property value="imieNazwisko"/><br>
</s:iterator>
opis:<br>
<s:text name="kurs.tresc"/>