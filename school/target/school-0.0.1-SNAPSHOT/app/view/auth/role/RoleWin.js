/*
 path: view.auth.RoleWin.js
 author: Drake
 description: 角色窗口
 */

Ext.define('School.view.auth.role.RoleWin', {
  extend: 'Ext.window.Window',
  alias: 'widget.rolewin',

  requires: [
  ],

  bodyPadding: '5 10',
  modal: true,
  title: '角色编辑窗口',

  initComponent: function () {
    var me = this;

    Ext.apply(me, {

      items: [{
        xtype: 'form',
        defaults: {
          allowBlank: false,
          xtype: 'textfield',
          labelWidth: 80,
          margin: '10 0 0 0',
          anchor: '100%',
          msgTarget:"under",
          afterLabelTextTpl: School.util.Util.required
        },

        items: [{
          name: 'id',
          hidden: true,
          allowBlank: true
        }, {
          fieldLabel: '角色名称',
          name: 'role',
          maxLength: 25,
          name: 'role',
          itemId: 'roleName',
        }, {
          xtype: 'textarea',
          name: 'description',
          rows: 10,
          fieldLabel: '角色描述',
          maxLength:64,
          itemId: 'roleDes',
          value: '待补充……'
        //}, {
        //  xtype: 'radiogroup',
        //  fieldLabel: '可用情况',
        //  columns: 2,
        //  defaults: {
        //    margin: '0 10 0 0'
        //  },
        //  items: [{
        //    boxLabel: '是',
        //    name: 'available',
        //    inputValue: 1
        //  }, {
        //    boxLabel: '否',
        //    name: 'available',
        //    inputValue: 0,
        //    checked: true
        //  }]
        }]
      }],

      buttons: [{
        text: '取消',
        itemId: 'cancel'
      }, {

        text: '保存',
        itemId: 'save'
      }, {
        text: '清空',
        itemId: 'roleWinreset'
      }]
    });

    me.callParent();
  }
});