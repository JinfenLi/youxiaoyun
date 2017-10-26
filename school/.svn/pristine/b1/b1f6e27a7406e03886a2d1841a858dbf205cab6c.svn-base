//AlbumMgr
Ext.define("School.store.multimedia.AlbumMgr", {
	extend: "Ext.data.Store",

	//按相册的创建日期排序，即最新创建的排在前面
	sorters: [{
	    property: 'updateTime',
	    direction: 'DESC'
	}],

	requires: [
		"School.model.multimedia.AlbumMgr"
	],
	model: "School.model.multimedia.AlbumMgr"
});