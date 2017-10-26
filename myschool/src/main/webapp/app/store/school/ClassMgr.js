//ClassMgr.js
Ext.define("School.store.school.ClassMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.ClassMgr"
	],
	model: "School.model.school.ClassMgr",
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {gradeId: zy_gradeId};
				store.setProxy(School.util.Util.setProxy('clazz/getClazzByGradeId.action', params , 'clazzs' ));
			}
		}
	}
	// proxy: {
	// 	type: "ajax",
	// 	url: zyHost + 'clazz/getClazzByGradeId.action',
	// 	reader: {
	// 		type: 'json',
	// 		root: 'clazzs'
	// 	}
	// },
	//autoLoad: true//自动加载数据
});