
Ext.define("School.view.clazz.appraise.CreateTpl", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.createtpl",
	closable: true,
	autoShow: true,
	modal: true,
	require: [
		"School.store.school.CourseMgr"
	],
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "上传评语模板",
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
				{
					fieldLabel: "适用学科",
					itemId: "fitToSubject",
					name: "subjectId",
					xtype: "combobox",
					triggerAction: "all",
					width: 200,
					emptyText: "请选择...",
					editable: false,
					displayField: "name",
					valueField: "id",
					store: Ext.create("School.store.school.CourseMgr"),
					queryMode: "remote"
				},
				{
					xtype: "radiogroup",
					fieldLabel: '模板类型',  
					itemId: "type",   
			        columns: 3,
			        vertical: true,
			        defaults: {
			        	margin: "15 0"
			        },
					items: [
						{ name: 'type', boxLabel: '个人模板',  inputValue: '1', checked: true},
			            { name: 'type', boxLabel: '学校模板',  inputValue: '0' },
			            { name: 'type', boxLabel: '超级模板',  inputValue: '3', itemId: "super" }
			        ]
				},
				{
					name: "file",
					xtype: "filefield",
					itemId: "content",
					fieldLabel: "文件选择"
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