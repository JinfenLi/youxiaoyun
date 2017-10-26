/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 登陆成功后，菜单渲染，以及菜单树的控制器
 * 
 */
Ext.define('School.controller.Menu', {
	extend: 'Ext.app.Controller',

	requires: [
		'School.view.MainPanel',
		'School.view.auth.UserMgr',
		'School.util.Util',
		'School.util.AuthUtil'
	],

	models: [
		"menu.Root",
		"menu.Item"
	],
	stores: [
		"Menu"
	],
	views: [
		"menu.Accordion",
		"menu.Item",
		"Header",
		"User"
	],
	refs: [{
		ref: "mainPanel",
		selector: "mainpanel"
	}],
	
	init: function (application) {
		this.control({
			"mainmenu": {
				render: this.OnPanelRender
			},
			"mainmenuitem": {
				select: this.onTreepanelSelect,
				itemclick: this.onTreepanelItemClick
			},
			//取消
			"#cancel": {
				click: function(button) {
					button.up("window").close();
				}
			},
			//清空
			"#reset": {
				click: function(button) {
					button.up("window").down("form").getForm().reset();
				}
			},
			//退出操作
			"#logout": {
				click: function(menuitem) {
					this.onButtonClickLogout();
				}
			}
		});
	},

	OnPanelRender: function (abstractcomponent, options) {
		
		var menuPanel = Ext.ComponentQuery.query("mainmenu")[0],
			me = this,
			header = Ext.ComponentQuery.query("appheader")[0]; 

		//获取到了Menu这个Store类之后，动态加载数据
		this.getMenuStore().load({
			
			callback: function (records, op, success) {

				//添加学校的背景图片
				header.add({
					xtype: "label",
					style: {
						//background: "url(resources/images/logo.png)"
						background: "url(" + zy_logoUrl + ") no-repeat center"
					},
					width: 100,
					height: 100
				});

				//添加当前学校名
				header.add({
					xtype: "label",
					html: '<div class="title"><h1>' + zy_schoolName  + ' </h1>优校云信息管理平台</div>',
					width: 250
				});

				//对非叶子节点集合进行遍历
				Ext.each(records, function (root) {
					//注意每一条记录都是一个Model的实例，因此具有Model的所有方法
					var menu = Ext.create("School.view.menu.Item", {
						title: root.get("text"),//通过get方法获取记录的某个字段值
						iconCls: root.get("iconCls")
					});

					//创建一个图片，用于添加到header里面
					var image = Ext.create("Ext.Img", {
						cls: 'nav',
						height: 60,
						width: 60,
						margin: "0 10",
						src: root.raw.imgUrl,
						title: root.get("text"),
						margin: "0 10"
					});

					//在顶部菜单添加相应的图标
					header.add(image);


					//因为root的items属性值是一个数组,会自动生成一个items方法
					//因此可以通过items方法来获取里面的值
					//这个是不能用get
					Ext.each(root.items(), function (items) { //遍历叶子节点集合
						Ext.each(items.data.items, function (item) {
							//通过getRootNode函数获取树的跟节点
							//并且用appendChild把相应的叶子追加到后面
							menu.getRootNode().appendChild({
								text: item.get("text"),
								leaf: true,
								iconCls: item.get("iconCls"),
								id: item.get("id"),
								className: item.get("className")
							});
						});
					});

					//把树添加到手风琴布局的面板上面
					menuPanel.add(menu);
				});
				

				var userMenu = Ext.create("Ext.menu.Menu", {
					allowOtherMenus : true,
					width: 100,
					margin: "0 50 0 0",
					defaults: {

					},
					items : [
						
						{
							text: '个人信息',
							iconCls: "edit", 
							handler: me.onButtonClickEdit
							//function() {
								//School.util.Util.showWarningMsg("该功能尚未实现，请您耐心等待系统升级");
							// }
						},
						{
							text: '退出系统',
							itemId: "logout",
							iconCls: "delete"
							//handler: me.onButtonClickLogout
						}
					]
				});
				

				function onMenuItem(item) {//菜单项的回调方法
					alert(item.text);//取得菜单项的text属性
				}	


				//添加退出按钮
				header.add([
					{
						xtype: "tbfill"
					}, {
						minWidth: 120,
						itemId: "userName",
						style: {
							background: "rgb(0, 79, 106)",
							border: "none"
						},
						// text: "华景小学管理员",
						text: '<span style="color: white">' + globalUserInfo.user_name + '</span>', 
						//iconCls: "menu_profile",
						menu : userMenu
					}					
				]);

				//当点击时，则使相应的子菜单项展开
				$(".x-img-default").on("click", function () {
					var me = this;
					$(".x-img-default").each(function (i) {
						if (me == this) {
							menuPanel.items.get(i).expand();
						}
					});
				});
			}
		});

	},

	// 点击左侧菜单数的节点，在右边生成相关界面
	onTreepanelSelect: function (selModel, record, index, options) {
		//mainPanel, title, xtype, iconCls
		var mainPanel = this.getMainPanel(),
			title = record.get("text"),
			xtype = record.raw.className,
			iconCls = record.get("iconCls"),
			itemId = record.get("id");

		var tab = School.util.AddTab.addTab(mainPanel, title, xtype, iconCls, itemId);

		School.util.AuthUtil.doBtnPermission(itemId, tab);
	},

	onTreepanelItemClick: function (view, record, item, index, event, options) {
		this.onTreepanelSelect(view, record, index, options);
	},

	//退出
	onButtonClickLogout: function () {
		Ext.Ajax.request({
			url: zyHost + "user/logout.action",
			success: function (conn, response, options, eOpts) {
				var result = School.util.Util.decodeJSON(conn.responseText, true);
				if (result.success) {
					//button.up("mainviewport").destroy();
					//重新加载页面,回到登陆状态
					window.location.reload();
				} else {
					School.util.Util.showErrorMsg("退出失败！");
				}
			},
			failure: function (conn, response, options, eOpts) {
				School.util.Util.showErrorMsg("连接服务器失败！");
			}
		});
	},

	onButtonClickEdit: function() {
		Ext.create("School.view.User").show();
	}

});