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

	// refs: [{
	// 	ref: 'teacherMgr',
	// 	selector: 'teachermgr'
	// }],

	init: function(application) {

		this.control({

			//换页时，编辑模式取消
			"teachermgr pagingtoolbar": {
				change: function(self, pageData, eOpts) {
					var grid = Ext.ComponentQuery.query("teachermgr")[0];
					if(grid.query("toolbar")[0].down("button#editAll")){
						grid.query("toolbar")[0].down("button#editAll").show();
						grid.query("toolbar")[0].down("button#saveAll").hide();
						grid.query("toolbar")[0].down("button#cancelEdit").hide();
						grid.editingPlugin.editStatus = false;
					}
				}
			},
				
			//页面渲染完成后请求数据
			"teachermgr": {
				afterrender: function(component) {
					component.getStore().reload();
				}
			},

			//搜索教师
			"teachermgr toolbar button#searchTeachers": {
				click: function(button) {
					this.searchTeachers(button);
				}
			},

			//查看所有教师
			"teachermgr toolbar button#getAllTeachers": {
				click: function(button) {
					this.getAllTeachers(button);
				}
			},

			//表格内修改所有老师
			"teachermgr toolbar button#editAll": {
				click: function(button) {
					this.editAllTeacher(button);
				}
			},
			//表格内修改所有老师，对应的保存按钮
			"teachermgr toolbar button#saveAll": {
				click: function(button) {
					this.saveAllTeacher(button);
				}
			},
			//表格内修改所有老师，对应的取消按钮
			"teachermgr toolbar button#cancelEdit": {
				click: function(button) {
					this.cancelEdit(button);
				}
			},

			//下载教师模板
			"teachermgr toolbar button#download": {
				click: function(button) {
					var url = "teacher/downloadTeacher.action";
					var params = '<input type="text" name="schoolId" value="'+ zy_schoolId +'"/>';
					School.util.Util.downloadFile(url, params);
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

			//编辑老师时清空
			"addteacher form button#teacherReset": {
				click: function(button) {
					this.resetTeacher(button);
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

	searchTeachers: function(button) {
		var grid = button.up("teachermgr");
		var searchKeywords = grid.query("toolbar")[0].down("#searchKeywords").getValue();
		console.log(searchKeywords);
		var params =  {
			schoolId: zy_schoolId,
			keyword: searchKeywords
		}

		grid.store.setProxy(School.util.Util.setProxy('teacher/findLikeByName.action', params, 'teachers', "totalCount"));
		grid.store.load({
			params:  {
				schoolId: zy_schoolId,
				keyword: searchKeywords,
				start: 0
			},
			callback : function(r, options, success) {
				console.log(r);
				console.log(options);
				console.log(success); 
				if (success == false) {    
					Ext.Msg.alert("提示","无法找到相应学生");   
				}   
				else{   
					// Ext.util.Util.decodeJSON();
//					console.log(Ext.util.Util.decodeJSON(options.response.responseText));  
					console.log("error");
				}    
			}    
		});
		grid.store.loadPage(1);
	},

	getAllTeachers: function(button) {
		var grid = button.up("teachermgr");
		var searchKeywords = grid.query("toolbar")[0].down("#searchKeywords").getValue();
		console.log(searchKeywords);
		var params =  {
			schoolId: zy_schoolId
		}

		grid.store.setProxy(School.util.Util.setProxy('teacher/getAllTeacher.action', params, 'teachers', "totalCount"));
		grid.store.load({
			params:  {
				schoolId: zy_schoolId,
				start: 0
			}
		});
		grid.store.loadPage(1);
	},

	editAllTeacher: function(button) {
		var grid = button.up("teachermgr");
		grid.editingPlugin.editStatus = true;
		grid.query("toolbar")[0].down("button#editAll").hide();
		grid.query("toolbar")[0].down("button#saveAll").show();
		grid.query("toolbar")[0].down("button#cancelEdit").show();
	},

	saveAllTeacher: function(button) {
		var grid = button.up("teachermgr");
		var store = grid ? grid.getStore() : null;
		var mr = store.getModifiedRecords().slice(0);  
		var jsonArray = []; 

		// 在view层editor里已经判断邮箱了 
		// var bool = true;
		// var pattern = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
		Ext.each(mr,function(item){
			// if(!pattern.test(item.data.email)) {
			// 	School.util.Util.showErrorMsg("请输入正确的邮箱格式。例如: example@example.com");
			// 	bool = false;
			// 	return;
			// }
			jsonArray.push(item.data);  
		});
		// if(!bool) return;
		var stringJson = JSON.stringify(jsonArray);
		url = "teacher/updateTeachers.action";

		School.util.Util.confirm("修改教师", function(buttonId) {
			if(buttonId !== "yes") { //如果不确定，则退出删除操作
				return;
			}
			// 如果确定，则开始删除
			
			School.util.Update.onSaveSeveralButtonClick(grid, url, store, stringJson,'','',function(result) {
				console.log(result);
				if(result.success && result.teachers.length) {
					var msgStr = '<ol style="margin:0; padding:0;">';
					var i = 1;
					result.teachers.forEach(function(value) {
						msgStr += "<li>" + (i++) + ". 账号: " + value.name + " 已被 " + value.schoolName + " 学校教师录入 ";
					});
					msgStr += '</ol><p>如需修改请联系QQ205303602调整</p>';
					Ext.Msg.alert("提示",msgStr);

					// Ext.Msg.alert("提示","该账号已被XX学校教师录入，如需修改请联系QQ205303602调整");
				}
				//更新老师store
				zy_teacherStore.reload();
				if(grid.query("toolbar")[0].down("button#editAll")) {
					grid.query("toolbar")[0].down("button#editAll").show();
					grid.query("toolbar")[0].down("button#saveAll").hide();
					grid.query("toolbar")[0].down("button#cancelEdit").hide();
					grid.editingPlugin.editStatus = false;
				}	
			});
		});
	},

	cancelEdit: function(button) {
		var grid = button.up("teachermgr");
		var store = grid ? grid.getStore() : null;
		store.reload();
		grid.query("toolbar")[0].down("button#editAll").show();
		grid.query("toolbar")[0].down("button#saveAll").hide();
		grid.query("toolbar")[0].down("button#cancelEdit").hide();
		grid.editingPlugin.editStatus = false;
	},

	//因为表单设置的隐藏的id等信息，所以清空重写
	resetTeacher: function(button) {
		button.up('window').down('#idCard').setValue('');
		button.up('window').down('#email').setValue('');					
		button.up('window').down('#phone').setValue('');
		button.up('window').down('#sex').reset();					
		button.up('window').down('#teacherName').setValue('');
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

		this.uploadFile(button, url, params, msg, store);

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
		win.down('#sex').setValue({sex:record.data.sex});
	},

	saveTeacher: function(button) {

		"use strict"; //启用严格模式

		if(!button.up("form").getForm().isValid()) {
			School.util.Util.showErrorMsg('请正确填好表单！');
			return;
		}

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
		// if(!win.down("form").getForm().isValid()) {
		// 	School.util.Util.showErrorMsg("请正确填好表单！");
		// 	return;
		// }					

		this.onSaveButtonClick(win, url, store, formValues,'','',function() {
						//更新老师store
			zy_teacherStore.reload();		
		});

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
			School.util.Update.onDeleteButtonClick(store, url, params,'',function() {
				//更新老师store
				zy_teacherStore.reload();
			});
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
	},

	// override util's method
  onSaveButtonClick : function(win, url, store, params, add, reloadParams, fn) {
    var self = this; // 重写了这一行
    // console.log("override onSaveButtonClick method");

    // 添加一层遮罩
    win.mask("正在保存，请耐心等待...","splashscreen");
    Ext.Ajax.request({
      url: zyHost + url,
      method: "POST",
      timeout: 60000,
      params: params,
      success: function(response, options) {

        var result = School.util.Util.decodeJSON(response.responseText);
          
        win.unmask();

        // 如果保存失败，则退出
        if(!result.success) {
          self.showErrorMsg(result, "保存失败!\n"); // 重写了这一行
          win.close();
          return;         
        }
        win.close();
        School.util.Util.showSuccessMsg("保存成功！" + (add ? add : ""));

        // 重新加载数据
        if(store) {
          if(reloadParams) {
            store.load({
              params: reloadParams
            });
          }else {
            store.reload();
          }
        }

        if(fn) {
          fn();
        }
      },

      failure: function(response, options) {
        win.unmask();
        School.util.Util.showErrorMsg("链接服务器失败！");
      }
    });   
  },

  // override util's method
  showErrorMsg: function(result, msg) {
  	console.log(result)
    var type = result.teachers ? "教师" : "学生";
    var typeObjArr = result.teachers ? result.teachers : result.students;
    typeObj = typeObjArr[0];
    Ext.Msg.show({
      title: "错误",
      msg: msg + "该账号已被 " + typeObj.schoolName + " 的" + type + "录录入,\n如需修改请联系 QQ205303602 调整",
      icon: Ext.Msg.ERROR,
      buttons: Ext.Msg.OK
    });
  },

  // override util's method
  uploadFile: function(button, url, params, msg, store, type, fn) { 
    var self = this  
    var form = button.up('form').getForm(),
        type = type || ".xls", 
        fileValue = button.up("form").down("filefield").getValue(), 
        length = fileValue.length;

    //验证表单是否有效，如果无效则提示
    if( !form.isValid()) {
      School.util.Util.showErrorMsg("请选择文件");
      return 0;
    }

    //判断文件类型是否匹配
    if(type.indexOf(fileValue.substr(length-4, 5).toLowerCase()) === -1) {
      School.util.Util.showErrorMsg(msg);
      return;
    }

    form.submit({
      url: zyHost + url,
      params: params,
      method: "post",
      waitMsg: '正在上传...',
      success: function(response, options) {
        var result = School.util.Util.decodeJSON(options.response.responseText);
        self.showUploadFileSuccessMsg(result.teachers);
        button.up("window").close();
        
        //如果传进了store,则刷新
        store && store.reload();

        //如果传进了回调函数则执行
        fn && fn();      
      },
      failure: function() {
        School.util.Util.showErrorMsg("文件上传失败！");
      }
    });
  },

  showUploadFileSuccessMsg: function(teachers) {

    var msg = '上传文件成功! '
    if (teachers.length > 0) {
      msg += '但以下教师导入失败: <ol style="margin:0; padding:0;">'
      teachers.forEach(function(teacher) {
        msg += '<li>' + teacher.name + ' 已被 ' + teacher.schoolName + ' 的教师录录入,</li>' 
      })
      msg += '如需修改请联系 QQ205303602 调整</ol>'
    }
    Ext.Msg.show({
      title: "成功",
      msg: msg,
      icon: Ext.Msg.INFO,
      buttons: Ext.Msg.OK
    })
  },
});