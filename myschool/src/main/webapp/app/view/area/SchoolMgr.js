/**
* @class School.view.area.SchoolMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 学校管理主界面
*/
Ext.define("School.view.area.SchoolMgr", {
	extend: "Ext.grid.Panel",
	alias:"widget.schoolmgr",
	itemId: "schoolmgr",

	requires:[
		"School.store.area.SchoolMgr"
	],
	initComponent: function() {
		this.store = Ext.create("School.store.area.SchoolMgr");
		this.columns = [
			{
				text: "id",
				//align: "center",
				hidden: true,
				sortable: false,
				dataIndex: "id"
			},
			{
				text: "学校名称",
				flex: 2,
				// align: "center",
				dataIndex: "name"
			},
			{
				text: "学校地址",
				flex: 2,
				// align: "center",
				dataIndex: "address"
			},
			{
				text: "联系电话",
				flex: 1,
				// align: "center",
				dataIndex: "phone"
			},
			{
				text: "邮政编码",
				flex: 1,
				// align: "center",
				dataIndex: "postcode"
			},
			{
				text: "电子邮箱",
				flex: 1,
				// align: "center",
				dataIndex: "email"
			},
			{
				text: "学校主页",
				flex: 1,
				// align: "center",
				dataIndex: "website"
			},
			{
				text: "学校logo",
				flex: 1,
				hidden: true,
				dataIndex: "logo"
			},
			// {
			// 	xtype: "actioncolumn",
			// 	flex: 1,
			// 	itemId: "schoolAlbum",
			// 	header: "查看校园相册",
			// 	align: "center",
			// 	items: [
			// 		{
			// 			iconCls: "key_go",
			// 			handler: function(grid, rowIndex, colIndex) {
			// 				//this.fireEvent("itemclick", grid, rowIndex, colIndex);
			// 			}
			// 		}
			// 	]
			// },
			{
				xtype: "actioncolumn",
				flex: 1,
				itemId: "addManager",
				header: "添加管理员",
				permissionId: 'addManager',
				align: "center",
				items: [
					{
						iconCls: "add",
						handler: function(grid, rowIndex, colIndex) {
							this.fireEvent("itemclick", grid, rowIndex, colIndex);
						}
					}
				]
			},
			{
				xtype: "actioncolumn",
				flex: 1,
				itemId: "introduce",
				header: "查看校园简介",
				//permissionId: 'readSchoolIntro',
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
						xtype: "button",
						text: "新增",
						itemId: "add",
						permissionId: 'addSchool',
						iconCls: "add"
					},{
						xtype:  "button",
						text:　"编辑",
						itemId: "edit",
						permissionId: 'editSchool',
						iconCls: "edit"
					},{
						xtype: "button",
						text: "删除",
						itemId: "delete",
						permissionId: 'deleteSchool',
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




		this.callParent(arguments);
	},
	forceFit: true//强制充满表格
});