//TeacherMgr.js
Ext.define("School.store.school.TeacherMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.TeacherMgr"
	],
	model: "School.model.school.TeacherMgr",
	// sorters: [{
	//     property: 'name',
	//     direction: 'ASC'
	// }],
	//因为选课用到老师的下拉框 要设置一个足够大的默认值
	pageSize: 2000,
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {
					schoolId: zy_schoolId
				};
				store.setProxy(School.util.Util.setProxy('teacher/getAllTeacher.action', params , 'teachers', "totalCount" ));
			}
		}
	},
	sorters: [{ 
		property: 'idcard'
	}],
	// proxy: {
	// 	type: "ajax",
	// 	url: 'data/school/teacher.json',
	// 	reader: {
	// 		type: 'json',
	// 		root: 'items'
	// 	}
	// },
	autoLoad: false//自动加载数据
});