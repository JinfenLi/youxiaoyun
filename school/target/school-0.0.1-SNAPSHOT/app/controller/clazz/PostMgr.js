/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 班级帖子控制器
 * 
 */
Ext.define("School.controller.clazz.PostMgr", {
	extend: "Ext.app.Controller",
	views: [
		"clazz.PostMgr"
	],
	init: function(application) {

		this.control({

			//store加载数据
			"postmgr": {
				render: function(component) {
					component.getStore().load();
				}
			},

			//查看帖子详情
			"postmgr #viewpost": {
				itemclick:  function(grid, rowIndex, colIndex) {
					this.viewPost(grid, rowIndex, colIndex);				
				}
			},

			//删除帖子
			"postmgr #deletepost": {
				itemclick:  function(grid, rowIndex, colIndex) {
					this.deletePost(grid, rowIndex);
				}
			}

		});
	},

	viewPost: function(grid, rowIndex ,colIndex) {

		"use strict";

		var rcd = grid.getStore().getAt(rowIndex),
			title = rcd.get("title"),
			context = rcd.get("context"),
			createTime = rcd.get("createTime"),
			praisesCount = rcd.get("praisesCount"),
			repliesCount = rcd.get("repliesCount"),
			publisherName = rcd.get("publisherName"),
			urls = rcd.get("urls");

		Ext.create("School.view.clazz.ViewPost");

		setTimeout(function() {

			var win = document.getElementById("postWin").contentWindow,
				$ = win.$;

			$(".user-value").text(publisherName);
			$(".title-value").text(title || "无");
			$(".time-value").text(createTime);
			$(".context-value").text(context);
			$(".comment-value").text("(" + (repliesCount || 0) + ")");
			$(".praise-value").text("(" + (praisesCount || 0) + ")");

			for(var i = 0, len = urls.length; i < len; i++ ) {
				$(".images").append( '<div class="col-xs-4"><img src="' +
						urls[i] + '" class="img-responsive" /></div>' );
			}

		}, 1000);
	},

	deletePost: function(grid, rowIndex) {

		"use strict";

		var store = grid.getStore(),
			rcd = store.getAt(rowIndex),
			postId = rcd.get("id"),
			requestUrl = "post/deletePost.action",
			params = {postId: postId};

		School.util.Util.confirm("删除新闻", function(buttonId) {
			if(buttonId === "yes") {
				School.util.Update.onDeleteButtonClick(store, requestUrl, params);
			}
		});	
	}
});