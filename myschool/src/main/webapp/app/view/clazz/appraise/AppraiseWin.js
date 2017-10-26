/*
 path: view.clazz.appraise.AppraiseWin
 author: Drake
 description: 评价窗口
 */

Ext.define('School.view.clazz.appraise.AppraiseWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.appraisewin',

	requires: [
		'School.view.clazz.appraise.WordTpl'
	],

	initComponent: function () {
		var me = this;

		Ext.apply(me, {
			layout: {
				type: 'hbox',
				align: 'stretch'
			},
			bodyPadding: '5 10',
			modal: true,

			items: [{
				xtype: 'form',
				title: '评价内容',
				defaults: {
					xtype: 'combo',
					labelWidth: 60,
					margin: '10 0 0 0',
					anchor: '100%'
				},

				items: [{
					name: 'star',
					fieldLabel: '评分',
					displayField: 'name',
					valueField: 'name',
					value: 5,
					store: Ext.create('Ext.data.Store', {
						fields: ['name'],
						data: [
							{name: 1}, {name: 2},
							{name: 3}, {name: 4}, {name: 5}
						]
					})
				}, {
					xtype: 'textarea',
					name: 'word',
					rows: 12,
					fieldLabel: '评语'
				}]
			}, {
				xtype: 'wordtpl'
			}],

			buttons: [{
				text: '清空',
				itemId: 'reset'
			}, {
				text: '保存',
				itemId: 'save'
			}]
		});

		me.callParent();
	}
});