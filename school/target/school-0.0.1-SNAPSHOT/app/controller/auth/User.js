/*
	path: controller.auth.User
  author: Drake
	description:
*/

Ext.define('School.controller.auth.User', {
	extend: 'Ext.app.Controller',
	requires: [
	],

	views: [
		'auth.UserMgr',
		'auth.Profile'
	],

	refs: [{
		ref: 'userGrid',
		selector: 'usermgr'
	}],

	init: function(application) {
		this.control({
			'usermgr button#add': {
				'click': this.addUser
			},

			'usermgr button#edit': {
				'click': this.editUser
			},

			'usermgr button#remove': {
				'click': this.removeUser
			},

			'profile button#undo': {
				'click': this.undoAddUser
			},

			'profile button#save': {
				'click': this.saveUser
			}
		});
	},

	// 删除用户
	removeUser: function (btn) {
		var me = this;
		var userGrid = this.getUserGrid();
		var selectedUser = userGrid.getSelectionModel().getSelection()[0];

		if (!selectedUser) {
			School.util.Util.showErrorMsg('请先选择一名用户');
			return 0;
		}

		Ext.Ajax.request({
			url: 'removeUser!action',
			failure: function (res) {
				School.util.Util.showErrorMsg(res.responseText);
			},

			success: function (res) {
				var result = School.util.Util.decodeJSON(res.responseText);

				if (!result.success) {
					School.util.Util.showErrorMsg(result.msg);
					return 0;
				}

				School.util.Util.showSuccessMsg(result.msg);
				me.getUserGrid().getStore().load();
			}
		});

	},

	// 修改用户信息
	editUser: function() {
		var userGrid = this.getUserGrid();
		var selectedUser = userGrid.getSelectionModel().getSelection()[0];

		if (!selectedUser) {
			School.util.Util.showErrorMsg('请先选择一名用户');
			return 0;
		}

		var userWin = Ext.widget('profile');
		var form = userWin.down('form');
		form.loadRecord(selectedUser);

		userWin.setTitle(selectedUser.get('username') + ' 的基本信息');
		userWin.show();

	},

	// 保存用户信息
	saveUser: function (btn) {
		var win = btn.up('window');
		var form = win.down('form');

		if (!form.getForm().isValid()) {
			School.util.Util.showErrorMsg('请填写必要的信息');
			return 0;
		}

		form.getForm().submit({
			url: 'saveUser!action',
			params: {},
			failure: function (form, action) {
				School.util.Util.showErrorMsg('无法提交或者提交失败');
			},

			success: function (form, action) {
				var result = action.result;

				if (!result.success) {
					School.util.Util.showErrorMsg(result.msg);
					return 0;
				}

				this.getUserGrid().getStore().load();
				this.undoAddUser(btn);
			}
		})


	},

	// 关闭新增用户window
	undoAddUser: function(btn) {
		var win = btn.up('window');
		win && (win.destroy());
	},

	// 新增用户window
	addUser: function(btn) {
		var win = Ext.widget('profile');
		win.setTitle('新增用户');
		win.show();
	}

});