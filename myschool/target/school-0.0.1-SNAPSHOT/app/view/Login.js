/**
* @class School.view.Login
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 登录界面的的Panel,
* 	事实上，真正的登录界面是login/login.html文件
* 	所以，是通过iframe把登录页面嵌进来的
*/
Ext.define("School.view.Login", {
	extend: "Ext.panel.Panel",
	requires: [
		"School.util.Util"
	],
	alias: "widget.login",
	width: "100%",
	height: School.util.Util.getInner().height,
	renderTo: Ext.getBody(),
	html: '<iframe src="login/login.html" id="login" frameborder=0 width=100% height=100%></iframe>'
});