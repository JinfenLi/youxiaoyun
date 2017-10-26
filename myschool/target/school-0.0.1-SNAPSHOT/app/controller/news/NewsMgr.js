/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 新闻管理控制器
 * 
 */

Ext.define("School.controller.news.NewsMgr", {
	extend: "Ext.app.Controller",
	views: [
		"news.NewsMgr",
		"news.PublishNews",
		"news.ReadNews"
	],
	init: function(application) {
		this.control({

			//新闻管理页面渲染时为新闻选择下拉框设置一个默认值
			"newsmgr": {
				afterrender: function(component) {
					this.afterRender(component);					
				}
			},

			//学校新闻的切换新闻类型
			"newsmgr combo#newscombo": {
				change: function(_this, newValue, oldValue, eOpts) {
					this.changeNews(_this, newValue);
				}
			},

			//年级新闻的切换年级
			"newsmgr combo#mygrades": {
				change: function(_this, newValue, oldValue, eOpts) {
					this.changeGrade(_this, newValue);
				}
			},			


			//查看新闻
			"newsmgr actioncolumn#details": {
				itemclick: function(grid, rowIndex, colIndex) {
					this.viewNews(grid, rowIndex);
				}
			},

			//复制新闻
			"newsmgr actioncolumn#copy": {
				itemclick: function(grid, rowIndex, colIndex) {
					this.createCopyWin(grid, rowIndex);
				}
			},

			"copynews": {
				afterrender: function(component) {
					this.initCopyNews(component);
				}
			},

			"copynews button#save": {
				click: function(btn) {
					this.copyNews(btn);
				}
			},

			"readnews": {
				afterrender: function(component) {
					this.readNews(component);
				}
			},			

			//删除新闻
			"newsmgr actioncolumn#delete": {
				itemclick: function(grid, rowIndex, colIndex) {
					this.deleteNews(grid, rowIndex);
				}
			},

			//新建新闻
			"newsmgr button#publish": {
				click: function(button) {
					this.createNews(button);
				}
			}
		});
	
	},

	afterRender: function(component) {

		"use strict";

		var itemId = component.getItemId();

		//如果是校园新闻
		if(itemId === "schoolnews") {

			component.down("combo#mygrades").destroy();
			component.down("combo#newscombo").setValue("news");
		
		//如果是教育热讯
		} else if(itemId === "hotnewslist") {

			component.down("combo#mygrades").destroy();
			component.down("combo#newscombo").destroy();
			component.down("button#publish").destroy();

		//如果是年级新闻
		} else {
			component.down("combo#newscombo").destroy();
			var mygrades = component.down("combo#mygrades");
			component.gradeStore.reload({
				callback: function() {
					 mygrades.setValue(component.gradeStore.getAt(0).get("id"));
				}
			});
		}
	},

	changeNews: function(_this, newValue) {
		_this.up("newsmgr").getStore().reload({
			params: {
				schoolId: zy_schoolId,
				type: newValue
			}
		});
	},

	changeGrade: function(_this, newValue) {
		_this.up("newsmgr").getStore().reload({
			params: {
				gradeId: newValue,
				type: "educate"
			}
		});
	},

	readNews: function(component) {
		component.add({
			xtype: "panel",
			html: '<iframe src= ' + zyHost 
					+ 'article/getArticle.action?articleId='
					+ component.getItemId() 
					+ '  frameborder=0 width=100% height=100%></iframe>'
		});
	},

	viewNews: function(grid, rowIndex) {

		"use strict";

		var newsId = grid.getStore().getAt(rowIndex).get("id"),
       		readnews = Ext.ComponentQuery.query("readnews")[0];

        //假如已经存在了查看新闻的面板，则把它关闭
        if(readnews) {
        	readnews.close();
        } 

        //重新打开查看新闻面板
        var mainPanel = Ext.ComponentQuery.query("mainpanel")[0],
	        title = "查看新闻",
	        xtype = "readnews"; 
        School.util.AddTab.addTab(mainPanel, title, xtype, "", newsId);
	},

	createCopyWin: function(grid, rowIndex) {

		var record = grid.getStore().getAt(rowIndex),
			id = record.get("id"),
			type = record.get("type");

		//创建一个复制新闻的学校选择窗口
		Ext.create("School.view.news.CopyNews", {
			itemId: id,
			type: type
		});
	},

	initCopyNews: function(component) {

		var item = {
			xtype: "selectschool",
			itemId: "#content"
		};	

		if(component.type === "teaching") {			
			component.down("#content").destroy();
			component.add(item);
			return;
		}

		

		//获取所有学校
		Ext.Ajax.request({
			url: zyHost + "sch/getAllSchool.action",
			timeout: 60000,
			success: function(conn,response,options,eOpts) {
				var schools = School.util.Util.decodeJSON(conn.responseText).schools,
					names = [],
					len = schools.length;

				//获取每个学校的所有年级
				for(var i = 0; i < len; i++) {					
					item = {
						xtype: "selectgrade",
						title: schools[i].name,
						itemId: schools[i].id
					};	

					component.down("#content").add(item).getStore().reload({
						params: {
							schoolId: schools[i].id
						}
					});
				}
				
			},
			failure: function(conn,response,options,eOpts) {
				School.util.Util.showErrorMsg("链接服务器失败！");
			}
		});


	},

	copyNews: function(btn) {

		"use strict";

		var ids = [],
			win = btn.up("copynews"),
			msg = (win.type === "educate" ? "年级" : "学校"),
			rcds = [],			
			gradeGrids = [];

		if(win.type === "teaching") {
			rcds = win.down("selectschool").getSelectionModel().getSelection();
		} else if(win.type === "educate") {

			gradeGrids = Ext.ComponentQuery.query("selectgrade");
			console.log(gradeGrids.length);
			for(var i = 0, len = gradeGrids.length; i < len; i++) {
				console.log(i);
				rcds = rcds.concat(gradeGrids[i].getSelectionModel().getSelection());
			}
		}
		
		if( !rcds.length ) {
			School.util.Util.showWarningMsg("没有选择" + msg + "，无法保存复制！");
			return 0;
		}

		for(var i = 0; i < rcds.length; i++) {
			ids.push(rcds[i].get("id"));
		}

		btn.up("window").mask("正在复制，请耐心等待...");

		//利用Ajax提交
		Ext.Ajax.request({
			url: zyHost + "article/copyArticle.action",
			timeout: 60000,
			params: {
				articleId: win.getItemId(),
				zoneId: ids.join(","),
				type: win.type
			},
			success: function(conn,response,options,eOpts) {

				var result = School.util.Util.decodeJSON(conn.responseText);

				btn.up("window").unmask();

				if(result.success) {

					win.destroy();
					School.util.Util.showSuccessMsg("复制成功!");							

				} else {
					School.util.Util.showErrorMsg("保存失败!");
				}
			},
			failure: function(conn,response,options,eOpts) {
				btn.up("window").unmask();
				School.util.Util.showErrorMsg("链接服务器失败！");
			}
		});
	},

	deleteNews: function(grid, rowIndex) {

		"use strict";

		var store = grid.getStore(),
			newsId = store.getAt(rowIndex).get("id"),
			type = store.getAt(rowIndex).get("type"),
			requestUrl = "article/deleteArticle.action",
			params = {articleId: newsId},
			extraParams = {};

		//刷新新闻管理的store需要用到的参数
		if(type === "educate") { //年级新闻的
			extraParams.type = "educate";
			extraParams.gradeId = grid.up("newsmgr").down("combo#mygrades").getValue();
		} else { //不是年级新闻的
			extraParams.type = type;
			extraParams.schoolId = grid.up("newsmgr").schoolId || zy_schoolId;
		} 

		console.log(extraParams);

		School.util.Util.confirm("删除新闻", function(buttonId) {
			if(buttonId === "yes") {
				School.util.Update.onDeleteButtonClick(store, requestUrl, params, extraParams);
			}
		});	
	},

	createNews: function(button) {

		"use strict";

		var mainPanel = Ext.ComponentQuery.query("mainpanel")[0],
			title = "发布年级新闻", 
			itemId = "publishclassnews",
			xtype = "publishnews"; 

		//如果是校园新闻
		if(button.up("newsmgr").getItemId() === "schoolnews") {
			title = '发布校园新闻';
			itemId = "publishschoolnews";
		} 	

		//通过addTab函数为mainpanel创建一个新的子项
		School.util.AddTab.addTab(mainPanel, title, xtype, "menu_profile", itemId);
	}


});