/**
* @class School.view.exam
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新增考试的Ext.Window
*/
Ext.define("School.view.exam.AddExam", {
	extend: "Ext.window.Window",
	requires: [
		"Ext.form.field.Time"
	],
	closeAction: "destroy",
	alias: "widget.addexam",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "创建考试",
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
				labelWidth: 70,
				afterLabelTextTpl : '<span style="color:red">*</span>', 
				labelSeparator: ":",
				allowBlank: false,
				style: {
					marginTop: "20px"
				}
				
			},
			items: [
				{
					name: "name",
					fieldLabel: "考试名称"
				},
				{
					fieldLabel: "学期",
					name: "semesterId",
					itemId: "termcombo",
					xtype: "combobox",
					triggerAction: "all",
					width: 200,
					emptyText: "请选择...",
					editable: false,
					displayField: "name",
					valueField: "id",
					store: Ext.create("School.store.school.Semester"),
					queryMode: "remote"
				},
				{
					name: "date",
					flex: 1,
					xtype: "datefield",
					editable: false,
					format: "Y-m-d",
					fieldLabel: "考试日期"
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