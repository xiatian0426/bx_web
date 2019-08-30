<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="http://localhost:8080/res/js/jquery-1.11.1.min.js"></script>
	<link rel="stylesheet" href="${cssRoot}/main.css"/>
    <script type="text/javascript">
        function submitForm(pageIndex, pageSize) {
            alert(0);
            alert(1);
                $.ajax({
                    url : '/homePage/editMovieInfo',
                    type : 'post',
                    data : {
                        id:1,
                        file:$("#file")[0].files[0]
                    },
                    processData : false,
                    contentType : false,
                    success : function(value) {
                        var result = JSON.parse(value);
                        if (result == 'true') {
                            alert(222);
                        } else {
                            Lobibox.alert('error', {msg : "媒资信息更新失败!!!"});
                        }
                    }
                });
            }

        //图片回显:
        function preview(file) {
            $("#imgHidden").css("display", "none");
            var prevDiv = document.getElementById('preview');
            if (file.files && file.files[0]) {
                var reader = new FileReader();
                reader.onload = function(evt) {
                    prevDiv.innerHTML = '<img style="width: 100px;height: 100px;" src="' + evt.target.result + '" />';
                }
                reader.readAsDataURL(file.files[0]);
            } else {
                prevDiv.innerHTML = '<div class="img" style="width: 100px;height:100px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' +
                    file.value + '\'"></div>';
            }
        }
    </script>
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
			您还不是会员，请先<a href="/account/goRegister">注册</a>!<br />如已注册，请<a href="/account/login">登录</a>！
		</h3>
	</div>
    <!-- 图片文本框 -->
    <input type="file" class="form-control" id="file" name="file" th:onchange="javascript:preview(this)">

    <!-- 这个是在上传之前回显图片图片展示 -->
    <div id="preview">
        　　<!--这个是为了将页面返回的图片展示出来的.默认隐藏-->
        　　<img style="width: 100px; height: 100px;display:none" id="imgHidden" />
    </div>

    <!-- 提交...这里pageIndex和pageSize可传可不传,主要取决于提交之后是否需要回到当前页面. -->
    <button type="submit" onclick="submitForm(1,2)" class="btn btn-primary">提交</button>
</div>
</body>
</html>