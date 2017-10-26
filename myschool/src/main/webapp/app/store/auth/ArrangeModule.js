/*
 Description: 给角色分配模块
 author: Drake
 */

Ext.define('School.store.auth.ArrangeModule', {
  extend: 'Ext.data.Store',

  requires: [
    'School.model.auth.ArrangeModule'
  ],

  model: 'School.model.auth.ArrangeModule',

  proxy: {
    type: 'ajax',
    url: '/school/module/role_module_list.action',

    reader: {
      type: 'json',
      root: 'modules'
    }
  },

  autoLoad: false

});