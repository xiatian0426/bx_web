<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>中项招标网</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="${jsRoot}/jquery-1.11.1.min.js"></script>
	<link rel="stylesheet" href="${cssRoot}/main.css"/>
</head>
<body>
<style>
	.lujing{
		height: 50px;
		line-height: 60px;
	}
	.lujing a{
		color: #000000;
	}
	.content { border-top: 1px solid #dcdddd; padding-top: 122px; padding-left:260px;}
	.content h3 { width: 295px; margin: auto; padding-left: 155px;  height: 78px; font-size: 18px; color: #666666; font-weight: normal; line-height: 44px;}
	.content h3 a { line-height: 22px; color: #0e8bcf; width: 60px; font-size: 14px;}
	.content h3 a:hover { text-decoration: underline;}
</style>			
<jsp:include  page="../def/top.jsp"/>
<div class="main" style="height: 600px;width: 1000px;margin: 0 auto; background:#ecebeb;">
	<div class="lujing">
	  	<a href="#" style="font-size: 14px;border-left:5px solid #448aca">&nbsp;位置&nbsp;></a>
	   	<a href="/index/index" style="font-size: 14px;">首页&nbsp;></a>
	   	<a href="#" style="font-size: 14px;">信息详情页</a>
  	</div>
	<div class="content clearfix" style="text-align:center;">
		<h2 style="float:left;">
			<img src="${imageRoot }/timg.jpg" width="150px;" height="150px;"/>
		</h2>
		<h3 style="float:left; padding-left:30px; line-height:34px; padding-top:40px;">
			此服务需要升级VIP会员!
		</h3>
	</div>
</div>
<jsp:include  page="../def/footer.jsp"/>
</body>
</html>