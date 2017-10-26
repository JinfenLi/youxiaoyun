/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 年级管理和班级管理的控制器
 * 
 */
Ext.define("School.controller.school.ClassMgr", {
	extend: "Ext.app.Controller",
	views: [
		"school.ClassMgr"
	],
	init: function(application) {

		"use strict";

		this.control({
				
			//打开批量新增班级窗口
			"classmgr toolbar button#add": {
				click: function(button) {
					this.openAddWin(button);
				}
			},

			//打开编辑班级的窗口
			"classmgr toolbar button#edit": {
				click: function(button) {
					this.openEditWin(button);					
				}
			},

			//选择当前年级
			"addmulticlass #gradecombo": {
				afterrender: function(component) {
					this.setCurrentGrade(component);
				}
			},

			//班主任下拉框自动请求数据
			"addclass": {
				afterrender: function(component) {
					this.getAllTeacher(component);
				}
			},

			//保存新增或者编辑的班级
			"addclass form button#save": {
				click: function(button) {
					this.saveClass(button);					
				} 
			},

			//保存批量新增的班级
			"addmulticlass form button#save": {
				click: function(button) {
					this.saveMultiClass(button);
				} 
			},


			//删除班级
			"classmgr toolbar button#delete": {
				click: function(button) {
					this.deleteClass(button);					
				} 
			},

			//班级管理的年级选择下拉框监听事件
			"classmgr toolbar combobox#classcombo": {
				change: function(_this, newValue, oldValue, eOpts) {					
					zy_gradeId = newValue;
					Ext.ComponentQuery.query("classmgr")[0].getStore().reload({
						params: {
							gradeId: zy_gradeId
						}
					});
				}
			}


		});
	
	},

	//打开批量新增班级的窗口
	openAddWin: function(button) {

		"use strict";

		var gradeId = button.up("classmgr").down("#classcombo").getValue();
		Ext.create("School.view.school.AddMultiClass", {
			itemId: gradeId
		});
	},

	//打开编辑班级的窗口
	openEditWin: function(button) {

		"use strict";

		var grid = button.up("gridpanel"),
			record = grid.getSelectionModel().getSelection(),
			win = {};

		if(!record.length) {
			School.util.Util.showWarningMsg("请选择需要编辑的班级！");
			return 0;
		}

		win = Ext.create("School.view.school.AddClass", {
			title: "编辑班级",
			teacherId: record[0].get("tScTeacherId"),
			itemId: "edit"
		});

		win.down("form").loadRecord(record[0]);

		win.down("form").queryById("className").setReadOnly(true);
	},

	//为批量新增班级的窗口的年级下拉框选择当前年级作为默认值
	setCurrentGrade: function(component) {
		component.getStore().reload({
			callback: function() {
				component.setValue(component.up("addmulticlass").getItemId());
			}
		});		
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

	saveClass: function(button)  {

		"use strict";

		var win = button.up("window"),
			store = Ext.ComponentQuery.query("classmgr")[0].getStore(),
			url = "clazz/updateClazz.action",
			formValues = win.down("form").getForm().getValues(); 

		if(!win.down("form").getForm().isValid()) {
			School.util.Util.showErrorMsg("请正确填写好表单");
			return ;
		}

		if(win.getItemId() === "add") {
			delete formValues.id;
			formValues.tScGradeId = zy_gradeId;
			url = "clazz/createClazz.action";
		}

		School.util.Update.onSaveButtonClick(win, url, store, formValues);
	},

	saveMultiClass: function(button)  {

		"use strict";

		var win = button.up("window"),
			store = Ext.ComponentQuery.query("classmgr")[0].getStore(),
			url = "clazz/createClazz.action",
			params = win.down("form").getForm().getValues(); 

		if(!win.down("form").getForm().isValid()) {
			School.util.Util.showErrorMsg("请正确填写好表单");
			return ;
		}

		params.level = win.down("#gradecombo").getRawValue();
		params.begin = win.down("#start").getValue();
		params.end = win.down("#end").getValue();

		School.util.Update.onSaveButtonClick(win, url, store, params);
	},

	deleteClass: function(button) {

		"use strict";
		
		var grid = button.up("gridpanel"),
			records = grid.getSelectionModel().getSelection(), 
			store = grid.getStore(), 
			url = "clazz/deleteClazz.action", 
			params = {};

		//假如没有选择任何一行，则提示用户，并且退出删除操作
		if(records.length === 0) {
			School.util.Util.showWarningMsg("请选择一个要删除的班级！");
			return;
		}

		//获取学校id
		params = {id: records[0].get("id")}; 
		
		//提示用户是否确定删除
		School.util.Util.confirm("删除班级", function(buttonId) {
			if(buttonId !== "yes") { //如果不确定，则退出删除操作
				return;
			}
			//如果确定，则开始删除
			School.util.Update.onDeleteButtonClick(store, url, params);
		});
	}

});