/*
	path: view.push.ContactTree
  	author: Drake
	description: 联系人列表
*/

Ext.define('School.view.push.ContactTree', {
	extend: 'Ext.tree.Panel',
	alias: 'widget.contacttree',

	requires: [
		'School.store.push.Contact'
	],

	title: '联系人列表',
	rootVisible: false,
	frame: false,
	split: true,
	displayField: 'name', // 显示在treepanel上的字段，默认是'text'
	store: Ext.create('School.store.push.Contact')

});
