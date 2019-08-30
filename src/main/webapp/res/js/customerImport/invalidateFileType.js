function lastname(){
	 //获取欲上传的文件路径
	var filepath = $("input:file[name='file']").val();
	//为了避免转义反斜杠出问题，这里将对其进行转换
	var re = /(\\+)/g;
	var filename=filepath.replace(re,"#");
	//对路径字符串进行剪切截取
	var one=filename.split("#");
	//获取数组中最后一个，即文件名
	var two=one[one.length-1];
	//再对文件名进行截取，以取得后缀名
	var three=two.split(".");
	 //获取截取的最后一个字符串，即为后缀名
	var last=three[three.length-1];
	//添加需要判断的后缀名类型
	var tp ="xls,xlsx";
	//返回符合条件的后缀名在字符串中的位置
	var rs=tp.indexOf(last);
	//如果返回的结果大于或等于0，说明包含允许上传的文件类型
	if(rs>=0){
		return true;
	}else{
		alert("您选择的上传文件不是有效的文件类型");
		return false;
	}
}

function importCurFile(){
	var file = $("input:file[name='file']").val();
	if(""==file){
		alert("请选择要上传的文件!");
		return false;
	}
	return lastname();
}

//隐藏
function goDownloadErrorInfo() {
	$("#errorInfoDIV").hide();
}