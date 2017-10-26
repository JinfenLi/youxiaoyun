/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 视频库的Store
 * Addition: 该Store的数据代理(proxy)是在School.controller.Lib_Video里面设置的
 * 
 */
Ext.define("School.store.multimedia.VideolibMgr", {
	extend: "Ext.data.Store",
	sorters: [{
	    property: 'createTime',
	    direction: 'DESC'
	}],
	requires: [
		"School.model.multimedia.VideolibMgr"
	],
	model: "School.model.multimedia.VideolibMgr",
	autoLoad: false //不要自动加载数据
	
});