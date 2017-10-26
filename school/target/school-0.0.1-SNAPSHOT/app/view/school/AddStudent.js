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
				msgTarget:"under",
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
					allowBlank: true,
					maxLength:20,
					emptyText:"字数不能超过20"
				},
			
				{
					name: "studentName",
					fieldLabel: "学生姓名",
					maxLength:25
				},
				{
					xtype: "radiogroup",
					//itemId: "range",
					// allowBlank: true,
					itemId: "student_sex",
					name: "studentGender",
					fieldLabel: "学生性别",
					columns: 2,
					labelAlign: "left",
					blankText:"该输入项为必输项",
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
					fieldLabel: "学生住址",
					maxLength:64,
					emptyText:"字数不能超过64"
				},
				{
					fieldLabel: "出生日期",
					xtype: 'datefield',
					allowBlank: true,
					itemId: 'student-Birthday',
					format: 'Y-m-d',
					msgTarget: "under",
                    invalidText:"格式有误，请按2010-09-01格式填写",
					// hidden: true,
					afterLabelTextTpl : ' ',
					name: "studentBirthday"
				},
				{
					name: "studentPhone",
					afterLabelTextTpl : ' ',
					allowBlank: true,
					fieldLabel: "固定电话",
					regex:/^[0-9]*$/,
					maxLength: 16,
					emptyText:"只能为数字，不能超过16位",
					invalidText:"只能为数字，不能超过16位"
				},
				{
					name: "parentName",
					afterLabelTextTpl : ' ',
					allowBlank: true,
					fieldLabel: "监护人姓名",
					maxLength:25
				},
				
				
				
				{
					name: "parentPhone",
					// // readOnly: true,
					// afterLabelTextTpl : ' ',
					fieldLabel: "监护人手机"
				},
				{
					name: "emergencyPhone",
					// readOnly: true,
					afterLabelTextTpl : ' ',
					allowBlank: true,
					fieldLabel: "紧急电话",
					maxLength:16
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