/**
* @class School.view.school.GraedeMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 年级管理的主面板
*/

Ext.define("School.view.school.GradeMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.GradeMgr"
	],
	itemId: "grademgr",
	alias:"widget.grademgr",
	forceFit: true,

	initComponent: function() {

		this.store = Ext.create("School.store.school.GradeMgr");

		this.columns = [
		
			{
				text: "年级id",
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "年级名称",
				dataIndex: "name"
			},
			{
				text: "年级简称",
				dataIndex: "sortName"
			},
			{
				text: "入学时间",
				dataIndex: "year"
			},
			{
				text: "学龄",
				hidden: true,
				hidden: true,
				dataIndex: "level"
			},
			{
				text: "级长",
				dataIndex: "gradeTeacher"
			},
			{
				text: "年级简介",
				dataIndex: "comment"
			},
			{
				text: "备注",
				dataIndex: "info"
			},
			{
				xtype: "actioncolumn",
				header: "查看相关班级",
				permissionId: 'seeClass',
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
					// {
					// 	fieldLabel: "学校选择",
					// 	labelWidth:60,
					// 	itemId: "gradecombo",
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
						text: "新增年级",
						itemId: "add",
						permissionId: 'addGrade',
						iconCls: "add"
					},{
						xtype:  "button",
						text: "编辑年级",
						itemId: "edit",
						permissionId: 'editGrade',
						iconCls: "edit"
					},{
						xtype: "button",
						text: "删除年级",
						itemId: "delete",
						permissionId: 'deleteGrade',
						iconCls: "delete"
					},{
						xtype:"button",
						text:"全校年级升级",
						itemId:"up",
						// permissionId:"upGrade",
						iconCls:"upload"
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