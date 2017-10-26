/**
* @class School.view.school.CurricularMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 课程管理的主面板
*/
Ext.define("School.view.school.CurriculaMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.CurriculaMgr",
	],
	alias:"widget.curriculamgr",
	itemId: "curriculamgr",
	forceFit: true, 
	initComponent: function() {
		this.store = Ext.create("School.store.school.CurriculaMgr");
		this.columns = [
		
			{
				text: "学科id",
				align: "center",
				hidden: true,
				dataIndex: "tScSubjectId"
			},
			{
				text: "课程id",
				align: "center",
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "课程负责人id",
				flex: 2,
				dataIndex: "tScTeacherId",
				hidden: true
			},
			{
				text: "课程名称",
				dataIndex: "name"
			},
			{
				text: "课程负责人",
				dataIndex: "teacherName"
			},
			{
				text: "所属学科",
				dataIndex: "tScSubjectId",
				//使用自定义函数渲染，把科目id转成相应的科目名称
				renderer: function(value) {
					return(School.util.Util.getOtherValue(value, zy_subjectRec));
				}
			},
			{
				text: "适用年级",
				dataIndex: "adaptiveGrade"
			},
			{
				text: "适用学期",
				dataIndex: "adaptiveTerm"
			},
			{
				text: "课程描述",
				dataIndex: "comment"
			}
		];

		//固定菜单栏
		this.dockedItems = [
			{
				xtype: "toolbar",
				flex: 1,
				dock: "top",
				items: [
					{
						fieldLabel: "学科选择",
						labelWidth:60,
						itemId: "curriculacombo",
						xtype: "combo",
						triggerAction: "all",
						width: 200,
						displayField: "name",
						emptyText: "请选择...",
						editable: false,
						valueField: "id",
						//获取数据集
						store: Ext.create("School.store.school.CourseMgr"),
						queryMode: "remote"
					},
					{
						xtype: "button",
						text: "新增课程",
						itemId: "add",
						// glyph: 0xf067
						iconCls: "add"
					},{
						xtype:  "button",
						text:　"编辑课程",
						itemId: "edit",
						// glyph: 0xf044
						iconCls: "edit"
					},{
						xtype: "button",
						text: "删除课程",
						itemId: "delete",
						// glyph: 0xf00d
						iconCls: "delete"
					},
					{
						xtype: "label",
						margin: "0 0 0 100",
						text: "当前学科："
					},
					{
						xtype: "label",
						itemId: "courseName",
						style: {
							color: "red",
							fontWeight: "800"
						}
					}
				]
			},
			{  
		        xtype: 'pagingtoolbar',  
		        store: this.getStore(),   // same store GridPanel is using  
		        dock: 'bottom',  
		        displayInfo: true  
		    }  
		];

		this.callParent(arguments);
	}

});