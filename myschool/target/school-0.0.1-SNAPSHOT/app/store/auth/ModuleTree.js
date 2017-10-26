/*
 Description: Module Store
 author: Drake
 */

Ext.define('School.store.auth.ModuleTree', {
  extend: 'Ext.data.TreeStore',

  requires: [
    'School.model.auth.Module'
  ],

  model: 'School.model.auth.Module',

  autoLoad: false,

  proxy: {
    type: 'ajax',
    url: '/school/module/module_list.action',

    reader: {
      type: 'json',
      root: 'items'
    }
  }
});