/**
* @class School.view.school.AddClass
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新增或者编辑班级的Ext.Window
*/

Ext.define("School.view.school.AddClass", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.addclass",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "新增班级",
	autoShow: true,
	initComponent: function() {
		this.items = [
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
					style: {
						marginTop: "20px"
					}
					
				},
				items: [
					
					{
						name: "id",
						hidden: true,
						fieldLabel: "班级id",
						allowBlank: true
					},
					{
						name: "tScGradeId",
						hidden: true,
						fieldLabel: "所属年级id",
						allowBlank: true
					},
					{
						name: "name",
						allowBlank: false,
						afterLabelTextTpl: School.util.Util.required,
						fieldLabel: "班级名称"
					},
					{
						name: "sortName",
						fieldLabel: "班级简称"
					},
					{
						name: "comment",
						fieldLabel: "班级简介"
					},
					{
						fieldLabel: "班主任",
						name: "teacherId",
						allowBlank: true,
						afterLabelTextTpl: "",
						// itemId: "selectHeadTeacher",
						xtype: "combobox",
						triggerAction: "all",
						emptyText: "请选择...",
						store: Ext.create("School.store.school.TeacherMgr", {
							pageSize: 300
						}),
						editable: true,
						typeAhead: true,
						typeAheadDelay: 50,
						forceSelection : true,
						queryMode: "local",
						displayField: "name",
						valueField: "id"					
					},
					{
						name: "info",
						xtype: 'textarea',
						fieldLabel: "备注"
					}
				],
				
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
		];
		
		this.callParent(arguments);
	}
	
	
});