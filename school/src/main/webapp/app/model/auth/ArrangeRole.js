/*
 Description: Role Model
 author: Drake
 */

Ext.define('School.model.auth.ArrangeRole', {
  extend: 'Ext.data.Model',

  fields: [{
    name: 'id', // 用户id
    type: 'string'
  }, {
    name: 'roles',
    type: 'auto'
  }, {
    name: 'name',
    type: 'string'
  }]

});