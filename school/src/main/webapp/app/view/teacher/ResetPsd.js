/**
* @class School.view.teacher.ResetPsd
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 重置教师密码的Ext.Window
*/
Ext.define("School.view.teacher.ResetPsd", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.resetpsd",
	closable: true,
	modal: true,
	autoShow: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "重置密码",
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
				labelWidth: 100,
				afterLabelTextTpl : '<span style="color:red">*</span>', //必填项后面加个红星
				labelSeparator: ":",
				allowBlank: false,
				inputType: "password",
				msgTarget:"under",
				style: {
					marginTop: "20px"
				}
				
			},
			items: [

				{
					name: "userId",
					hidden: true,
					allowBlank: true,
					fieldLabel: "用户id"
				},
				{
					name: "adminPassword",
					fieldLabel: "管理员密码",
					maxLength:32
				},
				{
					name: "newPassword",
					fieldLabel: "新密码",
					maxLength:32
				},
				{
					name: "repeatPassword",
					fieldLabel: "确认新密码",
					maxLength:32
				},
			],

			buttonAlign: "center",
			
			//底部的按钮栏
			buttons : [
					{
						text : '保存',
						itemId: "save"
					}
			]
		}
	]
	
});