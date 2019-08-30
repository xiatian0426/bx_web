/***********产品树的初始化*****************/
var productItem ;
var departmentInfo;
var menuInfo;
var allDepartments;
var corsourceList;
function initData(productData, departmentData, menuData, allDepartment,corsourceList){
	productItem = productData;
	departmentInfo = departmentData;
	menuInfo = menuData;
	allDepartments = allDepartment;
	corsourceList=corsourceList;
}
//栏目树初始化设置
var setting1 = {
	check: {
		enable: true,
		chkboxType:{ "Y" : "ps", "N" : "ps" }
	},
	data:{
		simpleData: {
			enable: true,
			rootPId: '0'
		}
	},
	view: {
		showIcon: false,
		dblClickExpand: dblClickExpand
	},
	callback: {
		onCheck:onCheck
	}
};
//已选栏目树初始化设置
var setting2 = {
	check: {
		enable: false,
		chkboxType: { "Y": "", "N": "" }
	},
	data:{
		simpleData: {
			enable: false,
			rootPId: '0'
		}
	},
	view: {
		showIcon: false
	},
	callback: {
		beforeDblClick: removeProductItem
	}
};
//部门树初始化设置
var setting3 = {
	check: {
		enable: true,
		chkboxType:{ "Y" : "ps", "N" : "ps" }
	},
	data:{
		simpleData: {
			enable: true,
			rootPId: '0'
		}
	},
	view: {
		showIcon: false,
		dblClickExpand: dblClickExpand
	},
	callback: {
		onCheck:departmentCheck
	}
};
//已选部门树初始化设置
var setting4 = {
	check: {
		enable: false,
		chkboxType: { "Y": "", "N": "" }
	},
	data:{
		simpleData: {
			enable: false,
			rootPId: '0'
		}
	},
	view: {
		showIcon: false
	},
	callback: {
		beforeDblClick: removeDepartment
	}
};
//菜单树初始化设置
var setting5 = {
	check: {
		enable: true,
		chkboxType:{ "Y" : "ps", "N" : "ps" }
	},
	data:{
		simpleData: {
			enable: true,
			rootPId: '0'
		}
	},
	view: {
		showIcon: false,
		dblClickExpand: dblClickExpand
	},
	callback: {
		onCheck:menuCheck
	}
};
//已选菜单树初始化设置
var setting6 = {
	check: {
		enable: false,
		chkboxType: { "Y": "", "N": "" }
	},
	data:{
		simpleData: {
			enable: false,
			rootPId: '0'
		}
	},
	view: {
		showIcon: false
	},
	callback: {
		beforeDblClick: removeMenu
	}
};
//已选部门树初始化设置
var setting7 = {
	check: {
		enable: false,
		chkboxType: { "Y": "", "N": "" }
	},
	data:{
		simpleData: {
			enable: false,
			rootPId: '0'
		}
	},
	view: {
		showIcon: false
	},
	callback: {
		beforeDblClick: selectUserDepartment				}
};
function selectUserDepartment(treeId, treeNode) {
	$("#department").val(treeNode.name);
	$(".departmentTree").hide();
};
//已选角色树初始化设置
var setting8 = {
	check: {
		enable: false,
		chkboxType: { "Y": "", "N": "" }
	},
	data:{
		simpleData: {
			enable: false,
			rootPId: '0'
		}
	},
	view: {
		showIcon: false
	},
	callback: {
		beforeDblClick: selectCorsource			
		}
};
function selectCorsource(treeId, treeNode) {
	$("#corsource").val(treeNode.name);
	$(".corsourceTree").hide();
};
//删除已选产品节点
function removeProductItem(treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("selectProductInfo");
	treeObj.removeNode(treeNode);
	var productNodes = $.fn.zTree.getZTreeObj("productInfo");
	var productNode = productNodes.getNodeByParam("name", treeNode.name);
	//当双击移除,同时去掉左边的选中状态
	getAllChildrenNodes(productNodes, productNode);
};
//删除已选部门节点
function removeDepartment(treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("selectDepartmentInfo");
	treeObj.removeNode(treeNode);
	var productNodes = $.fn.zTree.getZTreeObj("departmentInfo");
	var productNode = productNodes.getNodeByParam("name", treeNode.name);
	//当双击移除,同时去掉左边的选中状态
	getAllChildrenNodes(productNodes, productNode);
};
//删除已选菜单节点
function removeMenu(treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("selectMenuInfo");
	treeObj.removeNode(treeNode);
	var productNodes = $.fn.zTree.getZTreeObj("menuInfo");
	var productNode = productNodes.getNodeByParam("name", treeNode.name);
	getAllChildrenNodes(productNodes, productNode);
};
//设置当前节点及其子节点都未选中
function getAllChildrenNodes(parents, treeNode){
	if (treeNode.isParent) {
		treeNode.checked = false;
		parents.updateNode(treeNode);
		var childrenNodes = treeNode.children;
		if (childrenNodes.length>0) {
			for (var i = 0; i < childrenNodes.length; i++) {
				childrenNodes[i].checked = false;
				parents.updateNode(childrenNodes[i]);
				if (childrenNodes[i].isParent) {
					//递归调用,循环当前 节点下班的所有子节点
					getAllChildrenNodes(parents, childrenNodes[i]);
				}
			}
		}
	}else{
		treeNode.checked = false;
		parents.updateNode(treeNode);
	}
}
//双击节点不展开
function dblClickExpand(treeId, treeNode) {
	return treeNode.level = 0;
};
function departmentCheck(){
	//加载已选部门树
	var treeObj = $.fn.zTree.getZTreeObj("departmentInfo");
	var nodes = treeObj.getCheckedNodes(true);
	var msg = "";
    for (var i = 0; i < nodes.length; i++) {
        msg += nodes[i].id+",";
    }
	$.ajax({
		url:"/account/department",
		data:{
			departments:msg
			},
		dataType:'json',
		type: 'post',
		cache: false,
		async: false,
		success: function(data){
			var treeObj = $.fn.zTree.getZTreeObj("selectDepartmentInfo");
			treeObj.destroy();
		  	$.fn.zTree.init($("#selectDepartmentInfo"), setting4, data);
		  	//展开树节点
		  	var treeObj = $.fn.zTree.getZTreeObj("selectDepartmentInfo");
		  	var allNodes = treeObj.getNodes();
		  	for (var i = 0; i < allNodes.length; i++) {
		  		treeObj.expandNode(allNodes[i], true, true, true, true);
	        }
		}
	});
}
function onCheck(){
	//加载已选产品树
	var treeObj = $.fn.zTree.getZTreeObj("productInfo");
	var nodes = treeObj.getCheckedNodes(true);
	var msg = "";
    for (var i = 0; i < nodes.length; i++) {
        msg += nodes[i].id+",";
    }
	$.ajax({
		url:"/account/hasChecked",
		data:{
			checked:msg
		},
		dataType:'json',
		type: 'post',
		cache: false,
		async: false,
		success: function(data){
			var productTreeObj = $.fn.zTree.getZTreeObj("selectProductInfo");
			productTreeObj.destroy();
			$.fn.zTree.init($("#selectProductInfo"), setting2, data);
			//展开树节点
			var productTreeObj = $.fn.zTree.getZTreeObj("selectProductInfo");
			var allNodes = productTreeObj.getNodes();
			for (var i = 0; i < allNodes.length; i++) {
				productTreeObj.expandNode(allNodes[i], true, true, true, true);
			}
		}
	});
}
function menuCheck(){
	//加载已选菜单树
	var treeObj = $.fn.zTree.getZTreeObj("menuInfo");
	var nodes = treeObj.getCheckedNodes(true);
	var msg = "";
    for (var i = 0; i < nodes.length; i++) {
        msg += nodes[i].id+",";
    }
	$.ajax({
		url:"/account/menu",
		data:{
			menuInfo:msg
		},
		dataType:'json',
		type: 'post',
		cache: false,
		async: false,
		success: function(data){
			var treeObj = $.fn.zTree.getZTreeObj("selectMenuInfo");
			treeObj.destroy();
		  	$.fn.zTree.init($("#selectMenuInfo"), setting6, data);
		  	//展开树节点
		  	var treeObj = $.fn.zTree.getZTreeObj("selectMenuInfo");
		  	var allNodes = treeObj.getNodes();
		  	for (var i = 0; i < allNodes.length; i++) {
		  		treeObj.expandNode(allNodes[i], true, true, true, true);
	        }
		}
	});
}
//页面加载设置
$(function(){
	//加载产品树
	//$.fn.zTree.init($("#productInfo"), setting1, productItem);
	//已选产品树
	//$.fn.zTree.init($("#selectProductInfo"), setting2);
	//价格数据管理
	//$.fn.zTree.init($("#priceDataManager"), setting1, productItem);
	//船期数据管理
	//$.fn.zTree.init($("#shipDataManager"), setting1, productItem);
	//部门权限管理
	//$.fn.zTree.init($("#departmentInfo"), setting3, departmentInfo);
	//$.fn.zTree.init($("#selectDepartmentInfo"), setting4);
	//菜单权限管理
	//$.fn.zTree.init($("#menuInfo"), setting5, menuInfo);
	//$.fn.zTree.init($("#selectMenuInfo"), setting6);
	//制定用户的部门
	//$.fn.zTree.init($("#userDepartment"), setting7, allDepartments);
	//$("#showLoginLog").hide();
	//$("#department").click(function(){
		//$(".departmentTree").show();
	//});
	//$("#departmentimg").click(function(){
		//$(".departmentTree").show();
	//});
	//角色树形
	$.fn.zTree.init($("#customerCorsource"), setting8, corsourceList);
	//$("#showLoginLog").hide();
	$("#corsource").click(function(){
		$(".corsourceTree").show();
	});
	$("#corsources").click(function(){
		$(".corsourceTree").show();
	});
});

