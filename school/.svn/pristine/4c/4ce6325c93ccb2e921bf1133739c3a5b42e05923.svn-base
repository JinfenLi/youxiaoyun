/*
	path: view.push.ContactTree
  	author: Drake
	description: 联系人列表
*/

// Ext.define('School.view.push.ContactTree', {
// 	extend: 'Ext.tree.Panel',
// 	alias: 'widget.contacttree',

// 	requires: [
// 		'School.store.push.Contact'
// 	],

// 	title: '联系人列表',
// 	rootVisible: false,
// 	frame: false,
// 	split: true,
// 	displayField: 'name', // 显示在treepanel上的字段，默认是'text'
// 	store: Ext.create('School.store.push.Contact')

// });
Ext.define('School.view.push.ContactTree', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.contacttree',

	requires: [
		'School.store.push.Contact',
		'School.store.push.SchoolContact'
	],
	layout: 'border',
	items:[
	{
		region: 'north',
		xtype: 'treepanel',
		// height: 80,
		itemId:'schoolcontact',
		title: '发送至全校学生',
		permissionId: 'sendMessageToStudent',
		rootVisible:false,
		frame:false,
		displayField:'name',
		store:Ext.create('School.store.push.SchoolContact')
	},
	{
		region: 'center',
		itemId:'contactPanel',
		xtype: 'treepanel',
		title: '联系人列表',
		rootVisible: false,
		frame: false,
		split: true,
		overflowY: 'auto',
		displayField: 'name', // 显示在treepanel上的字段，默认是'text'
		store: Ext.create('School.store.push.Contact')		
	}
	]
});