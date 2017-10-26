//ClassMgr.js
Ext.define("School.store.area.AreaQuery", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.area.AreaQuery"
	],
	model: "School.model.area.AreaQuery",
	autoLoad: false,
	//设置数据代理
	proxy: {
		type: "postproxy",
		url: "http://baidu.com",
		reader: {
			type: 'json',
			root: 'areas'
		}
	}
});