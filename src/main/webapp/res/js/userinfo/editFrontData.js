function goEditFrontData(id){
	var openUrl = "/frontData/goEdit?id="+id;//弹出窗口的url
	var iWidth=1000; //弹出窗口的宽度;
	var iHeight=500; //弹出窗口的高度;
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	var opener= window.open(openUrl,"","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft+",toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no"); 
}

function deleteFrontData (id,isdelete) {
	$.ajax({
		url:'/frontData/deleteFrontData',
		data:{
			id:id,
			isdelete:isdelete
		},
		dataType:'json',
		type:'post',
		cache:false,
		async:false,
		success:function(data) {
			if(data) {
				alert("操作成功");
				location.reload();
			} else {
				alert("操作失败");
			}
		},
		error : function(){}
	});
}

