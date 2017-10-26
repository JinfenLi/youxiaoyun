/**
* @class School.view.multimedia.AlbumMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 相册管理的gridPanel
*/
Ext.define("School.view.multimedia.AlbumMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.multimedia.AlbumMgr"
	],
	alias:"widget.albummgr",
	forceFit: true,
	initComponent: function() {
		this.store = Ext.create("School.store.multimedia.AlbumMgr", {
			pageSize: 12
		});
		this.columns = [

			{
				text: "相册id",
				sortable: false,
				align: "center",
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "相册名称",
				sortable: false,
				dataIndex: "name"
			},
			{
				text: "更新时间",
				sortable: true,
				dataIndex: "updateTime"
			},
			
			{
				text: "相片张数",
				sortable: false,
				dataIndex: "photoCount2"
			},
			{
				xtype: "actioncolumn",
				header: "打开相册",
				itemId: "openAlbum",
				permissionId: 'openAlbum',
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
						permissionId: 'addAlbum',
						itemId: "addAlbum",
						text: "创建相册"
					},
					{
						xtype: "button",
						iconCls: "delete",
						permissionId: 'deleteAlbum',
						itemId: "deleteAlbum",
						text: "删除相册"
					}, {
						xtype: 'button',
						iconCls: 'edit',
						permissionId: '',
						itemId:'editAlbum',
						text: '编辑相册'
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