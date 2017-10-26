/*
	path: store.class.Appraise
  author: Drake
	description:
*/

Ext.define('School.store.clazz.Appraise', {
	extend: 'Ext.data.Store',

	requires: [
		'School.model.clazz.Appraise'
	],

	model: 'School.model.clazz.Appraise',

	proxy: {
		type: 'ajax',
		//url: 'data/class/appraise.json',
		//url: '/school/appraise/getAppraiseByteacher.action',
		url: '/school/appraise/getAppraiseByteacherForPc',
		actionMethods: {
			read: 'POST'
		},

		reader: {
			type: 'json',
			root: 'result'
		}
	},

	autoLoad: false
});