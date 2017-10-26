/**
* @path: util.AuthUtil
* @author: Drake
* @description: 权限管理的util
*	该类具有的方法：hideBtn doBtnPermission
*/

Ext.define('School.util.AuthUtil', {
	statics: {

		// 隐藏按钮或者action column
		hideBtn: function (permissions, panel) {
			panel = panel || Ext.ComponentQuery.query('mainpanel').getActiveTab();

			// 找到含有permissionId属性的button或者action column
			var buttons = panel.query('[permissionId]');

			permissions = JSON.stringify(permissions);

			for(var  i = 0, len = buttons.length; i < len; i++) {
				if(permissions.indexOf(buttons[i].permissionId) === -1) {
					if(buttons[i].getXType() === "actioncolumn") {
						buttons[i].destroy();
						console.log("destroy" + buttons[i].getItemId());
					} else {
						buttons[i].hide();
					}					
				}
			}


			// console.log(permissions + " " + typeof permissions);

			// Ext.each(buttons, function (button) {

			

			// 	// // 如果没有权限的话
			// 	// if (!permissions || permissions.length <= 0) {
			// 	// 	button.destroy();
			// 	// }

			// 	// for(var i = 0, len = permissions.length; i < len; i++) {
			// 	// 	// 比较权限的id和按钮的permissionId
			// 	// 	if (permissions[i].id.indexOf(button.permissionId)) {
			// 	// 		button.hide();
			// 	// 	} else {
			// 	// 		button.show();
			// 	// 		return false; // 结束循环
			// 	// 	}
			// 	// }

			// 	// Ext.each(permissions, function (permission) {
			// 	// 	// 比较权限的id和按钮的permissionId
			// 	// 	if (!permission.id.indexOf(button.permissionId)) {
			// 	// 		button.hide();
			// 	// 	} else {
			// 	// 		button.show();
			// 	// 		return false; // 结束循环
			// 	// 	}
			// 	// });

			// });
		},

		// 获取按钮权限, 然后隐藏按钮
		// moduleId: 模块对应的Id，用来获取他的按钮权限
		// panel: 所需要隐藏按钮的界面
		// classId: 切换班级时需要多传一个classId参数

		doBtnPermission: function (moduleId, panel, classId) {
			// 传给后台的参数
			var params = {};
			params.moduleId = moduleId;
			classId && (params.classId = classId);

			Ext.Ajax.request({
				scope: this,
				url: '/school/permission/user_module_permission_list.action',
				params: params,
				failure: function (res) {
					School.util.Util.showErrorMsg('无法获取按钮权限');
				},
				success: function (res) {
					var result = School.util.Util.decodeJSON(res.responseText);
					var permissions = result.permissions;

					this.hideBtn(permissions, panel)
				}
			})
		}
	}
});