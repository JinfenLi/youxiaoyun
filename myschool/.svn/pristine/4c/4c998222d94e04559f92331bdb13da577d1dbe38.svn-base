/*
 path: view.push.PushPanel
 author: Drake
 description: 消息推送
 */

Ext.define('School.view.push.PushPanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.pushpanel',

	requires: [
		'School.view.push.ContactTree'
	],

	layout: 'border',

	initComponent: function () {
		var me = this;

		Ext.apply(me, {
			items: [{
				region: 'east',
				xtype: 'contacttree',
				width: 300
			}, {
				region: 'center',
				xtype: 'form',
				layout: 'border',
				bodyPadding: 10,
				bodyStyle: {
					background: '#fff'
				},

				defaults: {
					labelWidth: 70,
					anchor: '100%',
					margin: '10 0'
				},
				items: [{
					region: 'north',
					height: 30,
					xtype: 'textfield',
					itemId: 'receiverName',
					name: 'name',
					fieldLabel: '收信人',
					readOnly: true,
					emptyText: '请在右边侧栏选择收信人'
				}, {
					region: 'center',
					xtype: 'textarea',
					itemId: 'content',
					name: 'content',
					rows: 10,
					grow: true,
					fieldLabel: '正文',
					allowBlank: false,
					emptyText: '信息的主要内容...'
				}],

				buttons: [{
					text: '清空正文',
					itemId: 'reset'
				}, {
					text: '发送',
					itemId: 'doSend',
					permissionId: 'doPush',
					formBind: true
				}]
			}]
		});

		me.callParent();
	}
});