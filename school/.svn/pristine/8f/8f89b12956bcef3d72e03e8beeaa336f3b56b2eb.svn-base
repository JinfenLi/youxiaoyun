/*
	path: store.push.ParentContact
  author: Drake
	description:
*/

Ext.define('School.store.push.ParentContact', {
	extend: 'Ext.data.Store',

	requires: [
		'School.model.push.ParentContact'
	],

	model: 'School.model.push.ParentContact',

	proxy: {
		type: 'ajax',
		url: '/school/contact/getParentContactForWeb.action',

		reader: {
			type: 'json',
			root: ''
		}
	}

});
