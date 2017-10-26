/**
* @class School.view.multimedia.UploadVideo
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 这是一个选择视频进行上传的Ext.Window
*/
Ext.define("School.view.multimedia.UploadVideo", {
	extend: "Ext.window.Window",
	title: "上传视频",
	closeAction: "destroy",
	alias: "widget.uploadvideo",
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
					xtype: "textfield",
					name: "name",
					allowBlank: true,
					fieldLabel: "视频名称",
				},
				{
					xtype: "filefield",
					name: "file",
					editable: false,
					allowBlank: false,
					afterLabelTextTpl : '<span style="color:red">*</span>',
					fieldLabel: "视频选择",
					buttonText: "浏览视频"
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