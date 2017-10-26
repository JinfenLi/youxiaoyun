//VideoMgr.js
Ext.define("School.store.multimedia.VideoMgr", {
	extend: "Ext.data.Store",
	autoLoad: true,
	requires: [
		"School.model.multimedia.VideoMgr"
	],
	model: "School.model.multimedia.VideoMgr",
	autoLoad: false,
	
	
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {
					 libraryId: zy_videolibId
					//type: 0,
				};
				store.setProxy(School.util.Util.setProxy("video/getPassVideo.action", params , "videos", "totalCount" ));
			}
		}
	}
});

