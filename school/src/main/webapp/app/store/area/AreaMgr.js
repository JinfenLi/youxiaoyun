//ClassMgr.js
Ext.define("School.store.area.AreaMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.area.AreaMgr"
	],
	model: "School.model.area.AreaMgr",
	autoLoad: false,
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {};
				store.setProxy(School.util.Util.setProxy('area/getSchoolAndLoginCountOfArea.action', params , 'schools' ));
			}
		}
	}
});