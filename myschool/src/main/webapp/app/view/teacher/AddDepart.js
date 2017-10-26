/**
* @class School.view.teacher.AddDepart
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新增部门或者编辑部门时弹出来的Ext.Window
*/
Ext.define("School.view.teacher.AddDepart", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.adddepart",
	closable: true,
	modal: true,
	autoShow: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "新增部门",
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
				afterLabelTextTpl : '<span style="color:red">*</span>', //必填项后面加个红星
				labelSeparator: ":",
				allowBlank: false,
				style: {
					marginTop: "20px"
				}
				
			},
			items: [
				//"id", "name", "function", "principal", "telephone","phone"
				//
				// tScSchoolId  (学校id)
                // name (部门名称)
                // info (备注)
                // phone(部门联系方式)
                // type(部门类型)

				//
				{
					name: "id",
					hidden: true,
					allowBlank: true,
					fieldLabel: "部门id"
				},
				{
					name: "tScSchoolId",
					hidden: true,
					allowBlank: true,
					fieldLabel: "部门id"
				},
				{
					name: "name",
					fieldLabel: "部门名称"
				},
				{
					fieldLabel: "部门类型",
					name: "type",
					allowBlank: false,
					value: "请选择...",
					xtype: "combobox",
					triggerAction: "all",
					emptyText: "请选择...",
					editable: false,
					displayField: "name",
					valueField: "id",
					//获取数据集
					store: [["行政", "行政"], ["教学", "教学"], ["其他", "其他"]],
					queryMode: "local"
				},
				{
					name: "phone",
					type: "numberfield",
					fieldLabel: "联系方式"
				},
				{
					name: "info",
					fieldLabel: "备注"
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