<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<sx:head />
<body>
<s:form method="post" namespace="/admin" action="users!dodaj.action">
	<s:actionerror />
	<s:textfield label="login" name="userImp.user.username" size="70"
		readonly="zmien" />
	<s:textfield label="email" name="userImp.user.email" value="%{userImp.user.email}"
		size="70" readonly="%{zmianaHasla}" />
	<s:if test="%{(!zmien) or (zmianaHasla)}">
		<s:password label="haslo" name="userImp.user.userpass"
			value="%{userImp.user.userpass}" size="70" />
		<s:password label="haslo ponownie" name="haslo2"
			value="%{userImp.user.userpass}" size="70" />
	</s:if>
	<s:else>
		<s:hidden name="userImp.user.userpass" value="%{userImp.user.userpass}" />
		<s:hidden name="haslo2" value="%{userImp.user.userpass}" />
	</s:else>
	<s:textfield label="imię i nazwisko" name="userImp.user.imieNazwisko" size="70"
		readonly="%{zmianaHasla}" />
	<s:textarea label="opis (ważne dla wykładowcy)" name="userImp.user.opis"
		rows="10" cols="70" readonly="%{zmianaHasla}" />
	<s:textfield label="ulica" name="userImp.user.ulica" size="70"
		readonly="%{zmianaHasla}" />
	<s:textfield label="miasto" name="userImp.user.miasto" size="70"
		readonly="%{zmianaHasla}" />
	<s:textfield label="nip" name="userImp.user.nip" size="70"
		readonly="%{zmianaHasla}" />
	<s:textfield label="telefon" name="userImp.user.tel1" size="70"
		readonly="%{zmianaHasla}" />
	<s:textfield label="data ostatniej modyfikacji" key="userImp.user.dataZmiany"
		value="%{getText('format.daty',{userImp.user.dataZmiany})}" readonly="true" />
	<s:checkboxlist name="zaznaczone" list="roleAll" listKey="rola"
		listValue="opis" />
	<s:if test="%{!prawo.equals('admin')}">
		<s:label
			value="Pokazuje wyłącznie kursy o dacie końca większej niż bieżąca" />
		<s:if test="%{kursyAll.size()>0}">
			<s:checkboxlist name="zaznaczoneKursy" list="kursyAll"
				listKey="idkursy" listValue="nazwa" labelposition="top"
				label="Kursy" />
			<s:url id="url1" namespace="/admin" action="kursy" method="form" />
		</s:if>
		<s:else><s:label value="brak kursów"/></s:else>
	</s:if>
	<s:hidden name="zmien" value="%{zmien}" />
	<sx:submit validate="true" key="submit_Dodaj" />
	<s:reset key="submit_wyjdz" onclick="history.go(-1)" />
</s:form>
</body>