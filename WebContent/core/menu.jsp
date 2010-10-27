<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<H2>menu</H2>
<s:if test="login!=null">
	Jesteś zalogowany jako:<s:text name="%{login}"/>
</s:if>
<s:else>jesteś wylogowany</s:else>
<ul>
<li>
<s:url id="url1" namespace="/core" action="szukaj"></s:url>
<s:a href="%{url1}">
	<s:text name="wyszukiwarka" />
</s:a>
</li>
<li>
<s:url id="url1" namespace="/admin" action="index" method=""></s:url>
<s:a href="%{url1}">
	<s:text name="admini" />
</s:a>
</li>
<li>
<s:url id="url1" namespace="/user" action="index" method=""></s:url>
<s:a href="%{url1}">
	<s:text name="userzy" />
</s:a>
</li>
<s:if test="login==null">
<li>
<s:url id="url1" namespace="/core" action="wniosek" method="form"></s:url>
<s:a href="%{url1}">
	<s:text name="zloz.wniosek" />
</s:a>
</li>
</s:if>
<s:if test="login!=null">
	<li>
	<s:url id="url1" namespace="/core" action="index" method="logout"></s:url>
	<s:a href="%{url1}">
		<s:text name="wyloguj" />
	</s:a>
	</li>
</s:if>
</ul>
