/**
* @class School.view.exam.EditExam
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 编辑考试的Ext.Window
*/
Ext.define("School.view.exam.EditExam", {
	extend: "Ext.window.Window",
	requires: [
		"Ext.form.field.Time"
	],
	closeAction: "destroy",
	alias: "widget.editexam",
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
					name: "id",
					hidden: true,
					fieldLabel: "班级考试",
					allowBlank: true
				},
				{
					name: "name",
					fieldLabel: "考试名称"
				},
				{
					name: "date",
					flex: 1,
					xtype: "datefield",
					editable: false,
					//width: 100,
					format: "Y-m-d",
					fieldLabel: "考试日期"
				},
				{
					fieldLabel: "考试类型",
					name: "templetId",
					itemId: "typecombo",
					xtype: "combobox",
					triggerAction: "all",
					width: 200,
					emptyText: "请选择...",
					editable: false,
					displayField: "templetName",
					valueField: "id",
					store: Ext.create("School.store.exam.ExamType"),
					allowBlank: true,
					queryMode: "remote"
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