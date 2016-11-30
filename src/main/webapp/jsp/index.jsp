<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/mytaglib" prefix="ck"%>

<t:layout>
	<jsp:attribute name="pageTitleFrag">
		SSME Index 
	</jsp:attribute>
	<jsp:attribute name="localCssFrag">
		<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/drrList.css" /> --%>
	</jsp:attribute>
	<jsp:attribute name="localJsFrag">
		<%--<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/scripts/drrList.js"></script>--%>	
	</jsp:attribute>
	<jsp:body>
	<form name="mvcForm" action="sendEmail.do" method="post">
		
	<h2>Hello ${user.name}, from ${user.email}</h2>
	<input id="btnOK" class="button-primary" type="submit">Submit</a>
	<a href="index.do" id="btnCancel" class="button-secondary">Cancel</a>
	<ck:token/>
	</form>
	</jsp:body>
</t:layout>
