/*
	path: store.push.Contact
  author: Drake
	description:
*/

Ext.define('School.store.push.TeacherContact', {
	//extend: 'Ext.data.TreeStore',
	extend:'Ext.data.Store',
	storeId: 'teachercontact',

	requires: [
	],

	model: 'School.model.push.TeacherContact',

	proxy: {
		type: 'ajax',
		url: '/school/contact/getTeacherContactForWeb.action',
		//url: 'data/push/contact.json',
		reader: {
			type: 'json',
			//root: 'children'
		}
	},

	//root: {
	//	expanded: true,
	//	text: '',
	//	children: []
	//},

	//folderSort: true,

	autoLoad: true

});