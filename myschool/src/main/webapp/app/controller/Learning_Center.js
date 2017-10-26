/**
 * 
 * Time: 2015/09/07
 * Author: Jason Liao
 * Contact: lmovingon2014@gmail.com
 * Path: School.controller.Learning_Center
 * Description: 学习园地控制
 * 
 */

//PhotoMgr.js
Ext.define("School.controller.Learning_Center", {
	extend: "Ext.app.Controller",
	
	views: [
		"multimedia.LearningCenter"
	],
	
//	stores: [
//	    "multimedia.LearningCenter"
//	],
	
	refs: [{
		ref: 'learningcenter',
		selector: 'learningcenter'
	}, {
		ref: 'uploadresources',
		selector: 'uploadresources'
	}],
	
	init: function(application) {
		var me = this;
		this.control({
			
			// 响应查看 下载
			"learningcenter": {
				cellclick: function (view, td, cellIndex, record) {
					var resourcePath = record.raw.resourcepath;
					var isLink = record.raw.isLink;
					if (isLink) {
						window.open(resourcePath, '_blank');
					} else {
						location.href = resourcePath;
					}
				},
				afterrender: function (view) {
					view.dockedItems.items[2].down('#refresh').hide();
				}
			},
			
			
			// 添加资源
			"learningcenter button#addRes": {
			 	click: function(button) {
			 		Ext.create('School.view.multimedia.UploadResources', {
			 			title: '新增资源'
			 		});
			 	}
			 },

			/* 
			// 修改资源
			"learningcenter button#editRes": {
			 	click: function(button) {
			 		alert('test');
			 	}
			 },
			*/

			// 删除资源
			"learningcenter button#deleteRes": {
			 	click: function(button) {
			 		var gridpanel = me.getLearningcenter();
			 		var sm = gridpanel.getSelectionModel().getSelection();
			 		if (sm.length != 0) {
			 			var dataModel = sm[0];
				 		Ext.Ajax.request({
				 			url: 'grade_resources/deleteResource',
				 			params: {
				 				id: dataModel.raw.id
				 			},
				 			success: function (result) {
				 				var responseObject = Ext.JSON.decode(result.responseText);
				 				if (responseObject.success) {
							 		Ext.Msg.alert('温馨提示', '删除成功');
							 		gridpanel.getStore().reload();
				 				} else {
							 		Ext.Msg.alert('温馨提示', '删除失败，请稍后再试');
				 				}
				 			}
				 		});
				 	} else {
				 		Ext.Msg.alert('温馨提示', '选择你要删除的资源')
				 	}
			 	}
			 },
			 
			 /*
			 // 选择我的班级
			"learningcenter combobox#mygrade": {
				render: function (combo) {
					var store = combo.getStore();
					store.load(function () {
						if (store.data.keys[0]) {
							combo.setValue(store.data.keys[0]);
						}
					});
				},
				select: function(combo) {
			 		var gradeId  = combo.getValue();
			 		var gridpanel = combo.up('#learningcenter');
			 		var typeCombo = gridpanel.down('#resourcesType');
			 		var resourcesType  = typeCombo.getValue();
			 		gridpanel.getStore().load({
			 			params: {
			 				schoolId: zy_schoolId,
			 				gradeId: gradeId,
			 				typeId: resourcesType
			 			}
			 		});
			 	}
			},
			*/
			
			// 选择资源类型
			 "learningcenter combobox#resourcesType": {
				 render: function (combo) {
					var store = combo.getStore();
					store.load(function () {
						combo.setValue("");
					});
				},
				change: function(combo) {
			 		var resourcesType  = combo.getValue();
			 		var gridpanel = combo.up('#learningcenter');
			 		gridpanel.getStore().load({
			 			params: {
			 				schoolId: zy_schoolId,
			 				typeId: resourcesType
			 			}
			 		});
			 	}
			 },
			 
			// 点击上传按钮
			"uploadresources button#upload": {
				click: function (button) {
					var window = button.up('#uploadresources');
					var form = window.down('form');
					var gridStore = me.getLearningcenter().getStore();
					if (form.getForm().isValid()) {
						form.getForm().submit({
							url: 'grade_resources/uploadResource.action',
							params: {
								schoolId: zy_schoolId
							},
							success: function (form, action) {
								if (action.result.success) {
									Ext.Msg.alert('温馨提示', '上传成功');
									window.close();
									gridStore.reload();
								} else {
									Ext.Msg.alert('温馨提示', '上传失败,请稍后再试');
								}
							}
						});
					} else {
						Ext.Msg.alert('温馨提示', '请按要求完整表单');
					}
				}
			},
			
			// 切换站内资源和站外链接
			"uploadresources radio#link": {
				focus: function (radio) {
					var window = me.getUploadresources();
					var link = window.down('[name="resourcepath"]');
					var file = window.down('[name="file"]');
					link.setVisible(true);
					file.setVisible(false);
				}
			},
			
			// 切换站内资源和站外链接
			"uploadresources radio#file": {
				focus: function (radio) {
					var window = me.getUploadresources();
					var link = window.down('[name="resourcepath"]');
					var file = window.down('[name="file"]');
					link.setVisible(false);
					file.setVisible(true);
				}
			}
			 
		});
	}
});