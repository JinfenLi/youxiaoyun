/**
* @class School.view.area.AddMultiCurricula
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 用来批量新增课程的
*/
Ext.define("School.view.school.AddMultiCurricula", {
	extend: "Ext.window.Window",
	alias: "widget.addmulticurricula",
	autoShow: true,
	closeAction: "destroy",
	autoShow: true,
	modal: true,
	width: 400,
	height: 400,
	title: "批量新增课程",
	layout: 'fit',
	initComponent: function() {
		this.items = [
			{
				xtype: "form",
				layout: "anchor",
				padding: "10 10",
				defaults: {
					labelWidth: 70,
					margin: "20 0",
					anchor: "100%"
				},

				items: [

					{
						fieldLabel: "所属学科",
						allowBlank: false,
						msgTarget: "side",
						afterLabelTextTpl: School.util.Util.required,
						name: "tScSubjectId",
						itemId: "coursecombo",
						xtype: "combo",
						triggerAction: "all",
						displayField: "name",
						emptyText: "请选择...",
						editable: false,
						valueField: "id",
						store: Ext.create("School.store.school.CourseMgr"),
						queryMode: "remote"
					},
					
					{
						fieldLabel: "适用学期",
						allowBlank: false,
						msgTarget: "side",
						afterLabelTextTpl: School.util.Util.required,
						name: "adaptiveTerm",
						allowBlank: false,
						value: "请选择...",
						itemId: "selectTerm",
						xtype: "combobox",
						triggerAction: "all",
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						//获取数据集
						store: [["上学期", "上学期"], ["下学期", "下学期"]],
						queryMode: "local"
					},

					//使用年级
					{
						xtype: "checkboxgroup",
						msgTarget: "side",
						afterLabelTextTpl: School.util.Util.required,
						fieldLabel: '适用年级',  
						allowBlank: false,  
						itemId: "gradecheckbox",   
				        columns: 3,
						items: [
							
				        ]
				     },

				     //备注
				     {
				     	xtype: "textarea",
				     	fieldLabel: "备注信息",
				     	name: "comment"
				     }			

				], //form's items end 

				//底部的按钮栏
				buttons: [
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
		];

		this.callParent(arguments);
	}
});
