/**
* @class School.view.multimedia.UploadMultiPhoto
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 多图上传的Ext.Window,注意：
	这是通过iframe嵌入fileUpload/index.html的
*/
Ext.define("School.view.multimedia.UploadPhoto", {
	extend: "Ext.window.Window",
	title: "上传照片",
	closeAction: "destroy",
	alias: "widget.uploadphoto",
	modal:true,
	layout: {
		type: "fit"
	},
	width: 350,
	items: [
		{
			xtype: "form",
			defaults: {
				width: 300,
				labelWidth: 70,
				margin: 10
			},
			items: [
				{
					xtype: "textfield",
					name: "name",
					allowBlank: true,
					fieldLabel: "照片名称"
				},
				{
					xtype: "filefield",
					name: "files",
					allowBlank: false,
					fieldLabel: "照片选择",
					buttonText: "浏览照片"
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