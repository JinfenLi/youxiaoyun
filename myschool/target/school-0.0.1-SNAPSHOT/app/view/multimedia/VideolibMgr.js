/**
* @class School.view.multimedia.VideolibMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 视频库管理的gridpanel
*/

Ext.define("School.view.multimedia.VideolibMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.multimedia.VideolibMgr"
	],
	alias:"widget.videolibmgr",
	itemId: "videolibMgr",
	forceFit: true,
	initComponent: function() {
		var store = Ext.create("School.store.multimedia.VideolibMgr");
		this.store = store;
		this.columns = [   
			{
				text: "视频库id",
				align: "center",
				sortable: false,
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "什么id？",
				align: "center",
				sortable: false,
				hidden: true,
				dataIndex: "tMId"
			},
			{
				text: "视频库名称",
				sortable: false,
				dataIndex: "name2"
			},
			{
				text: "创建时间",
				dataIndex: "createTime"
			},
			{
				text: "视频数量",
				sortable: false,
				dataIndex: "photoCount2"
			},
			{
				text: "描述",
				sortable: false,
				dataIndex: "description2"
			},
			{
				text: "备注",
				sortable: false,
				dataIndex: "comment"
			},
			{
				xtype: "actioncolumn",
				sortable: false,
				header: "打开视频库",
				permissionId: 'openVideoLib',
				itemId: "openVideolib",
				align: "center",
				items: [
					{
						iconCls: "key_go",
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
						fieldLabel: "我的班级",
						width: 200,
						labelWidth: 60,
						itemId: "myclasses",
						xtype: "combobox",
						triggerAction: "all",
						emptyText: "请选择...",
						editable: false,
						displayField: "name",
						valueField: "id",
						//获取数据集
						store: zy_classes,
						queryMode: "local"
					},
					{
						xtype: "button",
						iconCls: "add",
						itemId: "addLib",
						permissionId: 'addVideoLib',
						text: "创建视频库"
					},
					{
						xtype: "button",
						iconCls: "edit",
						itemId: "editLib",
						permissionId: 'editVideoLib',
						text: "修改视频库"
					},
					{
						xtype: "button",
						iconCls: "delete",
						itemId: "deleteLib",
						permissionId: 'deleteVideoLib',
						text: "删除视频库"
					}
					
				]
			},
			{  
		        xtype: 'pagingtoolbar',  
		        store: store,   
		        dock: 'bottom',  
		        displayInfo: true  
		    }  
		];

		this.callParent(arguments);
	}

});