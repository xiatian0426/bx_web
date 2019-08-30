//获取input的所有id
var user = document.getElementById("user");
var pwd = document.getElementById("pwd");
var surePwd = document.getElementById("surePwd");
var prompt = document.getElementById("prompt");
function submitB() {
	if (!user.value) {
		prompt.style.fontSize = "13px";
		prompt.style.width = "31%";
		prompt.style.height = "2em";
		prompt.style.textAlign = "center";
		prompt.style.lineHeight = "3em";
		prompt.innerHTML = '请填写您的用户名';
		user.focus();
		return false;
	}
	if (!pwd.value) {
		prompt.style.fontSize = "13px";
		prompt.style.width = "31%";
		prompt.style.height = "2em";
		prompt.style.textAlign = "center";
		prompt.style.lineHeight = "3em";
		prompt.innerHTML = '请填写您的用户密码';
		pwd.focus();
		return false;
	}
	if (!surePwd.value) {
		prompt.style.fontSize = "13px";
		prompt.style.width = "31%";
		prompt.style.height = "2em";
		prompt.style.textAlign = "center";
		prompt.style.lineHeight = "3em";
		prompt.innerHTML = '请填写您的登录验证';
		surePwd.focus();
		return false;
	} 
}
function changeImg() {
	$("#validatecodeimg").attr("src", "/account/verifycode?"+ Math.random())
}
