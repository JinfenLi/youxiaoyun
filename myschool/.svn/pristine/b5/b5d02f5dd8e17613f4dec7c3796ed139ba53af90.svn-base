/**
* @class School.view.school.AddCourse
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新增学科或者编辑学科的Ext.Window
*/
Ext.define("School.view.school.AddCourse", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.addcourse",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "新增学科",
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
					labelWidth: 90,
					afterLabelTextTpl : '<span style="color:red">*</span>', 
					labelSeparator: ":",
					msgTarget: "under",
					allowBlank: false,
					style: {
						marginTop: "20px"
					}
					
				},

				items: [
					{
						name: "id",
						hidden: true,
						allowBlank: true,
						fieldLabel: "科目id"
					},
					{
						name: "name",
						fieldLabel: "科目名称"
					},
					{
						fieldLabel: "负责人",
						name: "tScTeacherId",
						allowBlank: true,
						afterLabelTextTpl: "",
						itemId: "selectSubjectMgr",
						xtype: "combobox",
						triggerAction: "all",
						width: 250,
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
						xtype: "radiogroup",
						labelWidth: 80,
						allowBlank: false,
						itemId: "trunk",
						name: "trunk",
						fieldLabel: "主干课程",
						columns: 2,
						labelAlign: "left",
						items: [
							{
								boxLabel: "是",
								name: "trunk",
								inputValue: "1"
							},
							{
								boxLabel: "否",
								name: "trunk",
								inputValue: "0"
							}
							
						]
					},

					{
						name: "comment",
						xtype: "textarea",
						fieldLabel: "科目描述"

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
		];
		
		this.callParent(arguments);
	}
	
	
});