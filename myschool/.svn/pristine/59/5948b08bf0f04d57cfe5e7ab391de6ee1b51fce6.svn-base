/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 我的班级控制器，包含进入我的课程表的入口
 * 
 */
Ext.define("School.controller.clazz.MyClass", {
	extend: "Ext.app.Controller",
	views: [
		"school.MyClass",
		"clazz.PostMgr"
	],
	init: function(application) {

	
		this.control({
			
			//页面渲染后，是下拉框选择当前用户的的默认班级
			"myclass": {
				afterrender: function(component) {
					component.down("combobox#myclasses").setValue(zy_classId);
				}
			},

			//打开编辑窗口
			"myclass toolbar button#edit": {
				click: function(button) {
					this.createEditWin(button);
				}
			},

			//保存新增或者编辑的学生
			"addstudent button#save": {
				click: function(button) {
					this.saveStudent(button);				
				}
			},

			 //班级选择下拉框的监听事件
			"myclass combobox#myclasses": {
				change: function(_this, newValue, oldValue, eOpts) {
					this.changeClass(_this, newValue);
				}
			},
			
			//导出学生信息
			"myclass toolbar button#download": {
				click: function(button) {
					this.downloadStudent(button);
				}
			},

			//打开上传学生资料的新窗口
			"myclass toolbar button#upload": {
				click: function(button) {
					Ext.create("School.view.school.UploadStudent").show();
				}
			},

			//提交学生资料
			"uploadstudent button#save": {
				click: function(button) {
					this.uploadStudent(button);
				}
			},

			//打开课程表
			"myclass button#viewSyllabus": {
				click: function(button) {
					this.viewSyllabus(button);
				}
			},

			//打开班级帖子
			"myclass button#viewPost": {
				click: function(button) {
					this.viewPost(button);
				}
			},

			//删除指定学生
			"myclass actioncolumn": {
				itemclick: function(grid, rowIndex, colIndex) {
					this.deleteStudent(grid, rowIndex, colIndex);									
				}
			}


		});
	},

	createEditWin: function(button) {

		"use strict";

		var grid = button.up("gridpanel"),
			record = grid.getSelectionModel().getSelection(),
			win = {},
			sex = ""; 

		if(!record.length) {
			School.util.Util.showWarningMsg("请选择一个需要编辑的学生！");
			return 0;
		}

		win = Ext.create("School.view.school.AddStudent", {
			title: "编辑学生信息",
			itemId: "edit"
		});

		win.down("form").loadRecord(record[0]);

		sex = record[0].get("studentGender");
		win.down("radiogroup#student_sex").setValue({studentGender: sex});
	},

	saveStudent: function(button) {

		"use strict";

		var win = button.up("window"),
			store = Ext.ComponentQuery.query("myclass")[0].getStore(),
			url = "student/updateStudentInfo.action",
			formValues = win.down("form").getForm().getValues(); 

		if(!win.down("form").getForm().isValid()) {
			School.util.Util.showErrorMsg("请正确填写好表单");
			return ;
		}	

		School.util.Update.onSaveButtonClick(win, url, store, formValues);
	},

	changeClass: function(_this, newValue) {

		"use strict"; //启用严格模式

		//显示当前的班级
		_this.up("myclass").down("label#className").setText(_this.getRawValue());

		//如果课程表已经打开了，则刷新数据
		var mycourseSchedule = document.getElementById("mycourseSchedule");

		if(mycourseSchedule) {
			mycourseSchedule.contentWindow.getCurricula(newValue);
		}

		//刷新班级信息的数据集
		_this.up("myclass").getStore().reload({
			params: {
				clazzId: newValue
			},
			//请求成功后
			callback: function(records, options, success) {

				//如果请求失败, 则提示用户，并且退出
				if(!success) {
					School.util.Util.showErrorMsg("获取数据失败!");
					return;
				}									
			}
		});
	},

	uploadStudent: function(button) {

		"use strict";

		var url = "student/saveStudentAndParent.action",
			grid = Ext.ComponentQuery.query("myclass")[0],
			msg = "该文件类型不是excel文件，请重新选择！",
			classId = grid.down("combobox#myclasses").getValue(),
			store = grid.getStore(),
			params =  {
            	file: button.up("form").down("filefield").getValue(),
            	clazzId: classId
            };
		School.util.Util.uploadFile(button, url, params, msg, store);
	},

	downloadStudent: function(button) {

		"use strict";

		var inputs = '',
			url = "student/downloadStudentAndParentExcel.action",
			clazzId = button.up("myclass").down("#myclasses").getValue();

		inputs += '<input type="text" name="clazzId" value="' + clazzId + '"/>';
		School.util.Util.downloadFile(url, inputs);
	},

	viewSyllabus: function(button) {

		"use strict";

		var syllabus = Ext.ComponentQuery.query("syllabus")[0],
			mainPanel = Ext.ComponentQuery.query("mainpanel")[0], 
			title = '我的课表', 
			xtype = "syllabus",	
			itemId = button.up("myclass").down("#myclasses").getValue();

		if(syllabus) {
			syllabus.destroy();
		}

		School.util.AddTab.addTab(mainPanel, title, xtype, "", itemId);
	},

	viewPost: function(button) {

		"use strict";

		var postmgr = Ext.ComponentQuery.query("postmgr")[0],
			mainPanel = Ext.ComponentQuery.query("mainpanel")[0], 
			title = '班级帖子', 
			xtype = "postmgr",	
			itemId = button.up("myclass").down("#myclasses").getValue();

		if(postmgr) {
			postmgr.destroy();
		}

		School.util.AddTab.addTab(mainPanel, title, xtype, "", "postmgr");
	},

	deleteStudent: function(grid, rowIndex, colIndex) {

		"use strict";
		
		var store = grid.getStore(),
			studentId = store.getAt(rowIndex).get("studentId"),
			parentId = store.getAt(rowIndex).get("parentId"),
			requestUrl = "student/deleteStudent.action",
			params = {
				studentId: studentId,
				parentId: parentId
			};

		School.util.Util.confirm("删除学生", function(buttonId) {
			if(buttonId === "yes") {
				School.util.Update.onDeleteButtonClick(store, requestUrl, params);
			}
		});	
	}


});