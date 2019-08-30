/**
 * 
 * @param pageTarget
 * @param pageInfo
 */

function page(fromTarget, pageInfo, pageTarget) {
	var pageCount = parseInt(pageInfo.pageCount);
	var pageStr = "<ul class='pagination'>";
	pageStr += "<li onclick=\"setPageIndex("+1+",\'"+fromTarget+"\')\"><a href='javascript:void(0);'>首页</a></li>";
	var start = 1;
	var end = 1;
	if (parseInt(pageInfo.currentPage)-2 >=1 ) {
		start = parseInt(pageInfo.currentPage)-2;
	} else {
		start = 1;
	}
	if (parseInt(pageInfo.currentPage)+2 <=pageCount) {
		end = parseInt(pageInfo.currentPage)+2;
	} else {
		end = pageCount;
	}
	for (var index=start;index<=end;index++) {
		if (parseInt(pageInfo.currentPage) == index) {
			pageStr += "<li class='active'><a href='javascript:void(0);'>"+index+"</a></li>";
		} else {
			pageStr += "<li onclick=\"setPageIndex("+index+",\'"+fromTarget+"\')\"><a href='javascript:void(0);'>"+index+"</a></li>";
		}
	}
	pageStr += "<li onclick=\"setPageIndex("+pageInfo.pageCount+",\'"+fromTarget+"\')\"><a href='javascript:void(0);'>末页</a></li>";
	pageStr += "<li><a href='javascript:void(0);' style='cursor: not-allowed;'>共 "+pageInfo.recordCount+" 条记录</a></li>";
	pageStr += "<li><a href='javascript:void(0);' style=\"height: 32px;\" align=\"center\">跳转到 <input id='jumpIndex' type=\"text\" style=\"width: 30px;height: 18px;\" />页" +
			"&nbsp;&nbsp;<button id='btn' style=\"height: 21px;\" >GO</button></a></li>";
	pageStr += "</ul>";
	pageStr += "<input type='hidden' name='pageIndex' id='pageIndex'/>";
	$("." +pageTarget).html(pageStr);
	
	document.getElementById('btn').onclick=function(){
		var jumpIndexObj = document.getElementById("jumpIndex");
		var jumpIndexStr = jumpIndexObj.value;
		var reg = /^[0-9]*[1-9][0-9]*$/g;
		if (reg.test(jumpIndexStr)) {
			var jumpIndex = parseInt(jumpIndexStr);
			if (jumpIndex > pageCount) {
				jumpIndex = pageCount;
			}
			$("#pageIndex").val(jumpIndex);
			$("#"+fromTarget).submit();
		}
	};
}

function setPageIndex(pageIndex,fromTarget) {
	$("#pageIndex").val(pageIndex);
	$("#"+fromTarget).submit();
}
