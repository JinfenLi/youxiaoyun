/**
 * @Author: 张展宇
 * @Contact: k3note2@gmail.com
 * @Description: 相册管理控制器
 */

Ext.define("School.controller.multimedia.AlbumMgr", {
	extend: "Ext.app.Controller",
	views: [
		"multimedia.AlbumMgr"
	],
	init: function(application) {

		this.control({
			
			 "albummgr": {			 	
			 	afterRender: function(component) {
					this.afterRender(component);		 		
			 	}
			 },

			 //班级相册的"班级选择"下拉框的监听切换班级事件
			"albummgr#classalbum combobox#myclasses": {
				change: function(_this, newValue, oldValue, eOpts) {
					_this.up("albummgr#classalbum").getStore().reload({
						params: {
							clazzId: newValue,
							type: 1
						}
					});
				}
			},


			 //点击“创建相册”按钮时，实例化创建相册的窗口
			 "albummgr button#addAlbum": {

			 	click: function(button) {

			 		var albummgr = button.up("albummgr");

			 		Ext.create("School.view.multimedia.AddAlbum", {
			 			itemId: "add",
			 			params: {
			 				store: albummgr.getStore(), 
			 				range: albummgr.getItemId()
			 			}
			 		});
			 		
			 	}
			 },

			//删除相册
			"albummgr button#deleteAlbum": {
				click: function(button) {
					this.deleteAlbum(button);		
				} 
			},
			 
			 //保存新建的相册
			 "addalbum button#save": {
			 	click: function(button) {		
			 		this.saveAlbum(button);
			 	}
			 },

			 //查看相册里的照片
			 "albummgr actioncolumn#openAlbum": {
			 		itemclick: function(grid, rowIndex, colIndex) {
			 			this.openAlbum(grid, rowIndex, colIndex);						
			 		}
			 }		
		});
	
	},

	afterRender: function(component) {

		"use strict";

		var url = 'album/getAlbumNoPager.action',
 			params = {},
 			root = "albums",
 			total = "totalCount",
 			store = component.getStore(),
 			classCombo = component.down("#myclasses"); 
 		
 		//如果是校园相册
 		if(component.getItemId() === "schollalbum") {

 			classCombo.hide(); 
 			params = { 
 				schoolId: zy_schoolId,
	 			type: 1
 			}; 			

 			store.setProxy(School.util.Util.setProxy(url, params , root, total));
	 		store.load();
	 		return 0; 
 		} 

 		//如果是班级相册，则执行下面代码
 		params = {
 			clazzId: zy_classId,
			type: 1
 		};

 		store.setProxy(School.util.Util.setProxy(url, params , root, total ));
 		classCombo.setValue(zy_classId);
	},

	deleteAlbum: function(button) {

		"use strict";

		var grid = button.up("gridpanel"), 
			record = grid.getSelectionModel().getSelection(),
			store = grid.getStore(), 
			requestUrl = zyHost + "album/deleteAlbum.action", 
			params = {};

		//判断是否选中了一个相册
		if(record.length === 0) {
			School.util.Util.showErrorMsg("请选择一个需要删除的相册");
			return ;
		}

		params.albumId = record[0].get("id");

		School.util.Util.confirm("删除相册", function(buttonId) {
			if(buttonId === "yes") {
				School.util.Update.onDeleteButtonClick(store, requestUrl, params);
			}
		});				
	},

	saveAlbum: function(button) {

		"use strict";

		var win = button.up("addalbum"),
 			url = "album/createAlbum.action",
 			values = win.down("form").getForm().getValues(false),
 			store = win.params.store, 
 			range = win.params.range;

		//检测表单是否填写完整
 		if( !button.up("addalbum").down("form").getForm().isValid()) {
 			School.util.Util.showErrorMsg("相册名称不可为空或长度不可超出12个字符！");
 			return 0;
 		}
 		

		//校园相册
 		if(range === "schollalbum") {

 			delete values.clazzId; 
 			values.schoolId = zy_schoolId; 

 		//班级相册
 		} else {
 			delete values.schoolId; 
 			var albummgrs = Ext.ComponentQuery.query("albummgr"),
 				index;
 			index = albummgrs[0].getItemId() === "schollalbum" ? 1 : 0;
 			values.clazzId = albummgrs[index].down("#myclasses").getValue();
 			
 		}
 		
 		School.util.Update.onSaveButtonClick(win, url, store, values);	
	},

	openAlbum: function(grid, rowIndex, colIndex) {

		"use strict";

		var mainPanel = Ext.ComponentQuery.query("mainpanel")[0], 
			title = '照片管理',	
			xtype = "photomgr",
			record =  grid.getStore().getAt(rowIndex),
			albumName = record.get("name"),
			photomgrs = [],
			photo = "";
		
		//更新全局变量zy_albumId
		zy_albumId = grid.getStore().getAt(rowIndex).get("id");		
		
		//把照片管理面板添加到窗口
		School.util.AddTab.addTab(mainPanel, title, xtype);
		
		//获取到所有的别名为photomgr的面板
		photomgrs = Ext.ComponentQuery.query("photomgr");
		

		//因为"轮播照片"和"照片管理"都是"photomgr"的实例，而且是可以同时打开的，
		//因此，遍历"photomgr"的实例，然后找出不是"轮播照片"的那个实例
		for(var i = 0; i < photomgrs.length; i++) {
			if(photomgrs[i].getItemId() !== "viewPager") {
				photo = photomgrs[i];
				break;
			}
		}
		
		if(photo) {
			photo.down("dataview").getStore().reload({
				params: {
					albumId: zy_albumId
				},
				callback:function() {
					photo.down("label#albumName").setText(albumName);
				}
			});
		}
	}

});