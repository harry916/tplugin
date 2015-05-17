<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
 <!--  <body>
    This is my JSP page. <br>
  </body> -->
  
  <body>
  This is my JSP page. <br>
	<h1>File Upload with Jersey</h1>
 
	<form action="rest/upload/excel" method="post" enctype="multipart/form-data">
 
 		<p> Select a table:</p>
		 <select type="select" name="tableItem">
		 
		    <option value="sysMain">
		     SysMain表
		    </option>
		    <option value="login">
		     Login表
		    </option>
		     <option value="product">
		     Product表
		    </option>
		     <option value="sendAllow">
		     SendAllow表
		    </option>
		     <option value="sendOrder">
		     SendOrder表
		    </option>
		  </select> 
 	
 
	   <p>
		Select a file : <input type="file" name="file" size="45" />
	   </p>
 
	   <input type="submit" value="Upload It" />
	</form>
 
</body>
</html>
