/*
 path: controller.auth.role
 author: Drake
 description:
 */

Ext.define('School.controller.auth.Role', {
  extend: 'Ext.app.Controller',
  requires: [],

  views: [
    'auth.role.RoleMgr',
    'auth.role.RoleWin',

    'auth.role.ArrangeRole',
    'auth.role.ArrangeRoleWin'
  ],

  refs: [{
    ref: 'roleGrid',
    selector: 'rolemgr'
  }, {
    ref: 'roleWin',
    selector: 'rolewin'
  }, {
    ref: 'arrangeRoleGrid',
    selector: 'arrangerole'
  }, {
    ref: 'arrangeRoleWin',
    selector: 'arrangerolewin'
  }],

  init: function (application) {
    this.control({
      'rolemgr': {
        render: this.loadRoleGrid
      },

      'rolemgr button#add': {
        click: this.showRoleWin
      },

      'rolemgr button#edit': {
        click: this.editRole
      },

      'rolemgr button#delete': {
        click: this.deleteRole
      },

      'rolewin button#undo': {
        click: this.unShowRoleWin
      },

      'rolewin button#save': {
        click: this.saveRole
      },

      'arrangerole': {
        render: this.loadArrangeRoleGrid
      },

      'arrangerole #arrangeBtn': {
        click: this.showArrangeRoleWin
      },

      'arrangerolewin #arrangedGrid': {
        render: this.loadArrangedRole
      },
      'arrangerolewin #unarrangedGrid': {
        render: this.loadUnarrangedRole
      },

      'arrangerolewin button#cancel': {
        click: this.unShowRoleWin
      },

      'arrangerolewin button#save': {
        click: this.doSaveArrangeRole
      },

      'rolewin button#roleWinreset': {
        //重写重置，不能清除id，不然无法编辑
        click: function(button) {
          this.resetRole(button);
        }
      }

    });
  },

  //因为表单隐藏了id等信息，所以需要重写reset
  resetRole: function(button) {
    button.up('window').down('#roleName').setValue('');
    button.up('window').down('#roleDes').setValue('待补充……');  
  },
  // 保存已分配的角色
  doSaveArrangeRole: function (btn) {
    // 获取已分配的grid
    var win = this.getArrangeRoleWin(),
      arrangedGrid = win.down('#arrangedGrid'),
      allRecord = arrangedGrid.getStore().getRange();

    var roleIds = [];
    Ext.each(allRecord, function (item) {
      roleIds.push(item.get('id'));
    });

    Ext.Ajax.request({
      scope: this,
      url: '/school/role/arrange_role.action',
      params: {
        userId: this.getSelectedUser()[0].get('id'),
        roles: roleIds
      },
      failure: function (res) {
        School.util.Util.showErrorMsg('操作失败');
      },

      success: function (res) {
        var result = School.util.Util.decodeJSON(res.responseText);

        if (!result.success) {
          School.util.Util.showErrorMsg('操作失败');
          return 0;
        }

        School.util.Util.showSuccessMsg('操作成功');

        this.getArrangeRoleGrid().getStore().load();

        win.destroy();
      }
    })
  },

  // 加载选中用户已分配的角色
  loadArrangedRole: function (grid) {
    var selectedUser = this.getSelectedUser()[0];
    var store = grid.getStore();

    store.load({
      url: '/school/role/role_list_by_user_id.action',
      params: {
        userId: selectedUser.get('id')
      }
    })
  },

  // 加载选中用户未分配的角色
  loadUnarrangedRole: function (grid) {
    var selectedUser = this.getSelectedUser()[0];
    var store = grid.getStore();

    store.load({
      url: '/school/role/unarranged_role_list_by_user_id.action',
      params: {
        userId: selectedUser.get('id')
      }
    })
  },

  // 显示分配角色窗口
  showArrangeRoleWin: function (btn) {
    var selectedUser = this.getSelectedUser()[0];
    if (!selectedUser) {
      School.util.Util.showErrorMsg('请先选择一名人员');
      return 0;
    }

    var arrangeRoleWin = Ext.widget('arrangerolewin', {
      autoShow: true,
      title: '正在为"' + selectedUser.get('name') + '"分配角色(拖动即可分配)'
    });
  },

  // 加载用户以及其角色的信息
  loadArrangeRoleGrid: function (grid) {
    var store = this.getArrangeRoleGrid().getStore();

    store.load();
  },

  // 获取选中的用户(教师)
  getSelectedUser: function () {
    var grid = this.getArrangeRoleGrid();

    return grid.getSelectionModel().getSelection();
  },

  // 获取选中的角色
  getSeletedRecord: function () {
    var grid = this.getRoleGrid();

    return grid.getSelectionModel().getSelection();
  },

  // 获取所有角色信息
  loadRoleGrid: function () {
    var store = this.getRoleGrid().getStore();
    store.load();
  },

  // 删除用户
  deleteRole: function (btn) {
    var selectedRole = this.getSeletedRecord()[0];

    if (!selectedRole) {
      School.util.Util.showErrorMsg('请先选择一名用户');
      return 0;
    }

    Ext.Ajax.request({
      scope: this,
      url: '/school/role/del_role.action',
      params: {
        roleId: selectedRole.get('id')
      },

      failure: function (res) {
        School.util.Util.showErrorMsg(res.responseText);
      },

      success: function (res) {
        var result = School.util.Util.decodeJSON(res.responseText);

        if (!result.success) {
          School.util.Util.showErrorMsg('操作失败');
          return 0;
        }

        School.util.Util.showSuccessMsg('已成功删除"' + selectedRole.get('role') +
          '"该角色');

        this.getRoleGrid().getStore().load();
      }
    });

  },

  // 修改用户信息
  editRole: function () {
    var selectedRole = this.getSeletedRecord()[0]; // 获取选中的角色

    if (!selectedRole) {
      School.util.Util.showErrorMsg('请先选择你想要修改的角色');
      return 0;
    }

    var roleWin = Ext.widget('rolewin');
    var form = roleWin.down('form');
    form.loadRecord(selectedRole);

    roleWin.setTitle(selectedRole.get('role') + ' 的基本信息');
    roleWin.show();

  },

  // 保存角色信息
  saveRole: function (btn) {
    var win = btn.up('window');
    var form = win.down('form');

    if (!form.getForm().isValid()) {
      School.util.Util.showErrorMsg('请填写必要的信息');
      return 0;
    }

    var formValues = form.getValues();
    var url = '';

    // 如果id存在的话，则更新角色，否则添加角色
    url = formValues.id ?
      '/school/role/edit_role.action?' :
      '/school/role/add_role.action';

    Ext.Ajax.request({
      scope: this,
      url: url,
      params: formValues,
      failure: function (res) {
        School.util.Util.showErrorMsg('无法提交或者提交失败');
      },

      success: function (res) {
        var result = School.util.Util.decodeJSON(res.responseText);

        if (!result.success) {
          School.util.Util.showErrorMsg('操作失败');
          return 0;
        }

        School.util.Util.showSuccessMsg('操作成功');
        this.getRoleGrid().getStore().load();
        this.unShowRoleWin(btn);
      }
    })

  },

  // 关闭角色window
  unShowRoleWin: function (btn) {
    var win = btn.up('window');
    win && (win.destroy());
  },

  // 显示角色window
  showRoleWin: function (btn) {
    var win = Ext.widget('rolewin');
    win.setTitle('新增角色窗口');
    win.show();
  }

});