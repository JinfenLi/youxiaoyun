/*
 path: view.clazz.appraise.AppraiseMgr
 author: Drake
 description:
 */

Ext.define('School.view.clazz.appraise.AppraiseMgr', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.appraisemgr',

	requires: [
		'School.store.clazz.Appraise',
		'School.view.clazz.appraise.AppraiseToolbar'
	],


	initComponent: function () {
		var me = this;

		Ext.apply(me, {
			columnLines: true,
			selType: 'checkboxmodel',
			selModel: {
				mode: 'MULTI'
			},
			store: Ext.create('School.store.clazz.Appraise'),

			dockedItems: [{
				dock: 'top',
				xtype: 'appraisetoolbar'
			}],

			columns: [{
				hidden: true,
				dataIndex: 'id'
			}, {
				hidden: true,
				dataIndex: 'studentId'
			}, {
				dataIndex: 'studentName',
				text: '学生姓名'
			}, {
				dataIndex: 'studentNumber',
				text: '学号'
			}, {
				dataIndex: 'star',
				text: '评分',
				align: 'center'
			}, {
				dataIndex: 'word',
				text: '评语',
				width: 400
			}]
		});

		me.callParent();
	}
});
