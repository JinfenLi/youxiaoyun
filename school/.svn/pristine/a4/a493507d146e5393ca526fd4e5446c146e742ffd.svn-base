//GradeMgr.js
Ext.define("School.store.school.GradeMgr", {
	extend: "Ext.data.Store",
	requires: [
		 "School.model.school.GradeMgr"
	],
	model: "School.model.school.GradeMgr",

	//按level字段从小到大进行排序
	sorters: [{
	    property: 'level',
	    direction: 'ASC'
	}],
	//autoLoad: true,

	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {schoolId: zy_schoolId};
				store.setProxy(School.util.Util.setProxy('grade/getAllGrade.action', params , 'grades' ));
			}
		}
	}
});