/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 超级管理员查看选择的学校的登陆情况的控制器
 * 
 */
Ext.define("School.controller.area.SchoolLogin", {
	extend: "Ext.app.Controller",
	views: [
		"area.SchoolLogin"
	],
	init: function(application) {
	
		this.control({
			"schoollogin": {
				afterrender: function(component) {
					var itemId = component.getItemId();
					if(itemId == 'schoollogin') {
						Ext.Ajax.request({
					    url: 'user/getLoginCount.action',
					    params: {
					        schoolId: zy_schoolId
					    },
					    success: function(response){
					      var text = JSON.parse(response.responseText);
					      component.down('toolbar').down('label#schoolCountOfTeacher').setText('学校教师总数：' + text.schoolCountOfTeacher);  
					    	component.down('toolbar').down('label#NotLoginCountOfTeacher').setText('未登陆教师数：' + text.NotLoginCountOfTeacher);  
					    	component.down('toolbar').down('label#schoolCountOfParent').setText('学校家长总数：' + text.schoolCountOfParent);  
					    	component.down('toolbar').down('label#NotLoginCountOfParent').setText('未登陆家长数：' + text.NotLoginCountOfParent);  
					    	component.down('toolbar').down('label#loginCountOfParent').setText('已登陆家长数：' + text.loginCountOfParent);  
				    		component.down('toolbar').down('label#loginCountOfTeacher').setText('已登陆教师数：' + text.loginCountOfTeacher);  
					    }
						});
				  
				    component.down('toolbar').down('combobox').getStore().proxy.extraParams = {
				    	schoolId: zy_schoolId
				    }
				    component.down('toolbar').down('combobox').getStore().load();
				    component.down('toolbar').down('combobox').setValue(''); 
					}
				}
			},

			//点击进入查看班级登陆情况
			"schoollogin actioncolumn": {
				itemclick: function(grid, rowIndex, colIndex) {
					this.openClasses(grid, rowIndex, colIndex);
				}
			},

			//年级选择的change事件
			"schoollogin toolbar combobox#gradeselect": {
				change: function(combo, newValue, oldValue, eOpts) {
					this.loadClasses(combo,newValue);
				}
			}
			//页面渲染后，是下拉框选择当前用户的的默认班级

		});
	},

	//更新班级
	loadClasses: function(combo,newValue) {
		var grid = combo.up('grid'),
			store = grid.getStore();
		store.load({
			params: {
				gradeId: newValue
			}
		});
	},

	//打开班级
	openClasses: function(grid, rowIndex, colIndex) {
		var classId = grid.getStore().getAt(rowIndex).get('id'),
			name = grid.getStore().getAt(rowIndex).get('name'),
			mainPanel = Ext.ComponentQuery.query("mainpanel")[0],
			title = '班级登陆情况',
			xtype = "classlogin",
			examStore = grid.getStore(),
			scorePanel = {};
			//如果本来就有面板
			var classlogin = School.util.AddTab.addTab(mainPanel, title, xtype, '', 'classlogin', {classId:classId});

			classlogin.getStore().load({
				params: {
					classId: classId
				}
			});
			//获取班级登录信息，已经登录，未登录，总人数
			Ext.Ajax.request({
				url:"/school/user/getLoginCountOneClass",
				params:{
                  classId: classId
				},
				success:function(response){
                     var text = JSON.parse(response.responseText);
                     classlogin.down('toolbar').down('label#numberOfAll').setText('班级总人数' + text.allMan);
                      classlogin.down('toolbar').down('label#numberOfLogin').setText('登录人数' + text.loginMan);
                      classlogin.down('toolbar').down('label#numberOfOffline').setText('未登录人数' + text.notLoginMan);
                  },
				failure:function(){
                      School.util.Util.showErrorMsg('获取班级登录信息失败！');
				}
			});			
	}
});