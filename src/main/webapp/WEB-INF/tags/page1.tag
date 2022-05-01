<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" >

<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag body-content="scriptless"%>

<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ attribute name="header1" required="true" type="java.lang.String"%>
 --%>
 <!-- ==================================================================== -->
<%@tag import="java.util.Calendar"%>
<%@ tag body-content="scriptless"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@tag import="org.slf4j.Logger"%>
<%@tag import="org.slf4j.LoggerFactory"%>
<%@tag import="org.slf4j.Logger"%>
<%@ attribute name="header1" required="true" type="java.lang.String"%>











<!-- like page.tag file   -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    
    
    
    
</head>
<body>
 This is from page1.tag 00
  
  
 <jsp:doBody />
  
  
  
  
  
  
  
  
  
  
  
  
  
  
</body>
</html>
