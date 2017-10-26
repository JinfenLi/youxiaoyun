/**
* @class School.view.school.ShowSelectedCurricula
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 查看选课记录的主面板
*/

Ext.define("School.view.school.ShowSelectedCurricula", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.ShowSelectedCurricula"
	],
	itemId: "showselectedcurricula",
	alias: "widget.showselectedcurricula",
	forceFit: true,
	// selType: "checkboxmodel", //使用复选框选择模式，允许选择多行
	initComponent: function() {
		this.store = Ext.create("School.store.school.ShowSelectedCurricula");
		this.columns = [
		
			{
				text: "id", //选课id
				hidden: true,
				dataIndex: "id"
			},
			// {
			// 	text: "科目id",
			// 	dataIndex: "subjectId"
			// },
			{
				text: "课程id",
				hidden: true,
				dataIndex: "curriculaId"
			},
			{
				text: "课程名称",
				dataIndex: "curriculaName"
			},
			{	
				text: "班级名称",
				dataIndex: "clazzName"
			},
			{
				text: "任课老师",
				dataIndex: "teacherName"
			},
			{
				text: "任课老师电话",
				dataIndex: "phone"
			},
			{
				xtype: "actioncolumn",
				// flex: 1,
				header: "开设考试",
				itemId: "addExam",
				permissionId: 'addExam',
				//permissionId: 'deleteStudent',
				align: "center",
				items: [
					{
						iconCls: "add",
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
						width: 200,
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
					// 	itemId: "teachercombo",
					// 	width: 150,
					// 	labelWidth: 60,
					// 	itemId: "teachercombo",
					// 	xtype: "combobox",
					// 	triggerAction: "all",
					// 	emptyText: "请选择...",
					// 	store: Ext.create("School.store.school.TeacherMgr", {
					// 		pageSize: 500
					// 	}),
					// 	editable: true,
					// 	typeAhead: true,
					// 	typeAheadDelay: 50,
					// 	forceSelection : true,
					// 	queryMode: "local",
					// 	displayField: "name",
					// 	valueField: "id"

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
						text: "新增选课",
						iconCls: "add",
						itemId: "addSelect",
						// permissionId: 'addSelect',
						xtype: "button"
					},
					{
						xtype: "tbseparator"
					},
					{
						text: "删除选课",
						iconCls: "delete",
						itemId: "deleteSelect",
						// permissionId: 'deleteSelect',
						xtype: "button"
					},
					// {
					// 	xtype: "label",
					// 	margin: "0 0 0 20",
					// 	itemId: "curriculaname",
					// 	text: "当前课程："
					// },
					// {
					// 	xtype: "tbfill"
					// },
					{
						xtype: "tbseparator"
					},
					// {
					// 	text: "开设考试",
					// 	iconCls: "add",
					// 	itemId: "addExam",
					// 	permissionId: 'addExam',
					// 	xtype: "button"
					// }
					

				]
			},
			{  
		        xtype: 'pagingtoolbar',  
		        store: this.getStore(),   // same store GridPanel is using  
		        dock: 'bottom',  
		        displayInfo: true  
		    }  
		];
		// this.buttons = [
		// 		{
		// 			text : '保存',
		// 			itemId: "save"
		// 		},
		// 		{
		// 			text : '清空',
		// 			itemId: "reset"
		// 		}
		// ];




		this.callParent(arguments);
	}

});