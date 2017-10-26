Ext.define("School.controller.User", {
	extend: "Ext.app.Controller",
	requires: [
		"School.model.school.User"
	],
	views: [
		"User"
	],

	init: function (application) {

		"use strict";

		var me = this;

		this.control({
			
			"user #userinfo": {
				render: function(component) {

					me.getUserInfo(component);

				}
			},

			"user #userinfo #saveInfo": {

				click: function(btn) {

					var form = btn.up("form");

					if( !form.getForm().isValid() ) {
						School.util.Util.showErrorMsg("姓名不能为空！");
						return 0;
					}

					console.log(form.getForm().getValues());

					me.updateUserInfo(btn.up("window"), form.getForm().getValues());

				}
			},

			"user #userpass #savePass": {

				click: function(btn) {

					var form = btn.up("form"),
						values = form.getForm().getValues(),
						win = btn.up("window");

					if( !form.getForm().isValid() ) {
						School.util.Util.showErrorMsg("密码不能为空！");
						return 0;
					}

					if(values.newPassword !== values.newPasswordCfm) {
						School.util.Util.showErrorMsg("两次密码不一致！");
						return 0;
					}

					console.log(values);

					me.checkPassWord(values, win);
				}
			},

			"user #userpic": {
				render: function(component) {
				
					component.add([
						{
							xtype : "image",
							itemId: "oldpic",
							width : 180,
							height : 200,
							src: component.up("window").datas.picUrl ,							
							margin: "0 40"
						}, {
							xtype : "filefield",
							itemId: "newpic",
							name : "pic",
							fieldLabel : "新头像",
							labelWidth : 50,
							allowBlank : false,
							msgTarget : "under",
							anchor : "100%",
							buttonText : "选择图片..."
						}
					]);
					console.log(component.up("window").datas.picUrl);
				}
			},

			"user #userpic #savePic": {
				click: function(btn) {

					

					var value = btn.up("form").down("#newpic").getValue(),
						type = "jpeg, .jpg, .png, .gif";

					if(type.indexOf(value.substr(length-4, 5).toLowerCase()) === -1) {

						School.util.Util.showErrorMsg("你选择的图片格式不正确！");
						return 0;
					}

					me.updatePic(btn.up("form"));
				}
			}

		});

	},

	getUserInfo: function(form) {

		"use strict";

		Ext.Ajax.request({
			url: zyHost + "user/getUserInfo.action",
			timeout: 60000,

			success: function(conn,response,options,eOpts) {

				var result = School.util.Util.decodeJSON(conn.responseText).teacherInfo,
					record;

				console.log(result);



				record = School.model.school.User.create(result);				

				form.loadRecord(record);

				//form.up("window").datas = result.id;

				form.down("#sex").setValue({sex: result.sex});

				form.up("window").datas = result;
				
				
			},

			failure: function(conn,response,options,eOpts) {

				School.util.Util.showErrorMsg("链接服务器失败！");

			
			}
		});
	},

	updateUserInfo: function(win, params) {
		"use strict";

		Ext.Ajax.request({
			url: zyHost + "user/updateUserInfo.action",
			timeout: 60000,
			params: params,
			success: function(conn,response,options,eOpts) {
				var result = School.util.Util.decodeJSON(conn.responseText);
				win.close();

				Ext.ComponentQuery.query("#userName")[0].
						setText('<span style="color: white">' + params.name + '</span>');

				result.success	&& School.util.Util.showSuccessMsg("修改成功！");			
			},

			failure: function(conn,response,options,eOpts) {
				School.util.Util.showErrorMsg("链接服务器失败！");			
			}
		});
	},

	checkPassWord: function(values, win) {

		var me = this;

		Ext.Ajax.request({
			url: zyHost + "sch/judgePassword.action",
			timeout: 60000,
			params: {
				password: values.password
			},
			success: function(conn,response,options,eOpts) {

				var result = School.util.Util.decodeJSON(conn.responseText);

				if( !result.success ) {
					School.util.Util.showErrorMsg("您输入的旧密码不正确！无法修改密码！");
					return 0;
				}

				var params = win.datas;

				params.password = values.newPassword;

				me.updateUserInfo(win, params);
				

				//result.success	&& School.util.Util.showSuccessMsg("修改成功！");			
			},

			failure: function(conn,response,options,eOpts) {
				School.util.Util.showErrorMsg("链接服务器失败！");			
			}
		});
	},

	updatePic: function(formPanel) {

		var form = formPanel.getForm();

		form.submit({

	        url: zyHost + "contact/updatePic.action",
	        method: "post",
	        waitMsg: '正在上传...',

	        success: function(fp, o) {

	           School.util.Util.showSuccessMsg("头像上传成功！");

	            //关闭上传窗口
	           formPanel.up("window").close();
	          
	        },
	        failure: function() {
	        	
	        	 School.util.Util.showSuccessMsg("头像上传成功！");

	            //关闭上传窗口
	            formPanel.up("window").close();
	        }

	    });
	}

});
