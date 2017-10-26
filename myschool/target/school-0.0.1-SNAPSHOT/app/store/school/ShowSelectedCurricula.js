//ShowSelectedCurricula.js
Ext.define("School.store.school.ShowSelectedCurricula", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.ShowSelectedCurricula"
	],
	model: "School.model.school.ShowSelectedCurricula",
	sorters: [{
	    property: 'name',
	    direction: 'ASC'
	}],
	//pageSize: 15,
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {
					//schoolId: zy_schoolId
				};
				store.setProxy(School.util.Util.setProxy('curricula/showCurriculaVariable.action', params , 'info', "totalCount" ));
			}
		}
	}
	// proxy: {
	// 	type: "ajax",
	// 	url: 'data/school/teacher.json',
	// 	reader: {
	// 		type: 'json',
	// 		root: 'items'
	// 	}
	// },
	//autoLoad: true//自动加载数据
});