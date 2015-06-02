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
	<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	text-align:left;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
	<style type="text/css">
body {background:url(images/0452.jpg) repeat-x;text-align:center}

.upload{ margin:0 auto; width:800px; height:600px; background-color:#ffffff;} 
</style>
  </head>
  <script src="js/jquery-1.9.0.min.js"></script>
  <script type="text/javascript">
  $(document).ready(  
		    function(){  
// 		    	postOrderformNum();
		        document.onkeydown = function()  
		        {  
		            var oEvent = window.event;  
		            if (oEvent.keyCode == 86 && oEvent.ctrlKey) {  
// 		                alert("你按下了ctrl+c");  
		            	var str1=window.clipboardData.getData("text");  
		                alert(str1);
		                var regExp=/^\d+(\.\d+)?$/;
		               if(regExp .test (str1 ))
		               {
		            	   $.get("http://localhost:8080/tplugin-services/rest/customer/"+str1,
		            				function(data){
// 		            					var dataObj=eval("("+data+")");
										var html = "";
		            					$.each(data.itemInfoList,function(index,node){
		            						alert(node.orderNum);
		            						html += "<tr>"+
		            							"<td>"+node.proName+"</td>"+
		            							"<td>"+node.orderNum+"</td>"+
		            							"<td>"+node.shNum+"</td>"+
		            							"<td>"+node.bjNum+"</td>"+
		            							"<td>"+node.gzNum+"</td>"+
		            						"</tr>";
		            					});
		            					
		            					$("#orderformNum").html("阿里订单号：" + str1);
		            					$("#payAddress").html("买家地址：" + data.state);
		            					$("#orderformInfo table").append(html);
		            				});
		               	return true ;
		               }
		               else
		               {
		               alert ("this is not orderform number!");
		               return false ;
		               }
		            }  
		        }  
		    }  
		);
  
  function addTestData(){
	  var html = "";
// 		$.each(data.itemInfoList,function(index,node){
// 			alert(node.orderNum);
			html += "<tr>"+
				"<td>避孕套</td>"+
				"<td>33</td>"+
				"<td>11</td>"+
				"<td>11</td>"+
				"<td>11</td>"+
			"</tr>";
// 		});
		$("#orderformNum").html("阿里订单号：2323233");
		$("#payAddress").html("买家地址：" + "上海市外滩1号");
		$("#orderformInfo table").append(html);
  }
  
  function postOrderformNum(){
	  $.ajax({
		   type: "POST",
		   url: "http://localhost:8080/tplugin-services/customer/1056093233757093",
		   data: "name=John&location=Boston",
		   success: function(msg){
		     alert( "Data Ssaved: " + msg );
		   }
		});
  }
  </script>
 <!--  <body>
    This is my JSP page. <br>
  </body> -->
  
  <body>
  <%
String permission=request.getParameter("permission");
if (null != permission){
	if (!permission.equals("customer")){
		response.sendRedirect("index.jsp");
	    return;
	}
}else{
	response.sendRedirect("index.jsp");
    return;
}

%>


  <div class="upload">
  <br>
	<h1>Press CTRL + V to get orderform info</h1>
	<input type="button" value="测试" onclick="addTestData()"/>
 	<div id="orderformInfo">
 		<table class="gridtable">
 			<tr>
 				<th width="200"><p id="orderformNum">阿里订单号：</p></th>
 				<th colspan="4" width="300"><p id="payAddress">买家地址：</p></th>
 			</tr>
 			<tr>
 				<th colspan="5"><p>运算结果：</p></th>
 			</tr>
 			<tr>
 				<td><p>商品名称</p></td>
 				<td><p>购买数量</p></td>
 				<td><p>上海</p></td>
 				<td><p>北京</p></td>
 				<td><p>广州</p></td>
 			</tr>
 		</table>
 	</div>	
	</div>
</body>
</html>
