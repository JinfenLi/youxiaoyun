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
				style: {
					marginTop: "20px"
				}
				
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
				},
				// , ,  , ,  , 
				//"picUr"sex":"男","subject":"","tScClassId":"a6a91ed97b9e44e9bc499ccaba0e0276","tScGradeId":"","tScSchoolId":"578f59be8a78426cb10578e9ecc2583e"}
			
				{
					fieldLabel: "姓名",
					name: "name"
				},
			
				{
					xtype: "radiogroup",
					//itemId: "range",
					allowBlank: true,
					itemId: "sex",
					name: "sex",
					fieldLabel: "性别",
					columns: 2,
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

				// {
				// 	xtype: "radiogroup",
				// 	itemId: "isAuthc",
				// 	allowBlank: true,
				// 	afterLabelTextTpl: "",
				// 	name: "isAuthc",
				// 	fieldLabel: "管理员",
				// 	columns: 2,
				// 	labelAlign: "left",
				// 	items: [
				// 		{
				// 			boxLabel: "是",
				// 			name: "isAuthc",
				// 			inputValue: "是",
				// 		},
				// 		{
				// 			boxLabel: "否",
				// 			name: "isAuthc",
				// 			inputValue: "否"
				// 		}
						
				// 	]
				// },
				// {
				// 	name: "birthday",
				// 	xtype: "datefield",
				// 	editable: false,
				// 	format: "Y-m-d",
				// 	fieldLabel:  "出生日期"
				// },
				// {
				// 	fieldLabel: "学历",
				// 	allowBlank: true,
				// 	afterLabelTextTpl: "",
				// 	name: "education"
				// },
				// {
				// 	fieldLabel: "所教课程",
				// 	name: "subject"
				// },
				{
					fieldLabel: "联系电话",
					name: "phone"
				},
				{
					fieldLabel: "电子邮箱",
					allowBlank: true,
					afterLabelTextTpl: "",
					name: "email"
				}
			],
			//底部的按钮栏
			buttons : [
					{
						text : '保存',
						itemId: "save"
					},
					{
						text : '清空',
						itemId: "reset"
					}, 
					{
						text : '取消',
						itemId: "cancel"
					}
			]
		}
	]
	
});