//SelectCurricula.js
Ext.define("School.store.school.SelectCurricula", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.ClassMgr"
	],
	model: "School.model.school.ClassMgr",
	listeners: {
		beforeload: {
			fn: function(store) {
				zy_curriculaTeacher = new Array(); //重置全局变量zy_curriculaTeacher的值
				var params = {
					// subjectId: zy_subjectId,
		     //adaptiveGrade: "",
		     //adaptiveTerm: "",
		     //gradeId: zy_gradeId
				};
				store.setProxy(School.util.Util.setProxy('curricula/chooseCurricula.action', params , 'clazzs' ));
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