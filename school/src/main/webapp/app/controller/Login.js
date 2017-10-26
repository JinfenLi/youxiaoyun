/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 登陆的控制器
 * 
 */
Ext.define("School.controller.Login", {
	extend: "Ext.app.Controller",
	requires: [
		"School.util.MD5",
		"School.util.Util",
		"School.util.SessionMonitor" //session监听器，超时自动退出
	],

	views: [
		"Login",
		"MyViewport",
		"Header",
		"auth.CapsLockTooltip"
	],

	//获取信息提示的引用
	refs: [{
		ref: "capslockTooltip",
		selector: "capslocktooltip"
	}, {
		ref: 'loginPanel',
		selector: 'login'
	}],

	init: function (application) {

		//调节窗口的大小
		window.onresize = function() {
			var loginPanel = Ext.ComponentQuery.query("login")[0];
			// var loginPanel = this.getLoginPanel();
			loginPanel.setWidth(School.util.Util.getInner().width);
			loginPanel.setHeight(School.util.Util.getInner().height);

		};
	},

	//定义静态方法
	statics: {
		//获取存储在本地的用户名和密码
		getLocalStorage: function (userField, passField) {
			if ("localStorage" in window) {//加入浏览器支持localStorage
				var name = localStorage.getItem("account"),
					password = localStorage.getItem("password");
				return {name: name || "", password: password || ""};
			}
		},
		//将用户名或者密码保存到本地
		setLocalStorage: function (account, password) {
			if ("localStorage" in window) {
				localStorage.setItem("account", account);
				localStorage.setItem("password", password);
			}
		},

		submit: function(user, pass, sign) {

			var login = Ext.ComponentQuery.query("login")[0];

			Ext.get(login.getEl()).mask("正在验证登陆信息...请等待...",
					"loading");

			Ext.Ajax.request({
				url: zyHost + "teacher_website/login",
				//method: "POST",
				params: {
					account: user,
					password: pass
				},

				//登陆失败，连接不上服务器所致
				failure: function (conn, response, options, eOpts) { 
					Ext.get(login.getEl()).unmask();
					School.util.Util.showErrorMsg("连接服务器失败！");
				},

				success: function (conn, response, options, eOpts) {

					var result = School.util.Util.decodeJSON(conn.responseText),
						msg = "",
						classes = [],
						info = [];

					//去除遮罩
					Ext.get(login.getEl()).unmask(); 
					
					//登陆成功
					if (result.success) { 
						//保存用户名和密码
						if(!sign) {
							pass = '';
						}
						School.controller.Login.setLocalStorage(user, pass); 

						//更新全局变量
						globalUserInfo = result.userInfo;
						zy_schoolId = result.userInfo.school_id;
						zy_classId = result.userInfo.class_id;
						zy_className = result.userInfo.class_name; 
						zy_schoolName = result.schoolName;
						zy_logoUrl = result.logo; 
						zy_teacherId = result.userInfo.user_id;

						//获取当前用户的所有班级，
						//并且把班级id和名字存到全局变量zy_classes里面
						classes  = globalUserInfo.teacher_info.classes;
							

						for(var i = 0; i < classes.length; i++) {
							info = [classes[i].id, classes[i].name];
							zy_classes.push(info);
						}

						//获取所有教师，并且放在zy_teacherStore里缓存起来
						zy_teacherStore = Ext.create("School.store.school.TeacherMgr", {
							autoLoad: true,
							pageSize: 1000
						});

						Ext.create("School.store.school.Semester").reload({
							callback: function() {
								zy_termId = this.getAt(0).get("id");
							}
						});

						//把登陆窗口关闭掉
						login.close(); 

						//移除window.onresize事件
						window.onresize = null;  

						//创建一个viewPort主视图，尽到主页
						Ext.create("School.view.MyViewport"); 

						//开启session监听器，超时自动退出
						School.util.SessionMonitor.start(); 

						//弹窗新功能信息
						Ext.Ajax.request({
							url:  zyHost + 'user/showNewFeatures.action',
							params: {
								userId: zy_teacherId
							},
							success: function(response) {
								var status = School.util.Util.decodeJSON(response.responseText);
								if(status.status == '0') {
									Ext.create('Ext.window.Window', {
										title: '新版本功能提醒',
										width: 400,
										height: 300,
										html: '<style type="text/css">.red{color:red;}</style>'+
										'<h3>&nbsp&nbsp<span class="red">* </span>1. 修复了选择日期时年月失效的问题</h3>'+
										'<h3>&nbsp&nbsp<span class="red">* </span>2. 新增了相册照片放大查看功能：</h3>'+
										'<h3>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp双击照片，可放大查看和左右切换浏览</h3>' +
										'<h3>&nbsp&nbsp<span class="red">* </span>3. 管理成绩时，可以导出成绩模板</h3>' + 
										'<h3>&nbsp&nbsp<span class="red">* </span>4. 学校管理员可修改任课老师</h3>' +										
										'<h3>&nbsp&nbsp<span class="red">* </span>5. 一些其他问题的修复</h3>',
										buttons: [{
											text: '不再提醒',
											handler: function(button) {
												Ext.Ajax.request({
													url: zyHost+'user/setAlreadyRead.action',
													params: {
														userId: zy_teacherId,
														status: '1'
													}
												});
												button.up('window').close();
											}
										}, {
											text: '下次提醒',
											handler: function(button) {
												button.up('window').close();
											}
										}],
									}).show();
								} else {
									console.log(status.status);
								}
							}
						});

					} else { //登陆失败，密码错误导致
						msg = result.msg || "";
						School.util.Util.showErrorMsg("登陆失败! " + msg);
					}
				}
			});
		},

		//清除本地的用户名和密码
		clearLocalStorage: function () {

		}
	}


});