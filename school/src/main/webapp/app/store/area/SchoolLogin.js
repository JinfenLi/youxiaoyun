/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 超级管理员查看选择的学校的登陆情况的store
 * 
 */
Ext.define("School.store.area.SchoolLogin", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.clazz.Getstudentmgr"
	],
	model: "School.model.clazz.Getstudentmgr",

	sorters: [{
	    property: 'name',
	    direction: 'ASC'
	}],

	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {
					gradeId: Ext.ComponentQuery.query("schoollogin")[0].down('toolbar').down('combobox').getValue()
				};
				store.setProxy(School.util.Util.setProxy('clazz/getClazzByGradeId.action', params , 'clazzs', 100 ));
			}
		}
	},

	autoLoad: false
});