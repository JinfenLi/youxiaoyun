/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 照片审核的控制器
 * 
 */
Ext.define("School.controller.multimedia.CheckPhoto", {
	extend: "Ext.app.Controller",
	views: [
		"multimedia.CheckPhoto"
	],
	init: function(application) {
		this.control({

			"checkphoto": {
				afterRender: function(component) {
					//选择默认班级
					component.down("#myclasses").setValue(zy_classId);
				}
			},

			 //照片的班级选择下拉框的监听事件
			"checkphoto combobox#myclasses": {
				change: function(_this, newValue, oldValue, eOpts) {
					this.changeClass(_this, newValue, this);					

				}
			},

			//通过审核的监听事件
			"checkphoto button#pass": {
				click: function(button) {
					this.createCheckWin(button, this);
				}
			},

			//确定审核
			"passcheck button#save": {
				click: function(button) {
					this.submitCheck(button, this);
				}
			},

			//删除照片
			"checkphoto button#deletePhoto": {
				click: function(button) {
					this.deletePhoto(button, this);
				}
			},

			//修改照片信息
			"checkphoto button#editPhoto": {
				click: function() {
					this.editPhoto(this);
				}
			},

			//保存编辑后的照片
			"editphoto button#save": {
				click: function(button) {
					this.savePhoto(button, this);						
				}
			},


			//顶部全选框触发的全选和取消全选事件
			"checkphoto checkbox#selectAll": {
				change: function(_this, newValue, oldValue, eOpts) {
					this.selectAll(_this, newValue, this);
				}
			}
			
		});
	
	},

	addClickEvent: function() {

		var photoNum = Ext.ComponentQuery.query("checkphoto label#photonum")[0],
			photoSelector = '.nopass-photolist .nopass-photo',
			tagSelector = ".nopass-photolist .nopass-tag",
			appendImg = '<img class="nopass-tag" src="resources/images/tick_on.png" ';

		$(photoSelector).click(function(event) {

			//判断照片是被选择，如果被选择，则取消，否则选择
			if(!$(this).next(".nopass-tag").length) {
				$(this).after(appendImg + 'id="' + this.id + '"/>');
			} else {
				$(this).next(".nopass-tag").remove();
			}

			photoNum.setText($(tagSelector).length);

		});
	},

	//切换班级
	changeClass: function(_this, newValue, me) {

		var checkphoto = _this.up("checkphoto");

		//刷新照片审核数据集
		checkphoto.store.reload({
			params: {
				clazzId: newValue
			},

			//请求成功后，为相片注册一个点击事件
			callback: function(records, options, success) {
				me.addClickEvent();				
			}
		});

		//获取当前未审核相册的id
		Ext.Ajax.request({
			url: zyHost + "album/getNoPassAlbum.action",
			method: "POST",
			timeout: 60000,
			params: {
				clazzId: newValue
			} ,
			success: function(response, options) {
				var result = School.util.Util.decodeJSON(response.responseText);
				if(result.success) {
					zy_noPassAlbumId =  result.album.id;
				} else {
					School.util.Util.showErrorMsg("请求数据失败");
				}
			},
			failure: function(response, options) {
				School.util.Util.showErrorMsg("链接服务器失败！");
			}
		});	
	},

	createCheckWin: function(button) {
		var win,
			classId = button.up("checkphoto").down("combobox#myclasses").getValue(),
			ids = [];

		//获取选择的相片的id, 并且放到ids数组里面
		$(".nopass-photolist .nopass-tag").each(function() {
			ids.push(this.id);
		});

		//如果用户没有选择任何相片，则提示并且结束保存操作
		if(ids.length === 0) {
			School.util.Util.showErrorMsg("您没有选中任何相片，无法通过审核！");
			return; 
		}
		
		//打开相册选择的窗口
		win = Ext.create("School.view.multimedia.PassCheck", {
			params: ids.join(",")
		});
		win.show();

		//加载审核窗口的相册列表
		win.down("combobox#selectAlbum").getStore().reload({
			params: {
				clazzId: classId,
				type: 1
			}
		});	
	},

	submitCheck: function(button, me) {

		if(!button.up("passcheck").down("combobox#selectAlbum").getValue()) {
			School.util.Util.showErrorMsg("请选择一个相册！");
			return;
		}

		var win = button.up("passcheck"),
			url = zyHost + "photo/updatePhoto.action",
			grid = Ext.ComponentQuery.query("checkphoto")[0],
			store = grid.store,
			reloadParams = {
				clazzId: grid.down("combobox#myclasses").getValue()
			},				
			params = {
				id: win.params,
				tmId: win.down("combobox#selectAlbum").getValue(),
				demoId: zy_noPassAlbumId
			};

			School.util.Update.onSaveButtonClick(win, url, store, params, ".", reloadParams, me.addClickEvent);
	},

	deletePhoto: function(button, me) {

		var grid = button.up("checkphoto"), 
			url = zyHost + "photo/deletephoto.action",
			title = "删除照片",
			store = grid.store,
			params = {},
			reloadParams = {
				clazzId: grid.down("combobox#myclasses").getValue()
			},
			ids = [];

		//获取选择的相片的id, 并且放到ids数组里面
		$(".nopass-photolist .nopass-tag").each(function() {
			ids.push(this.id);
		});

		//如果用户没有选择任何相片，则提示并且结束保存操作
		if(ids.length === 0) {
			School.util.Util.showErrorMsg("您没有选中任何相片，无法删除！");
			return; 
		}

		params.id = ids.join(",") ;
		params.tmId = zy_noPassAlbumId;

		School.util.Update.onDeleteButtonClick(store, url, params, reloadParams, me.addClickEvent);

	},

	editPhoto: function() {
		var ids = [],
			win = {};

		//获取选择的相片的id, 并且放到ids数组里面
		$(".nopass-photolist .nopass-tag").each(function() {
			ids.push(this.id);
		});

		//如果用户没有选择任何相片，则提示并且结束保存操作
		if(ids.length === 0) {
			School.util.Util.showErrorMsg("您没有选中任何相片，无法修改！");
			return; 
		}

		Ext.create("School.view.multimedia.EditPhoto", {
			params: ids
		}).show();
	},

	savePhoto: function(button, me) {
		var win = button.up("editphoto"),
			url = zyHost + "photo/updateName.action",
			grid = Ext.ComponentQuery.query("checkphoto")[0],
			store = grid.store,
			params = {},
			name = win.down("#photoName").getValue(),
			ids = win.params,
			reloadParams = {
				clazzId: grid.down("combobox#myclasses").getValue()
			};

		//循环请求更新操作
		for(var  i = 0; i < ids.length; i++) {
			params.id = ids[i];
			params.name = name;
			School.util.Update.onSaveButtonClick(win, url, store, params, ".", reloadParams, me.addClickEvent);
		}
	},

	selectAll: function(_this, newValue, oldValue) {

		//将已经选择的全部取消掉
		$(".nopass-photolist .nopass-tag").remove();

		//判断当前的全选值是否为真，若真，则全选
		if(newValue) {	
			$(".nopass-photolist .nopass-photo").each(function() {
				$(this).after('<img class="nopass-tag" src="resources/images/tick_on.png" ' + 'id="' + this.id + '"/>')
			});
		}
		//修改已选照片的数目
		_this.up("checkphoto").down("label#photonum").setText($(".nopass-photolist .nopass-tag").length);
	}
});
			 