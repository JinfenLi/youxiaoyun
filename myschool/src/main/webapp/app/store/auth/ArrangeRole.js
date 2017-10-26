/*
 Description: Role Store
 author: Drake
 */

Ext.define('School.store.auth.ArrangeRole', {
  extend: 'Ext.data.Store',

  requires: [
    'School.model.auth.ArrangeRole'
  ],

  model: 'School.model.auth.ArrangeRole',

  proxy: {
    type: 'ajax',
    url: '/school/role/user_role_list.action',

    reader: {
      type: 'json',
      root: 'user_roles'
    }
  },

  autoLoad: false

});