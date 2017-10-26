/*
 Description: Role Store
 author: Drake
 */

Ext.define('School.store.auth.Role', {
  extend: 'Ext.data.Store',

  requires: [
    'School.model.auth.Role'
  ],

  model: 'School.model.auth.Role',

  autoLoad: false,

  proxy: {
    type: 'ajax',
    url: '/school/role/role_list.action',

    reader: {
      type: 'json',
      root: 'roles'
    }
  }
});