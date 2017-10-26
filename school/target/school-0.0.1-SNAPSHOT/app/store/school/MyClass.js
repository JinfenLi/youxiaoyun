//MyClass.js
Ext.define("School.store.school.MyClass", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.MyClass"
	],
	model: "School.model.school.MyClass",

	sorters: [{
	    property: 'studentIDCard',
	    direction: 'ASC'
	}],

	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {clazzId: zy_classId};
				store.setProxy(School.util.Util.setProxy('student/getStudentsByClazzId.action', params , 'studentInfo', "totalCount" ));
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