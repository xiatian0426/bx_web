$(function(){
	var zhezhao = document.getElementById("zhezhao");
	var login = document.getElementById("login");

	$("#bt").click(function() {
		zhezhao.style.display = "block";
		login.style.display = "block";
	});
	$("#uploadSuccess").click(function() {
		zhezhao.style.display = "none";
		login.style.display = "none";
		window.open("/","_self");
	});
}) 