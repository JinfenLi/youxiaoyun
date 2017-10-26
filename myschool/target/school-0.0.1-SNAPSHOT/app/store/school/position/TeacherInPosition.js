/*
	path:
  author: Drake
	description: 
*/

Ext.define('School.store.school.position.TeacherInPosition', {
	extend: 'Ext.data.Store',
	storeId: 'teacherinposition',

	requires: [
		"School.model.school.TeacherMgr"
	],

	model: "School.model.school.TeacherMgr",

	proxy: {
		type: 'ajax',
		url: '/school/teacher/getTeacherByPositionId.action',

		reader: {
			type: 'json',
			root: 'teachers',
			totalProperty: 'totalCount'
		}

	},

	pageSize: 10,
	autoLoad: false

});
