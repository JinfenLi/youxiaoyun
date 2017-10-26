/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 课程表控制器
 * 
 */
Ext.define("School.controller.clazz.Syllabus", {
	extend: "Ext.app.Controller",
	views: [
		"school.Syllabus"
	],
	init: function(application) {
		this.control({

			//渲染页面时，刷新课程表，
			//因为是通过iframe嵌入的，所以用到时间定时器setTimeout
			//防止iframe还没有嵌入就刷新引起的错误
			"syllabus": {
				render: function(component) {
					 //因为setTimeout的this是window对象
					 //所以把控制器的this赋值到me,就可以在
					 //setTimeout里调用了
					var me = this;
					setTimeout(function() {
						me.refreshSyllabus(component.getItemId());
					}, 1000);
				}
			},

			//打开上传课程表的窗口
			"syllabus button#upload": {
				click: function() {
					Ext.create("School.view.school.UploadSyllabus").show();
				}
			},

			//为上传上传窗口的学期选择设置一个默认值
			"uploadsyllabus combobox#termcombo": {
				afterrender: function(component) {
					console.log("upload");
					School.util.Util.setComboboxValue(component,
							"currentSemester", "id", 1);
				}
			},

			//确认上传
			"uploadsyllabus button#save": {
				click: function(button) {
					this.uploadSyllabus(button, this);
				}
			},


			//下载课程表
			"syllabus button#download": {
				click: function() {
					this.downloadSyllabus();
				}
			}
		});
	
	},

	//该函数用来刷新课程表
	refreshSyllabus: function(classId) {

		"use strict";

		Ext.Ajax.request({
			url: zyHost + "curricula/getCurricula.action",
			timeout: 60000,
			params: {clazzId: classId},
			success: function(conn,response,options,eOpts) {
				var result = School.util.Util.decodeJSON(conn.responseText);

				if(result.success) {
					var courses = result.result,
						length = courses.length,
						selector1,
						selector2,
						$ = document.getElementById("mycourseSchedule").contentWindow.$;

					//说明：
					//下面用到的"$"不是index.html的jQuery对象
					//而是同目录下的course-schedule.html的jQuery对象
					//因为我们上面有"$ = document.getElementById("mycourseSchedule").contentWindow.$;"语句

					//把课表的所有行隐藏起来
					for(var i = 1; i <= 9; i++) {
						$(".section" + i).hide();
					}

					$('div[class*=week]').html("-"); //将课程表清空
					console.log($('div[class*=week]').length);


					for(var i = 0 ; i < length; i++) {
						selector1 = ".section" + courses[i].section;
						selector2 = selector1 + " .week" + courses[i].week;

						$(selector1).show();
						$(selector2).html(courses[i].name);
					}


					//显示备注信息
					$(".comment").html(response.comment);
				} else {
					School.util.Util.showErrorMsg("获取课程表失败！");
				}
			},
			failure: function(conn,response,options,eOpts) {
				School.util.Util.showErrorMsg("链接服务器失败！");
			}
		});

	},

	//上传课表
	uploadSyllabus: function(button, me) {

		"use strict";
		
		var termCombobox = button.up("window").down("#termcombo"),
			file = button.up("form").down("filefield"),
			syllabus = Ext.ComponentQuery.query("syllabus")[0],
			classId = syllabus.getItemId(),
			url = "curricula/uploadCurriculaExcel.action",
			msg = "该文件类型不是excel文件，请重新选择！",
			store = "",
			type = ".xls",
			params = {
				file: file.getValue(),
				clazzId: classId,
				semesterId: termCombobox.getValue()
			};

		if(!termCombobox.getValue() || !file.getValue()) {
			School.util.Util.showErrorMsg("\"适用学期\"和\"文件选择\"不能为空");
			return 0;
		}
		
		School.util.Util.uploadFile(button, url, params, msg, store, type, refresh);

		function refresh() {
			me.refreshSyllabus(classId);
		}
	},

	//下载课表
	downloadSyllabus: function() {

		"use strict";

		var inputs = '',
			url = "curricula/downloadCurricula.action",
			classId = Ext.ComponentQuery.query("myclass")[0]
					 .down("combobox#myclasses").getValue();
		
		inputs += '<input type="text" name="clazzId" value="' + classId + '"/>';
		School.util.Util.downloadFile(url, inputs);
	}

});