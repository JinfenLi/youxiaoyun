Ext.define('School.view.school.position.PositionWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.positionwin',

	requires: [
		'Ext.form.field.TextArea',
		'School.util.Util'
	],

	width: 400,
	modal: true,
	autoShow: true,
	title: '职位',
	layout: 'fit',

	initComponent: function () {
		var me = this;

		Ext.apply(me, {
			items: [{
				xtype: 'form',
				bodyPadding: 10,

				defaults: {
					xtype: 'textfield',
					labelWidth: 80,
					anchor: '100%',
					msgTarget:"under"
				},

				items: [{
					fieldLabel: '职位名称',
					allowBlank: false,
					afterLabelTextTpl: School.util.Util.required,
					emptyText: '职位名称不能为空',
					name: 'name',
					maxLength:25
				}, {
					xtype: 'textarea',
					margin: '20 0',
					fieldLabel: '职位简介',
					name: 'comment',
					maxLength:64
				}, {
					hidden: true,
					name: 'id'
				}]
			}],

			buttons: [{
				text: '提交',
				itemId: 'save'
			}]

		});

		me.callParent();
	}
});