/*
	path: view.auth.role.RoleMgr
  author: Drake
	description:
*/
Ext.define('School.view.auth.role.RoleMgr', {
  extend: 'Ext.grid.Panel',
  alias: 'widget.rolemgr',

  columnLines: true,
  viewConfig: {
    stripeRows: true
  },

  requires: [
    'School.store.auth.Role'
  ],

  initComponent: function () {
    var me = this;

    me.store = Ext.create('School.store.auth.Role');

    me.dockedItems = [{
      dock: 'top',
      xtype: 'toolbar',
      items: [{
        text: '添加角色',
        itemId: 'add',
        permissionId: 'addRole',
        iconCls: 'add'
      }, {
        text: '编辑角色',
        itemId: 'edit',
        permissionId: 'editRole',
        iconCls: 'edit'
      }, {
        text: '删除角色',
        permissionId: 'deleteRole',
        iconCls: 'delete',
        itemId: 'delete'
      }]
    }];

    me.callParent();
  },

  columns: [{
    hidden: true,
    dataIndex: 'id'
  }, {
    text: '角色名称',
    dataIndex: 'role',
    align: 'center'
  //}, {
  //  text: '可用',
  //  dataIndex: 'available',
  //  align: 'center',
  //  renderer: function (value) {
  //    return value ? '是' : '否';
  //  }
  }, {
    text: '描述',
    dataIndex: 'description',
    flex: 1
  }]
});