/**
* @class School.view.school.ClassMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 班级管理的主面板
*/

Ext.define("School.view.school.ClassMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.ClassMgr"
	],
	alias:"widget.classmgr",
	itemId: "classmgr",
	forceFit: true,
	initComponent: function() {
		this.store = Ext.create("School.store.school.ClassMgr");
		this.columns = [
			{
				text: "id",
				align: "center",
				hidden: true,
				dataIndex: "id"
			},			
			{
				text: "班级名称",
				sortable: false,
				dataIndex: "name"
			},
			{
				text: "所属年级",
				dataIndex: "tScGradeId",
				renderer: function(value) {
					return (School.util.Util.getOtherValue(value, zy_gradeRec));
				}
			},
			{
				text: "班级简称",
				sortable: false,
				dataIndex: "sortName"
			},
			{
				text: "班级简介",
				dataIndex: "comment"
			},
			{
				text: "班主任",
				sortable: false,
				dataIndex: "headTeacher"
			},
			{
				text: "备注",
				dataIndex: "info"
			}
			// {
			// 	xtype: "actioncolumn",
			// 	header: "进入班级",
			// 	itemId: "gotoMyclass",
			// 	align: "center",
			// 	items: [
			// 		{
			// 			iconCls: "key_go",
			// 			handler: function(grid, rowIndex, colIndex) {
			// 				this.fireEvent("itemclick", grid, rowIndex, colIndex);
			// 			}
			// 		}
			// 	]
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
						fieldLabel: "年级选择",
						labelWidth:60,
						itemId: "classcombo",
						xtype: "combo",
						triggerAction: "all",
						width: 250,
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						//把年级管理的数据集赋值给这个下拉框
						store: Ext.create("School.store.school.GradeMgr"),
						queryMode: "remote"
					},
					{
						xtype: "button",
						text: "新增班级",
						itemId: "add",
						// glyph: 0xf067
						iconCls: "add"
					},
					{
						xtype:  "button",
						text:　"编辑班级",
						itemId: "edit",
						// glyph: 0xf044
						iconCls: "edit"
					},
					{
						xtype: "button",
						text: "删除班级",
						itemId: "delete",
						// glyph: 0xf00d
						iconCls: "delete"
					},
					{
						xtype: "label",
						margin: "0 0 0 100",
						text: "当前年级："
					},
					{
						xtype: "label",
						itemId: "gradeName",
						style: {
							color: "red",
							fontWeight: "800"
						}
					}
				]
			},
			{  
		        xtype: 'pagingtoolbar',  
		        store: this.getStore(), 
		        dock: 'bottom',  
		        displayInfo: true  
		    }  
		];


		this.callParent(arguments);
	}

});