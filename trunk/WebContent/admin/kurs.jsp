<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<sx:head extraLocales="pl-pl"/>
<s:actionerror/>
<s:fielderror/>
<table border="1">
<s:form method="post" namespace="/admin" action="kursy!dodaj.action" theme="simple">
<tr><td >
	nazwa<br>
	<s:hidden name="kurs.idkursy" value="%{kurs.idkursy}"/>
	<s:textfield label="nazwa" name="kurs.nazwa" size="130"/>
	</td></tr><tr><td>
	<s:if test="zmien">
	od:<s:textfield key="kurs.dataOd" label="data od" value="%{getText('format.daty',{kurs.dataOd})}"/>
	do:<s:textfield name="kurs.dataDo" label="data do" value="%{getText('format.daty',{kurs.dataDo})}"/>
	</s:if>
	<s:else>
	od:<s:textfield key="kurs.dataOd" label="data od" value="RRRR-MM-DD"/>
	do:<s:textfield name="kurs.dataDo" label="data do" value="RRRR-MM-DD"/>
	</s:else>
	</td></tr><tr><td>
	krótki opis<br>
	<s:textarea label="krotki opis" name="kurs.opisKrotki" rows="5" cols="130"/>
	</td></tr>
<tr><td><s:fielderror fieldName="kurs.opis"/></td></tr>
<tr height="200px" valign="top"><td>
	<s:textarea id="editor1" name="kurs.tresc" rows="10" cols="70" theme="simple"/>
	<script type="text/javascript">CKEDITOR.replace( 'editor1' );</script>
</td></tr><tr><td>
	<s:hidden name="zmien" value="%{zmien}"/>
	<s:submit key="submit_Dodaj"/>
	<s:reset key="submit_wyjdz" onclick="history.go(-1)"/>
	</td></tr>
</s:form>
</table>