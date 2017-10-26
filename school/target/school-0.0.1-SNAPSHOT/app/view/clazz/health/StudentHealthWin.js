/*
 path: view.class.health.StudentHealthWin
 author: Drake
 description: 学生健康窗口
 */

Ext.define('School.view.clazz.health.StudentHealthWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.studenthealthwin',

	autoShow: true,
	modal: true,
	width: 500,
	layout: 'fit',

	requires: [
		'Ext.form.Panel',
		'Ext.layout.container.Column'
	],

	initComponent: function () {
		var me = this;

		Ext.apply(me, {
			items: [{
				xtype: 'form',
				bodyPadding: 10,
				layout: 'column',

				items: [{
					columnWidth: .5,
					defaults: {
						margin: '10 0',
						xtype: 'textfield',
						labelWidth: 60
					},
					items: [{
						hidden: true,
						name: 'id'
					}, {
						hidden: true,
						name: 'studentId'
					}, {
						name: 'studentName',
						fieldLabel: '学生姓名',
						readOnly: true
					}, {
						name: 'height',
						fieldLabel: '身高(cm)'
					}, {
						name: 'bloodType',
						fieldLabel: '血型'
					}, {
						name: 'bloodPressure',
						fieldLabel: '血压'
					}, {
						name: 'oral',
						fieldLabel: '口腔'
					}, {
						name: 'historyMedical',
						fieldLabel: '既往病史'
					}]

				}, {
					columnWidth:.5,
					defaults: {
						margin: 10,
						xtype: 'textfield',
						labelWidth: 60
					},

					items: [{
						fieldLabel: '左视力',
						name: 'leftVision'
					}, {
						fieldLabel: '右视力',
						name: 'rightVision'
					}, {
						fieldLabel: '过敏',
						name: 'allergy'
					}, {
						fieldLabel: '体温(摄氏度)',
						name: 'bodyTem'
					}, {
						fieldLabel: '备忘',
						name: 'remarks'
					}]
				}]
			}],

			buttons: [{
				text: '提交',
				itemId: 'saveHealth'
			}]
		});

		me.callParent();
	}
});