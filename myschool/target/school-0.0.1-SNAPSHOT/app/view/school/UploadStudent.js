/**
* @class School.view.school.UploadStudent
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 选择excel文件进行上传学生的Ext.Window
*/

Ext.define("School.view.school.UploadStudent", {
	extend: "Ext.window.Window",
	title: "导入学生",
	closeAction: "destroy",
	alias: "widget.uploadstudent",
	modal:true,
	layout: {
		type: "fit"
	},
	width: 350,
	items: [
		{
			xtype: "form",
			//standarSubmit: true,
			defaults: {
				width: 300,
				labelWidth: 70,
				margin: 10
			},
			items: [
				{
					xtype: "filefield",
					name: "file",
					allowBlank: false,
					fieldLabel: "文件选择",
					buttonText: "浏览文件"
				}
			],
			//底部的按钮栏
			buttons : [
					{
						text : '上传',
						itemId: "save"
					},
					{
						text : '取消',
						itemId: "cancel"
					}
			]
		}
	]

});