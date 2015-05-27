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
		            	   $.post("http://localhost:8080/tplugin-services/customer/"+str1, { ff:"ff" },
		            				function(data){
		            					var dataObj=eval("("+data+")");
		            					
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
  <br>
	<h1>Press CTRL + V to get orderform info</h1>
 
	
</body>
</html>
