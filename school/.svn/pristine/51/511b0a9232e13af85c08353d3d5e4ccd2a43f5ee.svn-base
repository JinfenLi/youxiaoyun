/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 我的课程和选课管理的控制器
 * 
 */
Ext.define("School.controller.course.SelectCurricula", {
	extend: "Ext.app.Controller",
	views: [
		"school.SelectCurricula",
		"school.ShowSelectedCurricula"
	],
	init: function(application) {

		this.control({

			//"我的课程"界面渲染后，为学期下拉框和教师下拉框选择默认值
			"showselectedcurricula": {
				afterrender: function(component) {
					this.setDefaultValue(component);
				}
			},

			//“我的课程”的下拉框改变时触发的查询事件
			"showselectedcurricula combobox": {
				change: function(combobox, newValue) {
					this.showSelectedCurricula(combobox, newValue, "showselectedcurricula");
				}
			},

			//“我的课程”顶部的“新增选课”按钮的触发事件
			//这会打开“选课管理”界面
			"showselectedcurricula button#addSelect": {
				click: function(button) {
					var mainPanel = Ext.ComponentQuery.query("mainpanel")[0];
					School.util.AddTab.addTab(mainPanel, "新增选课", "selectcurricula");
				}
			},

			//“我的课程”中的删除选课按钮的监听事件
			"showselectedcurricula button#deleteSelect": {
				click: function(button) {					
					this.deleteCurricula(button);
				}
			},


			//开设考试
			"showselectedcurricula actioncolumn#addExam": {
				itemclick: function(grid, rowIndex, colIndex) {
					this.addExam(grid, rowIndex);					
				}
			},

			//重置我的课程中的下拉框的值
			"showselectedcurricula button#resetcombo": {
				click: function(button) {
					var selector = "showselectedcurricula combobox";
					this.resetComboboxes(selector);
				}
			},

			//"新增选课"面板渲染后为下拉框设置默认值
			"selectcurricula": {
				afterrender: function(component) {
					var termCombo = component.down("#termcombo");
					School.util.Util.setComboboxValue(termCombo, "currentSemester",
							"name", 1);
				}
			},

			//"新增选课"面板的查询事件
			"selectcurricula button#queryCurricula": {
				click: function(button) {
					this.beforeQuery(button, this);	
				}
			},

			//选课管理的保存选课
			"selectcurricula button#save": {
				click: function(button) {
					this.saveCurricula(button);					
				}
			}
			
		});
	
	},

	setDefaultValue: function(component) {

		"use strict";

		var termCombo = component.down("#termcombo"),
			teacherCombo = component.down("#teachercombo"),
			userId = globalUserInfo.user_id;

		//设置学期选择的默认值为当前学期(currentSemester===1的学期)
		School.util.Util.setComboboxValue(termCombo, "currentSemester",
				"id", 1);
		
		//设置教师下拉框的默认值为当前用户
		// School.util.Util.setComboboxValue(teacherCombo, "id",
		// 		"id", userId);

	},

	resetComboboxes: function(selector) {
		var comboboxes =  Ext.ComponentQuery.query(selector);
		for(var i = 0; i < comboboxes.length; i++) {
			comboboxes[i].reset();
		}
	},


	beforeQuery: function(button, me) {

		"use strict";

		var grid = button.up("gridpanel"), 
			adaptiveTerm = "",
			adaptiveGrade = "",	
			params = {},
			gradecombo = grid.down("combobox#gradecombo"), 
			subjectcombo = grid.down("combobox#subjectcombo"),
			termcombo = grid.down("combobox#termcombo");

		//判断三个查询条件是否全部都选了
		if(!subjectcombo.getValue()) {
			School.util.Util.showErrorMsg('"学科选择"没有选中任何值！');
			return;
		} 
		if(!gradecombo.getValue()) {
			School.util.Util.showErrorMsg('"年级选择"没有选中任何值！');
			return;
		} 
		if(!termcombo.getValue()) {
			School.util.Util.showErrorMsg('"学期选择"没有选中任何值！');
			return;
		} 

		//更新全局变量：年级记录zy_gradeRec
		zy_gradeRec = gradecombo.getStore();
		//更新全局变量：当前的年级id字段zy_gradeId
		zy_gradeId = gradecombo.getValue();
		//更新全局变量：科目记录zy_subjectRec
		zy_subjectRec = subjectcombo.getStore();
		//更新全局变量：当前的科目id字段zy_subjectId
		zy_subjectId = subjectcombo.getValue();
		//更新全局变量：使zy_curriculaTeacher为空
		zy_curriculaTeacher = [];


		adaptiveTerm = termcombo.getValue();
		//通过年级名称获取年级id
		adaptiveGrade = School.util.Util.getOtherValue(zy_gradeId, zy_gradeRec);

		//查询的请求的参数集合
		params = {
			subjectId: zy_subjectId, //学科id
            adaptiveGrade: adaptiveGrade, //适应年级
            adaptiveTerm: adaptiveTerm, //适应学期
            gradeId: zy_gradeId //年级id
		};

		me.startQuery(params, grid, me);
	},

	startQuery: function(params, grid, _this) {

		"use strict";

		Ext.Ajax.request({

			url: zyHost + "curricula/chooseCurricula.action",
			method: "POST",
			timeout: 60000,
			params: params,

			callback: function(options, success, response) {

				if( !School.util.Util.decodeJSON(response.responseText).success) {
					School.util.Util.showSuccessMsg("查询课程失败！");
					return 0;
				}

				var result = School.util.Util.decodeJSON(response.responseText);

				//如果没有符合条件的课程，则提示用户
				//并且退出
				if(result.curriculas.length === 0) {
					//更新全局变量，
					zy_chooseCurricula = "";
				 	zy_curriculaId = "";

				 	//提示用户无符合条件的课程
				 	grid.down("label#curriculaname").setText("无符合条件的课程");
				 	School.util.Util.showErrorMsg("没有符合的课程，请重新选择！");

				 	//清空班级列表
				 	grid.getStore().removeAll(); 
				 	return ;
				}
				
				//有符合条件的课程时，则继续执行
				zy_chooseCurricula = result.curriculas[0];
				zy_curriculaId = zy_chooseCurricula.id;
				grid.down("label#curriculaname").setText(zy_chooseCurricula.name);
				

				//查询符合条件的班级
				grid.getStore().reload({

					params:params, 

					callback: function(records, options, success) {

						if(!success) {
							School.util.Util.showErrorMsg("连接服务器失败！");
						}

						//为"任课老师"下拉框添加监听事件
						setTimeout(function() {
							var teachers =  Ext.ComponentQuery.query("selectcurricula #selectTeacher");
							for(var i = 0, len = teachers.length; i < len; i++) {
								teachers[i].on("change", function(me, newValue, oldValue, eOpts) {
									_this.onTeacherChange(me, newValue, oldValue, eOpts);
								});
							}
						}, 100);

						
					}
				});
				
			}
		});
	},

	//保存选课
	saveCurricula: function(button) {

		"use strict";

		//如果查询结果没有符合条件的课程，则提示用户，并退出保存
		if( !zy_curriculaId ) {
			School.util.Util.showErrorMsg("没有符合条件的课程，无法保存，请重新选择！");
			return ;
		} 
	
		var grid = button.up("grid"),
			gridStore = grid.getStore(), 
			termStore = grid.down("combo#termcombo").getStore(), 
			termValue = grid.down("combo#termcombo").getValue(),
	 		tScSemesterId = School.util.Util.getOtherValue(termValue,
	 						termStore, "name", "id");

	
		// console.log(zy_classes);
		//开始保存符合条件的课程
		for(var k = 0, count = 0, len = zy_curriculaTeacher.length; k < len; k++) {
			// var newclass = zy_curriculaTeacher[k];	
			Ext.Ajax.request({
				url: zyHost + "curricula/saveCurriculaVariable.action",
				method: "POST",
				sync: false, //不要异步
				timeout: 60000,
				params: {
					tScCurriculaId: zy_curriculaId,
					tScClassId: zy_curriculaTeacher[k].classId,
					tScSemesterId: tScSemesterId,
					tScTeacherId: zy_curriculaTeacher[k].teacherId
				},
				
				callback: function(options, success, response) {					
					if( !School.util.Util.decodeJSON(response.responseText).success) {
						School.util.Util.showSuccessMsg("保存失败！");
						return 0;
					}

					count++; //成功的选课数量加1
					// if(newclass.teacherId == zy_teacherId) {
					// 	var teacher_newclass = [
					// 		newclass.classId,
					// 		newclass.className,
					// 	];
					// 		zy_classes.push(teacher_newclass);
					// }
					// console.log(newclass.classId);
					
					if(count === len) {

						var mainPanel = Ext.ComponentQuery.query("mainpanel")[0],
							title = "我的课程",
							xtype = "showselectedcurricula",
							showPanel = {};

						//为了显示刚刚成功选的课程
						//跳转到我的课程面板去
						showPanel = School.util.AddTab.
									addTab(mainPanel, title, xtype);

						//重置我的课程面板里面的教师下拉框值
						// showPanel.down("#teachercombo").reset();
						//设置"我的课程"面板的学科选择下拉框的值
						//等于"新增选课"面板的学科选择下拉框的值
						// School.util.Util.setComboboxValue(
						// 	showPanel.down("#subjectcombo"),
						// 	"id",
						// 	"id",
						// 	grid.down("#subjectcombo").getValue()
						// );
						//设置"我的课程"面板的年级选择下拉框的值
						//等于"新增选课"面板的年级选择下拉框的值
						// School.util.Util.setComboboxValue(
						// 	showPanel.down("#gradecombo"),
						// 	"id",
						// 	"id",
						// 	grid.down("#gradecombo").getValue()
						// );

						//设置"我的课程"面板的学期选择下拉框的值
						//等于"新增选课"面板的年级选择下拉框的值
						School.util.Util.setComboboxValue(
							showPanel.down("#termcombo"),
							"name",
							 "id",
							 grid.down("#termcombo").getValue()
						);
						//grid.getStore().reload(); //重新加载数据
						grid.destroy();
						zy_curriculaTeacher = []; //令zy_curriculaTeacher为空
						School.util.Util.showSuccessMsg("保存成功！");
						Ext.ComponentQuery.query('showselectedcurricula')[0].getStore().reload();
					}
					

						
				}
			});
		}
	},

	deleteCurricula: function(button) {

		"use strict";

		var grid = button.up("showselectedcurricula"),
			records = grid.getSelectionModel().getSelection(),
			store = grid.getStore(),
			title = "删除选课",
			requestUrl = "curricula/deleteCurriculaVariable.action",
			params = {};

		//如果用户没有选择选课，则提示无法删除
		if(records.length === 0) {
			School.util.Util.showErrorMsg("您没有选择任何课程，无法删除！");
			return;
		}

		//提示用户是否确定要删除选课
		School.util.Util.confirm("删除选课", function(buttonId) {
			if(buttonId === "yes") { 
				for(var  i = 0; i < records.length; i++) {
					params = {
						curriculaVariableId: records[i].get("id")
					};
					School.util.Update.onDeleteButtonClick(store, requestUrl, params, null, refresh);
				}
			}
		});

		//该函数在删除成功后会回调执行
		//作用是刷新选课列表的
		function refresh() {

			var comboboxes = ["#termcombo", "#gradecombo",
							 "#subjectcombo", "#teachercombo"],
				value = "";

			for(var i = 0; i < 3; i++) {
				if(grid.down(comboboxes[i]).getValue()) {
					break;
				}
			}

			value = grid.down(comboboxes[i]).getValue();
			grid.down(comboboxes[i]).reset();
			grid.down(comboboxes[i]).setValue(value);
		}
	},

	onTeacherChange: function(me, newValue, oldValue, eOpts) {

		"use strict";

		var comboboxes = Ext.ComponentQuery.query("#selectTeacher"),
			gridStore = Ext.ComponentQuery.query("selectcurricula")[0].getStore(),
			rowIndex; 

		//遍历grid里面的itemId为selectTeacher的combobox,取得当前的行号
		for(var rowIndex = 0; ; rowIndex++) {
			if(comboboxes[rowIndex] === me) { 
				break;
			}
		}

		
		//遍历全局变量zy_curriculaTeacher，
		//如果里面已经存在了当前行号的记录，
		//则修改任课老师的id
		for(var i = 0; i < zy_curriculaTeacher.length; i++) {
			if( gridStore.getAt(rowIndex).get("id") === zy_curriculaTeacher[i].classId) {
				zy_curriculaTeacher[i].teacherId = newValue;
				break;
			}
		}
		//如果i大于zy_curriculaTeacher的长度，则说明里面没有当前班级的记录
		//因此把当前班级id以及任课老师id加进去
		if(i >= zy_curriculaTeacher.length) {
			zy_curriculaTeacher.push({ 
				classId: gridStore.getAt(rowIndex).get("id"),
				teacherId:newValue,
				className: gridStore.getAt(rowIndex).get('name')
			});
		}
	},

	showSelectedCurricula: function(combo, newValue,className) {

		"use strict";

		var comboboxes = Ext.ComponentQuery.query(className + " combo"),
			params = {
					subjectId: comboboxes[0].getValue() || "",
					gradeId: comboboxes[1].getValue() || "",
					semesterId: comboboxes[2].getValue() || "",
					teacherId: zy_teacherId
					// comboboxes[3].getValue() || ""
			};

		if(newValue !== null) {
			combo.up("grid").getStore().reload({
					params: params,
					callback: function(records, options, success) {}
			});
		} else if(!params.subjectId && !params.gradeId && !params.semesterId && !params.teacherId){
			combo.up("grid").getStore().reload({
				params: params,
				callback: function(records, options, success) {}
			})
		}
	},

	addExam: function(grid, rowIndex) {

		"use strict";

		"use strict";
		
		var records = [];

		records.push(grid.getStore().getAt(rowIndex));
      
      	Ext.create("School.view.exam.AddExam", {
      		extraParams: {
      			records: records
      		}
      	}).show();
		
		// var grid = button.up("grid"),
		// 	records = grid.getSelectionModel().getSelection(),
		// 	length = records.length;
			

		// //检查用户是否选择了需要开始考试的课程，如果不是，则提示用户去选择
		// if(length === 0) {
		// 	School.util.Util.showErrorMsg("您没有选择任何课程，无法开设考试！");
		// 	return ;
		// } 
      
      	//如果用户选择了课程，则实例化一个创建考试的窗口
      	// Ext.create("School.view.exam.AddExam", {
      	// 	extraParams: {
      	// 		records: records
      	// 	}
      	// }).show();
	}

});