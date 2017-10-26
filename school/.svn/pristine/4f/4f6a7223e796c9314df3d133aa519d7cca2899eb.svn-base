 /**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 照片展示的控制器
 * 
 */
 
Ext.define("School.controller.multimedia.PhotoMgr", {
	extend: "Ext.app.Controller",
	views: [
		"multimedia.PhotoMgr"
	],
	refs: [
		{
			ref: "photoMgr",
			selector: "photomgr"
		}
	],

	init: function(application) {

		var me = this;

		this.control({

			//页面渲染后加载相片
			"photomgr": {
				afterrender: function(component) {
					if(component.getItemId() === "viewPager") {
						me.getViewPager(component, me); //获取轮播相册的相片
					} else {
						me.getCommonPhoto(component, me); //普通相册的相片
					}
				},
				//因为tpl模板的渲染问题，所以要在layout后监听图片的图片查看器才生效
				afterlayout: function() {
					$('.images').viewer();
				}
			},


			
			//打开上传照片的选择窗口
			"photomgr button#uploadPhoto": {
				click: function(button) {
					me.createUploadWin(button, me);
				}
			},

			//开始上传照片
			// "uploadphoto button#save": {
			// 	click: function(button) {
			// 		//获取当前的相册id
			// 		var albumId = zy_albumId;

			// 		//如果是轮播相册则把轮播相册id取出来
			// 		if(button.up("window").isViewPager) {
			// 			albumId = zy_viewPagerId;
			// 		}
			// 		var url = "photo/uploadPhoto.action",
			// 			type = ".jpg .gif jpeg .png",
			// 			msg = "文件类型不对！只支持png，gif，jpg，jpeg格式的图片，请重新选择！",
			// 			store = button.up("window").dataView.getStore(),
			// 			params =  {
			//             	files: button.up("form").down("filefield").getValue(),
			//             	name: button.up("form").down("textfield").getValue(),
			//             	tMId: albumId
			//             };

			// 		School.util.Util.uploadFile(button, url, params, msg, store, type);
			// 	}
			// },

			//删除照片
			"photomgr button#deletePhoto": {
				click: function(button) {
					me.deletePhoto(button);
				}
			}, 

			'photomgr button#editPhoto': {
				click: function(button) {
					this.updatePhoto(button)
				}
			}		
		});
	
	},

	updatePhoto: function(button) {
		var selectedPhoto = $(".selectedPhoto")[0];
			//如果用户，没有选择照片，则提示并退出
			if(!selectedPhoto) {
				School.util.Util.showErrorMsg("请选择一张要编辑的照片！");
				return 0;
			}

			var grid = button.up("photomgr"),
				itemId =  button.up("photomgr").getItemId(),
				url = zyHost + "photo/updatePhotoSortAndDescription",
				title = "编辑照片",
				store = grid.store,
				params = { id: selectedPhoto.id};

				var description = selectedPhoto.getAttribute("description"),
				    sort  = selectedPhoto.getAttribute("sort");
			//params.tMId = (itemId === "viewPager" ? zy_viewPagerId : zy_albumId);这个是以前传参内容
			Ext.create('Ext.window.Window', {
				title: title,
				width: 300,
				layout: 'anchor',
				items: [{
					padding:'10 10 10 10',
					xtype:"textfield",
					fieldLabel:"排列序号",
					itemId:"sortPhoto",
					allowBlank:false,
					emptyText:"输入0-199之间的数字",
					anchor: '100%',
					labelWidth:60,
					value: sort?sort:0
				},{
					margin: '10 10 10 10',
					xtype: 'textarea',
					itemId: 'photoDescription',
					fieldLabel: '照片描述',
					height:80,
					anchor: '100%',
					labelWidth: 60,
					allowBlank:true,
					value: description?description:''
				}],
				buttons: [{
					text: '保存', 
					handler: function(button) {
						params.description = button.up('window').down('textarea#photoDescription').getValue();
					    params.sort = button.up('window').down('textfield#sortPhoto').getValue();
					    if(isNaN(params.sort)){
					    	School.util.Util.showErrorMsg("只能输入0到199之间的数字");
					    	return ;
					    }else if(params.sort <0 || params.sort >199){
					    	School.util.Util.showErrorMsg("数字只能在0到199之间");
					    	return;
					    }
					   
						Ext.Ajax.request({
							url: url,
							params: params,
							success: function(conn,response,options,eOpts) {
								var result = School.util.Util.decodeJSON(conn.responseText);
								if(!result.success){
									School.util.Util.showErrorMsg("照片编辑失败！");
								}else{
                                    School.util.Util.showSuccessMsg('照片编辑成功!');									
								}
								button.up('window').close();									
								// viewStore.setProxy(School.util.Util.setProxy(url, params , "photos", "totalCount" ));
								store.reload();
							}
						});								
					}
				}, {
					text: '取消',
					handler: function(button) {
						button.up('window').close();
					}
				}]
			}).show();
	},

	getCommonPhoto: function(component, me) {

		"use strict";

		var viewStore = component.down("pagingtoolbar").getStore(),
			params = {albumId: zy_albumId},
			url = "photo/getPassPhoto.action";

		viewStore.setProxy(School.util.Util.setProxy(url,
				params , "photos", "totalCount" ));

		//刷新store,并且还要为照片添加点击事件
		viewStore.reload({
			callback: function() {
				me.PhotoEvents();
			}
		});
	},

	getViewPager: function(component, me) {

		"use strict";

		var viewStore = component.down("pagingtoolbar").getStore(),
			params = {},
			result = {},
			url = "photo/getPassPhoto.action";

		Ext.Ajax.request({
			url: zyHost + "album/getAlbumNoPager.action",
			timeout: 60000,
			params: {
				schoolId: zy_schoolId,
				type: 0
			},
			success: function(conn,response,options,eOpts) {

				result = School.util.Util.decodeJSON(conn.responseText);
				params = {};

				if(result.success) {

					zy_viewPagerId = result.albums[0].id;
					params.albumId = zy_viewPagerId;

					viewStore.setProxy(School.util.Util.setProxy(url, params , "photos", "totalCount" ));
						viewStore.reload({
								callback: function() {
									me.PhotoEvents(); //为图片添加事件
								}
						});
				} 
			},

			failure: function(conn,response,options,eOpts) {
				School.util.Util.showErrorMsg("链接服务器失败！");
			}
		});
	},

	createUploadWin: function(button, me) {

		"use strict";

		var photomgr = button.up("photomgr"),
			win,
			isViewPager = false;

		if(photomgr.getItemId() === "viewPager") {
			isViewPager = true;
			if(photomgr.down("dataview").getStore().getCount() >= 5) {
				School.util.Util.showWarningMsg("轮播照片不得多于5张，请删除后再上传");
				return 0;
			}
		} 

		win = Ext.create("School.view.multimedia.UploadMultiPhotos", {
			isViewPager: isViewPager,
			dataView: photomgr.down("dataview")
		});

		win.mask("正在初始化上传插件...", "loading");

		// 由于会上面代码生成一个iframe框架，产生异步
		// 因此使用定时器使下面代码也是异步执行，防止异常
		setTimeout(function() {
			me.uploadMultiPhostos(win);
		}, 2000);
	},

	

	deletePhoto: function(button) {

		"use strict";

		var selectedPhoto = $(".selectedPhoto")[0];
			//如果用户，没有选择照片，则提示并退出
			if(!selectedPhoto) {
				School.util.Util.showErrorMsg("请选择一张要删除的照片！");
				return 0;
			}

			var grid = button.up("photomgr"),
				itemId =  button.up("photomgr").getItemId(),
				url = zyHost + "photo/deletephoto.action",
				title = "删除照片",
				store = grid.store,
				params = { id:  selectedPhoto.id};

			params.tmId = (itemId === "viewPager" ? zy_viewPagerId : zy_albumId);


			School.util.Util.confirm("删除照片", function(buttonId) {
				if(buttonId === "yes") {
					School.util.Update.onDeleteButtonClick(store, url, params);
				}
			});	
	},

	PhotoEvents: function() {

		"use strict";

		$("body").on('click', '.passed-photolist .passed-photo', function () {
			$(".selectedPhoto").removeClass("selectedPhoto");
			$(this).addClass("selectedPhoto");
		});
	},

	uploadMultiPhostos: function(win) {

		"use strict";

		win.unmask();

		var child = document.getElementById("fileUpload").contentWindow,
			$ = child.$,
			currentIndex = 0,
			len = 0,
			success = 0, //上传成功的图片数量
			error = 0,	//上传失败的图片数量
			datas = [];	

		function increment() {
			currentIndex++;
			if(currentIndex === len) {
				
				win.dataView.getStore().reload();
				win.close();

				School.util.Util.showSuccessMsg("图片上传完成！");
			}
		}							
					
		$("#upload").click(function() {			
			len = datas.length;
			var i = 0, setIn;
			setIn = setInterval(function() {
				if(i == len) {
					clearInterval(setIn);
					return;
				}
				datas[i].submit();
				// var url = datas[i].fileInput[0].value;
				// var resultUrl = School.util.Util.compressImg(url);
				// console.log(resultUrl);
				i++;
			},0);
		});

		//因为name这个参数是可变的 所以呢要通过这个方法来改变formdata的值				
	  $('#fileupload').bind('fileuploadsubmit', function (e, data) {
      data.formData = {
				files: "files",
				name: $("#photoname").val(),
				tMId: win.isViewPager ? zy_viewPagerId : zy_albumId
			}; 
    });

    $('#fileupload').fileupload({
        url: "../photo/uploadPhoto.action",
        dataType: 'json',
        autoUpload: false,
				type: "post",
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 5000000, // 5 MB
        disableImageResize:/Android(?!.*Chrome)|Opera/
            .test(window.navigator.userAgent),
        previewMaxWidth: 100,
        previewMaxHeight: 100,
        previewCrop: true
      //添加
	    }).on('fileuploadadd', function (e, data) {	
	        data.context = $('<div class="show"/>').appendTo('#files');
	        $.each(data.files, function (index, file) {
            var node = $('<p/>').append($('<span/>'));
            if (!index) {
							datas.push(data);
            }
            node.appendTo(data.context);
	        });
	    }).on('fileuploadprocessalways', function (e, data) {
        var index = data.index,
            file = data.files[index],
            node = $(data.context.children()[index]);
        if (file.preview) {
            node.prepend('<br>').prepend(file.preview);
        }
        if (file.error) {
            node.append('<br>').append($('<span class="text-danger"/>').text(file.error));
        }
        if (index + 1 === data.files.length) {
            data.context.find('button').text('Upload').prop('disabled', !!data.files.error);
        }
      //进度条
	    }).on('fileuploadprogressall', function (e, data) {
        var progress = parseInt(data.loaded / data.total * 90, 10);
        $('#progress .progress-bar').css(
            'width',
            progress + '%'
        );		
		//成功！
	    }).on('fileuploaddone', function (e, data) {
	    	increment();
	    	success++;
		//失败
	    }).on('fileuploadfail', function (e, data) {
	    	increment();
	    	error++;
	    });
	}


});