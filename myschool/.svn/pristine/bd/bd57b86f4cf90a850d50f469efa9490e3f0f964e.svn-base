/**
* @class School.view.exam.UploadScore
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 上传成绩的Ext.Window 
*/
Ext.define("School.view.exam.UploadScore", {
	extend: "Ext.window.Window",
	title: "上传成绩",
	closeAction: "destroy",
	alias: "widget.uploadscore",
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
						//glyph: 0xf093,
						itemId: "save"
					},
					{
						text : '取消',
						//glyph: 0xf0e2,
						itemId: "cancel"
					}
			]
		}
	]

});