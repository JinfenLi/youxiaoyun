//used in AddExam.js
Ext.define("School.store.exam.ExamType", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.exam.ExamType"
	],
	model: "School.model.exam.ExamType",

	listeners: {
		beforeload: {
			fn: function(store) {
				store.setProxy(School.util.Util.setProxy('exam/showExamTemplet', '' , 'examTemplets', "" ));
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