/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 区域管理的控制器
 * 
 */
Ext.define("School.controller.area.AreaMgr", {
	extend: "Ext.app.Controller",
	views: [
		"area.AreaMgr",
		 "area.SelectSchool"
	],

	init: function(application) {

		"use strict"; //启用严格模式

		this.control({
			
			"combo#province": {

				render: this.onProvinceRender,

				change: this.onProvinceChange
			},

			//地级市下拉框的触发事件
			"combo#city": {
				change: this.onCityChange
			},

			//县查询
			"combo#town": {
				change: this.onTownChange
			},


			//查看某个学校的教育热讯
			"areamgr actioncolumn#readHotNews": {
				itemclick: this.readHotNews
			},

			//选择学校
			"publishnews selectschool": {
				selectionchange: this.onSelectSchoolChange
			}


		});
	
	},



	//省份下拉框渲染时的出发函数
	onProvinceRender: function(component) {

		"use strict";

		var store = component.getStore(),
			url = "area/findArea.action", 
			root = "areas",
			params = {
				parentId: "0",
				leave: "1"
			};

		//重新设置数据代理
		store.setProxy(School.util.Util.setProxy(url, params , root));

		store.reload({
			callback: function() {
				var defaultVal = store.getAt(0).get("id");
				component.setValue(defaultVal);
			}
		});
	},


	//切换省份时的触发函数
	onProvinceChange: function(me, newValue, oldValue, eOpts) {

		"use strict"; //启用严格模式

		this.emptySelectedSchools(me); //清空已经选择了的学校

		//查询该省份的所有学校
		me.up("grid").getStore().reload({
			params: {
				id: newValue,
				leave: 1
			}
		});

		//设置地级市的数据代理
		var store =	me.up("toolbar").down("combo#city").getStore(),
			url = "area/findArea.action", //请求地址
			root = "areas",	//json数据的根节点
			params = {
				parentId: newValue,
				leave: "2"
			};

		store.setProxy(School.util.Util.setProxy(url, params , root));
		store.reload();


	},

	//切换城市时的触发函数
	onCityChange: function(me, newValue, oldValue, eOpts) {

		"use strict"; //启用严格模式

		this.emptySelectedSchools(me);

		//查询该县的学校
		me.up("grid").getStore().reload({
			params: {
				id: newValue,
				leave: 2
			}
		});

		//设置县级市的数据代理
		var store =	me.up("toolbar").down("combo#town").getStore(),
			url = "area/findArea.action", //请求地址
			root = "areas",	//json数据的根节点
			params = {
				parentId: newValue,
				leave: "3"
			}

		store.setProxy(School.util.Util.setProxy(url, params , root));
		store.reload();
	},

	//切换县级市时的触发事件函数
	onTownChange: function(me, newValue, oldValue, eOpts) {	

		"use strict"; //启用严格模式
		
		this.emptySelectedSchools(me);

		me.up("grid").getStore().reload({
			params: {
				id: newValue,
				leave: 3
			}
		});
	},

	//查看教育热讯
	readHotNews: function(grid, rowIndex, colIndex) {

		"use strict"; //启用严格模式

		//获取学校id
		var schoolId = grid.getStore().getAt(rowIndex).get("id"),
			mainPanel = Ext.ComponentQuery.query("mainpanel")[0],
			activeTab = mainPanel.getActiveTab(),
			title = "教育热讯列表",
	        xtype = "newsmgr",
	        hotnews = {};

	     School.util.AddTab.addTab(mainPanel, title, xtype, "", "hotnewslist");
	     hotnews = Ext.ComponentQuery.query("#hotnewslist")[0];
	     hotnews.schoolId = schoolId;

          hotnews.getStore().reload({
          	params: {
          		type: "teaching",
          		schoolId: schoolId
          	}
          });    
	},

	//切换学校是的触发函数
	onSelectSchoolChange: function(me, selected, eOpts) {

		"use strict"; //启用严格模式

		var schoolNames = [];
		zy_selectedSchools = [];
		
		Ext.each(selected, function(rcd) {
			zy_selectedSchools.push(rcd.get("id"));
			schoolNames.push(rcd.get("name"));
		});

		Ext.ComponentQuery.query("#publishnews textfield")[0].setValue(schoolNames.join("; "));
	},

	//如果当前是"教育热讯"面板，则将"可见学校"的textfield和zy_selectedSchools清空
	emptySelectedSchools: function(combo) {

		"use strict"; //启用严格模式

		var publishnews = combo.up("publishnews");

		if(publishnews) {
			publishnews.down("#selectedschools").setValue("");
			zy_selectedSchools = [];
		}
	}
});