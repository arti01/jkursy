<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<sx:head extraLocales="pl-pl"/>
<body>
<s:actionerror />
<s:form method="post" namespace="/admin" action="statyczne!dodaj.action" theme="simple">
<table border="1">
<tr><td>
	<s:hidden name="strona.idStatyczne" value="%{strona.idStatyczne}"/>
	<s:fielderror fieldName="strona.tytul"/>
	tytuł:<s:textfield name="strona.tytul" size="70" theme="simple"/><br><br>
</td></tr><tr><td>
	<s:fielderror fieldName="strona.opis"/>
	opis:<s:textfield name="strona.opis" size="70" theme="simple"/><br><br>
</td></tr>
<tr><td><s:fielderror fieldName="strona.tresc"/></td></tr>
<tr height="200px" valign="top"><td>
	<s:textarea id="editor1" name="strona.tresc" rows="10" cols="70" theme="simple"/>
	<script type="text/javascript">CKEDITOR.replace( 'editor1' );</script>
</td></tr><tr><td>
	<s:hidden name="zmien" value="%{zmien}"/>
	<s:submit key="submit_Dodaj" theme="simple"/>
	<s:reset key="submit_wyjdz" onclick="history.go(-1)" theme="simple"/>
</td></tr></table>
</s:form>
</body>