/**
* @class School.view.school.AddCurricula
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新增课程或者编辑课程的Ext.Window
*/
Ext.define("School.view.school.AddCurricula", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.addcuricula",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "增加课程",
	autoShow: true,
	initComponent: function() {
		this.items =  [
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
						name: "id",
						hidden: true,
						allowBlank: true,
						fieldLabel: "课程id"
					},
					{
						name: "tScSubjectId",
						allowBlank: true,
						hidden: true,
						fieldLabel: "科目id"
					},
					{
						name: "name",
						fieldLabel: "课程名字",
						maxLength: 32,
						emptyText:"不能超过32字"
					},
					{
						fieldLabel: "负责人",
						name: "tScTeacherId",
						allowBlank: true,
						afterLabelTextTpl: "",
						itemId: "selectcourseMgr",
						xtype: "combobox",
						emptyText: "请选择...",
						store: Ext.create("School.store.school.TeacherMgr", {
							pageSize: 300
						}),
						editable: true,
						typeAhead: true,
						typeAheadDelay: 50,
						forceSelection : true,
						displayField: "name",
						valueField: "id",
						queryMode: "local"
					},
					{
						fieldLabel: "适用年级",
						name: "adaptiveGrade",
						allowBlank: false,
						itemId: "selectGradeTeacher",
						xtype: "combobox",
						triggerAction: "all",
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "name", //
						//获取数据集
						store: Ext.create("School.store.school.GradeMgr"),
						queryMode: "remote"
					},
					{
						fieldLabel: "适用学期",
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
					{
						name: "comment",
						xtype: "textarea",
						fieldLabel: "课程描述",
						maxLength:150,
						allowBlank:true,
						afterLabelTextTpl:"",
						emptyText:"不能超过150字"
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