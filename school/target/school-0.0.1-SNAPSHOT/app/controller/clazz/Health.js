/*
	path: controller.class.Health
  author: Drake
	description: 健康管理
*/
Ext.define('School.controller.clazz.Health', {
	extend: 'Ext.app.Controller',

	requires: [
		'School.util.Util',
		'Ext.form.field.File'
	],

	views: [
		'clazz.health.HealthMgr',
		'clazz.health.UploadHealthWin',
		'clazz.health.StudentHealthWin'
	],

	stores: [
		'School.store.clazz.Health'
	],

	refs: [{
		ref: 'healthList',
		selector: 'healthmgr'
	}],

	init: function () {
		var me = this;

		this.control({
			'healthmgr': {
				render: this.setParamsForStore
			},

			'healthmgr combobox': {
				render: this.setDefaultClass,
				change: this.setClassIdAndName
			},

			'healthmgr toolbar #deleteHealth': {
				click: this.doDeleteStudentHealth
			},

			'healthmgr toolbar #editHealth': {
				click: this.editStudentHealth
			},

			'studenthealthwin #saveHealth': {
				click: this.doSaveStudentHealth
			},

			'healthmgr button#uploadHealth': {
				click: this.uploadHealthWin
			},

			'healthmgr button#downloadHealth': {
				click: this.doDownloadHealth
			},

			'healthmgr button#refresh': {
				click: this.loadStore
			},

			'uploadhealthwin button#save': {
				click: this.doUploadHealth
			}

		});
	},

	// 设置默认班级
	setDefaultClass: function (combo) {
		combo.setValue(zy_classId);
		this.setClassIdAndName(combo);
	},

	// 改变当前班级id和名字
	setClassIdAndName: function (combo) {
		this.classId = combo.getValue();
		this.className = combo.getRawValue(); // 下载excel时用到
		this.loadStore();
	},

	// 给store设置参数
	setParamsForStore: function () {
		var me = this;
		var healthStore = Ext.getStore('healthStore');

		healthStore.addListener('beforeload', function (store, operation) {
			store.getProxy().setExtraParam('classId', me.classId);
		});
	},

	// 删除学生健康
	doDeleteStudentHealth: function (btn) {
		var me = this,
			healthList = this.getHealthList(),
			store = healthList.getStore();

		var record = healthList.getSelectionModel().getSelection()[0];

		if (!record) {
			School.util.Util.showErrorMsg('请先选择一名学生');
			return 0;
		}

		var msg = '你确定删除"' + record.get('studentName') + '"的健康信息？若删除后，将无法恢复';
		School.util.Util.confirm(msg, function (buttonId) {
			if (buttonId != 'yes') return 0;

			Ext.Ajax.request({
				url: '/school/healthy/deleteHealthy.action',
				params: {
					healthyId: record.get('id')
				},
				failure: function (res) {
					School.util.Util.showErrorMsg(res.responseText);
				},
				success: function (response) {
					var res = School.util.Util.decodeJSON(response.responseText);

					if (!res.success) {
						School.util.Util.showErrorMsg('操作失败');
						return 0;
					}

					School.util.Util.showSuccessMsg('成功删除"' + record.get('studentName') + '"的健康信息');
					me.loadStore();

				}
			})
		})
	},

	// 保存学生健康窗口
	doSaveStudentHealth: function (btn) {
		var me = this,
			win = btn.up('window'),
			form = win.down('form');

		form.getForm().submit({
			url: '/school/healthy/updateHealthy.action',
			failure: function (form, action) {
				School.util.Util.showErrorMsg(action.result);
			},
			success: function (form, action) {
				var res = action.result;

				if (!res.success) {
					School.util.Util.showErrorMsg('操作失败');
					return 0;
				}

				School.util.Util.showSuccessMsg('成功保存"' + form.getValues().studentName + '"的健康信息');
				me.loadStore(me.getHealthList());
			}
		});
	},


	// 编辑学生健康窗口
	editStudentHealth: function (btn) {
		var healthList = this.getHealthList();
		var store = healthList.getStore();

		var record = healthList.getSelectionModel().getSelection()[0];

		if (!record) {
			School.util.Util.showErrorMsg('请先选择一名学生');
			return 0;
		}

		var win = Ext.widget('studenthealthwin');
		win.setTitle('正在编辑"' + record.get('studentName') + '"的健康信息');

		var form = win.down('form');
		form.loadRecord(record);
	},


	// 下载班级健康表
	doDownloadHealth: function (btn) {
		var inputs = '';
		var url = '/school/healthy/downClassHealthy.action';

		inputs = '<input type="text" name="classId" value="' +
			this.classId + '"/>'  +
			'<input type="text" name="className" value="' +
			this.className + '"/>';

		School.util.Util.downloadFile(url, inputs);
	},

	// 上传班级健康表
	doUploadHealth: function (btn) {
		var url = '/school/healthy/uploadHealthy.action',
			combo = Ext.ComponentQuery.query("healthmgr combobox")[0],
			classId = combo.getValue(),
			msg = '该文件类型不是excel文件，请重新选择！',
			params = {
				file: btn.up("form").down("filefield").getValue(),
				clazzId: classId
			};

		School.util.Util.uploadFile(
			btn, url, params, msg, null, ".xls", function() {
				combo.up("healthmgr").getStore().reload({
					params: classId
				});
		});
	},

	// 新建上传班级健康表window
	uploadHealthWin: function (btn) {
		Ext.widget('uploadhealthwin');
	},

	// 通过班级id来加载班级健康
	loadStore: function (self) {
		var store = this.getHealthList().getStore();

		store.load();

	}
});