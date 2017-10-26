/*
	path: view.school.position.TeacherInPosition
  author: Drake
	description: 查看该职位下的所有教师
*/
Ext.define('School.view.school.position.TeacherInPosition', {
	extend: 'Ext.window.Window',
	alias: 'widget.teacherinpositionwin',

	requires: [
		'School.store.school.position.TeacherInPosition'
	],

	width: 500,
	height: 400,
	autoShow: true,
	layout: 'fit',
	overflowY: 'scroll',

	initComponent: function () {
		var me = this;

		Ext.apply(me, {
			items: [{
				xtype: 'grid',

				store: Ext.create('School.store.school.position.TeacherInPosition'),

				columns: [{
					hidden: true,
					dataIndex: 'id'
				}, {
					text: '教师姓名',
					dataIndex: 'name'
				}, {
					text: '性别',
					dataIndex: 'sex'
				}, {
					text: '教育程度',
					dataIndex: 'education'
				}, {
					text: '电话',
					dataIndex: 'phone'
				}]

				//dockedItems: [{
				//	dock: 'bottom',
				//	xtype: 'pagingtoolbar',
				//	store: Ext.create('School.store.school.position.TeacherInPosition'),
				//	displayInfo: true
				//}]
			}]
		});

		me.callParent();
	}
});