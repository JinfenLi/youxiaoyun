/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 学科管理的控制器
 * 
 */
Ext.define("School.controller.course.CourseMgr", {
	extend: "Ext.app.Controller",
	views: [
		"school.CourseMgr",
		"school.CurriculaMgr"
	],
	init: function(application) {
		this.control({

			"coursemgr": {
				afterrender: function(component) {
					component.getStore().reload();
				}
			},

			//打开新增窗口
			"coursemgr toolbar button#add": {
				click: function(button) {
					Ext.create("School.view.school.AddCourse", {
						itemId: "add"
					}).show();
				}
			},

			//打开编辑窗口
			"coursemgr toolbar button#edit": {
				click: function(button) {					 	
					this.createEditWin(button);
				}
			},

			//addcourse实例化后自动去请求数据，获取所有教师
			"addcourse": {
				afterrender: function(component) {
					this.getAllTeacher(component);
				}
			},

			//保存新增或者编辑的学科
			"addcourse form button#save": {
				click: function(button) {
					this.saveCourse(button);
				} 
			},



			//删除学科
			"coursemgr toolbar button#delete": {
				click: function(button) {
					this.deleteCourse(button);					
				} 
			},

			//查看该科目的相关课程
			"coursemgr actioncolumn": {
				itemclick: function(grid, rowIndex, colIndex) {
					this.viewCurricula(grid, rowIndex);
				}
			}
		});
	
	},

	createEditWin: function(button) {

		"use strict";

		var grid = button.up("gridpanel"),
			record = grid.getSelectionModel().getSelection(),
			win = {},
			trunk = ""; 

		if(!record.length) {
			School.util.Util.showWarningMsg("请选择需要编辑的学科！");
			return 0;
		}

		win = Ext.create("School.view.school.AddCourse", {
			title: "编辑学科",
			itemId: "edit",
			teacherId: record[0].get("tScTeacherId")
		});

		win.down("form").loadRecord(record[0]);

		trunk = record[0].get("trunk") ? "1" : "0";
	 	win.down("#trunk").setValue({trunk: trunk});
	},

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

	saveCourse: function(button) {

		"use strict";

		var win = button.up("window"),
			store = Ext.ComponentQuery.query("coursemgr")[0].getStore(),
			url = "subject/updateSubject.action",
			formValues = win.down("form").getForm().getValues(); 

		if(!win.down("form").getForm().isValid()) {
			School.util.Util.showErrorMsg("请正确填写好表单");
			return ;
		}

		if(win.getItemId() === "add") {
			delete formValues.id;
			formValues.tScSchoolId = zy_schoolId;
			url = "subject/createSubject.action";
		}

		School.util.Update.onSaveButtonClick(win, url, store, formValues);

	},

	deleteCourse: function(button) {

		"use strict";

		var grid = button.up("gridpanel"), 
			records = grid.getSelectionModel().getSelection(), 
			store = grid.getStore(), 
			url = "subject/delectSubject.action", 
			params = {};

		//假如选择学科，则提示用户，并且退出删除操作
		if(records.length === 0) {
			School.util.Util.showWarningMsg("请选择一个要删除的学科！");
			return;
		}

		//获取学科id
		params = {subjectId: records[0].get("id")}; 
		
		//提示用户是否确定删除
		School.util.Util.confirm("删除学科", function(buttonId) {
			if(buttonId !== "yes") { //如果不确定，则退出删除操作
				return;
			}
			//如果确定，则开始删除
			School.util.Update.onDeleteButtonClick(store, url, params);
		});
	},

	viewCurricula: function(grid, rowIndex) {

		"use strict";

		var rec = grid.getStore().getAt(rowIndex), 
			curriculaName = rec.get("name"),
			curriculamgr = {},
			mainPanel = Ext.ComponentQuery.query("mainpanel")[0], 
			title = "课程管理",	
			xtype = "curriculamgr"; 

		//更新与学科有关的全局变量
		zy_subjectRec = grid.getStore();
		zy_subjectId = rec.get("id");
		
		//把别名为curricualmgr的实例添加到tabpanel里面
		School.util.AddTab.addTab(mainPanel, title, xtype);

		curriculamgr = Ext.ComponentQuery.query("curriculamgr")[0];	

		//重新加载班级数据
		curriculamgr.getStore().reload({
			params: {
				subjectId: zy_subjectId
			},
			callback: function() {
				//显示科目名称
				curriculamgr.down("label#courseName").setText(rec.get("name"));
			}
		});		

		//选中当前的科目
		curriculamgr.down("#curriculacombo").getStore().reload({
			callback: function() {
				curriculamgr.down("#curriculacombo").setValue(zy_subjectId);
			}
		});

		
	}


});