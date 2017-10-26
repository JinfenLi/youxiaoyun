/*
 path: view.auth.Profile
 author: Drake
 description: 用户信息窗口
 */

Ext.define('School.view.auth.Profile', {
	extend: 'Ext.window.Window',
	alias: 'widget.profile',

	title: '用户信息',
	height: 320,
	width: 550,
	//autoShow: true,
	modal: true,

	requires: [
		'School.util.Util',
		'Ext.form.FieldSet',
		'Ext.form.field.Hidden',
		'Ext.form.field.File'
	],

	layout: {
		align: 'stretch',
		type: 'vbox'
	},

	initComponent: function () {
		var me = this;

		Ext.apply(me, {
			items: [{
				xtype: 'form',
				bodyPadding: 5,
				layout: {
					type: 'hbox',
					align: 'stretch'
				},
				items: [{
					xtype: 'fieldset',
					flex: 2,
					title: '基本信息',
					defaults: {
						afterLabelTextTpl: School.util.Util.required,
						anchor: '100%',
						margin: '10 0',
						xtype: 'textfield',
						allowBlank: false,
						labelWidth: 80
					},
					items: [{
						xtype: 'hiddenfield',
						fieldLabel: 'Label',
						name: 'id'
					}, {
						fieldLabel: '用户名',
						name: 'username'
					}, {
						fieldLabel: '真实姓名',
						maxLength: 100,
						name: 'realName'
					}, {
						fieldLabel: '电子邮箱',
						maxLength: 100,
						name: 'email'
					}, {
						xtype: 'filefield',
						fieldLabel: '头像',
						buttonText: '选择文件',
						name: 'picture',
						allowBlank: true,
						afterLabelTextTpl: ''
					}]
				}, {
					xtype: 'fieldset',
					title: '头像',
					width: 170,
					items: [{
						xtype: 'image',
						height: 150,
						width: 150,
						src: 'resources/images/avantar.png'
					}]
				}]
			}],
			dockedItems: [{
				xtype: 'toolbar',
				flex: 1,
				dock: 'bottom',
				ui: 'footer',
				layout: {
					pack: 'end',
					type: 'hbox'
				},
				items: [{
					xtype: 'button',
					text: '取消',
					itemId: 'undo',
					//iconCls: 'cancel'
					glyph: 0xf0e2
				}, {
					xtype: 'button',
					text: '提交',
					itemId: 'save',
					//iconCls: 'save'
					glyph: 0xf00c
				}]
			}]
		});

		me.callParent(arguments);
	}
});