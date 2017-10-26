/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 年级管理和班级管理的控制器
 * 
 */
Ext.define("School.controller.school.GradeMgr", {
	extend: "Ext.app.Controller",
	views: [
		"school.GradeMgr",
	],
	init: function(application) {

		"use strict";

		this.control({
				
			//页面渲染完成后开始加载数据
			"grademgr": {
				afterrender: function(component) {
					component.getStore().reload();
				}
			},

			//打开新增窗口
			"grademgr toolbar button#add": {
				click: function(button) {
					Ext.create("School.view.school.AddGrade", {
						itemId: "add"
					}).show();
				}
			},

			//打开编辑窗口
			"grademgr toolbar button#edit": {
				click: function(button) {
					this.createEditWin(button);
				}
			},

			"addgrade": {
				afterrender: function(component) {
					this.getAllTeacher(component);
				}
			},

			//保存新增或者编辑的年级
			"addgrade form button#save": {
				click: function(button) {
					this.saveGrade(button);
				} 
			},

			//删除年级
			"grademgr toolbar button#delete": {
				click: function(button) {
					this.deleteGrade(button);					
				} 
			},

			//年级管理的学校选择下拉框监听事件
			"grademgr toolbar combobox#gradecombo": {
				change: function(_this, newValue, oldValue, eOpts) {
					zy_schoolId = newValue;
					Ext.ComponentQuery.query("grademgr")[0].getStore().reload({
						params: {
							schoolId: zy_schoolId
						}
					});
				}
			},

			//查看与当前年级相关的班级
			"grademgr actioncolumn": {
				itemclick: function(grid, rowIndex, colIndex) {
					this.viewClasses(grid, rowIndex, colIndex);
				}
			}

		});
	
	},

	createEditWin: function(button) {

		"use strict"; //开启严格模式

		var grid = button.up("grid"),
			record = grid.getSelectionModel().getSelection(), 
			win = {};

		//如果没有选择需要编辑的年级，则提示用户
		if(!record[0]) {
			School.util.Util.showErrorMsg("请选择一个需要编辑的年级！");
			return 0;
		}

		win = Ext.create("School.view.school.AddGrade", {
			itemId: "edit",
			teacherId: record[0].get("tScTeacherId"),
			title: "编辑年级"
		}).show();

		//用loadRecord把用户选中的记录填入到表单当中
		win.down("form").loadRecord(record[0]); 
	},

	getAllTeacher: function(component) {

		var teaCombobox = component.down("combobox"),
			teaStore = teaCombobox.getStore(),
			teacherId = component.teacherId;

		teaStore.reload({
			params: {
				schoolId: zy_schoolId
			},
			callback: function(records, options, success) {
				teacherId || teaCombobox.setValue(teacherId);		
			}
		});


	},

	saveGrade: function(button) {

		"use strict";

		var win = button.up("window"),
			store = Ext.ComponentQuery.query("grademgr")[0].getStore(),
			url = "grade/updateGrade.action",
			formValues = win.down("form").getForm().getValues(); 

		if(!win.down("form").getForm().isValid()) {
			School.util.Util.showErrorMsg("请正确填写好表单");
			return ;
		}

		if(win.getItemId() === "add") {
			delete formValues.id;
			formValues.tScSchoolId = zy_schoolId;
			url = "grade/createGrade.action";
		}

		School.util.Update.onSaveButtonClick(win, url, store, formValues);
	},

	deleteGrade: function(button) {

		"use strict";

		var grid = button.up("gridpanel"),
			records = grid.getSelectionModel().getSelection(),
			store = grid.getStore(),
			url = "grade/deleteGrade.action",
			params = {};

		//假如没有选择任何一行，则提示用户，并且退出删除操作
		if(records.length === 0) {
			School.util.Util.showWarningMsg("请选择一个要删除的年级！");
			return;
		}

		//获取学校id
		params = {id: records[0].get("id")}; 
		
		//提示用户是否确定删除
		School.util.Util.confirm("删除年级", function(buttonId) {
			if(buttonId !== "yes") { //如果不确定，则退出删除操作
				return;
			}
			//如果确定，则开始删除
			School.util.Update.onDeleteButtonClick(store, url, params);
		});
	},

	viewClasses: function(grid, rowIndex, colIndex) {

		"use strict";

		var record = grid.getStore().getAt(rowIndex), 
			gradeName = record.get("name"),
			classmgr = {}, 
			mainPanel = Ext.ComponentQuery.query("mainpanel")[0], 
			title = '班级管理',
			xtype = "classmgr";	

		//更新全局变量
		zy_gradeRec = grid.getStore();
		zy_gradeId = record.get("id");
		
		//把班级管理面板添加进来
		School.util.AddTab.addTab(mainPanel, title, xtype);

		classmgr = Ext.ComponentQuery.query("classmgr")[0];

		//选中当前的年级
		classmgr.down("#classcombo").getStore().reload({
			callback: function() {
				classmgr.down("#classcombo").setValue(zy_gradeId);
			}
		});

		//重新加载班级数据
		classmgr.getStore().reload({
			params: {
				gradeId: zy_gradeId
			},
			callback: function() {
				//显示当前年级名称
				classmgr.down("label#gradeName").setText(gradeName);
			}
		});

	}
});