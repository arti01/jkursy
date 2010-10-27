<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
function takNie(){
	   return confirm('czy jestes pewien, ze chcesz tego?');
};
</script>

<h3>Strony statyczne</h3>

<ul>
<s:iterator value="statyczne">
	<li>
		<s:url id="url1" namespace="/admin" action="statyczne" method="form"><s:param name="strona.idStatyczne" value="idStatyczne"/></s:url>
		<s:a href="%{url1}">
			<s:property value="tytul"/>
		</s:a>
		<s:url id="url1" namespace="/admin" action="statyczne" method="usun"><s:param name="strona.idStatyczne" value="idStatyczne"/></s:url>
		<s:a href="%{url1}" onclick="return takNie()">
			<s:text name="usuń"/>
		</s:a>
		<s:form method="post" namespace="/admin" action="statyczne!zmienLp.action" theme="simple">
		<s:hidden name="strona.idStatyczne" value="%{idStatyczne}"/>
		pozycja w menu:<s:select name="strona.lp" list="lpAll" onchange="submit();" value="lp"/>
		</s:form>
	</li>
</s:iterator>
</ul>