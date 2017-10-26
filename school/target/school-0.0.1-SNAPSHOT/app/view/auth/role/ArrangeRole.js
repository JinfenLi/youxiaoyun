/*
 path: view.auth.role.ArrangeRole
 author: Drake
 description: 给用户分配角色
 */

Ext.define('School.view.auth.role.ArrangeRole', {
  //extend: 'Ext.ux.LiveSearchGridPanel',
  extend: 'Ext.grid.Panel',
  alias: 'widget.arrangerole',

  columnLines: true,
  viewConfig: {
    stripeRows: true
  },

  requires: [
    'School.store.auth.ArrangeRole'
  ],

  initComponent: function () {
    var me = this;

    me.store = Ext.create('School.store.auth.ArrangeRole');

    me.dockedItems = [{
      dock: 'top',
      xtype: 'toolbar',
      items: [{
        text: '分配角色',
        itemId: 'arrangeBtn',
        permissionId: 'arrangeRole',
        iconCls: 'key'
      }]
    }];

    me.callParent();
  },

  columns: [{
    hidden: true,
    dataIndex: 'id'
  }, {
    text: '姓名',
    dataIndex: 'name',
    align: 'center'
  }, {
    text: '当前角色',
    dataIndex: 'roles',
    flex: 1,
    renderer: function (value) {
      var roles = [];

      for (var i = 0; i < value.length; i++) {
        roles.push(value[i].role);
      }

      return roles.join(', ');
    }
  }]
});