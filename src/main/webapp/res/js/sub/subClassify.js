$(function(){
	//加载二级分类
	$("#columnId").change(function(){
		var columnId = $("#columnId").val();
		if ('undefind' != columnId && columnId != "") {
			changeSubClassify(columnId);
		} else {
			$("select[name='classifyId']").empty();
			$("select[name='classifyId']").append("<option value=''>---请选择---</option>");
		}
	});
	//加载曲线
	$("select[name='classifyId']").change(function(){
		var classifyId = $("select[name='classifyId']").val();
		var columnId = $("#columnId").val();
		if (classifyId != "") {
			$("#chart").attr("src","/chartSearch/chartCreate?columnId="+columnId+"&classifyId="+classifyId);
			//window.open("/chartSearch/chartCreate?columnId="+columnId+"&classifyId="+classifyId);
		}
	});
	
});

function changeSubClassify(columnId) {
	$.ajax({
		type: "POST",
		url: "/productClassify/changeSubClassify",
		dataType : 'json',
		data: {
			columnId:columnId
		},
		beforeSend: function() {
			$("select[name='classifyId']").empty();
			$("select[name='classifyId']").append("<option value=''>---请选择---</option>");
		},
		success: function(data) {
			
			var coment = "";
			for (var one in data){
				coment += "<option value='"+data[one].id+"'>";
				coment += data[one].name;
				coment += "</option>";
			}
			
			$("select[name='classifyId']").append(coment);
		}
	});
}
