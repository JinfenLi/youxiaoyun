/**
* @class School.view.multimedia.AddVideolib
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新增或者修改视频库的Ext.Window
*/
Ext.define("School.view.multimedia.AddVideolib", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.addvideolib",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "创建视频库",
	autoShow: true,
	items: [
		{
			xtype: "form",
			border: false,
			style: {
				padding: "10px"
			},
			layout: {
				type: "anchor"
			},
			defaults: {
				xtype: "textfield",
				anchor: "100%",
				labelWidth: 80,
				afterLabelTextTpl : '<span style="color:red">*</span>', 
				labelSeparator: ":",
				allowBlank: false,
				msgTarget:"under",
				style: {
					marginTop: "20px"
				}
				
			},

			items: [
				{
					name: "schoolId",
					hidden: true,
					fieldLabel: "学校id",
					allowBlank: true
				},
				{
					name: "clazzId",
					hidden: true,
					fieldLabel: "班级id",
					allowBlank: true
				},
				{
					name: "name2",
					fieldLabel: "视频库名称",
					maxLength: 25
				},
				{
					name: "description2",
					allowBlank: true,
					afterLabelTextTpl :"",
					fieldLabel: "视频库描述",
					maxLength: 32
				},
				{
					name: "comment",
					allowBlank: true,
					afterLabelTextTpl :"",
					fieldLabel: "备注",
					xtype:"textarea",
					maxLength:"64"
				}
			],
			//底部的按钮栏
			buttons : [
					{
						text : '保存',
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