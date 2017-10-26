Ext.define('School.store.auth.User', {
	extend: 'Ext.data.Store',

	requires: [
	'School.model.auth.User'
	],

	model: 'School.model.auth.User',

	proxy: {
		type: 'ajax',
		url: 'data/auth/user.json',

		reader: {
			type: 'json',
			root: 'data'
		}
	}
});