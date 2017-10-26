/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 部门管理的控制器
 * 
 */
Ext.define("School.controller.teacher.DepartMgr", {
	extend: "Ext.app.Controller",
	views: [
		"school.DepartMgr"
	],

	init: function(application) {

		"use strict";

		this.control({

			"departmgr": {

				afterrender: function(component) {
					component.getStore().reload();
				}
			},

			//打开新增部门窗口
			"departmgr toolbar button#add": {

				click: function(button) {
					Ext.create("School.view.teacher.AddDepart", {
						itemId: "add"
					});
				}
			},

			//打开编辑部门窗口
			"departmgr toolbar button#edit": {
				click: function(button) {

					var record, win;

					//如果用户没有选择任何记录，则提醒并且编辑操作退出
					record = button.up("gridpanel").getSelectionModel().getSelection()[0];
					if( !record ) {
						School.util.Util.showWarningMsg("请选择需要编辑的部门！");
						return 0;
					}

					win = Ext.create("School.view.teacher.AddDepart", {
						title: "编辑部门",
						itemId: "edit",
					});

					//把用户选择的记录加载到表单里
					win.down("form").loadRecord(record);
				}
			},

			//保存新增或者编辑的部门
			"adddepart form button#save": {
				click: function(button, e, options) {

					var win = button.up("adddepart"),
						grid = Ext.ComponentQuery.query("departmgr")[0],
						formValues = button.up("form").getForm().getValues(),
						store = grid ? grid.getStore() : null,
						url = "";

					//检验表单是否有效，如果无效则提示用户并且退出保存提交操作
					if( !win.down("form").getForm().isValid() ) {
						School.util.Util.showWarningMsg("请正确填好表单！");
						return 0;
					} 

					formValues.tScSchoolId = zy_schoolId;					
					url = win.getItemId() === "edit" ? "department/updateDepartment.action" : 
						  "department/createDepartment.action";
					
					School.util.Update.onSaveButtonClick(win, url, store, formValues);	
				} 
			},

			//删除部门
			"departmgr toolbar button#delete": {
				click: function(button) {
					var grid = button.up("gridpanel"),
						records = grid.getSelectionModel().getSelection(),
						store = grid.getStore(),
						url = "department/deleteDepartment.action", 
						params = {};

					//假如没有选择任何一行，则提示用户，并且退出删除操作
					if(records.length === 0) {
						School.util.Util.showWarningMsg("请选择一个要删除的部门！");
						return;
					}

					//获取学校id
					params = {id: records[0].get("id")}; 
					
					//提示用户是否确定删除
					School.util.Util.confirm("删除部门", function(buttonId) {
						if(buttonId !== "yes") { //如果不确定，则退出删除操作
							return;
						}
						//如果确定，则开始删除
						School.util.Update.onDeleteButtonClick(store, url, params);
					});
					
					
				} 
			},

			//弹出编辑部门人员窗口
			"departmgr actioncolumn#edit": {
				itemclick: function(grid, rowIndex, colIndex) {

					var ids  = [], 
						departName = grid.getStore().getAt(rowIndex).get("name"),
						departId = grid.getStore().getAt(rowIndex).get("id"),
						teachers = grid.getStore().getAt(rowIndex).get("teachers"); 
					
					//得到该部门人员的id，并且放到了ids里
					for(var i = 0; i < teachers.length; i++) {
						ids.push(teachers[i].id);
					}
				
					Ext.create("School.view.teacher.DepartStaff", {
						title: "编辑部门人员(" + departName + ")",
						datas: {
							grid: grid, 
							rowIndex: rowIndex,
							colIndex: colIndex,
							teachersId: ids.join(","),
							departId: departId
						}
					});
				}
			},

			//全选其他人员
			"departstaff #allNotDept": {
				change: function(me, newValue) {
					newValue ? this.selectAll(".teachers .others input[type=checkbox]") :
							this.deSelectAll(".teachers .others input[type=checkbox]");
				}
			},

			//全选部门人员
			"departstaff #allDept": {
				change: function(me, newValue) {
					newValue ? this.selectAll(".teachers .staffs input[type=checkbox]") :
							this.deSelectAll(".teachers .staffs input[type=checkbox]");
				}
				
			},

			//添加部门人员
			"departstaff button#add": {
				click: function(button) {
					var selector = ".teachers .others input[type=checkbox]",
						type = 1;
					this.saveStaff(button, selector, type);
					
				}
			},

			//删除部门人员
			"departstaff button#delete": {
				click: function(button) {
					var selector = ".teachers .staffs input[type=checkbox]",
						type = 2;
					this.saveStaff(button, selector, type);
				}
			},

			//部门人员列表加载数据
			"window dataview": {
				afterrender: function(component) {
					component.getStore().reload();
				}
			}

		});
	
	},


	//全选
	selectAll: function(selector) {
		$(selector).each(function() {
			this.checked = true;
		});
	},

	//取消全选
	deSelectAll: function(selector) {
		$(selector).each(function() {
			this.checked = false;
		});
	},

	/*
		* @功能说明：保存编辑后的部门人员
		* @参数说明：
		* button: 当前触发事件的按钮
		* selector: jquery选择器，用来遍历选中了的教师
		* type: 提交类型，1为新增，2为移除
		* 
	*/
	saveStaff: function(button, selector, type) {
		var datas = button.up("#departstaff").datas, 
			teacherIds = [],
			index = 0; 

		//遍历复选框，取得所有选中的教师的id
		$(selector).each(function() {
			if(this.checked === true) {
				teacherIds.push(this.id);
			}
		});

		//提交到服务器的参数集合
		var params = {
			option: type,
			departmentId: datas.departId
		};

		//如果没有选中任何教师，则提示并且退出
		if(teacherIds.length === 0) {
			School.util.Util.showErrorMsg("您没有选中任何教师，无法提交!");
			return 0 ;
		} 

		//因为服务器是接受单个id进行保存的，因此用循环提交
		for(var i = 0, len = teacherIds.length; i < len; i++) {

			params.teacherId = teacherIds[i];

			Ext.Ajax.request({

				url: zyHost + "department/assignTeacherToDepartment.action",
				method: "POST",
				timeout: 60000,
				params: params,

				callback: function(options, success, response) {

					index++; 

					if(index === teacherIds.length) {
						Ext.ComponentQuery.query("departmgr")[0].getStore().reload({
							callback: function() {
									button.up("departstaff").close();
									Ext.ComponentQuery.query("departmgr actioncolumn#edit")[0].fireEvent("itemclick",
											datas.grid, datas.rowIndex, datas.colIndex);
							}
						});
					
					}
				}
			});
		}

	}

});