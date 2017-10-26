/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 根据年级id获取班级id的store
 * 
 */
Ext.define("School.store.school.GetClassByGradeId", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.clazz.Getstudentmgr"
	],
	model: "School.model.clazz.Getstudentmgr",

	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {
					gradeId: Ext.ComponentQuery.query("getothercurriculamgr")[0].down('toolbar').down('combobox#gradeselect').getValue()
				};
				store.setProxy(School.util.Util.setProxy('clazz/getClazzByGradeId.action', params , 'clazzs', 100 ));
			}
		}
	},

	autoLoad: false
});