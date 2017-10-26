/*
 Description: Module Model
 author: Drake
 */

Ext.define('School.model.auth.Module', {
  extend: 'Ext.data.Model',

  fields: [{
    name: 'id'
  }, {
    name: 'text',
    type: 'string'
  }, {
    name: 'className',
    type: 'string'
  }, {
    name: 'iconCls',
    type: 'string'
  }, {
    name: 'menu_admin',
    type: 'string'
  }, {
    name: 'leaf',
    type: 'boolean'
  }, {
    name: 'parentId',
    type: 'string'
  }, {
    name: 'imageurl',
    type: 'string'
  }, {
    name: 'check',
    type: 'boolean'
  }]

});