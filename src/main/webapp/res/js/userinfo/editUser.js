function goEditUserInfo(userId){
	var openUrl = "/user/goEdit?userId="+userId;//弹出窗口的url
	var iWidth=1000; //弹出窗口的宽度;
	var iHeight=500; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	var opener= window.open(openUrl,"","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft+",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no"); 
}

//验证用户名称
function validateUserName (oldName) {
	//用户名是否存在  存在 false 不存在 true 
	var status = false;
	var userName=$("#userName").val();
	$.ajax({
		url:'/user/validateUserName',
		data:{
			newUserName:userName,
			oldUserName:oldName
		},
		dataType:'json',
		type:'post',
		cache:false,
		async:false,
		success:function(data) {
			status = data;
		},
		error : function(){
			status = false;
		}
	});
	return status;
}

function save(){
	//用户名
	var oldUserName = $("#oldUserName").val();
	var userName = $("#userName").val();
	if(userName == ''){
		$("#userName").validationEngine("showPrompt","登录名称不能是空","error");
		$(this).focus();
		return false;
	}else if (validateUserName (oldUserName)) {
		$("#userName").validationEngine("showPrompt","登录名称已存在","error");
		$(this).focus();
		return false;
	}
	return true;
}

//禁用或者启用用户
function userUseOrNot (userId) {
	//用户名是否存在  存在 false 不存在 true 
	var html = $("#useOrNot_"+userId).html();
	var changeHtml = "";
	var status = "";
	var buttonClass = "";
	var removerClass = "";
	if (html == '禁用') {
		changeHtml = "启用";
		status = "0";
		buttonClass = "btn-warning";
		removerClass = "btn-danger";
	} else if (html == '启用') {
		changeHtml = "禁用";
		status = "1";
		buttonClass = "btn-danger";
		removerClass = "btn-warning";
	}
	$.ajax({
		url:'/user/userUseOrNot',
		data:{
			userId:userId,
			status:status
		},
		dataType:'json',
		type:'post',
		cache:false,
		async:false,
		success:function(data) {
			if(data) {
				$("#useOrNot_"+userId).html(changeHtml);
				$("#useOrNot_"+userId).removeClass(removerClass);
				$("#useOrNot_"+userId).addClass(buttonClass);
				alert("用户已被"+html);
			} else {
				alert(html + " 失败");
			}
		},
		error : function(){}
	});
}

