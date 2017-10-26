/**
* @class School.view.area.AreaMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 区域管理的主界面
*/
Ext.define("School.view.area.AreaMgr", {
	extend: "Ext.grid.Panel",
	alias:"widget.areamgr",
	itemId: "areamgr",
	requires:[
		"School.store.area.AreaMgr"
	],
	initComponent: function() {
		this.store = Ext.create("School.store.area.AreaMgr");
		this.columns = [
			{
				text: "id",
				hidden: true,
				sortable: false,
				text: "学校id",
				dataIndex: "id"
			},
			{
				text: "学校名称",
				flex: 2,
				// align: "center",
				dataIndex: "name"
			},
			{
				text: "学校地址",
					flex: 2,
				// align: "center",
				dataIndex: "address"
			},
			{
				text: "联系电话",
				flex: 2,
				// align: "center",
				dataIndex: "phone"
			},
			{
				xtype: "actioncolumn",
				flex: 1,
				itemId: "readHotNews",
				header: "查看教育热讯",
				//permissionId: 'seeEduHotNews',
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
				defaults: {
					margin: "0 20 0 0"
				},
				items: [

					{
						fieldLabel: "省",
						labelWidth: 20,
						itemId: "province",
						xtype: "combo",
						triggerAction: "all",
						width: 185,
						displayField: "name",
						emptyText: "请选择...",
						editable: false,
						valueField: "id",
						//获取数据集
						store: Ext.create("School.store.area.AreaQuery"),
						queryMode: "remote"
					},

					{
						fieldLabel: "市",
						labelWidth: 20,
						itemId: "city",
						xtype: "combo",
						triggerAction: "all",
						width: 185,
						displayField: "name",
						emptyText: "请选择...",
						editable: false,
						valueField: "id",
						//获取数据集
						store: Ext.create("School.store.area.AreaQuery"),
						queryMode: "remote"
					},

					{
						fieldLabel: "区",
						labelWidth: 20,
						itemId: "town",
						xtype: "combo",
						triggerAction: "all",
						width: 185,
						displayField: "name",
						emptyText: "请选择...",
						editable: false,
						valueField: "id",
						//获取数据集
						store: Ext.create("School.store.area.AreaQuery"),
						queryMode: "remote"
					}
				]
			},
			{  
		        xtype: 'pagingtoolbar',  
		        store: this.getStore(),   // same store GridPanel is using  
		        dock: 'bottom',  
		        displayInfo: true  
		    }  
		];

		this.callParent(arguments);
	},
	forceFit: true//强制充满表格
});