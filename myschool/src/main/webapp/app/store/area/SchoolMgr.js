//SchoolMgr.js
Ext.define("School.store.area.SchoolMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.area.SchoolMgr"
	],
	model: "School.model.area.SchoolMgr",
	//按照学校名字进行排序
	sorters: [{
	    property: 'name',
	    direction: 'ASC'
	}],
	proxy: {
		type: "ajax",
		url: zyHost + 'sch/getAllSchool.action',
		reader: {
			type: 'json',
			root: 'schools'
		}
	}
	//autoLoad: true//自动加载数据
});