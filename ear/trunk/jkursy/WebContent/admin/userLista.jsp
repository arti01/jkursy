<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
function takNie(){
	   return confirm('czy jestes pewien, ze chcesz tego?');
};
</script>
	<s:if test="asc">
	<s:url id="url1" namespace="/admin" action="users" method="sortList">
		<s:param name="asc" value="false"/>
		<s:param name="sortTyp" value="'imieNazwisko'"/>
		</s:url>
	<s:a href="%{url1}">sort nazwa</s:a>
	</s:if><s:else>
	<s:url id="url1" namespace="/admin" action="users" method="sortList">
		<s:param name="sortTyp" value="'imieNazwisko'"/>
		<s:param name="asc" value="true"/></s:url>
	<s:a href="%{url1}">sort nazwa</s:a>
	</s:else>

	<s:if test="asc">
	<s:url id="url1" namespace="/admin" action="users" method="sortList"><s:param name="asc" value="false"/><s:param name="sortTyp" value="'dataZmiany'"/></s:url>
	<s:a href="%{url1}">sort data zm</s:a>
	</s:if><s:else>
	<s:url id="url1" namespace="/admin" action="users" method="sortList"><s:param name="asc" value="true"/><s:param name="sortTyp" value="'dataZmiany'"/></s:url>
	<s:a href="%{url1}">sort data zm</s:a>
	</s:else>
<ul>
<s:iterator value="users">
	<li>
	<s:property />
	<s:if test="%{prawo.equals('kursant')}">
		<s:url id="url1" namespace="/admin" action="users" method="formKursant">
		<s:param name="user.username" value="%{username}"/></s:url>
	</s:if>
	<s:elseif test="%{prawo.equals('admin')}">
		<s:url id="url1" namespace="/admin" action="users" method="formAdmin">
		<s:param name="userImp.user.username" value="username"/></s:url>
	</s:elseif>
	<s:else>
		<s:url id="url1" namespace="/admin" action="users" method="formWyklad">
		<s:param name="user.username" value="%{username}"/></s:url>
	</s:else>
		<s:a href="%{url1}">
			<s:property value="imieNazwisko"/>
		</s:a>
		<s:url id="url1" namespace="/admin" action="users" method="formAdmin"><s:param name="user.username" value="%{username}"/><s:param name="zmianaHasla" value="true"/></s:url>
		<s:a href="%{url1}">
			<s:text name="zmien haslo" />
		</s:a>
		<s:if test="login!=username">
			<s:url id="url1" namespace="/admin" action="users" method="usun"><s:param name="user.username" value="%{username}"/></s:url>
			<s:a href="%{url1}" onclick="return takNie()" >
				<s:text name="usun" />
			</s:a>
		</s:if>
		<s:else><s:text name="nie usuwamy samego siebie"/></s:else>
	</li>
</s:iterator>
</ul>