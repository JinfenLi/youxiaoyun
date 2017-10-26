//NewsMgr.js
Ext.define("School.store.news.NewsMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.news.NewsMgr"
	],
	model: "School.model.news.NewsMgr",
	autoLoad: false,
	
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {};
				store.setProxy(School.util.Util.setProxy(
						'article/getAllArticle.action', params , 'articles' ));
			}
		}
	}
});