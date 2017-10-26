/**
 * 
 * Author: 张展宇 小玉改复制新闻用treepanel
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
	stores:[
       'news.CopyNewsSchool'
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
            //置顶
            "newsmgr actioncolumn#settop":{
            	itemclick:function(grid,rowIndex,colIndex){
            		this.setTop(grid,rowIndex);
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

      "copynews treepanel#allschool":{
      	checkchange: this.selectToCopy
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
    setTop: function(grid,rowIndex){
        
      var record = grid.getStore().getAt(rowIndex), 
        newsId = record.get("id"),
        type   = record.raw.type,
        createTime = record.raw.createTime;
      var params ={
       	articleId: newsId,
       	type: type,
       	createTime: createTime
      }
      Ext.Ajax.request({
     		url: zyHost + "article/setTop",
     		params:params,
     		timeout: 60000,
				success: function(conn,response,options,eOpts) {
					var result = School.util.Util.decodeJSON(conn.responseText);
					if(result.success){
						grid.getStore().reload();
						console.log(grid.getStore())
						School.util.Util.showSuccessMsg("已经置顶！");
					}
				},
				failure:function(){
					School.util.Util.showErrorMsg("链接服务器失败！");
				}
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
		// console.log(grid.itemId);
        var mainPanel =  Ext.ComponentQuery.query("mainpanel")[0] ,
            winId = mainPanel.getActiveTab().itemId;//获取所在tab的itemId，用于渲染这个窗口的判断
                                                    //复制新闻如果是教育热讯是返回所有学校不包括年级
		var	store = grid.getStore(), 
			record = grid.getStore().getAt(rowIndex),
			id = record.get("id"),
			type = record.get("type");

		//创建一个复制新闻的学校选择窗口
		Ext.create("School.view.news.CopyNews", {
			preStore: store,
			itemId: id,
			type: type,
			winId: winId
		});
	},

	initCopyNews: function(component){
		this.idArr=[];
		var item  =	 {
      	xtype:'treepanel',
      	displayField:'name',
      	itemId:"allschool",
      	rootVisible:false,
      	frame:false,
      	useArrows:true,
      	animate:true,
      	expanded:true,
      	store:Ext.create('School.store.news.CopyNewsSchool')
      }	
     component.add(item);
    
	},
//selectToCopy返回的是选中的所有id数组
    idArr:[],
	selectToCopy:function(node,checked){
    School.util.Util.cascadeNode(node,checked);
    var checkedNodeArr = Ext.ComponentQuery.query('copynews')[0].down('treepanel').getChecked();//所有选中的节点
    this.idArr = [];
    for(var i = 1; i<checkedNodeArr.length;i++){
      if(checkedNodeArr[i].isLeaf()){
      	var nodeId = checkedNodeArr[i].raw.id;
      	this.idArr.push(nodeId);
      }
    }
	},
//复制新闻提交按钮
	copyNews: function(btn){
        var win = btn.up("copynews"),
          params = {
         	articleId:win.getItemId(),
         	zoneId:this.idArr.join(","),
         	type:win.type
         };
         if(this.idArr.length!=0){
         	btn.up("window").mask("正在复制，请耐心等待...");
         	Ext.Ajax.request({
         		url: zyHost + "article/copyArticle.action",
         		params:params,
         		timeout: 60000,
			success: function(conn,response,options,eOpts) {

				var result = School.util.Util.decodeJSON(conn.responseText);

				btn.up("window").unmask();

				if(result.success) {
					btn.up('window').preStore.reload();
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
         	})
         }else{
         	School.util.Util.showWarningMsg("没有选择学校或年级，无法保存复制！");
         }
	},


	// function(btn) {

	// 	"use strict";

	// 	var ids = [],
	// 		win = btn.up("copynews"),
	// 		msg = (win.type === "educate" ? "年级" : "学校"),
	// 		rcds = [],			
	// 		gradeGrids = [];

	// 	if(win.type === "teaching") {
	// 		rcds = win.down("selectschool").getSelectionModel().getSelection();
	// 	} else if(win.type === "educate") {

	// 		gradeGrids = Ext.ComponentQuery.query("selectgrade");
	// 		console.log(gradeGrids.length);
	// 		for(var i = 0, len = gradeGrids.length; i < len; i++) {
	// 			console.log(i);
	// 			rcds = rcds.concat(gradeGrids[i].getSelectionModel().getSelection());
	// 		}
	// 	}
		
	// 	if( !rcds.length ) {
	// 		School.util.Util.showWarningMsg("没有选择" + msg + "，无法保存复制！");
	// 		return 0;
	// 	}

	// 	for(var i = 0; i < rcds.length; i++) {
	// 		ids.push(rcds[i].get("id"));
	// 	}

	// 	btn.up("window").mask("正在复制，请耐心等待...");

	// 	//利用Ajax提交
	// 	Ext.Ajax.request({
	// 		url: zyHost + "article/copyArticle.action",
	// 		timeout: 60000,
	// 		params: {
	// 			articleId: win.getItemId(),
	// 			zoneId: ids.join(","),
	// 			type: win.type
	// 		},
			// success: function(conn,response,options,eOpts) {

			// 	var result = School.util.Util.decodeJSON(conn.responseText);

			// 	btn.up("window").unmask();

			// 	if(result.success) {

			// 		win.destroy();
			// 		School.util.Util.showSuccessMsg("复制成功!");							

			// 	} else {
			// 		School.util.Util.showErrorMsg("保存失败!");
			// 	}
			// },
			// failure: function(conn,response,options,eOpts) {
			// 	btn.up("window").unmask();
			// 	School.util.Util.showErrorMsg("链接服务器失败！");
			// }
	// 	});
	// },

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

		if(button.up("newsmgr").getItemId() === "schoolnews") {
			var newValue = button.up('toolbar').down('combobox').getValue();
			Ext.ComponentQuery.query("publishnews")[0].down('combobox#newscombo').setValue(newValue);
		}
	}


});