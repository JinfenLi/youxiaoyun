/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 课程管理的控制器
 * 
 */

Ext.define("School.controller.course.CurriculaMgr", {

	extend: "Ext.app.Controller",

	requires: [
		"Ext.data.Model",
		"Ext.data.Store"
	],

	views: [
		"school.CourseMgr",
		"school.CurriculaMgr"
	],

	init: function(application) {
		this.control({

			//打开新增课程的窗口
			"curriculamgr toolbar button#add": {
				click: function(button) {
					this.createAddWin(button);
				}
			},

			//打开编辑课程窗口
			"curriculamgr toolbar button#edit": {
				click: function(button) {
					this.createEditWin(button);
				}
			},

			//请求所有教师
			"addcuricula": {
				afterrender: function(component) {
					this.getAllTeacher(component);
				}
			},

			//保存新增或者编辑的课程
			"addcuricula form button#save": {
				click: function(button) {
					this.saveCurricula(button);									
				} 
			},

			"addmulticurricula form button#save": {
				click: function(button) {
					this.saveMultiCurricula(button);
				}
			},

			//删除课程
			"curriculamgr toolbar button#delete": {
				click: function(button) {
					this.deleteCurricula(button);					
				} 
			},

			//课程管理的科目选择下拉框监听事件
			"curriculamgr toolbar combobox#curriculacombo": {
				change: function(_this, newValue, oldValue, eOpts) {
						this.changeCourse(_this, newValue);				
				}
			}
			
		});
	
	},


	//打开新增窗口
	createAddWin: function(button) {

		"use strict";

		var curriculacombo = button.up("gridpanel").down("#curriculacombo"),
			gradeStore = Ext.create("School.store.school.GradeMgr"),
			grades = [];

		//将选择的课程id存储到全局变量zy_subjectId里面
		zy_subjectId = curriculacombo.getValue();

		gradeStore.reload({

			callback: function() {

				var win = {};

				for(var i = 0, len = gradeStore.getCount(); i < len; i++) {
					grades.push({
						name: "gradesName",
						boxLabel: gradeStore.getAt(i).get("name"),
						inputValue: gradeStore.getAt(i).get("name")
					});
				}

				win = Ext.create("School.view.school.AddMultiCurricula", {
					itemId: zy_subjectId
				});

				win.down("#gradecheckbox").add(grades);

				win.down("#coursecombo").getStore().reload({
					callback: function() {
						win.down("#coursecombo").setValue(zy_subjectId);
					}
				});
			}
		});
	},

	//打开编辑窗口
	createEditWin: function(button) {

		"use strict";

		var grid = button.up("gridpanel"),
			record = grid.getSelectionModel().getSelection(),
			win = {};

		if(!record.length) {
			School.util.Util.showWarningMsg("请选择需要编辑的课程！");
			return 0;
		}

		win = Ext.create("School.view.school.AddCurricula", {
			title: "编辑课程",
			teacherId: record[0].get("tScTeacherId"),
			itemId: "edit"
		}).show();

		win.down("form").loadRecord(record[0]);
	},

	//获取所有教师
	getAllTeacher: function(component) {

		"use strict";

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

	//保存单个课程的
	saveCurricula: function(button) {
		
		"use strict";

		var win = button.up("window"),
			store = Ext.ComponentQuery.query("curriculamgr")[0].getStore(),
			url = "curricula/updateCurricula.action",
			formValues = win.down("form").getForm().getValues(); 

		if(!win.down("form").getForm().isValid()) {
			School.util.Util.showErrorMsg("请正确填写好表单");
			return ;
		}

		if(win.getItemId() === "add") {
			formValues.tScSubjectId = zy_subjectId;
			url = "curricula/createCurricula.action";
		}

		School.util.Update.onSaveButtonClick(win, url, store, formValues);

	},

	//保存批量课程的
	saveMultiCurricula: function(button) {
		
		"use strict";

		var win = button.up("window"),
			store = Ext.ComponentQuery.query("curriculamgr")[0].getStore(),
			url = "curricula/createCurriculas.action",
			formValues = win.down("form").getForm().getValues(); 

		formValues.name = win.down("#coursecombo").getRawValue(); 

		if(!win.down("form").getForm().isValid()) {
			School.util.Util.showErrorMsg("请正确填写好表单");
			return ;
		}


		School.util.Update.onSaveButtonClick(win, url, store, formValues);
	},

	//删除课程
	deleteCurricula: function(button) {

		"use strict";

		var grid = button.up("gridpanel"),
			records = grid.getSelectionModel().getSelection(),
			store = grid.getStore(), 
			url = "curricula/delectCurricula.action",
			params = {};

		//假如没有选择课程，则提示用户，并且退出删除操作
		if(records.length === 0) {
			School.util.Util.showWarningMsg("请选择一个要删除的课程！");
			return;
		}

		//获取学校id
		params = {curriculaId: records[0].get("id")}; 
		
		//提示用户是否确定删除
		School.util.Util.confirm("删除课程", function(buttonId) {
			//如果确定，则开始删除
			if(buttonId === "yes") { 
				School.util.Update.onDeleteButtonClick(store, url, params);
			}
			
		});
	},

	//学科下拉框改变时回调函数
	changeCourse: function(_this, newValue) {

		"use strict";

		zy_subjectId = newValue;

		Ext.ComponentQuery.query("curriculamgr")[0].getStore().reload({
			params: {
				subjectId: zy_subjectId
			},
			callback: function() {
				var courseName = _this.up("curriculamgr").down("label#courseName");
				courseName.setText(_this.getRawValue());
			}
		});
	}


});