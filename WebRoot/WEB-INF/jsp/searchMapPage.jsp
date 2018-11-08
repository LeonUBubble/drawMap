<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>查找地图</title>
<link rel="icon" href="./img/icon.ico" type="image/x-icon"/>
<link rel="stylesheet"
	href="plugin/bootstrap-3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/public.css">
<link rel="stylesheet" href="css/searchMapPage.css">
<script type="text/javascript" src="plugin/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<script src="plugin/bootstrap-table-1.11.1/bootstrap-table.js"></script>
<link href="plugin/bootstrap-table-1.11.1/bootstrap-table.css"
	rel="stylesheet" />
<script
	src="plugin/bootstrap-table-1.11.1/locale/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="js/userTreat.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/searchMapPage.js"></script>
</head>

<body>
	<c:set var="pagename" value="searchMap" />
	<!-- 加载头  -->
	<%@include file="header.jsp"%>

	<div role="tabpanel" class="container" id="MapMana">
		<h3>查询条件</h3>
		<hr />
		<form id="formSearch" class="form-horizontal">
			<div class="form-group">
				<label class="control-label col-sm-3" for="map_txt_mapname">地图名</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="map_txt_mapname">
				</div>
				<div class="col-sm-3" style="text-align: left;">
					<button type="button" id="map_btn_query"
						class="btn btn-primary">查询</button>
				</div>
			</div>
		</form>
		<table class="table" id="tb_maps">
		</table>

	</div>
	<%@include file="footer.jsp"%>

	<%@include file="loginModal.jsp"%>
</body>

</html>