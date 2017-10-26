/*
	path: view.clazz.appraise.WordTpl
  author: Drake
	description: 评语模版
*/

Ext.define('School.view.clazz.appraise.WordTpl', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.wordtpl',
	height: 300,
	requires: [
		'School.store.clazz.WordTpl',
		'School.store.clazz.Subject'
	],

	title: '评语模版',
	width: 250,
	margin: '0 0 0 10',
	hideHeaders: true,

	initComponent: function() {
		var me = this;

		Ext.apply(me, {



			store: Ext.create('School.store.clazz.WordTpl'),
			selType: 'checkboxmodel',
			selModel: {
				mode: 'SINGLE',
				allowDeselect: true
			},

			columns: [{
				dataIndex: 'word',
				text: '评语',
				flex: 1
			}],

			dockedItems: [{

				xtype: 'toolbar',
				items: [{
					xtype: "radiogroup",
					itemId: "type",
					labelWidth: 60,
					allowBlank: true,
					fieldLabel: "模板类型",
					defaults: {
						margin: "0 5"
					},
					columns: 3,
					labelAlign: "left",
					items: [
						{
							boxLabel: "个人",
							inputValue: "resultForTeacher",
							name: "type",
							checked: true
						},
						{
							boxLabel: "学校",
							name: "type",
							inputValue: "resultForSchool"
						},
						{
							boxLabel: "公共",
							name: "type",
							inputValue: "result"
						}
						
					]
				}]
			}]

		});

		me.callParent(arguments);
	}
});