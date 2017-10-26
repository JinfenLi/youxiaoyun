//UserMgr.js
Ext.define("School.view.auth.UserMgr", {
	extend: "Ext.grid.Panel",
	alias: "widget.usermgr",
	title: "用户管理",

	requires: [
		'Ext.toolbar.Toolbar',
		'Ext.form.Panel',
		'School.store.auth.User'
	],


	initComponent: function() {
		var me = this;
		me.store = Ext.create('School.store.auth.User');

		Ext.apply(me, {
			dockedItems: [{
				xtype: 'toolbar',
				dock: 'top',
				defaults: {
					iconAlign: 'left'
				},
				items: [{
					text: '添加用户',
					itemId: 'add',
					glyph: 0xf234
				}, {
					text: '修改用户',
					itemId: 'edit',
					glyph: 0xf0f0
				}, {
					text: '删除用户',
					itemId: 'remove',
					glyph: 0xf235
				}, {
					// 查询表单
					xtype: 'form',
					layout: 'hbox',
					defaults: {
						margin: '0 10'
					},
					items: [{
						xtype: 'textfield',
						labelWidth: 60,
						fieldLabel: '用户名称'
					}, {
						xtype: 'button',
						width: 100,
						text: '查询'
					}, {
						xtype: 'button',
						width: 100,
						text: '重置'
					}]
				}]
			}, {
				xtype: 'pagingtoolbar',
				store: me.store,
				dock: 'bottom',
				displayInfo: true
			}],

			columns: [{
				text: '用户名称',
				dataIndex: 'username'
			}, {
				text: '真实姓名',
				dataIndex: 'realName'
			}, {
				text: '创建时间',
				dataIndex: 'createTime'
			}, {
				text: '激活状态',
				dataIndex: 'active'
			}, {
				text: '所属角色',
				dataIndex: 'role'
			}, {
				text: '所属部门',
				dataIndex: 'department'
			}]

		});
		me.callParent(arguments);
	}

});
