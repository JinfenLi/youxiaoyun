/**
* @class School.view.news.NewsMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新闻管理的gridPanel
*/

Ext.define("School.view.news.NewsMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.news.NewsMgr"
	],
	alias: "widget.newsmgr",
	//itemId: "newsmgr",
	forceFit: true,
	initComponent: function() {
		var me = this;
		me.store = Ext.create("School.store.news.NewsMgr");
		me.gradeStore = Ext.create("School.store.school.GradeMgr", {
			autoLoad: true
		});
		me.columns = [
	
			{
				text: "新闻id",
				hidden: true,
				dataIndex: "id"
			},
			{
				tex: "新闻地址",
				hidden: true,
				dataIndex: "url"
			},
			{
				text: "标题图片",
				hidden: true,
				dataIndex: "titlephoto"
			},
			{
				text: "新闻标题",
				dataIndex: "title"
			},
			{
				text: "发表时间",
				dataIndex: "createTime"
			},
			{
				text: "新闻类型",
				dataIndex: "type",
				renderer: function(value) {
					switch(value) {
						case "news":
							return "校园新闻";
							break;
						case "notice":
							return "校园公告";
							break;
						case "reward":
							return "荣誉之窗";
							break;
						case "teaching": 
							return "教育热讯";
							break;
						default: 
							return "校园新闻";
							break;

					}
				}
			},
			{
				xtype: "actioncolumn",
				width: 50,
				header: "查看新闻",
				// permissionId: 'readNews',
				itemId: "details",
				align: "center",
				items: [
					{
						iconCls: "key_go",
						handler: function(grid, rowIndex, colIndex) {
							this.fireEvent("itemclick", grid, rowIndex, colIndex);
						}
					}
				]
			},
			{
				xtype: "actioncolumn",
				width: 50,
				header: "复制新闻",
				permissionId: 'copyNews',
				itemId: "copy",
				// hidden: true,
				align: "center",
				items: [
					{
						iconCls: "copy",
						handler: function(view, rowIndex, colIndex) {
							var itemId = me.getItemId();
							if(itemId !== "hotnewslist" && itemId !== "classnews") {
								School.util.Util.showWarningMsg("该新闻不可以被复制");
								return 0;
							}

							this.fireEvent("itemclick", view, rowIndex, colIndex);
						
						}
					}
				]
			},
			{
				xtype: "actioncolumn",
				width: 50,
				header: "删除新闻",
				permissionId: 'deleteNews',
				align: "center",
				itemId: "delete",
				items: [
					{
						iconCls: "delete",
						handler: function(grid, rowIndex, colIndex) {
							this.fireEvent("itemclick", grid, rowIndex, colIndex);
						}
					}
				]
			}
		];

		//固定菜单栏
		this.dockedItems = [
			{
				xtype: "toolbar",
				flex: 1,
				dock: "top",
				items: [
					{
						fieldLabel: "新闻类别",
						labelWidth:60,
						itemId: "newscombo",
						xtype: "combo",
						triggerAction: "all",
						width: 250,
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						store: [["news","校园新闻"], ["notice","校园公告"], ["reward","荣誉之窗"], ["teaching","教育热讯"]],
						queryMode: "local"
					},
					
					{
						fieldLabel: "年级选择",
						width: 200,
						labelWidth: 60,
						itemId: "mygrades",
						xtype: "combobox",
						triggerAction: "all",
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						//获取数据集
						store: this.gradeStore,
						queryMode: "local"
					},
					{
						xtype: "button",
						text: "发表新闻",
						itemId: "publish",
						permissionId: 'publishNews',
						iconCls: "menu_users"
					}
				]
			},
			{  
		        xtype: 'pagingtoolbar',  
		        store: this.getStore(),   
		        dock: 'bottom',  
		        displayInfo: true  
		    }  
		];

		this.callParent(arguments);
	}

});