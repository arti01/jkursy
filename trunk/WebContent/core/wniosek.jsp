<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<body>
<s:form method="post" namespace="/core" action="wniosek!dodaj.action">
	<s:actionerror />
	<s:textfield label="login - nadany automatycznie" name="user.username" value="%{loginNew}" size="70" readonly="true"/>
	<s:textfield label="email" name="user.email"  value="%{user.email}" size="70" readonly="%{zmianaHasla}"/>
	<s:hidden name="user.userpass" value="%{loginNew}"/>
	<s:hidden name="haslo2" value="%{loginNew}"/>
	<s:textfield label="nazwa użytkownika" name="user.nazwa" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="nazwa wyswietlana" name="user.nazwaWyswietlana" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="ulica" name="user.ulica" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="miasto" name="user.miasto" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="nip" name="user.nip" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="telefon" name="user.tel1" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="osoba kontaktowa" name="user.osobaKontaktowa" size="70" readonly="%{zmianaHasla}"/>
	<s:textfield label="nazwa obiektu" name="obiekt.nazwa" size="70" />
	<s:select name="kategorieid" list="kategorie" listKey="idkategorie" listValue="nazwa"/>
	<s:textarea label="opis" name="obiekt.opis" cols="70" rows="15" />
	<s:checkboxlist name="zaznaczone" list="dodatkiWszystkie"
		listKey="idobiektydodatki" listValue="nazwa" />
	<s:textfield label="ilosc miejsc" name="obiekt.iloscMiejsc" size="15" />
	<s:textfield label="od stoku" name="obiekt.odlStok" size="15" />
	<s:textfield label="od sklepu" name="obiekt.odlSklep" size="15"/>
	<s:textfield label="od lasu" name="obiekt.odlLas" size="15"/>
	<s:textfield label="od bankomatu" name="obiekt.odlBankomat" size="15"/>
	<s:textfield label="od przejscia granicznego" name="obiekt.odlPrzejscieGraniczne" size="15"/>
	<s:textfield label="min cena noclegu" name="obiekt.cenaMin" size="15"/>
	<s:textfield label="max cena noclegu" name="obiekt.cenaMax" size="15"/>
	<s:select list="panstwa" label="panstwo" name="obiekt.panstwo" />
	<s:select label="Miejscowości: wybierz z listy" name="miejscid" list="miejscowosci" listKey="idmiejsc" listValue="nazwa" emptyOption="true"/>
	<s:textfield label=" lub wprowadz nową" name="miejscowosc" size="70" />
	<s:textfield label="ulica/numer domu" name="obiekt.ulicaNumerDomu" size="70" />
	<s:textfield label="telefon 1" name="obiekt.tel1" size="70" />
	<s:textfield label="telefon2" name="obiekt.tel2" size="70" />
	<s:textfield label="kod pocztowy" name="obiekt.kodPocztowy" size="70" />
	<s:textfield label="gg" name="obiekt.gg" size="70" />
	<s:textfield label="skype" name="obiekt.skype" size="70" />
	<s:textfield label="www" name="obiekt.www" value="http://www.naprzyklad.com.pl" onfocus="this.value='http://'" size="70" />
	<sx:submit validate="true" key="submit_Dodaj"/>
</s:form>
</body>