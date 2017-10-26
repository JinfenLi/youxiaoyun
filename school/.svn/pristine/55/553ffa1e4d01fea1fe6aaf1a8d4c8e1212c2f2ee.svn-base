/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 视频管理的控制器
 * 
 */
Ext.define("School.controller.multimedia.VideoMgr", {
	extend: "Ext.app.Controller",
	views: [
		"multimedia.VideoMgr"
	],
	init: function(application) {
		var me = this;

		this.control({

			//打开上传视频的选择窗口
			"videomgr button#uploadVideo": {
				click: function(button) {
					Ext.create("School.view.multimedia.UploadVideo").show();
				}
			},

			//开始播放视频
			"videomgr #playvideo": {
				itemclick: function(grid, rowIndex) {					
					this.playVideo(grid, rowIndex);
				}
			},

			//开始上传视频
			"uploadvideo button#save": {
				click: function(button) {
					var url = "video/uploadVideo.action",
						type = ".wmv .flv .mp4 .avi .webm .ogv .f4v",
						msg = "文件类型不对！只支持.wmv .flv .mp4 .avi .webm .ogv格式的视频，请重新选择！",
						store = Ext.ComponentQuery.query("videomgr")[0].getStore(),
						params =  {
			            	file: button.up("form").down("filefield").getValue(),
			            	name: button.up("form").down("textfield").getValue,
			            	tMId: zy_videolibId
			            };
						var field = document.getElementById("school_video_upload");    
						var inputs = field.getElementsByTagName('input');  
						for(var i = 0; i < inputs.length; i ++){  
							if(inputs[i].type == 'file'){  
								alert("视频大小为：" + School.util.Util.getFileSize(inputs[i]));  
								break;  
							}  
						}

					School.util.Util.uploadFile(button, url, params, msg, store, type);
				}
			},

			 //删除视频
			"videomgr button#deleteVideo": {
				click: function(button, e, options) {
					var grid = button.up("gridpanel"), 
						record = grid.getSelectionModel().getSelection(); 

					if(!record.length) {
						School.util.Util.showErrorMsg("请选择一个需要删除的视频");
						return 0;
					}

					var store = grid.getStore(),
						requestUrl = zyHost + "video/deleteVideo.action",
						params = { 
							videoId: record[0].get("id"),
							tMId: zy_videolibId
						};	

					School.util.Util.confirm("删除视频", function(buttonId) {
						if(buttonId === "yes") {
							School.util.Update.onDeleteButtonClick(store, requestUrl, params);
						}
					});					
				} 
			}


		});
	
	},

	playVideo: function(grid, rowIndex) {

		var url = grid.getStore().getAt(rowIndex).get("videoPath");
		// var url = "../video/test.flv";

		console.log(url);

		Ext.create("School.view.multimedia.PlayVideoWin");

		setTimeout(function() {
			var childWin = $("#palyVideoPage")[0].contentWindow;
			childWin.startPlayer(url);
		}, 1000);


	}

});