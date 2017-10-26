//DepartMgr.js
Ext.define("School.store.school.DepartMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.DepartMgr"
	],
	model: "School.model.school.DepartMgr",
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {
					// subjectId: zy_subjectId,
					schoolId: zy_schoolId
					// type: ""
				};
				store.setProxy(School.util.Util.setProxy('department/selectDepartments.action', params , 'departments' ));
			}
		}
	}
	//autoLoad: true//自动加载数据

});