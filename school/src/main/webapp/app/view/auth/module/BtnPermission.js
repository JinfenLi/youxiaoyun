/*
 path: view.auth.role.BtnPermission.js
 author: Drake
 description: 给模块分配按钮权限
 */

Ext.define('School.view.auth.module.BtnPermission', {
  extend: 'Ext.grid.Panel',
  alias: 'widget.btnpermission',

  columnLines: true,
  viewConfig: {
    stripeRows: true
  },

  requires: [
    'School.store.auth.BtnPermission',
    'School.store.auth.Role',
    'School.store.auth.Module'
  ],

  initComponent: function () {
    var me = this;

    me.store = Ext.create('School.store.auth.BtnPermission');

    me.dockedItems = [{
      dock: 'top',
      xtype: 'toolbar',
      defaults: {
        xtype: 'combo',
        labelWidth: 70,
        valueField: 'id',
        emptyText: '请选择...',
        editable: false,
        triggerAAction: 'all'
      },

      items: [{
        fieldLabel: '选择角色',
        itemId: 'selectRole',
        store: Ext.create('School.store.auth.Role'),
        displayField: 'role'
      }, {
        disabled: true,
        fieldLabel: '选择父模块',
        itemId: 'selectParentModule',
        store: Ext.create('School.store.auth.Module'),
        displayField: 'text',
        queryMode: 'local'
      }, {
        disabled: true,
        fieldLabel: '选择子模块',
        itemId: 'selectChildModule',
        store: Ext.create('School.store.auth.Module'),
        displayField: 'text',
        queryMode: 'local'
      }, {
        xtype: 'tbseparator'
      },{
        xtype: 'button',
        text: '分配按钮权限',
        permissionId: 'arrangeBtnPermission',
        itemId: 'arrangeBtn',
        iconCls: 'key'
      }]
    }];

    me.callParent();
  },

  columns: [{
    hidden: true,
    dataIndex: 'id'
  }, {
    text: '对应的按钮权限',
    dataIndex: 'permission',
    width: 160,
    align: 'center'
  }, {
    text: '可用',
    dataIndex: 'available',
    align: 'center'
  }, {
    text: '描述',
    dataIndex: 'description',
    flex: 1
  }]
});