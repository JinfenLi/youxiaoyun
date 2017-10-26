/**
* @class School.view.User
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 显示和修改当前登录用户信息的的面板
*/
Ext.define("School.view.User", {

	extend: "Ext.window.Window",

	requires: [
		"Ext.draw.Text",
		"Ext.tab.Panel",
		"Ext.form.Panel",
	],

	layout: "fit",
	autoShow: true,
	draggable: false,
	resizable: false,
	alias: "widget.user",
	title: "修改个人信息",

	items: [
		{
			xtype: "tabpanel",
			width : 350,
			height : 350,
			activeTab : 0,
			//tabPosition : "left",
			items: [

				//个人资料

				{
					xtype : "form",
					itemId: "userinfo",
					title : "个人信息",
					bodyPadding : "10 20",
					defaultType : "textfield",
					defaults : {
						labelWidth : "40%",
						msgTarget : "under",
						allowBlank : false,
						anchor : "-20",
						margin : "12px 0"
					},
					items : [
					//"id", "name", "idcard", "email", "phone", "picUrl", "sex"
						{
							fieldLabel : "id",
							name : "id",
							hidden: true
						}, 
						{
							fieldLabel : "picUrl",
							name : "picUrl",
							hidden: true
						}, 
						{
							fieldLabel : "姓名",
							allowBlank: false,
							name : "name",
						}, {
							fieldLabel : "email",
							name : "email"
						}, {
							xtype : "radiogroup",
							fieldLabel : "性别",
							name: "sex",
							itemId: "sex",
							anchor : "100%",
							items : [{
										boxLabel : "男",
										inputValue : "男",
										name : "sex"
										,
									}, {
										boxLabel : "女",
										name : "sex",
										inputValue : "女"
									}]
						}, 
						{
							fieldLabel : "教工号",
							name : "idcard",
							disabled: true
						}, 
						{
							fieldLabel : "电话号码",
							// disabled: true,
							name : "phone",
							stripCharsRe : /[^0-9]/
						},
					],
					
					// Save the modified information
					buttonAlign : "center",
					buttons : [{
						xtype : "button",
						itemId: "saveInfo",
						text : "保存",
						scale : "medium"
					}]
				},


				//修改密码
				{
					xtype : "form",
					itemId: "userpass",
					title : "修改密码",
					bodyPadding : "30 30 10 30",
					fieldDefaults : {
						labelWidth : 80,
						msgTarget : "under"
					},
					defaults : {
						anchor : "100%",
						inputType : "password",
						margin : "20 0",
						allowBlank : false
					},
					defaultType : "textfield",
					items : [
						{
							xtype : "text",
							text : "你正在修改密码...",
							anchor : "100%",
							border : "0 0 1 0",
							margin : "0 0 20 0",
							padding : "0 0 10 0",
							style : {
								borderColor : "#333",
								borderStyle : "dashed"
							}
						}, {
							fieldLabel : "旧密码",
							name : "password",
							allowBlank: false
							
						}, {
							fieldLabel : "新密码",
							name : "newPassword",
							allowBlank: false
							
						}, {
							fieldLabel : "确认新密码",
							allowBlank: false,													
							name : "newPasswordCfm"
							
						}
					],

					buttonAlign: "center",
					buttons : [
						{
							text : "确认修改",
							scale : "medium",
							itemId: "savePass",
							margin : "0 20"
						}
					]
						
				}, 


				//修改用户头像
				{
					xtype : "form",
					title : "头像",
					itemId: "userpic",
					bodyPadding : "10, 50",
					defaults : {
						padding : "10, 0"
						,
					},

					items : [
						
					],

					buttonAlign: "center",

					buttons : [{
						text : "上传",
						itemId: "savePic"						
					}]
				}
			]
		}
	]

}); 
