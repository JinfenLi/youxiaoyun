/**
* @class School.view.school.DepartMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 部门管理的主面板
*/

Ext.define("School.view.school.DepartMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.DepartMgr"
		// "Ext.ux",
		// "Ext.grid.plugin.RowExpander"//展开和收缩插件
	],
	alias:"widget.departmgr", 
	itemId: "departmgr", 
	forceFit: true,
	initComponent: function() {
		this.store = Ext.create("School.store.school.DepartMgr");
		this.columns = [
		
			{
				text: "部门id",
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "学校Id",
				hidden: true,
				dataIndex: "tScSchoolId"
			},
			{
				text: "科目id",
				hidden: true,
				dataIndex: "tScSubjectId"
			},

			{
				text: "部门名称",
				dataIndex: "name"
			},
			{
				text: "联系电话",
				dataIndex: "phone"
			},
			{
				text: "部门类型",
				dataIndex: "type"
			},
			{
				text: "部门人员",
				//hidden: true,
				dataIndex: "teachers",
				renderer: function(value) {
					var teachers = "";
					for(var i = 0; i < value.length; i++) {
						teachers += value[i].name + " ";
					}
					return teachers;
				}
			},
			{
				text: "备注",
				dataIndex: "info"
			},
			// {
			// 	xtype: "actioncolumn",
			// 	itemId: "search",																																															
			// 	header: "查看部门人员",
			// 	align: "center",
			// 	items: [
			// 		{
			// 			iconCls: "key_go",
			// 			handler: function(grid, rowIndex, colIndex) {
			// 				this.fireEvent("itemclick", grid, rowIndex, colIndex);
			// 			}
			// 		}
			// 	]
			// },
			{
				xtype: "actioncolumn",
				itemId: "edit",
				header: "编辑部门人员",
				permissionId: 'editDepartmentMember',
				align: "center",
				items: [
					{
						iconCls: "edit",
						handler: function(grid, rowIndex, colIndex) {
							this.fireEvent("itemclick", grid, rowIndex, colIndex);
						}
					}
				]
			}
			// {
			// 	text: "部门负责人",
			// 	align: "center",
			// 	dataIndex: "principal"
			// },
			// {
			// 	text: "负责人联系电话",
			// 	align: "center",
			// 	dataIndex: "telephone"
			// },
			// {
			// 	text: "办公电话",
			// 	align: "center",
			// 	dataIndex: "phone"
			// },
			// {
			// 	text: "部门职责",
			// 	align: "center",
			// 	dataIndex: "function"
			// }
		];

		//固定菜单栏
		this.dockedItems = [
			{
				xtype: "toolbar",
				flex: 1,
				dock: "top",
				items: [
					{
						xtype: "button",
						text: "新增",
						itemId: "add",
						permissionId: 'addDepartment',
						iconCls: "add"
					},{
						xtype:  "button",
						text:"编辑",
						itemId: "edit",
						permissionId: 'editDepartment',
						iconCls: "edit"
					},{
						xtype: "button",
						text: "删除",
						itemId: "delete",
						permissionId: 'deleteDepartment',
						iconCls: "delete"
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

		// this.plugins = [
		// 	{
	 //            ptype: 'rowexpander',
	 //            rowBodyTpl : new Ext.XTemplate(
		// 			"<p>部门人员:张三，李四，王五，赵六，小白，菜鸟，大神，</p>"
	 //           )
	 //        }
  //       ];


		this.callParent(arguments);
	}

});