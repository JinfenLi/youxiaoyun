/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 部门管理和教师管理的控制器
 * 
 */
Ext.define("School.controller.teacher.TeacherMgr", {
	extend: "Ext.app.Controller",
	views: [
		"teacher.TeacherMgr"
	],

	init: function(application) {

		this.control({
				
			//页面渲染完成后请求数据
			"teachermgr": {
				afterrender: function(component) {
					component.getStore().reload();
				}
			},

			//下载教师模板
			"teachermgr toolbar button#download": {
				click: function(button) {
					var url = "teacher/downloadTeacherInfo.action";
					School.util.Util.downloadFile(url);
				}
			},

			//打开上传教师资料的新窗口
			"teachermgr toolbar button#upload": {
				click: function(button) {
					Ext.create("School.view.school.UploadTeacher").show();
				}
			},

			//提交教师资料
			"uploadteacher button#save": {
				click: function(button) {
					this.uploadTeacher(button);
				}
			},

			//打开新增窗口
			"teachermgr toolbar button#add": {
				click: function(button) {
					Ext.create("School.view.teacher.AddTeacher", {
						itemId: "add"
					});
				}
			},

			//打开编辑窗口
			"teachermgr toolbar button#edit": {
				click: function(button) {
					this.createEditWin(button);
				}
			},

			//保存新增或者编辑的教师
			"addteacher form button#save": {
				click: function(button) {
					this.saveTeacher(button);
				} 
			},

			//删除教师
			"teachermgr toolbar button#delete": {
				click: function(button) {
					this.deleteTeacher(button);					
				} 
			},

			//创建重置教师密码的窗口
			"teachermgr #resetPsd": {
				itemClick: function(teacherId) {

					Ext.create("School.view.teacher.ResetPsd", {
						datas: {
							teacherId: teacherId	
						}
					});
				}
			},

			//保存密码
			"resetpsd #save": {
				click: function(btn) {						
					this.updatePsd(btn.up("window"));
				}				
			}


		});
	
	},

	uploadTeacher: function(button) {

		"use strict"; //启用严格模式

		var url = "teacher/uploadTeacherInfo.action",
			msg = "该文件类型不是excel文件，请重新选择！",
			store = Ext.ComponentQuery.query("teachermgr")[0].getStore(),
			params =  {
            	file: button.up("form").down("filefield").getValue(),
            	schoolId: zy_schoolId
            };

		School.util.Util.uploadFile(button, url, params, msg, store);

		//刷新教师数据集
		setTimeout(function() {
			zy_teacherStore && zy_teacherStore.reload();
		}, 10000);
	},

	createEditWin: function(button) {

		"use strict"; //启用严格模式

		var record = button.up("gridpanel").getSelectionModel().getSelection()[0],
			win = {};

		//如果用户没有选择任何记录，则提醒并且编辑操作退出
		if( !record ) {
			School.util.Util.showWarningMsg("请选择需要编辑的教师！");
			return 0;
		}

		win = Ext.create("School.view.teacher.AddTeacher", {
			title: "编辑教师",
			itemId: "edit",
		});

		//把用户选择的记录加载到表单里
		win.down("form").loadRecord(record);
	},

	saveTeacher: function(button) {

		"use strict"; //启用严格模式

		var win = button.up("addteacher"),
			grid = Ext.ComponentQuery.query("teachermgr")[0],
			formValues = button.up("form").getForm().getValues(),
			store = grid ? grid.getStore() : null,
			sex = button.up("form").down("#sex"), 
			url = "teacher/updateTeacher.action";
		

		//添加sex字段
		formValues.sex = sex.getValue().sex || "";


		//如果是新增教师
		if(win.getItemId() === "add") {

			delete formValues.id;
			delete formValues.tScClassId;
			delete formValues.tScGradeId;

			url = "teacher/addTeacher.action";

			//如果是新增管理员
			if(win.schoolId && win.isAuthc) {
				formValues.tScSchoolId = win.schoolId;
				formValues.isAuthc = win.isAuthc;

			//否则
			} else {
				formValues.tScSchoolId = zy_schoolId;
				formValues.isAuthc = "否";
			}						
		}

		//检验表单是否正确填写好
		if(!win.down("form").getForm().isValid()) {
			School.util.Util.showErrorMsg("请正确填好表单！");
			return;
		}					

		School.util.Update.onSaveButtonClick(win, url, store, formValues);

	},

	deleteTeacher: function(button) {

		"use strict"; //启用严格模式

		var grid = button.up("teachermgr"),
			records = grid.getSelectionModel().getSelection(),
			store = grid.getStore(), 
			url = "teacher/delete.action",
			params = {};

		//假如没有选择任何一行，则提示用户，并且退出删除操作
		if(records.length === 0) {
			School.util.Util.showWarningMsg("请选择一个要删除的教师！");
			return;
		}

		//获取学科id
		params.teacherId = records[0].get("id"); 
		
		//提示用户是否确定删除
		School.util.Util.confirm("删除教师", function(buttonId) {
			if(buttonId !== "yes") { //如果不确定，则退出删除操作
				return;
			}
			//如果确定，则开始删除
			School.util.Update.onDeleteButtonClick(store, url, params);
		});
	},

	updatePsd: function(win) {

		"use strict";

		var teacherId = win.datas.teacherId,
			form = win.down("form"),
			values = form.getForm().getValues();

		if(!form.getForm().isValid()) {
			School.util.Util.showErrorMsg("请正确填好表单");
			return 0;
		}

		if(values.newPassword !== values.repeatPassword) {
			School.util.Util.showErrorMsg("两次密码不一致！");
			return 0;
		}

		values.userId = teacherId;

		Ext.Ajax.request({
			url: zyHost + "user/resetPassword.action",
			timeout: 60000,
			params: values,
			success: function(conn,response,options,eOpts) {

				var result = School.util.Util.decodeJSON(conn.responseText);

				if(result.success) {					
					win.close();
					School.util.Util.showSuccessMsg("密码修改成功!");
					return 0;
				}

				School.util.Util.showErrorMsg("密码修改失败：" + result.msg);		
			},

			failure: function(conn,response,options,eOpts) {
				School.util.Util.showErrorMsg("链接服务器失败！");			
			}
		});
	}
});