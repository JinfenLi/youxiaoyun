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
				//三个地方重用了这个模块，所以需要判断参数，避免分页之后出错
				//三个地方分别是校园新闻，年级新闻，区域管理里的查看教育热讯
				var params = {};
				var actTab = Ext.ComponentQuery.query("mainpanel")[0].getActiveTab();
				var itemId = actTab.getItemId();
				
				// if(Ext.ComponentQuery.query('newsmgr')[0]) {
					if(itemId == 'schoolnews') {
						params = {
							schoolId: zy_schoolId,
							type: actTab.down('#newscombo').getValue()
						};
					} else if(itemId == 'classnews'){
						params = {
							gradeId: actTab.down('#mygrades').getValue(),
							type: 'educate'
						};
					} else {
						params = {
	          	type: "teaching",
          		schoolId: actTab.schoolId						
						}
					} 
				// } 
				store.setProxy(School.util.Util.setProxy(
						'article/getAllArticle.action', params , 'articles'));
			}
		}
	}
});