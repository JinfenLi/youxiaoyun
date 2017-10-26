/**
* @class School.view.school.AddStudent
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新增学生或者修改学生的Ext.Window
*/

Ext.define("School.view.school.AddStudent", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.addstudent",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "新增学生",
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
				labelWidth: 90,
				afterLabelTextTpl : '<span style="color:red">*</span>', 
				labelSeparator: ":",
				allowBlank: false,
				style: {
					marginTop: "20px"
				}
				
			},
			items: [
				{
					name: "studentId",
					hidden: true,
					fieldLabel: "学生id",
					allowBlank: true
				},
				{
					name: "parentId",
					hidden: true,
					fieldLabel: "家长id",
					allowBlank: true
				},
				{
					name: "studentIDCard",
					afterLabelTextTpl : ' ',
					fieldLabel: "学生学号",
					allowBlank: true
				},
			
				{
					name: "studentName",
					fieldLabel: "学生姓名"
				},
				{
					xtype: "radiogroup",
					//itemId: "range",
					allowBlank: true,
					itemId: "student_sex",
					name: "studentGender",
					fieldLabel: "学生性别",
					columns: 2,
					labelAlign: "left",
					items: [
						{
							boxLabel: "男",
							name: "studentGender",
							inputValue: "男"
						},
						{
							boxLabel: "女",
							name: "studentGender",
							inputValue: "女"
						}
						
					]
				},

				{
					name: "studentAddress",
					afterLabelTextTpl : ' ',
					allowBlank: true,
					fieldLabel: "学生住址"
				},
				{
					name: "studentPhone",
					afterLabelTextTpl : ' ',
					allowBlank: true,
					fieldLabel: "固定电话"
				},
				{
					name: "parentName",
					afterLabelTextTpl : ' ',
					allowBlank: true,
					fieldLabel: "监护人姓名"
				},
				
				
				
				{
					name: "parentPhone",
					// readOnly: true,
					afterLabelTextTpl : ' ',
					fieldLabel: "监护人手机"
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