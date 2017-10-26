/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 根据年级获取班级，班级的store
 * 
 */
Ext.define("School.store.clazz.Getstudentmgr", {
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
					gradeId: Ext.ComponentQuery.query("getstudentmgr")[0].down('toolbar').down('combobox').getValue()
				};
				store.setProxy(School.util.Util.setProxy('clazz/getClazzByGradeId.action', params , 'clazzs', 100 ));
			}
		}
	},

	autoLoad: false
});