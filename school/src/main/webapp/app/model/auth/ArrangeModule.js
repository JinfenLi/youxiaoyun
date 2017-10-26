/*
 Description: 左侧栏模块分配
 author: Drake
 */

Ext.define('School.model.auth.ArrangeModule', {
  extend: 'Ext.data.Model',

  fields: [{
    name: 'id', // 角色id
    type: 'string'
  }, {
    name: 'name', // 角色名称
    type: 'string'
  }, {
    name: 'modules',
    type: 'auto'
  }]

});