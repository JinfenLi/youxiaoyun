/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 视频库管理的控制器
 * 
 */
Ext.define("School.controller.multimedia.LibMgr", {
	extend: "Ext.app.Controller",
	views: [
		"multimedia.VideolibMgr",
		"multimedia.VideoMgr"
	],
	init: function(application) {
		
		this.control({

			//渲染完成后自动请求数据
			"videolibmgr": {
				afterrender: function(component) {
					this.afterRender(component);					
				}
			},


			//切换班级
			"videolibmgr#classlib combobox#myclasses": {
				change: function(_this, newValue, oldValue, eOpts) {
					_this.up("videolibmgr#classlib").getStore().reload({
						params: {
							clazzId: newValue
						}
					});
				}
			},

			//点击“创建视频库”按钮时，实例化一个创建视频库的窗口
			 "videolibmgr button#addLib": {
			 	click: function(button) {

			 		Ext.create("School.view.multimedia.AddVideolib", {
			 			itemId: "add",
			 			params: {
				 			itemId: button.up("videolibmgr").getItemId()
				 		}
			 		});			 		
			 	}
			 },


			 //点击“修改视频库”按钮时，实例化一个创建视频库的窗口
			 "videolibmgr button#editLib": {
			 		click: function(button) {
			 			this.createEditWin(button);						
			 		}
			 },

			  //保存视频库
			 "addvideolib button#save": {
			 	click: function(button) {
					this.saveLib(button);			 		
			 	}
			 },

			 //删除视频库
			"videolibmgr button#deleteLib": {
				click: function(button, e, options) {
					this.deleteLib(button);
				} 
			},

			//打开视频库
			"videolibmgr actioncolumn#openVideolib": {
				itemclick: function(grid, rowIndex, colIndex) {
					this.openLib(grid, rowIndex, colIndex);					
				}
			}

		});
	
	},

	afterRender: function(component) {

		"use strict";

		var url = "library/getLibrary.action",
			params = {},
			root = "librarys",
			total = "totalCount",
			store = component.getStore();

		//如果是校园视频库
		if(component.getItemId() === "schoollib") {
			component.down("combobox#myclasses").hide(); //隐藏班级选择下拉框
			params.schoolId = zy_schoolId;
			store.setProxy(School.util.Util.setProxy(url, params , root, total));
			store.load();

		//否则如果是班级视频库
		} else { 
			params = {clazzId: zy_classId};
			store.setProxy(School.util.Util.setProxy(url, params , root, total));
			component.down("combobox#myclasses").setValue(zy_classId);
		}
	},

	createEditWin: function(button) {

		"use strict";

		var grid = button.up("gridpanel"),
			selected = grid.getSelectionModel().getSelection();

		//判断是否选择了一个视频库
		if(selected.length === 0) {
			School.util.Util.showErrorMsg("请选择一个视频库");
			return;
		}			 			
		
		Ext.create("School.view.multimedia.AddVideolib", {
			title: "修改视频库",
			itemId: "edit",
			params: {
				itemId: grid.getItemId(),
				libId: selected[0].get("id")
			}
		}).down("form").loadRecord(selected[0]);
	},

	saveLib: function(button) {

		"use strict";

		var win = button.up("addvideolib"),
 			videolibmgr = Ext.ComponentQuery.query("videolibmgr#" + 
 						  button.up("window").params.itemId)[0],
 			store = videolibmgr.getStore(),
 			url = "library/createLibrary.action",
 			values = button.up("addvideolib")
 					.down("form").getForm().getValues(false);
 	
 		if( !button.up("addvideolib").down("form").getForm().isValid() ) {
			School.util.Util.showWarningMsg("请正确填好表单！");
			return;
		}
 		
			switch(win.getItemId()) {

				case "add": 
					//判断相册的类型
		 		if(win.params.itemId === "schoollib") {
		 			delete values.clazzId;
		 			values.schoolId = zy_schoolId;
		 		} else {
		 			delete values.schoolId;
		 			values.clazzId = videolibmgr.down("combobox#myclasses").getValue();
		 		}
					break;

				//修改
				default: 
					url = "library/updateLibrary.action";
					values.id = win.params.libId;
					break;
			}

 			//提交表单
			School.util.Update.onSaveButtonClick(win, url, store, values);

	},

	deleteLib: function(button) {

		"use strict";

		var grid = button.up("gridpanel"), 
			record = grid.getSelectionModel().getSelection(); 

		//判断是否选中一个视频库
		if(!record.length) {
			School.util.Util.showErrorMsg("请选择一个需要删除的视频库！");
			return 0;
		}

		//如果视频库里面有视频文件，则提示并退出删除操作
		if(record[0].get("photoCount2")) {
			School.util.Util.showErrorMsg("该视频库里有视频文件，无法删除，您可以先把里面的视频删除！");
			return 0;
		}

		var store = grid.getStore(),
			title = "删除视频库",
			requestUrl = "library/deleteLibrary.action", 
			params = { libraryId: record[0].get("id") };
				
		School.util.Util.confirm("删除视频库", function(buttonId) {
			if(buttonId === "yes") {
				School.util.Update.onDeleteButtonClick(store, requestUrl, params);
			}
		});
	},

	openLib: function(grid, rowIndex, colIndex) {

		"use strict";

		var mainPanel = Ext.ComponentQuery.query("mainpanel")[0],
			title = '视频管理',	
			xtype = "videomgr",
			video = {}, 
			videolibName = grid.getStore().getAt(rowIndex).get("name2");

		//更新与视频库相关的全局变量
		zy_videolibId = grid.getStore().getAt(rowIndex).get("id"); 
		
		//通过addTab函数为mainpanel创建一个新的子项
		School.util.AddTab.addTab(mainPanel, title, xtype);

		video = Ext.ComponentQuery.query("videomgr")[0];	

		video.getStore().reload({
			callback:function() {
				video.down("label#libName").setText(videolibName);
			}
		});
	
	}


});