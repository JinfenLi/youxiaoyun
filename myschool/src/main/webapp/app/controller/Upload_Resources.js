/**
 * 
 * Time: 2015/09/07
 * Author: Jason Liao
 * Contact: lmovingon2014@gmail.com
 * Path: School.controller.Upload_Resources
 * Description: 学习园地控制
 * 
 */

Ext.define("School.controller.Upload_Resources", {
	extend: "Ext.app.Controller",
	
	views: [
		"multimedia.UploadResources"
	],
	
//	stores: [
//	    "multimedia.LearningCenter"
//	],
	
	refs: [{
		ref: 'uploadresources',
		selector: 'uploadresources'
	}],
	
	init: function(application) {
		var me = this;
		this.control({
			
			// 点击上传按钮
			"uploadresources button#upload": {
				click: function (button) {
					var window = button.up('#uploadresources');
					var form = window.down('form');
					if (form.getForm().isValid()) {
						form.getForm().submit({
							url: 'grade_resources/uploadResource.action',
							success: function (form, action) {
								if (action.result.success) {
									Ext.Msg.alert('温馨提示', '上传成功');
									window.close();
									
								} else {
									Ext.Msg.alert('温馨提示', '上传失败,请稍后再试');
								}
							}
						});
					} else {
						Ext.Msg.alert('温馨提示', '请按要求完整表单');
					}
				}
			}
			
		});
	},
});