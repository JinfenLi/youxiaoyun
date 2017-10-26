/**
* @class School.view.teacher.
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 教师管理的主面板
*/

Ext.define("School.view.teacher.TeacherMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.TeacherMgr"
	],
	alias:"widget.teachermgr",
	itemId: "teachermgr",
	forceFit: true,
	initComponent: function() {
		this.store = Ext.create("School.store.school.TeacherMgr", {
			pageSize:15
		});
		this.columns = [
		
			{
				text: "教师id",
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "班级id",
				hidden: true,
				dataIndex: "tScClassId"
			},
			{
				text: "年级id",
				hidden: true,
				dataIndex: "tScGradeId"
			},
			{
				text: "学校id",
				hidden: true,
				dataIndex: "tScSchoolId"
			},
			{
				text: "教工号",
				dataIndex: "idcard"
			},
		
			{
				text: "姓名",
				dataIndex: "name"
			},
			
			{
				text: "性别",
				dataIndex: "sex"
			},
			{
				text: "联系电话",
				dataIndex: "phone"
			},
			{
				text: "电子邮箱",
				dataIndex: "email"
			},
			{
				text: "管理员",
				hidden: true,
				align: "center",
				dataIndex: "isAuthc",
				renderer: function(value) {
					return (parseInt(value) === 1 ? "是" : "否");
				}
			},
			{
				xtype: "actioncolumn",
				itemId: "resetPsd",
				header: "修改密码",
				permissionId: 'resetPsd',
				align: "center",
				items: [
					{
						iconCls: "edit",
						handler: function(grid, rowIndex, colIndex) {
							var id = grid.getStore().getAt(rowIndex).get("id");
							this.fireEvent("itemclick", id);
						}
					}
				]
			}
			// {
			// 	text: "图片",
			// 	dataIndex: "picUrl"
			// },

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
						text: "导出教师",
						itemId: "download",
						permissionId: 'downloadTeacher',
						iconCls: "download"
					},
					{
						xtype:  "button",
						text:　"导入教师",
						permissionId: 'uploadTeacher',
						itemId: "upload",
						iconCls: "upload"
					},
					{
						xtype: "button",
						text: "添加教师",
						permissionId: 'addTeacher',
						itemId: "add",
						iconCls: "add"
					},
					{
						xtype: "button",
						text: "编辑教师",
						permissionId: 'editTeacher',
						itemId: "edit",
						iconCls: "edit"
					},
					{
						xtype: "button",
						text: "删除教师",
						permissionId: 'deleteTeacher',
						itemId: "delete",
						iconCls: "delete"
					}
				]
			},
			{  
		        xtype: 'pagingtoolbar',  
		        //pageSize: 10, //每页显示几条数据  
		        store: this.getStore(),  // same store GridPanel is using  
		        dock: 'bottom',  
		        displayInfo: true  
		    }  
		];




		this.callParent(arguments);
	}

});