//Semester.js
Ext.define("School.store.school.Semester", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.Semester"
	],
	model: "School.model.school.Semester",
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {schoolId: zy_schoolId};
				store.setProxy(School.util.Util.setProxy('semester/getSemesterBySchoolId.action', params , 'semesters' ));
			}
		}
	}
	//autoLoad: true//自动加载数据
});