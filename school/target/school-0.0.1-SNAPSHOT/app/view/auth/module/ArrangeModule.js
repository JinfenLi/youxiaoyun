/*
 path: view.auth.role.ArrangeModule.js
 author: Drake
 description: 给角色分配模块
 */

Ext.define('School.view.auth.module.ArrangeModule', {
  //extend: 'Ext.ux.LiveSearchGridPanel',
  extend: 'Ext.grid.Panel',
  alias: 'widget.arrangemodule',

  columnLines: true,
  viewConfig: {
    stripeRows: true
  },

  requires: [
    'School.store.auth.ArrangeModule'
  ],

  initComponent: function () {
    var me = this;

    me.store = Ext.create('School.store.auth.ArrangeModule');

    me.dockedItems = [{
      dock: 'top',
      xtype: 'toolbar',
      items: [{
        text: '分配模块',
        itemId: 'arrangeBtn',
        permissionId: 'arrangeModule',
        iconCls: 'key'
      }]
    }];

    me.callParent();
  },

  columns: [{
    hidden: true,
    dataIndex: 'id'
  }, {
    text: '角色',
    dataIndex: 'name',
    align: 'center',
    width: 120
  }, {
    text: '当前模块',
    dataIndex: 'modules',
    flex: 1,
    renderer: function (value) {
      var roles = [];

      for (var i = 0; i < value.length; i++) {
        roles.push(value[i].text);
      }

      return roles.join(', ');
    }
  }]
});