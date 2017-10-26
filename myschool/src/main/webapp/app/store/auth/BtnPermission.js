/*
 Description: 给模块分配按钮
 author: Drake
 */

Ext.define('School.store.auth.BtnPermission', {
  extend: 'Ext.data.Store',

  requires: [
    'School.model.auth.BtnPermission'
  ],

  model: 'School.model.auth.BtnPermission',

  proxy: {
    type: 'ajax',
    url: '/school/permission/permission_list_by_role_module_id.action',

    reader: {
      type: 'json',
      root: 'permissions'
    }
  },

  autoLoad: false

});