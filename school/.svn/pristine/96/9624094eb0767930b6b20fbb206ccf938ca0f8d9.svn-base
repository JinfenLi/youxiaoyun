/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 成绩管理的控制器
 * 
 */
Ext.define("School.controller.exam.ScoreMgr", {
	extend: "Ext.app.Controller",
	views: [
		"exam.ScoreMgr"
	],
	init: function(application) {
		this.control({

			//下载成绩
			"scoremgr button#downloadScore": {
				click: function(button) {
					this.downloadScore(button);
				}
			},

			//打开成绩上传窗口
			"scoremgr button#uploadScore": {
				click: function() {
					Ext.create("School.view.exam.UploadScore").show();
				}
			},

			//撤销上传
			"uploadscore button#cancel": {
				click: function(button) {
					button.up("uploadscore").destroy();
				}
			},	

			//上传成绩
			"uploadscore button#save": {
				click: function(button) {
					this.uploadScore(button);
				}
			},

			//提交成绩
			'scoremgr button#submitExam': {
				click: function(button) {
					this.submitExam(button);
				}
			},

			//删除考试成绩
			"scoremgr button#delete": {
				click: function(button) {
					this.deleteScore(button);					
				}
			}
		
		});
	
	},
	
	//提交考试
	submitExam: function(button) {
		var length = button.up('grid').getStore().data.length
		console.log(length);
		if(length <= 0) {
			School.util.Util.showErrorMsg('尚未上传成绩，不能提交！');
			return;
		}
		Ext.Msg.confirm('确认！', '提交后将不能再修改成绩！', function(res) {
			if(res == 'yes') {
				Ext.Ajax.request({
				    url: zyHost + 'exam/updateExamStatus.action',
				    params: {
				        commitStatus: 0,
				        examId: zy_examRec.raw.id, 
				    },
				    success: function(response){
				      School.util.Util.showSuccessMsg('提交成功！');
							button.up('toolbar').down('button#uploadScore').hide();
							button.up('toolbar').down('button#delete').hide();
							button.hide();
				    }
				});
			} else {
				return;
			}
		});
	},

	downloadScore: function(button) {

		"use strict";

		var inputs = '';

		inputs += '<input type="text" name="clazzId" value="' + zy_examRec.raw.clazzId + '"/>';
		inputs += '<input type="text" name="clazzName" value="' + zy_examRec.raw.clazzName + '"/>';
		inputs += '<input type="text" name="examId" value="' + zy_examRec.raw.id + '"/>';
		inputs += '<input type="text" name="examName" value="' + zy_examRec.raw.name + '"/>';
		
		School.util.Util.downloadFile("score/downClazzScore.action", inputs);
	},

	uploadScore: function(button) {

		"use strict";

		var url = "score/uploadScore.action",
			msg = "该文件类型不是excel文件，请重新选择！",
			store = Ext.ComponentQuery.query("scoremgr")[0].getStore(),
			params =  {
            	file: button.up("form").down("filefield").getValue(),
            	examId: zy_examId
            };

		School.util.Util.uploadFile(button, url, params, msg, store);
	},

	deleteScore: function(button) {

		"use strict";

		var grid = button.up("scoremgr"), 
			store = grid.getStore(), 
			url = "score/deleteByExamId.action", 
			params = {
				examId: zy_examId
			};	

		//提示用户是否确定删除
		School.util.Util.confirm("删除成绩", function(buttonId) {

			if(buttonId === "yes") { 
				School.util.Update.onDeleteButtonClick(store, url, params);
			}			
			
		});
	}
});