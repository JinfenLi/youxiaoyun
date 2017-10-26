//ClassMgr.js
Ext.define("School.store.clazz.PostMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.clazz.PostMgr"
	],
	model: "School.model.clazz.PostMgr",
	autoLoad: false,

	listeners: {
		beforeload: {
			fn: function(store) {
				
				var clazzId = Ext.ComponentQuery.query("myclass #myclasses")[0].getValue(),
					params = { clazzId: clazzId};
				store.setProxy(School.util.Util.setProxy('post/seePost.action', params , 'posts' ));
			}
		}
	}
});