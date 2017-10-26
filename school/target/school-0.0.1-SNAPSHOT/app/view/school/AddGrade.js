/**
* @class School.view.school.AddGrade
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新增年级或者修改年级的Ext.Window
*/
Ext.define("School.view.school.AddGrade", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.addgrade",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "新增年级",
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
						allowBlank: true,
						fieldLabel: "年级id"
					},
					{
						name: "name",
						fieldLabel: "年级名称",
						itemId:"gradeName",
						maxLength: 16,
						emptyText:"提交之后不能修改,字数不能超过16"
					},
					{
						name: "sortName",
						fieldLabel: "年级简称",
						maxLength: 10,
						emptyText:"字数不能超过10"
					},
					{
						name: "year",
						xtype: "datefield",
						format: "Y-m-d",
						editable: true,
						emptyText: "格式如：2010-09-01",
						fieldLabel: "入学时间",
						msgTarget: "under",
						invalidText:"格式有误，请按2010-09-01格式填写"
					},
					{
						name: "level",
						allowBlank: true,
						hidden: true,
						fieldLabel: "学龄"
					},
					{
						fieldLabel: "级长",
						name: "teacherId",
						allowBlank: true,
						afterLabelTextTpl: "",
						itemId: "selectGradeTeacher",
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
						fieldLabel: "年级简介",
						name: "comment",
						allowBlank: true,
						xtype: "textarea",
						afterLabelTextTpl: "",
						maxLength:150,
						emptyText:"字数不能超过150"
					},
					{
						name: "info",
						fieldLabel: "备注",
						xtype: "textarea",
						allowBlank: true,
						afterLabelTextTpl: "",
						maxLength:150,
						emptyText:"字数不能超过150"
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
		];
		this.callParent(arguments);
	}
	
});