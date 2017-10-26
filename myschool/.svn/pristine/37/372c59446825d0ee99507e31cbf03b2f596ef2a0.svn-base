/*
 path: view.clazz.health.Health
 author: Drake
 description:
 */

Ext.define('School.view.clazz.health.HealthMgr', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.healthmgr',

	requires: [
		'School.store.clazz.Health'
	],

	initComponent: function () {
		var me = this;
		var store = Ext.create('School.store.clazz.Health');

		Ext.apply(me, {
			store: store,

			dockedItems: [{
				dock: 'top',
				xtype: 'toolbar',
				items: [{
					text: '修改学生健康信息',
					itemId: 'editHealth',
					permissionId: 'editHealth',
					iconCls: 'edit'
				}, {
					text: '删除学生健康信息',
					itemId: 'deleteHealth',
					permissionId: 'deleteHealth',
					iconCls: 'delete'
				}, {
					xtype: 'tbseparator'
				}, {
					xtype: 'tbseparator'
				}, {
					text: '上传班级健康表',
					iconCls: 'upload',
					permissionId: 'uploadHealth',
					itemId: 'uploadHealth'
				}, {
					text: '下载班级健康表',
					iconCls: 'download',
					permissionId: 'downloadHealth',
					itemId: 'downloadHealth'
				}, {
					xtype: 'tbseparator'
				}, {
					xtype: 'tbseparator'
				}, {
					xtype: 'combobox',
					fieldLabel: '当前班级',
					labelWidth: 60,
					itemId: "myclasses",
					triggerAction: "all",
					emptyText: "请选择...",
					editable: false,
					displayField: "name",
					valueField: "id",
					store: zy_classes, // found in LoginCtrl
					queryMode: "local"
				}, {
					text: '刷新',
					iconCls: 'key',
					itemId: 'refresh'
				}]
			}, {
				xtype: 'pagingtoolbar',
				dock: 'bottom',
				store: store,
				displayInfo: true
			}],

			columns: [{
				hidden: true,
				dataIndex: 'id'
			}, {
				hidden: true,
				dataIndex: 'studentId'
			}, {
				text: '学生名称',
				dataIndex: 'studentName'
			}, {
				text: '身高(cm)',
				dataIndex: 'height'
			}, {
				text: '体重(kg)',
				dataIndex: 'weight'
			}, {
				text: '血型',
				dataIndex: 'bloodType'
			}, {
				text: '血压',
				dataIndex: 'bloodPressure'
			}, {
				text: '左视力',
				dataIndex: 'leftVision'
			}, {
				text: '右视力',
				dataIndex: 'rightVision'
			}, {
				text: '左听力',
				dataIndex: 'leftVision'
			}, {
				text: '右听力',
				dataIndex: 'rightVision'
			}, {
				text: '口腔',
				dataIndex: 'oral'
			}, {
				text: '过敏',
				dataIndex: 'allergy'
			}, {
				text: '体温(摄氏度)',
				dataIndex: 'bodyTem'
			}, {
				text: '备注',
				dataIndex: 'remarks'
			}]
		});

		me.callParent();
	}
});