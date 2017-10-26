/**
* @class School.view.area.SelectSchool
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 发布教育热讯的窗口右边的选择学校的面板
*/

Ext.define("School.view.area.SelectSchool", {
	extend: "Ext.grid.Panel",
	alias:"widget.selectschool",
	selType: "checkboxmodel",
	itemId: "selectschool",
	height: 300,
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
				flex: 15,
				text: "学校名称",
				sortable: false,
				// align: "center",
				dataIndex: "name"
			}
		];

		//固定菜单栏
		this.dockedItems = [
			{
				xtype: "toolbar",
				layout: "vbox",
				defaults: {
					margin: "0 0 10 0",
					width: "98%"
				},
				flex: 1,
				dock: "top",
				items: [

					{
						fieldLabel: "省",
						labelWidth: 20,
						itemId: "province",
						xtype: "combo",
						triggerAction: "all",
						// width: 185,
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
						// width: 185,
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
						// width: 185,
						displayField: "name",
						emptyText: "请选择...",
						editable: false,
						valueField: "id",
						//获取数据集
						store: Ext.create("School.store.area.AreaQuery"),
						queryMode: "remote"
					}
				]
			}
		];


		this.callParent(arguments);
	},
	forceFit: true//强制充满表格
});