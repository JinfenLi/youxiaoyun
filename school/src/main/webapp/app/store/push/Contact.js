/*
	path: School.store.push.Contact.js
  author: Drake
	description:
*/

Ext.define('School.store.push.Contact', {
	extend: 'Ext.data.TreeStore',

	model: 'School.model.push.Contact',

	proxy: {
		type: 'memory'
	},

	root: {
		//expanded: true,
		text: '',
		children: []
	},

	reader: {
		type: 'json'
	}

});