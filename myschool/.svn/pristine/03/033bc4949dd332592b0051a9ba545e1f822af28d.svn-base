/*
 path: controller.auth.Module
 author: Drake
 description:
 */

Ext.define('School.controller.auth.Module', {
  extend: 'Ext.app.Controller',
  requires: [
    'School.util.Util'
  ],

  views: [
    'auth.module.ArrangeModule',
    'auth.module.ArrangeModuleWin',

    'auth.module.BtnPermission',
    'auth.module.ArrangeBtnPermissionWin'
  ],

  refs: [{
    ref: 'arrangeModuleGrid',
    selector: 'arrangemodule'
  }, {
    ref: 'arrangeModuleWin',
    selector: 'arrangemodulewin'
  }, {
    ref: 'btnPermissionGrid',
    selector: 'btnpermission'
  }, {
    ref: 'arrangeBtnPermissionWin',
    selector: 'arrangebtnpermissionwin'
  }],

  init: function (application) {
    this.control({
      'arrangemodule': {
        render: this.loadArrangeModule
      },

      'arrangemodule button#arrangeBtn': {
        click: this.showArrangeModuleWin
      },

      'arrangemodulewin treepanel': {
        render: this.loadModuleTree,
        checkchange: this.checkModule
      },

      'arrangemodulewin button#save': {
        click: this.doSaveArrange
      },

      // ===========================

      'btnpermission combo#selectRole': {
        change: this.loadParentModule
      },

      'btnpermission combo#selectParentModule': {
        change: this.loadChildModule
      },

      'btnpermission combo#selectChildModule': {
        change: this.loadBtnPermission
      },

      'btnpermission button#arrangeBtn': {
        click: this.showArrangePermissionWin
      },

      'arrangebtnpermissionwin #unarrangedGrid': {
        render: this.loadUnarrangedBtnPermission
      },
      'arrangebtnpermissionwin #arrangedGrid': {
        render: this.loadarrangedBtnPermission
      },

      'arrangebtnpermissionwin button#save': {
        click: this.doSaveBtnPermission
      }

    });
  },

  // ============ 按钮权限分配 start ====================

  // 保存分配好的按钮权限
  doSaveBtnPermission: function (btn) {
    // 获取已分配的grid
    var win = btn.up('window'),
      arrangedGrid = win.down('#arrangedGrid'),
      allRecord = arrangedGrid.getStore().getRange();

    var permissions = [];
    Ext.each(allRecord, function (item) {
      permissions.push(item.get('id'));
    });

    Ext.Ajax.request({
      scope: this,
      url: '/school/permission/arrange_permission.action',
      params: {
        roleId: this.getRoleCombo().getValue(),
        moduleId: this.getChildModuleCombo().getValue(),
        permissions: permissions || []
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

        this.getBtnPermissionGrid().getStore().load({
          params: {
            roleId: this.getRoleCombo().getValue(),
            moduleId: this.getChildModuleCombo().getValue()
          }
        });

        win.destroy();
      }
    })
  },

  // 加载已分配的按钮权限
  loadarrangedBtnPermission: function (grid) {
    var store = grid.getStore();

    store.load({
      params: {
        roleId: this.getRoleCombo().getValue(),
        moduleId: this.getChildModuleCombo().getValue()
      }
    });
  },

  // 加载未分配的按钮权限
  loadUnarrangedBtnPermission: function (grid) {
    var store = grid.getStore();

    store.load({
      url: '/school/permission/unarranged_permission_list_by_role_module_id.action',
      params: {
        roleId: this.getRoleCombo().getValue(),
        moduleId: this.getChildModuleCombo().getValue()
      },
      callback: function (records) {
        if (records && !records.length) {
          School.util.Util.showWarningMsg('可分配的按钮为空或者已经分配完毕');
          //Ext.ux.Tips.msg('提示', '可分配的按钮为空，或者已经全部分配');
        }
      }
    });
  },

  // 显示/打开按钮权限分配窗口
  showArrangePermissionWin: function (btn) {
    Ext.widget('arrangebtnpermissionwin', {
      autoShow: true,
      title: '正在分配按钮权限(向右拖动即可分配)'
    })
  },

  // 根据角色加载对应的父模块
  loadParentModule: function () {
    var parentModuleCombo = this.getParentModuleCombo();

    parentModuleCombo.reset();
    parentModuleCombo.setDisabled(false);
    this.getChildModuleCombo().reset();

    parentModuleCombo.getStore().load({
      //url: '/school/module/module_list_by_role_id.action',
      url: '/school/module/father_module_list_by_role_id.action',
      params: {
        roleId: this.getRoleCombo().getValue()
      },
      callback: function (records) {
        if (records && !records.length) {
          School.util.Util.showWarningMsg('该角色的父模块为空');
          parentModuleCombo.setDisabled(true);
        }
      }
    });
  },

  // 根据父模块，加载对应的子模块
  loadChildModule: function (combo) {
    var childModuleCombo = this.getChildModuleCombo();

    childModuleCombo.reset();
    childModuleCombo.setDisabled(false);

    childModuleCombo.getStore().load({
      url: '/school/module/son_module_list_by_father_module_id.action',
      params: {
        fatherModuleId : this.getParentModuleCombo().getValue(),
        roleId: this.getRoleCombo().getValue()
      },
      callback: function (records) {
        if (records && !records.length) {
          School.util.Util.showWarningMsg('该父模块的子模块为空');
          childModuleCombo.setDisabled(true);
        }
      }
    });
  },

  // 根据角色和模块，加载对应的按钮权限
  loadBtnPermission: function (combo) {
    var grid = this.getBtnPermissionGrid();
    var store = grid.getStore();

    store.load({
      params: {
        roleId: this.getRoleCombo().getValue(),
        moduleId: this.getChildModuleCombo().getValue()
      },
      callback: function (records) {
        if (!records || records.length <= 0) {
          School.util.Util.showWarningMsg('该角色和模块对应的按钮权限为空');
        }
      }
    });
  },

  // ============ 按钮权限分配 end ====================



  // ============ 模块分配 start ========================

  // 保存给角色分配好的模块
  doSaveArrange: function () {
    // 获取已分配的grid
    var win = this.getArrangeModuleWin(),
      moduleTree = win.down('#moduleTree'),
      checkedModule = moduleTree.getChecked();

    var moduleIds = [];
    Ext.each(checkedModule, function (item) {
      moduleIds.push(item.get('id'));
    });

    Ext.Ajax.request({
      scope: this,
      url: '/school/module/arrange_module.action',
      params: {
        roleId: this.getSelectedRole()[0].get('id'),
        modules: moduleIds
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

        this.getArrangeModuleGrid().getStore().load();

        win.destroy();
      }
    })
  },

  // 获取选中的角色
  getSelectedRole: function () {
    var grid = this.getArrangeModuleGrid();

    return grid.getSelectionModel().getSelection();
  },

  // 节点勾选
  checkModule: function (node, checked) {
    // 如果是父节点的话，自动勾选他下面的叶子节点
    if (!node.raw.leaf) {
      node.expand();
      Ext.each(node.childNodes, function(childNode) {
        childNode.set('checked', checked);
      });
    } else {
      // 如果勾选的是叶子节点的话，自动勾选其父节点
      var parentNode = node.parentNode;
      if (!parentNode.get('checked'))
        parentNode.set('checked', checked);
    }

  },

  // 加载模块树
  loadModuleTree: function (treepanel) {
    var store = treepanel.getStore();
    var selectedRole = this.getSelectedRole()[0];

    store.load({
      url: '/school/module/module_tree_by_role_id.action',
      params: {
        roleId: selectedRole.get('id')
      }
    });
  },

  // 加载角色分配主面板
  loadArrangeModule: function (grid) {
    var moduleGrid = grid ? grid : this.getModuleGrid();
    var store = grid.getStore();

    store.load();
  },

  // 显示分配模块窗口
  showArrangeModuleWin: function (btn) {
    var selectedRole = this.getSelectedRole()[0];

    if (!selectedRole) {
      School.util.Util.showErrorMsg('请先选择一个角色');
      return 0;
    }

    Ext.widget('arrangemodulewin', {
      autoShow: true,
      title: '给角色分配模块窗口'
    });
  },

  // 加载角色已分配的模块
  loadArrangedModule: function (grid) {
    var selectedRole = this.getSelectedRole()[0];
    var store = grid.getStore();

    store.load({
      url: '/school/module/module_list_by_role_id.action',
      params: {
        roleId: selectedRole.get('id')
      }
    });
  },

  // 加载选中角色未分配的模块
  loadUnarrangedModule: function (grid) {
    var selectedRole = this.getSelectedRole()[0];
    var store = grid.getStore();

    store.load({
      url: '/school/module/unarranged_module_list_by_role_id.action',
      params: {
        roleId: selectedRole.get('id')
      }
    })
  },

  // ============ 模块分配 end ========================


  // 获取角色combo
  getRoleCombo: function () {
    var grid = this.getBtnPermissionGrid();
    return grid.down('combo#selectRole');

  },

  // 获取parent模块combo
  getParentModuleCombo: function () {
    var grid = this.getBtnPermissionGrid();
    return grid.down('combo#selectParentModule');

  },

  // 获取child模块combo
  getChildModuleCombo: function () {
    var grid = this.getBtnPermissionGrid();
    return grid.down('combo#selectChildModule');
  }
});