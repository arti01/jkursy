<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertAttribute name="header" />
<body>
<table>
<tr>
<td>
<tiles:insertAttribute name="menu" />
</td><td>
<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="body2"/>
</td><td>
<tiles:insertAttribute name="menuP" />
</td>
</tr>
</table>
<tiles:insertAttribute name="footer" />
</body>
