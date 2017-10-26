Ext.define('School.view.school.position.PositionMgr', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.positionmgr',

	requires: [
		'School.store.school.position.Position'
	],


	initComponent: function () {
		var me = this;

		Ext.apply(me, {
			store: Ext.create('School.store.school.position.Position'),

			columns: [{
				hidden: true,
				dataIndex: 'id'
			}, {
				text: '职位名称',
				dataIndex: 'name'
			}, {
				text: '职位简介',
				dataIndex: 'comment',
				 width: 400
			}, {
				xtype: 'actioncolumn',
				text: '删除',
				align: 'center',
				itemId: 'rmPosition',
				permissionId: 'deletePosition',
				iconCls: 'delete',
				handler: function (view, rowIndex, colIndex, item, e, record) {
					//'itemclick'事件会在controller哪里捕获
					this.fireEvent('itemclick', 'remove', record);
				}
			}, {
				xtype: 'actioncolumn',
				text: '查看和修改',
				align: 'center',
				iconCls: 'save',
				permissionId: 'editPosition',
				itemId: 'detailPosition',
				handler: function (view, rowIndex, colIndex, item, e, record) {
					this.fireEvent('itemclick', 'detail', record);
				}
			}],

			dockedItems: [{
				xtype: 'pagingtoolbar',
				store: Ext.create('School.store.school.position.Position'),
				dock: 'bottom',
				displayInfo: true
			}, {
				xtype: 'toolbar',
				dock: 'top',
				items: [{
					text: '增加新职位',
					iconCls: 'add',
					permissionId: 'addPosition',
					itemId: 'addPosition'
				}, {
					text: '给选中的职位分配教师',
					permissionId: 'arrangePosition',
					iconCls: 'key',
					itemId: 'allocatePosition'
				}, {
					text: '查看该职位下的所有教师',
					permissionId: 'getTeacherInPosition',
					itemId: 'teacherInPosition'
				}]
			}]
		});

		me.callParent();
	}
});