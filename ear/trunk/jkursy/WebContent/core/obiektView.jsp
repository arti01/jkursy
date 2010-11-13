<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<H2>Strona obiektu</H2>

<s:text name="ob.nazwa"/>:
<s:property value="obiekt.nazwa"/><br>
<s:text name="ob.kategoria"/>:
<s:property value="obiekt.obiektKategoria.nazwa"/><br>
<s:text name="ob.opis"/>:
<s:property value="obiekt.opis"/><br>
<s:text name="ob.cenaMin"/>:
<s:property value="obiekt.cenaMin"/><br>
<s:text name="ob.cenaMax"/>:
<s:property value="obiekt.cenaMax"/><br>
<s:text name="ob.iloscMiejsc"/>:
<s:property value="obiekt.iloscMiejsc"/><br>
<s:text name="ob.dodatki"/>:
<s:iterator value="obiekt.dodatki"><s:property value="nazwa"/>-</s:iterator>
<br>
<s:text name="ob.odlStok"/>:
<s:property value="obiekt.odlStok"/><br>
<s:text name="ob.odlLas"/>:
<s:property value="obiekt.odlLas"/><br>
<s:text name="ob.odlBankomat"/>:
<s:property value="obiekt.odlBankomat"/><br>
<s:text name="ob.odlPrzejscieGraniczne"/>:
<s:property value="obiekt.odlPrzejscieGraniczne"/><br>
<s:text name="ob.odlSklep"/>:
<s:property value="obiekt.odlSklep"/><br>
<s:text name="ob.panstwo"/>:
<s:property value="obiekt.panstwo"/><br>
<s:text name="ob.miejscowosc"/>:
<s:property value="obiekt.miejsc.nazwa"/>
<s:url id="url1" namespace="/core" action="miejsc"><s:param name="miejsc.idmiejsc" value="%{obiekt.miejsc.idmiejsc}"/></s:url>
	<s:a href="%{url1}">wiecej...</s:a>
<br>
<s:text name="ob.ulicaNumerDomu"/>:
<s:property value="obiekt.ulicaNumerDomu"/><br>
<s:text name="ob.tel1"/>:
<s:property value="obiekt.tel1"/><br>
<s:text name="ob.tel2"/>:
<s:property value="obiekt.tel2"/><br>
<s:text name="ob.kodPocztowy"/>:
<s:property value="obiekt.kodPocztowy"/><br>
<s:text name="ob.gg"/>:
<s:property value="obiekt.gg"/><br>
<s:text name="ob.www"/>:
<s:property value="obiekt.www"/><br>
<s:text name="ob.skype"/>:
<s:property value="obiekt.skype"/>

wyslij mail
<s:form method="post" namespace="/core" action="obiekt!sendMail.action">
<s:actionerror />
<s:textfield label="Twoj adres email" name="adres" size="30"/>
<s:textarea label="wpisz tresc" name="tresc" rows="5" cols="30"/>
<s:checkbox label="czy wyslac kopie do ciebie" name="doSiebie"/>
<s:submit key="submit_Dodaj"/>
</s:form>

<br>
<h3>wydarzenia w poblużu obiektu:</h3>
<s:iterator value="obiekt.miejsc.newsy">
    <s:url id="url1" namespace="/core" action="news"><s:param name="news.idnews" value="%{idnews}"/></s:url>
		<s:a href="%{url1}">
			<h3><s:property value="nazwa"/></h3>
		</s:a>
		data wydarzenia:<s:property value="dataWydarzenia"/><br>
		miejscowość:<s:property value="miejsc.nazwa"/><br>
		dodany przez:<s:property value="user.nazwaWyswietlana"/><br>
		<s:property value="opisShort"/>
		<s:a href="%{url1}">
			wiecej...
		</s:a>
		<br><br>
    </s:iterator>

<s:iterator value="obiekt.foty">
<h3><s:property value="nazwa"/></h3>
<s:property value="opis"/><br>
	<s:url id="url4" namespace="/core" action="obiekt" method="obraz" includeParams="none">
		<s:param name="fota.id_foty" value="%{id_foty}" />
	</s:url>
	<img alt="fotaMini" src="<s:property value="%{url4}"/>">
	<br>
</s:iterator>