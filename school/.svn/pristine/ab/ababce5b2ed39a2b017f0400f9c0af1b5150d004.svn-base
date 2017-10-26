Ext.define("School.store.school.CurriculaMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.CurriculaMgr"
	],
	model: "School.model.school.CurriculaMgr",
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {subjectId: zy_subjectId};
				store.setProxy(School.util.Util.setProxy('curricula/getCurriculaBySubjectId.action', params , 'curriculas' ));
			}
		}
	}
	// proxy: {
	// 	type: "ajax",
	// 	url: zyHost + 'curricula/getCurriculaBySubjectId.action',
	// 	// extraParams: {
	// 	// 	subjectId: "1"
	// 	// },
	// 	reader: {
	// 		type: 'json',
	// 		root: 'curriculas'
	// 	}
	// },
	//autoLoad: true//自动加载数据

});