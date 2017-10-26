/*
	path: store.class.Health
  author: Drake
	description:
*/

Ext.define('School.store.clazz.Health', {
	extend: 'Ext.data.Store',
	storeId: 'healthStore',

	requires: [
		'School.model.clazz.Health'
	],

	model: 'School.model.clazz.Health',

	proxy: {
		type: 'ajax',
		url: '/school/healthy/selectByClass.action',
		//url: 'data/class/health.json',

		reader: {
			type: 'json',
			root: 'result',
			totalProperty: 'totalCount'
		}

	},

	autoLoad: true,
	pageSize: 20

});