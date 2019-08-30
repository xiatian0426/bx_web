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
	<link rel="stylesheet" href="${cssRoot}/detail.css"/>
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
	.zhengwen { border-top: 1px solid #dcdddd;}
</style>			
<div class="main" style="width: 1000px;margin: 0 auto;">
  	<div class="lujing">
	  	<a href="#" style="font-size: 14px;border-left:5px solid #448aca">&nbsp;位置&nbsp;></a>
	   	<a href="/index/index" style="font-size: 14px;">首页&nbsp;></a>
	   	<a href="#" style="font-size: 14px;">信息详情页</a>
  	</div>
  	<div class="zhengwen">
  		<span class="tit">${messageData.title }</span>
  		<div class="sjdd"><font>${messageData.provincename }</font>&nbsp;&nbsp;&nbsp;<font>${messageData.messagename }</font>&nbsp;&nbsp;&nbsp;<font>${messageData.dateString }</font></div>
  		<div class="nrr">
  			${messageData.content }
  		</div>
  	</div>
</div>
</body>
</html>