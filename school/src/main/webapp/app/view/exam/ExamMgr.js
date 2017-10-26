/**
* @class School.view.exam.ExamMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 考试管理的gridPanel
*/
Ext.define("School.view.exam.ExamMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.ExamMgr"
	],
	itemId: "exammgr",
	alias: "widget.exammgr",
	forceFit: true,
	initComponent: function() {
		this.store = Ext.create("School.store.school.ExamMgr", {
			pageSize: 10,
			autoLoad: false
		});
		this.columns = [
			{
				text: "开课id",
				hidden: true,
				dataIndex: "curriculaVariableId"
			},
			{
				text: "考试id",
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "班级id",
				hidden: true,
				dataIndex: "clazzId"
			},
			{
				text: "考试名称",
				dataIndex: "name"
			},
			{
				text: "班级",
				dataIndex: "clazzName"
			},
			{
				text: "考试类型",
				dataIndex: "examTemplet",
				renderer: function(value) {
					if(value && value.templetName) {
						return value.templetName;	
					}
					else {
						return null;
					}
				}
			},
			{
				text: "考试时间",
				dataIndex: "date"
			},
			{
				text: "提交情况",
				// dataIndex: "commitStatus",
				dataIndex: "type",
				renderer: function(value) {
					switch(value) {
						case 1: 
							return "<font color='red'>"+"未提交"+"</font>";
							break;
						default:
							return "<font color='green'>"+"已提交"+"</font>";
							break;
					}
				}
			},
			{
				xtype: "actioncolumn", 
				header: "查看成绩",
				permissionId: 'seeExamScore',
				align: "center",
				items: [
					{

						iconCls: "key_go",
						handler: function(grid, rowIndex, colIndex) {
							this.fireEvent("itemclick", grid, rowIndex, colIndex);
						}
					}
					
				]
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
						itemId: "subjectcombo",
						xtype: "combo",
						triggerAction: "all",
						width: 150,
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						//获取数据集
						store: Ext.create("School.store.school.CourseMgr"),
						queryMode: "remote"
					},
					{
						fieldLabel: "年级选择",
						labelWidth:60,
						itemId: "gradecombo",
						xtype: "combo",
						triggerAction: "all",
						width: 150,
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						//获取数据集
						store: Ext.create("School.store.school.GradeMgr"),
						queryMode: "remote"
					},
					{
						fieldLabel: "学期选择",
						labelWidth: 60,
						itemId: "termcombo",
						xtype: "combobox",
						triggerAction: "all",
						width: 200,
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						//获取数据集
						store: Ext.create("School.store.school.Semester"),
						queryMode: "remote"
					},
					// {
					// 	fieldLabel: "任课老师",
					// 	hidden: true,
					// 	itemId: "teachercombo",
					// 	width: 200,
					// 	labelWidth: 60,
					// 	itemId: "teachercombo",
					// 	xtype: "combobox",
					// 	triggerAction: "all",
					// 	emptyText: "请选择...",
					// 	editable: false,
					// 	displayField: "name",
					// 	valueField: "id",
					// 	//获取数据集
					// 	store: Ext.create("School.store.school.TeacherMgr"),
					// 	queryMode: "remote"
					// },
					{
						xtype: "button",
						iconCls: "cancel",
						text: "重置",
						itemId: "resetcombo"
					},
					{
						xtype: "tbseparator"
					},
					
					{
						xtype: 'button',
						text: "修改",
						iconCls: "edit",
						permissionId: 'editExam',
						itemId: "edit"

					},
					{
						xtype: "button",
						iconCls: "delete",
						text: "删除",
						permissionId: 'deleteExam',
						itemId: "delete"
						//margin: "0 20 0 0"
					},
					
					{
						xtype: "tbseparator"
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

