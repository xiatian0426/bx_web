
/**
 * 获取是否可用于下载错误信息,显示错误信息下载的链接
 */
function Push(url) {
	$.ajax({
		type: "POST",
		url: url,
		dataType : 'json',
		/*data: {
			databaseCode:databaseCode,
			industry:industry
		},*/
		beforeSend: function() {},
		success: function(data) {
			//处理完成
			if (data.complete == "1") {
				$('#btn').trigger("click");
				$("#wait").hide();
				//处理成功
				if (data.ressult == "1") {
					$("#success").show();
					$("#uploadSuccess").removeAttr("disabled");
				//处理失败
				} else {
					$("#uploadSuccess").removeAttr("disabled");
					$("#download").show();
					var errorList = data.errorList;
					var coment = "";
					for (var one in errorList){
						coment += "<tr>";
						coment += "<td align='center' height='33' width='170'>";
						coment += errorList[one].error + "</td>";
						coment += "</tr>";
					}
					$("#errorTable").append(coment);
				}
			}
		}
	});
}