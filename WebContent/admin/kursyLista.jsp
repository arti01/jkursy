<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
function takNie(){
		
	   return confirm('czy jestes pewien, ze chcesz tego?');
};
</script>
kursy :)

<ul>
<s:iterator value="kursy">
	<li>
		<s:url id="url1" namespace="/admin" action="kursy" method="form"><s:param name="kurs.idkursy" value="idkursy"/></s:url>
		<s:a href="%{url1}">
			<s:property value="nazwa"/>
		</s:a>
		<s:date name="dataOd" format="yyyy-MM-dd"/>
		<s:date name="dataDo" format="yyyy-MM-dd"/>
		<s:iterator value="wykladowcy">
		<s:property value="imieNazwisko"/>
		</s:iterator>
		<s:url id="url1" namespace="/admin" action="kursy" method="usun"><s:param name="kurs.idkursy" value="idkursy"/></s:url>
		<s:a href="%{url1}" onclick="return takNie()">
			<s:text name="usuń"/>
		</s:a>
	</li>
</s:iterator>
</ul>