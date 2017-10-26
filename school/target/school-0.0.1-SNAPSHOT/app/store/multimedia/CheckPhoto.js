Ext.define("School.store.multimedia.CheckPhoto", {
	extend: "Ext.data.Store",
	autoLoad: true,
	requires: [
		"School.model.multimedia.CheckPhoto"
	],
	model: "School.model.multimedia.CheckPhoto",
	storeId: "photos",
	autoLoad: false,
	sorters: [
		{
		    property: 'name',
		    direction: 'DESC' //降序排序
		}
	],
	
	// sorters: [
	// 	{name: "lastWriteTime", direction: "DESC"}
	// ],
	
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {
					//albumId: zy_albumId
					//type: 0,
				};
				store.setProxy(School.util.Util.setProxy("album/getNoPassAlbum.action", params , "photos", "totalCount" ));
			}
		}
	}
});

