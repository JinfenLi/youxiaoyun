/**
* @class School.view.school.AddMultiClass
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 该类是用来批量为某一个年级增加班级的
*/

Ext.define("School.view.school.AddMultiClass", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.addmulticlass",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "批量新增班级",
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
					afterLabelTextTpl : School.util.Util.required,
					labelSeparator: ":",
					allowBlank: false,
					style: {
						marginTop: "20px"
					}
					
				},
				items: [
					{
						fieldLabel: "所属年级",
						itemId: "gradecombo",
						xtype: "combo",
						name: "tScGradeId",
						triggerAction: "all",
						width: 250,
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						store: Ext.create("School.store.school.GradeMgr"),
						queryMode: "remote"
					},
					{
				        xtype: 'numberfield',
				        itemId: "start",
				        fieldLabel: '最小班级序号',
				        emptyText: "请输入最小班级序号，如：1",
				        maxValue: 100,
				        minValue: 1,
				        allowDecimals: false,
				        hideTrigger: true
				    },
				    {
				    	xtype: 'numberfield',
				        itemId: "end",
				        fieldLabel: '最大班级序号',
				        emptyText: "请输入最大班级序号，如：20",
				        maxValue: 100,
				        minValue: 1,
				        allowDecimals: false,
				        hideTrigger: true
				    }




					// {
					// 	name: "id",
					// 	hidden: true,
					// 	fieldLabel: "班级id",
					// 	allowBlank: true
					// },
					// {
					// 	name: "tScGradeId",
					// 	hidden: true,
					// 	fieldLabel: "所属年级id",
					// 	allowBlank: true
					// },
					// {
					// 	name: "name",
					// 	fieldLabel: "班级名称"
					// },
					// {
					// 	name: "sortName",
					// 	fieldLabel: "班级简称"
					// },
					// {
					// 	name: "comment",
					// 	fieldLabel: "班级简介"
					// },
					// {
					// 	fieldLabel: "班主任",
					// 	name: "teacherId",
					// 	allowBlank: true,
					// 	afterLabelTextTpl: "",
					// 	// itemId: "selectHeadTeacher",
					// 	xtype: "combobox",
					// 	triggerAction: "all",
					// 	emptyText: "请选择...",
					// 	store: Ext.create("School.store.school.TeacherMgr", {
					// 		pageSize: 300
					// 	}),
					// 	editable: true,
					// 	typeAhead: true,
					// 	typeAheadDelay: 50,
					// 	forceSelection : true,
					// 	queryMode: "local",
					// 	displayField: "name",
					// 	valueField: "id"					
					// },
					// {
					// 	name: "info",
					// 	xtype: 'textarea',
					// 	fieldLabel: "备注"
					// }
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