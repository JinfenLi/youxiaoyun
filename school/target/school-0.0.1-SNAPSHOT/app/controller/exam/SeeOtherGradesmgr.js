/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 查看其它考试的控制器
 * 
 */
Ext.define("School.controller.exam.SeeOtherGradesmgr", {
	extend: "Ext.app.Controller",
	views: [
		"exam.SeeOtherGradesmgr"
	],
	init: function(application) {

		"use strict";

		this.control({

			//界面渲染后为学期选择当前学期为默认值
			"seeothergradesmgr": {
				afterrender: function(component) {
					this.setDefaultValue(component);
				}
			},

			//重置考试管理面板下的所有的下拉框
			"seeothergradesmgr button#resetcombo": {
				click: function(button) {
					var xtype = "seeothergradesmgr combo";
					this.resetComboboxes(xtype);
				}
			},

			//考试管理的下拉框监听事件
			"seeothergradesmgr combobox": {
				change: function(combobox, newValue) {
					var xtype = "seeothergradesmgr";
					this.showSelectedCurricula(combobox, newValue, xtype);
				}
			},

			//重置表单
			"seeothergradesmgr button#reset": {
				click: function(button) {
					button.up("form").getForm().reset();
				}
			},

			//查看考试成绩
			"seeothergradesmgr actioncolumn": {
				itemclick: function(grid, rowIndex, colIndex) {					
					this.viewScore(grid, rowIndex, colIndex);
				}
			},

			//删除学校
			"seeothergradesmgr toolbar button#delete": {
				click: function(button) {
					this.deleteExam(button);					
				} 
			},		
		});
	
	},

	deleteExam: function(button) {

		"use strict";

		var grid = button.up("gridpanel"), 
			records = grid.getSelectionModel().getSelection(),
			store = grid.getStore(),
			url = "exam/deleteExam.action",
			params = {};

		//假如没有选择任何一行，则提示用户，并且退出删除操作
		if(records.length === 0) {
			School.util.Util.showWarningMsg("请选择一个要删除的考试！");
			return;
		}

		//获取学校id
		params.id = records[0].get("id"); 
		
		//提示用户是否确定删除
		School.util.Util.confirm("删除考试", function(buttonId) {
			if(buttonId === "yes") { 
				School.util.Update.onDeleteButtonClick(store, url, params, {}, refresh);	
			}
		});

		//该函数在删除成功后会回调执行
		//作用是刷新考试记录
		function refresh() {

			var comboboxes = ["#termcombo", "#gradecombo", "#subjectcombo"],
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
	
	setDefaultValue: function(component) {
		"use strict";

		var termCombo = component.down("#termcombo");

		//设置学期选择的默认值为当前学期(currentSemester===1的学期)
		School.util.Util.setComboboxValue(termCombo, "currentSemester",
				"id", 1);
	},

	/**
	* @method resetComboboxes
	* @param void
	* @return void
	* @description: 用来重置所有的comboboxes组件
	*/
	resetComboboxes: function(xtype) {

		"use strict";

		var comboboxes =  Ext.ComponentQuery.query(xtype);
		for(var i = 0, len = comboboxes.length; i < len; i++) {
			comboboxes[i].reset();
		}
	},

	/**
	* @method showSelectedCurricula
	* @param comobo: 当前改变的combobox组件
	* @param newValue: combobox的新值
	* @param xtype: 当前面板的别名
	* @return void
	* @description: 用来重置所有的comboboxes组件
	*/
	showSelectedCurricula: function(combo, newValue, xtype) {

		"use strict";
			combo.up("grid").getStore().reload();
	},


	viewScore: function(grid, rowIndex, colIndex) {

		"use strict";

		var	mainPanel = Ext.ComponentQuery.query("mainpanel")[0],
			title = '成绩管理',
			xtype = "scoremgr",
			examStore = grid.getStore(),
			scorePanel = {};

		//更新与考试相关的全局变量
		zy_examRec = examStore.getAt(rowIndex);
		zy_examId = examStore.getAt(rowIndex).get("id") ;
		
		//将成绩面板添加到mainPanel
		School.util.AddTab.addTab(mainPanel, title, xtype);

		//假如已经存在scroemgr的实例，则重新加载数据并且改变下拉框的值
		scorePanel = Ext.ComponentQuery.query("scoremgr")[0];
		scorePanel.down('button#uploadScore').hide();
		scorePanel.down('button#submitExam').hide();
		// scorePanel.down('button#downloadScore').hide();
		scorePanel.down('button#delete').hide();
		if(scorePanel) {
			//重新加载班级数据
			scorePanel.getStore().reload({
				params: {
					examId: zy_examId
				},
				callback:function() {
					scorePanel.down("label#examName").setText(examStore.getAt(rowIndex).get("name"));
				}
			});
		}
	}

});