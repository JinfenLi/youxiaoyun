//CourseMgr.js
Ext.define("School.store.school.CourseMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.CourseMgr"
	],
	model: "School.model.school.CourseMgr",
	
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {schoolId: zy_schoolId};
				store.setProxy(School.util.Util.setProxy('subject/getAllSubject.action', params , 'subjects' ));
			}
		}
	}
	//autoLoad: true//自动加载数据
	// proxy: {
	// 	type: "ajax",
	// 	url: zyHost + 'subject/getAllSubject.action',
	// 	extraParams: {
	// 		schoolId: zy_schoolId
	// 	},
	// 	reader: {
	// 		type: 'json',
	// 		root: 'subjects'
	// 	}
	// },
	

});