//保存前验证
$(function(){
	var $ch_open=$(".check_open");
	$ch_open.click(function(){
		if($(this).is(":checked"))
		{
			$(this).parent().parent().next().show();
		}
		else{
			$(this).parent().parent().next().hide();
		}
	});
});
//取得所有的classId 并以,分开
function getAllClassId(nodes){
	var classIds = "";
	if(nodes.length > 0){
		for(var i=0;i<nodes.length;i++){
			classIds+=nodes[i].classId+",";
		}
	}
	return classIds;
}
//取得所有的classid 并以,分开
function getAllclassid(nodes){
	var classIds = "";
	if(nodes.length > 0){
		for(var i=0;i<nodes.length;i++){
			classIds+=nodes[i].classid+",";
		}
	}
	return classIds;
}
//取得所有id 并以,分开
function getAllId(nodes){
	var ids = "";
	if(nodes.length > 0){
		for (var i = 0; i < nodes.length; i++) {
	        ids += nodes[i].id+",";
	    }
	}
	return ids;
}
//重置 输入框中的信息
function perReset() {
	if (confirm("确认重置信息吗?")){
		//因为需要清除所有的权限等,所以采取重新加载
		 location.reload();
	}
}
function invalidationUserName(){
	//用户名是否存在  存在 false 不存在 true 
	var userName = $("#userName").val();
	var status = false;
	$.ajax({
		url:'/account/invalidateUserName',
		data:{
			userName:userName
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

function invalidateStaffName(){
	//用户名是否存在  存在 false 不存在 true 
	var staffName = $("#userId").val();
	var status = false;
	$.ajax({
		url:'/account/invalidateStaffName',
		data:{
			staffName:staffName
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
//保存前进行最后的验证
function save(){
	alert(1);
	//用户名
	var fullName = $("#fullName").val();
	if(fullName == ''){
		$("#fullName").validationEngine("showPrompt","用户名不能是空","error");
		$(this).focus();
		return false;
	}
	var userNameStatus = invalidationUserName();
	if(!userNameStatus){
		$("#userName").validationEngine("showPrompt","用户名已存在","error");
		$(this).focus();
		return false;
	}
	if(userName.match(/\D/)==null){
		$("#userName").validationEngine("showPrompt","用户名不能是纯数字","error");
		$(this).focus();
		return false;
	}
	var staffName = invalidateStaffName();
	if(!staffName){
		$("#userId").validationEngine("showPrompt","姓名已存在","error");
		$(this).focus();
		return false;
	}
	//部门不可空白
	/*var requiredDepartment = $("#department").val();
	if(requiredDepartment == ''||requiredDepartment == null){
		$("#department").validationEngine("showPrompt","此处不可空白","error");
		return false;
	}*/
	//角色不可以空白
	var requiredCorsource = $("#corsource").val();
	if(requiredCorsource == ''||requiredCorsource == null){
		$("#corsource").validationEngine("showPrompt","客户来源不能为空","error");
		return false;
	}
	//已选的产品 权限
	var productTreeObj = $.fn.zTree.getZTreeObj("productInfo");
	var productNodes = productTreeObj.getCheckedNodes(true);
	var products = getAllClassId(productNodes);
	//已选的部门权限
	var departmentTreeObj = $.fn.zTree.getZTreeObj("departmentInfo");
	var departmentNodes = departmentTreeObj.getCheckedNodes(true);
	var departments = getAllClassId(departmentNodes);
	//已选的菜单权限
	var menuTreeObj = $.fn.zTree.getZTreeObj("menuInfo");
	var menuNodes = menuTreeObj.getCheckedNodes(true);
	var menus = getAllclassid(menuNodes);
	$("#proSerc").val(products);
	$("#departSerc").val(departments);
	$("#menuSerc").val(menus);
	//选择 的ip地址
	if($("#ippbd").attr("checked")=="checked"){
		var ip = $("input[name='ipbands']").attr("checked", "checked");
		if(ip.length< 1){
			$("#ippbd").validationEngine("showPrompt","请选择绑定的ip","error");
			$(this).focus();
			return false;
		}
	}
	
	var treeObj = $.fn.zTree.getZTreeObj("productInfo");
	var nodes = treeObj.getCheckedNodes(true);
	if(nodes.length<1){
		alert('请选择产品权限！');
		return false;
	}
	var treeObj1 = $.fn.zTree.getZTreeObj("departmentInfo");
	var nodes1 = treeObj1.getCheckedNodes(true);
	if(nodes1.length<1){
		alert('请选择部门权限！');
		return false;
	}
	var treeObj2 = $.fn.zTree.getZTreeObj("menuInfo");
	var nodes2 = treeObj2.getCheckedNodes(true);
	if(nodes2.length<1){
		alert('请选择菜单权限！');
		return false;
	}
	
	var reSub = $("#reSubmit").val();
	if(reSub == 1){
		$("#reSubmit").val(2);
		return true;
	}else{
		return false;
	}
}
$(function(){
	$("#form2").validationEngine("attach",{ 
		promptPosition:"topRight", 
		scroll:false 
	});
	$("#userName").blur(function(){
		var userName = $(this).val();
		if(userName == null||userName==""){
			$("#userName").validationEngine("showPrompt","用户名不能为空","error");
		}
		if(userName.match(/\D/)==null){
			$("#userName").validationEngine("showPrompt","用户名不能是纯数字","error");
		}
		var userNameStatus = invalidationUserName();
		if(!userNameStatus){
			$("#userName").validationEngine("showPrompt","用户名已存在","error");
		}
	});
	$("#userId").blur(function(){
		var staffName = invalidateStaffName();
		if(!staffName){
			$("#userId").validationEngine("showPrompt","姓名已存在","error");
		}
	});
});