<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<sx:head/>
<body>
<s:form method="post" namespace="/admin" action="users!dodaj.action">
<s:actionerror />
	<s:textfield label="login" name="user.username" size="70" readonly="zmien"/>
	<s:textfield label="email" name="user.email"  value="%{user.email}" size="70" readonly="%{zmianaHasla}"/>
	<s:if test="%{(!zmien) or (zmianaHasla)}">
		<s:password label="haslo" name="user.userpass" value="%{user.userpass}" size="70"/>
		<s:password label="haslo ponownie" name="haslo2" value="%{user.userpass}" size="70"/>
	</s:if>
	<s:else>
		<s:hidden name="user.userpass" value="%{user.userpass}"/>
		<s:hidden name="haslo2" value="%{user.userpass}"/>
	</s:else>
	<s:textfield label="imię i nazwisko" name="user.imieNazwisko" size="70" readonly="%{zmianaHasla}"/>
	<s:textarea label="opis (ważne dla wykładowcy)" name="user.opis" rows="10" cols="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="ulica" name="user.ulica" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="miasto" name="user.miasto" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="nip" name="user.nip" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="telefon" name="user.tel1" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="data ostatniej modyfikacji" key="user.data_zmiany" value="%{getText('format.daty',{user.data_zmiany})}" readonly="true"/>
	<s:checkboxlist name="zaznaczone" list="roleAll"
		listKey="rola" listValue="opis" />
	<s:if test="%{!prawo.equals('admin')}">
	<s:label value="Pokazuje wyłącznie kursy o dacie końca większej niż bieżąca"/>
	<s:checkboxlist name="zaznaczoneKursy" list="kursyAll"
		listKey="idkursy" listValue="nazwa" labelposition="top" label="Kursy"/>
		<s:url id="url1" namespace="/admin" action="kursy" method="form"/>
	</s:if>
	<s:hidden name="zmien" value="%{zmien}"/>
	<sx:submit validate="true" key="submit_Dodaj"/>
	<s:reset key="submit_wyjdz" onclick="history.go(-1)"/>
</s:form>
</body>