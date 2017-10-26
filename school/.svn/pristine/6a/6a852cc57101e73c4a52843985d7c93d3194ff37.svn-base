/*
* School.view.school.AddTerm
* author:小玉
* description:新增学期或修改学期的弹出window
*/
Ext.define("School.view.school.AddTerm",{
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.addterm",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "新增学期",
	autoShow: true,
	initComponent:function(){
		var me = this;
		me.items =[
           {
           	xtype: "form",
           	border: false,
           	style:{
           		padding: "10px"
           	},
           	layout:{
           		type:"anchor"
           	},
           	defaults:{
           		xtype: "textfield",
           		anchor: "100%",
           		labelWidth: 70,
           		msgTarget: "under",
           		afterLabelTextTpl: '<span style="color:red">*</span>',
				labelSeparator: ":",
				allowBlank: false,
				style: {
					marginTop: "20px"
				}
           	},
           	items:[
           	{
           		name: "id",
           		hidden: true,
           		allowBlank: true,
           		fieldLabel:"学期id"
           	},
           	{
           		name:"name",
           		fieldLabel:"学期名称",
           		emptyText:"格式为 xxxx年秋上(春下)学期",
           		regex: /^[0-9]{4}年(([秋][上])|([春][下]))学期$/,
           		regexText:"格式为 xxxx年秋上(春下)学期"
           	},
           	{
           		name:"startTime",
           		fieldLabel:"开始时间",
           		format:"Y-m-d",
           		xtype: 'datefield',
              msgTarget: "under",
              invalidText:"格式有误，请按2010-09-01格式填写",
           		emptyText: "格式如：2016-08-08",
           		editable:true,

           	},
           	{
           		name:"endTime",
           		fieldLabel:"结束时间",
           		format:"Y-m-d",
           		xtype: 'datefield',
              msgTarget: "under",
              invalidText:"格式有误，请按2010-09-01格式填写",
           		emptyText: "格式如：2016-08-08",
           		editable:true
           	},
           	{
                name:"week",
                fieldLabel:"持续周数",
                regex:/^[0-9]*$/,
                msgTarget: "under",
              invalidText:"只能为数字"
           	},
           	{
           		xtype: "radiogroup",
           		labelWidth: 80,
           		allowBlank: false,
           		name:"currentSemester",
           		fieldLabel:"当前学期",
           		columns: 2,
           		itemId:"isCurrent",
           		labelAlign: "left",
           		items: [
                    {
                    	boxLabel:"是",
                    	name:"currentSemester",
                    	inputValue: "1"
                    },
                    {
                    	boxLabel:"否",
                    	name: "currentSemester",
                    	inputValue: "0"
                    }
           		]
           	}
           	],
           	buttons: [
                {
                	text:"保存",
                	itemId:"save"
                },
                {
                	text:"取消",
                	itemId:"cancel"
                }
           	]

           }
		];
		me.callParent(arguments);
	}
})