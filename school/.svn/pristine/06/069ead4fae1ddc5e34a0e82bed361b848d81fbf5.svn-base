/**
* @class School.view.school.ClassMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 班级管理的主面板
*/

Ext.define("School.view.school.ClassMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.ClassMgr",
		"School.store.school.TeacherMgr"
	],
	alias:"widget.classmgr",
	itemId: "classmgr",
	forceFit: true,
	initComponent: function() {

		var t_store = Ext.create("School.store.school.TeacherMgr", {
							pageSize: 300,
							autoload: true,
		});
		t_store.load({
			async: true
		});


		this.store = Ext.create("School.store.school.ClassMgr");

		var cellEditingPlugin = Ext.create('Ext.grid.plugin.CellEditing', {
			clicksToEdit: 1,
			editStatus: false,
			listeners: { 
				beforeedit: function( editor, e, eOpts ) {
					return this.editStatus;
				}
				// ,
				// validateedit: function(editor, e, eOpts) {
				// 	console.log(e);
				// 	if(e.field == "parentPhone") {
				// 		var originalVal = e.originalValue;
				// 		var inputVal = e.value;
				// 		if(inputVal) {
				// 			return true;
				// 		}
				// 		else {
				// 			School.util.Util.showErrorMsg("监护人手机为必填项");
				// 			return false;
				// 		}

				// 	}
				// 	return true;
				// }
			}
		});
		this.plugins = [cellEditingPlugin];

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
				dataIndex: "sortName",
				editor: {
						xtype: "textfield",
						allowBlank: true
				}
			},
			{
				text: "班级简介",
				dataIndex: "comment",
				editor: {
						xtype: "textfield",
						allowBlank: true
				}
			},
			{
				text: "班主任",
				sortable: false,
				dataIndex: "headTeacherId",
				renderer: function(value) {
					return (School.util.Util.getOtherValue(value, t_store));
				},
				editor: {
						xtype: 'combobox',
						emptyText: "请选择...",
    				hideLabel: true,
            lazyRender: true, //值为true时阻止ComboBox渲染直到该对象被请求
            displayField: "name",
						valueField: "id",
            mode: "local",
            editable: false,
            triggerAction: "all",
						store: t_store
				}
			},
			{
				text: "备注",
				dataIndex: "info",
				editor: {
						xtype: "textfield",
						allowBlank: true
				}
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
						xtype: "button",
						text: "编辑所有班级",
						itemId: "editAll",
						// permissionId: 'editStudent',
						iconCls: "edit"
					},
					{
						xtype: "button",
						hidden: true,
						text: "保存",
						itemId: "saveAll",
						// permissionId: 'editStudent',
						iconCls: "edit"
					},
					{
						xtype: "button",
						hidden: true,
						text: "取消",
						itemId: "cancelEdit",
						// permissionId: 'editStudent',
						iconCls: "edit"
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