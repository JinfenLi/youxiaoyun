/*
 path: view.clazz.appraise.AppraiseToolbar
 author: Drake
 description: 评价主面板上方的工具栏,有添加删除查看评价等功能...
 */

Ext.define('School.view.clazz.appraise.AppraiseToolbar', {
  extend: 'Ext.panel.Panel',
  xtype: 'appraisetoolbar',

  requires: [
    'School.store.school.Semester',
    'School.store.clazz.Identity'
  ],

  layout: {
    type: 'vbox'
  },


  initComponent: function () {
    this.items = [{
      xtype: 'toolbar',
      defaults: {
        labelWidth: 60
      },
      items: [{
        text: '添加评价',
        itemId: 'add',
        iconCls: 'add'
      }, {
        text: '删除评价',
        itemId: 'delete',
        iconCls: 'delete'
      }, {
        text: '修改评价',
        itemId: 'edit',
        iconCls: 'edit'
      }, {
        xtype: 'tbseparator'
      }, {
        text: '导出为Excel',
        itemId: 'download',
        iconCls: 'download'
      }, {
        text: '评语模版管理',
        itemId: 'wordTplMgr',
        hidden: true,
        iconCls: 'key'
      }, {
        text: '上传评语模版',
        itemId: 'createTpl',
        // hidden: true,
        iconCls: 'upload'
      },{
        text:"导出评语模板",
        itemId:"downloadTpl",
        iconCls:"download"
      }]

    }, {
      xtype: 'container',
      defaults: {
        labelWidth: 60,
        margin: '6 6 8 8'
      },
      layout: {
        type: 'hbox'
      },

      items: [ {
        xtype: "combo",
        width: 200,
        fieldLabel: "学期选择",
        itemId: "termCombo",
        triggerAction: "all",
        emptyText: "请选择...",
        editable: false,
        displayField: "name",
        valueField: "id",
        store: Ext.create("School.store.school.Semester", {
          autoLoad: false
        }),
        queryMode: "remote"
      }, {
        xtype: 'combo',
        width: 200,
        itemId: 'stageCombo',
        fieldLabel: '学期阶段',
        queryMode: 'local',
        displayField: 'name',
        valueField: 'name',
        forceSelect: true,
        value: '期中',
        store: Ext.create('Ext.data.Store', {
          fields: ['name'],
          data: [
            {name: '期中'},
            {name: '期末'}
          ]
        })
      }, {
        xtype: 'combo',
        width: 200,
        fieldLabel: '当前班级',
        itemId: "classCombo",
        triggerAction: "all",
        emptyText: "请选择...",
        editable: false,
        displayField: "name",
        valueField: "id",
        store: zy_classes, // found in contorller/Login.js
        queryMode: "local"
      }, {
        xtype: 'combo',
        width: 200,
        itemId: 'identityCombo',
        fieldLabel: '评价身份',
        emptyText: "请首先选择...",
        editable: false,
        displayField: 'name',
        valueField: 'type',
        queryMode: 'remote',
        store: Ext.create('School.store.clazz.Identity', {
          autoLoad: true
        })
      }]
    }];

    this.callParent(arguments);
  }
});
