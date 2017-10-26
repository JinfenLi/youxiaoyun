/*
	path: store.class.Subject
  author: Drake
	description: 学科
*/
Ext.define('School.store.clazz.Subject', {
	extend: 'Ext.data.Store',

	requires: [
		'School.model.clazz.Subject'
	],

	model: 'School.model.clazz.Subject',

	proxy: {
		type: 'ajax',
		url: '/school/appraiseSubject/getSubject.action',

		reader: {
			type: 'json',
			root: 'result'
		}
	},

	autoLoad: false
});