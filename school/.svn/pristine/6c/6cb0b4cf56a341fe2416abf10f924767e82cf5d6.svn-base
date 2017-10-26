Ext.define('School.store.school.position.Position', {
	extend: 'Ext.data.Store',

	requires: [
		'School.model.school.Position'
	],

	model: 'School.model.school.Position',

	proxy: {
		type: 'ajax',
		url: '/school/teacher_position/getAllPositions.action',

		//api: {
		//	create: '/school/teacher_position/addPosition.action',
		//	read: '/school/teacher_position/getAllPositions.action',
		//	update: '/school/teacher_position/updatePosition.action',
		//	destroy: '/school/teacher_position/deletePosition.action'
		//},

		reader: {
			type: 'json',
			root: 'result'
		}

	},

	autoLoad: true
});