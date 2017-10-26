Ext.define('School.store.clazz.WordTpl', {
	extend: 'Ext.data.Store',

	requires: [
		'School.model.clazz.WordTpl'
	],

	model: 'School.model.clazz.WordTpl',

	listeners: {
		beforeload: {
			fn: function(store) {

				var url = "appraiseSubject/getAppraiseSubject.action",
					query = "appraisewin wordtpl #type",
					typeRadio = Ext.ComponentQuery.query(query)[0],	
					root = typeRadio ? typeRadio.getValue().type : "resultForTeacher",	
					params = {};

				console.log(root);

				store.setProxy(School.util.Util.setProxy(url, params , root));
			}
		}
	}

});