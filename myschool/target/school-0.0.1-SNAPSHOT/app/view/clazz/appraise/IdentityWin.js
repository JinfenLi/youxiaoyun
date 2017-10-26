/*
	path: view.clazz.appraise.GetIdentity
  author: Drake
	description: 判断评价身份: 科任老师/班主任
*/

Ext.define('School.view.clazz.appraise.IdentityWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.identitywin',

	title: '请选择你的评价身份',

	initComponent: function () {
		var me = this;

		Ext.apply(me, {
			autoShow: true,
			modal: true,
			width: 300,
			closable: false,
			bodyPadding: '10 5 5 5',

			items: [{
				xtype: 'component',
				margin: '0 0 10 0',
				html: '你想使用哪种身份进行评价？'
			}, {
				xtype: 'radiogroup',
				layout: {
					type: 'anchor'
				},
				vertical: true,
				items: [{
					boxLabel: '班主任',
					name: 'type',
					inputValue: 1,
					checked: true
				}, {
					boxLabel: '科任老师',
					name: 'type',
					inputValue: 0
				}]
			}],
			buttons: [{
				text: '确认选择',
				iconCls: 'accept',
				itemId: 'select'
			}]
		});

		me.callParent(arguments);
	}
});

