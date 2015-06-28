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
  <style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #ffffff;
	border-collapse: collapse;
	text-align:left;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
/* 	border-color: #666666; */
	background:#b5cfd2 url('images/cell-blue.jpg');
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
/* 	border-color: #666666; */
	background-color: #ffffff;
}
</style>
  <style type="text/css">
body {text-align:center;background-color:#2A8DDA;}

.upload{ margin:0 auto; width:600px; background-color:#ffffff;} 
</style>
 <!--  <body>
    This is my JSP page. <br>
  </body> -->
  
  <body>
    <br>
  <%
String permission=request.getParameter("permission");
if (null != permission){
	if (!permission.equals("admin")){
		response.sendRedirect("index.jsp");
	    return;
	}
}else{
	response.sendRedirect("index.jsp");
    return;
}

%>
	<div class="upload">

	<form action="rest/upload/excel" method="post" enctype="multipart/form-data">
 	<table class="gridtable">
 			<tr>
 				<th width="200">Select a table</th>
 				<th width="300">
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
		  </select> </p></th>
 			</tr>
 			<tr>
 				<td>Select a file : <input type="file" name="file" size="45" /></td>
 				<td><input type="submit" value="Upload It" /></td>
 			</tr>
 		</table>
 		</form>
<!-- 	<form action="rest/upload/excel" method="post" enctype="multipart/form-data"> -->
 
<!--  		<p> Select a table:</p> -->
<!-- 		 <select type="select" name="tableItem"> -->
		 
<!-- 		    <option value="sysMain"> -->
<!-- 		     SysMain表 -->
<!-- 		    </option> -->
<!-- 		    <option value="login"> -->
<!-- 		     Login表 -->
<!-- 		    </option> -->
<!-- 		     <option value="product"> -->
<!-- 		     Product表 -->
<!-- 		    </option> -->
<!-- 		     <option value="sendAllow"> -->
<!-- 		     SendAllow表 -->
<!-- 		    </option> -->
<!-- 		     <option value="sendOrder"> -->
<!-- 		     SendOrder表 -->
<!-- 		    </option> -->
<!-- 		  </select>  -->
 	
 
<!-- 	   <p> -->
<!-- 		Select a file : <input type="file" name="file" size="45" /> -->
<!-- 	   </p> -->
 
<!-- 	   <input type="submit" value="Upload It" /> -->
<!-- 	</form> -->
	</div>
	
 
</body>
</html>
