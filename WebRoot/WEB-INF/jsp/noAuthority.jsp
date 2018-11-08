<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>错误页面</title>
</head>

<body>
<div>
	<h1>出错啦</h1>
	<br\>
	<p>该地图未通过审核，您将无法查看该地图~</p>
	<br\>
	3s后将自动跳转到首页~
</div>
</body>

<script type="text/javascript"> 
	onload=function(){ 
		setTimeout(go, 5000); 
	}; 
	function go(){ 
		location.href="http://localhost:8090/AncientMap/index.action"; 
	} 
</script> 

</html>
