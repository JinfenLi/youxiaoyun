/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 管理员管理校内学生的控制器，包含进入班级入口
 * 
 */
Ext.define("School.controller.clazz.Getstudentmgr", {
	extend: "Ext.app.Controller",
	views: [
		"clazz.Getstudentmgr"
	],
	init: function(application) {
	
		this.control({
			//点击进入查看班级学生
			"getstudentmgr actioncolumn": {
				itemclick: function(grid, rowIndex, colIndex) {
					this.openClasses(grid, rowIndex, colIndex);
				}
			},

			//年级选择的change事件
			"getstudentmgr toolbar combobox#gradeselect": {
				change: function(combo, newValue, oldValue, eOpts) {
					this.loadClasses(newValue);
				}
			}
			//页面渲染后，是下拉框选择当前用户的的默认班级

		});
	},

	//更新班级
	loadClasses: function(newValue) {
		var grid = Ext.ComponentQuery.query("getstudentmgr")[0],
			store = grid.getStore();
		store.load();
	},

	//打开班级
	openClasses: function(grid, rowIndex, colIndex) {
		var classId = grid.getStore().getAt(rowIndex).get('id'),
			name = grid.getStore().getAt(rowIndex).get('name'),
			mainPanel = Ext.ComponentQuery.query("mainpanel")[0],
			title = '查看班级',
			xtype = "myclass",
			examStore = grid.getStore(),
			scorePanel = {};

		//更新与班级相关的全局变量
		zy_classId = classId;
		//将查看班级面板添加到mainPanel
		var newTab = School.util.AddTab.addTab(mainPanel, title, xtype, '', 'seeClasses');

		newTab.addListener('beforeclose', function() {
			newTab.down('toolbar').down('combobox#myclasses').show();	
			if(zy_classes.length == 0) {
				zy_classId = '';
			}	else {
				zy_classId = zy_classes[0];
			}
		});
		newTab.getStore().load({params:{clazzId: zy_classId}});
		newTab.down('toolbar').down('combobox#myclasses').hide();
		newTab.down('toolbar').down('combobox#myclasses').setValue(zy_classId);
		newTab.down('toolbar').down('label#className').setText(name);
	}
});