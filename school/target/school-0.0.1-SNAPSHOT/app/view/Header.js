/**
* @class School.view.Header
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 窗口顶部的用来图标菜单的部分
* 	图标菜单是后台返回来动态生成的，
*/
Ext.define("School.view.Header", {
	extend: "Ext.toolbar.Toolbar",
	alias: "widget.appheader",
	height: 100,
	cls: 'appheader',
	requires: [
		'Ext.form.Label'
	]
});