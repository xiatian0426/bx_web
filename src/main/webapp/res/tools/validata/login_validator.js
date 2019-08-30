function validateMobile(field,rules,i,options){
	var returnValue = "";
	if (/^(((13[0-9]{1})|(18[0-9]{1})|159|153)+\d{8})$/.test($("#mobile").val())){
		returnValue = true;
	}else{
		returnValue = "* 手机号码格式不符";
	}
	return returnValue;
}
function validataRePass(field,rules,i,options){
	var returnValue = "";
	var passwd = $("#passwd").val();
	var repasswd = $("#repasswd").val();
	if (passwd != repasswd){
		returnValue = "* 两次密码输入不一致";
	}else{
		returnValue = true;
	}
	return returnValue;
}

function validataUserId(field,rules,i,options){
	var returnValue = "";
	$.ajax({
		url:webRoot + "user/checkUser.json",
		data : {
			userkey : $("#userid").val()
		},
		dataType : 'json',
		type : 'get',
		cache : false,
		async : false,
		success : function(data) {
			if (data.flag == true) {
				returnValue = "* 此用户已存在";
			}else{
				returnValue = true;
			}
		}
	});
	return returnValue;
}