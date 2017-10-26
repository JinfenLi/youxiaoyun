Ext.define('School.view.school.position.AllocatePosition', {
	extend: 'Ext.window.Window',
	alias: 'widget.allocateposition',

	requires: [
		'Ext.grid.*',
		'Ext.layout.container.HBox',
		'School.model.school.TeacherMgr',
		'Ext.ux.Tips'
	],

	autoShow: true,
	width: 650,
	height: 300,

	layout: {
		type: 'hbox',
		align: 'stretch',
		padding: 5
	},


	initComponent: function(){
		var group1 = this.id + 'group1',
			group2 = this.id  + 'group2',

			columns = [{
				text: '姓名',
				flex: 1,
				sortable: true,
				dataIndex: 'name'
			}, {
				text: '性别',
				width: 80,
				sortable: true,
				dataIndex: 'sex'
			}, {
				text: '教育程度',
				width: 80,
				sortable: true,
				dataIndex: 'education'
			}];

		this.items = [{
			itemId: 'doAllocateGrid',
			flex: 1,
			xtype: 'grid',
			//multiSelect: true,
			viewConfig: {
				plugins: {
					ptype: 'gridviewdragdrop',
					dragGroup: group1,
					dropGroup: group2
				}
				//listeners: {
				//	drop: function(node, data, dropRec, dropPosition) {
				//		//Ext.ux.Tips.msg();
				//	}
				//}
			},

			store: Ext.create('School.store.school.TeacherMgr', {
				pageSize: 1000
			}),

			columns: columns,
			stripeRows: true,
			title: '所有教师',
			margins: '0 5 0 0'
			//tools: [{
			//	type: 'refresh',
			//	tooltip: 'Reset both grids',
			//	scope: this,
			//	handler: this.onResetClick
			//}],
		}, {
			itemId: 'undoAllocateGrid',
			flex: 1,
			xtype: 'grid',
			viewConfig: {
				plugins: {
					ptype: 'gridviewdragdrop',
					dragGroup: group2,
					dropGroup: group1
				}
			},
			store: Ext.create('School.store.school.position.TeacherInPosition'),
			columns: columns,
			stripeRows: true,
			title: '已分配的教师'
		}];

		this.callParent();
	}
});