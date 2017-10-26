/*
 path: view.auth.role.ArrangeRoleWin.js
 author: Drake
 description: 分配角色窗口
 */
Ext.define('School.view.auth.module.ArrangeModuleWin', {
  extend: 'Ext.window.Window',
  alias: 'widget.arrangemodulewin',

  requires: [
    'Ext.grid.*',
    'Ext.layout.container.HBox',
    'School.store.auth.ModuleTree'
  ],

  width: 350,
  height: 350,
  autoScroll: true,
  modal: true,

  layout: {
    type: 'fit'
    //type: 'hbox',
    //align: 'stretch',
    //padding: 5
  },


  initComponent: function() {
    this.items = [{
      xtype: 'treepanel',
      itemId: 'moduleTree',
      rootVisible: false,
      store: Ext.create('School.store.auth.ModuleTree')
    }];

    this.buttons =  [{
      text: '取消',
      itemId: 'cancel'
    }, {
      text: '保存',
      itemId: 'save'
    }];

    this.callParent();
  }
});