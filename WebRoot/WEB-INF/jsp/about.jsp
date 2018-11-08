<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>关于</title>
<link rel="icon" href="img/icon.ico" type="image/x-icon"/>
<link rel="stylesheet" href="plugin/bootstrap-3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/public.css">
<link rel="stylesheet" href="css/searchMapPage.css">
<script type="text/javascript" src="plugin/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="plugin/bootstrap-table-1.11.1/bootstrap-table.js"></script>
<link href="plugin/bootstrap-table-1.11.1/bootstrap-table.css" rel="stylesheet" />
<script src="plugin/bootstrap-table-1.11.1/locale/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="js/userTreat.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/searchMapPage.js"></script>
</head>

<body>
	<c:set var="pagename" value="about" />
	<!-- 加载头  -->
	<%@include file="header.jsp"%>

	<div role="tabpanel" class="container" id="MapMana">
		<h3>关于</h3>
		<hr />
		<div style="font-size: 18px">
			<p>　　灿烂辉煌的人类文明、浩如烟海的古今文献以及广袤无垠的陆地海洋，存在着海量的与人类活动息息相关的地理信息。就单个人物来说，包括人物的籍贯、行迹、社会关系的地理分布；就群体来说，包括一个群体的地理分布和迁徙轨迹；就非生命的物体来说，也有其存在、分布和变化的地理区域；就一个地方来说，则又包含了既往时间里人、事、物等地理信息的总汇。</p>
			<p>　　学术地图发布平台为广大用户提供地理信息研究成果的发布、可视化分析及多功能查询服务，平台所形成的大数据，可以为未来科学研究、政府决策及社会服务提供重要的参考。</p>
			<br>
			<p style="text-align: right">浙江大学大数据与中国学术地图创新团队</p>
			<p style="text-align: right">2017年9月14日</p>
		</div>
	</div>
	<%@include file="loginModal.jsp"%>
	<%@include file="footer.jsp"%>
</body>

</html>