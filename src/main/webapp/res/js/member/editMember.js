function goEditMember(userId){
	var openUrl = "/member/goEdit?userId="+userId;//弹出窗口的url
	if(userId==''){
		openUrl = "/member/goAdd";//弹出窗口的url
	}
	var iWidth=700; //弹出窗口的宽度;
	var iHeight=500; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	var opener= window.open(openUrl,"","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft+",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no"); 
}

//验证用户名
function validateUserId (userid) {
	//用户名是否存在  存在 false 不存在 true 
	var status = true;
	$.ajax({
		url:'/member/validateUserId',
		data:{
			userid:userid
		},
		dataType:'json',
		type:'post',
		cache:false,
		async:false,
		success:function(data) {
			status = data;
		},
		error : function(){
			status = true;
		}
	});
	return status;
}

function save(){
	var oldUserid = $("#oldUserid").val().trim();
	//新增客户验证用户名
	if(oldUserid==''){
		//用户名
		var userid = $("#userid").val().trim();
		if(userid == ''){
			$("#userid").validationEngine("showPrompt","用户名不能是空","error");
			$(this).focus();
			return false;
		}else if (validateUserId (userid)) {
			$("#userid").validationEngine("showPrompt","用户名已存在","error");
			$(this).focus();
			return false;
		}
	}
	var pwd1 = $("#pwd1").val().trim();
	var pwd2 = $("#pwd2").val().trim();
	if(pwd1!=''){
		if(pwd1!=pwd2){
			$("#pwd2").validationEngine("showPrompt","两次密码不一样","error");
			$(this).focus();
			return false;
		}
	}
	var phone = $("#phone").val().trim();
	var tel = $("#tel").val().trim();
	if(phone=='' && tel==''){
		$("#phone").validationEngine("showPrompt","手机或座机至少填一个","error");
		$("#phone").focus();
		return false;
	}
	if(phone!=''){
		 var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
		 if (!reg.test(phone)) {
			 $("#phone").validationEngine("showPrompt","手机有误","error");
				$(this).focus();
				return false;
		 }
	}
	if(tel!=''){
		var reg = /^0\d{2,3}-?\d{7,8}-\d{3,5}$/;
		 var reg2 = /^0\d{2,3}-?\d{7,8}$/;
		 var reg3 = /^\d{3,8}$/;
		 if (!reg.test(tel)&&!reg2.test(tel)&&!reg3.test(tel)) {
			 $("#tel").validationEngine("showPrompt","座机有误","error");
				$(this).focus();
				return false;
		 }
	}
	return true;
}
