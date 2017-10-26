/*
	path: view.clazz.health.UploadHealthWin
  author:
	description:
*/
Ext.define("School.view.clazz.health.UploadHealthWin", {
	extend: "Ext.window.Window",
	alias: "widget.uploadhealthwin",

	title: "上传班级健康表",
	closeAction: "destroy",
	autoShow: true,
	modal: true,
	width: 350,

	layout: {
		type: "fit"
	},

	items: [{
		xtype: "form",
		defaults: {
			width: 300,
			labelWidth: 70,
			margin: 10
		},
		items: [{
			xtype: "filefield",
			name: "file",
			allowBlank: false,
			fieldLabel: "文件选择",
			buttonText: "浏览文件"
		}],

		//底部的按钮栏
		buttons: [{
			text: '上传',
			itemId: "save"
		//}, {
		//	text: '取消',
		//	itemId: "cancel"
		}]
	}]

});