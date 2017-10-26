/**
* @class School.view.school.CourseMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 学科管理的主面板
*/

Ext.define("School.view.school.CourseMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.CourseMgr",
	],
	alias: "widget.coursemgr",
	itemId: "coursemgr",
	forceFit: true,
	initComponent: function() {
		this.store = Ext.create("School.store.school.CourseMgr");
		this.columns = [
			{
				text: "id",
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "科目负责人id",
				flex: 2,
				dataIndex: "tScTeacherId",
				hidden: true
			},
			{
				text: "学科名称",
				flex:2,
				// align: "center",
				dataIndex: "name"
			},
			{
				text: "学科负责人",
				flex: 2,
				dataIndex: "teacherName"
			},
			{
				text: "主干课程",
				flex: 2,
				dataIndex: "trunk",
				renderer: function(value) {
					if(value == "1") {
						return "是";
					} else {
						return "否";
					}
				}
			},
			{
				text: "学科描述",
				flex: 2,
				// align: "center",
				dataIndex: "comment"
			},
			{
				xtype: "actioncolumn",
				permissionId: 'seeCourse',
				header: "查看相关课程",
				align: "center",
				flex: 2,
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
					// {
					// 	fieldLabel: "学校选择",
					// 	labelWidth:60,
					// 	itemId: "coursecombo",
					// 	xtype: "combo",
					// 	triggerAction: "all",
					// 	width: 250,
					// 	emptyText: "请选择...",
					// 	editable: false,
					// 	displayField: "name",
					// 	valueField: "id",
					// 	//获取数据集
					// 	store: Ext.create("School.store.school.SchoolMgr"),
					// 	queryMode: "remote"
					// },
					{
						xtype: "button",
						text: "新增学科",
						itemId: "add",
						permissionId: 'addSubject',
						iconCls: "add"
					},
					{
						xtype:  "button",
						text:　"编辑学科",
						itemId: "edit",
						permissionId: 'editSubject',
						iconCls: "edit"
					},
					{
						xtype: "button",
						text: "删除学科",
						itemId: "delete",
						permissionId: 'deleteSubject',
						iconCls: "delete"
					}
					// {
					// 	xtype: "button",
					// 	text: "课程管理",
					// 	itemId: "details",
					// 	iconCls: "key_go"
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

		this.callParent(arguments);
	}

});