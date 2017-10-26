/**
* @class School.view.teacher.AddTeahcer
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新增教师或者编辑教师时弹出来的Ext.Window
*/

Ext.define("School.view.teacher.AddTeacher", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.addteacher",
	closable: true,
	modal: true,
	autoShow: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "增加教师",
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
				msgTarget: "under",
				style: {
					marginTop: "20px"
				},
				msgTarget: 'side'
				
			},
			items: [
				{
					fieldLabel: "教师id",
					hidden: true,
					allowBlank: true,
					name: "id"
				},
				{
					fieldLabel: "班级id",
					hidden: true,
					allowBlank: true,
					name: "tScClassId"
				},
				{
					fieldLabel: "年级id",
					allowBlank: true,
					hidden: true,
					name: "tScGradeId"
				},
				{
					fieldLabel: "学校id",
					allowBlank: true,
					hidden: true,
					name: "tScSchoolId"
				},
				{
					fieldLabel: "教工号",
					name: "idcard",
					maxLength:18,
					regex: /^[0-9]*$/,
					regexText: "请输入纯数字",
					itemId: 'idCard',
				},
				{
					fieldLabel: "姓名",

					name: "name",
					maxLength: 25,
					itemId: 'teacherName',
				},
			
				{
					xtype: "radiogroup",
					//itemId: "range",
					itemId: "sex",
					name: "sex",
					fieldLabel: "性别",
					columns: 2,
					blankText:"该输入项为必输项",
					labelAlign: "left",
					items: [
						{
							boxLabel: "男",
							name: "sex",
							inputValue: "男"
						},
						{
							boxLabel: "女",
							name: "sex",
							inputValue: "女"
						}
						
					]
				},
				{
					fieldLabel: "联系电话",
					name: "phone",
					maxLength: 16,
					itemId: 'phone',
				},
				{
					fieldLabel: "电子邮箱",
					allowBlank: true,
					afterLabelTextTpl: "",
					name: "email",
					regex: /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/,
					regexText: "请输入正确格式的邮箱",
					maxLength:32,
					itemId: 'email'
				}
			],
			//底部的按钮栏
			buttons : [
					{
						text : '保存',
						itemId: "save"
					},
					{

				/*		text : '清空',
						itemId: "teacherReset"
					}, 
					{
*/
						text : '取消',
						itemId: "cancel"
					}
			]
		}
	]
	
});