/**
* @class School.view.area.VisitorVolumn
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 访问情况
*/
Ext.define("School.view.area.VisitorVolumn", {
	extend: "Ext.grid.Panel",
	alias:"widget.visitorvolumn",
	itemId: "visitorvolumn",
	requires:[
		"School.store.area.VisitorVolumn"
	],
	initComponent: function() {
		this.store = Ext.create("School.store.area.VisitorVolumn", {
			autoLoad: true
		});
		this.columns = [			
			{
				text: "模块名称",
				dataIndex: "name"
			},
			{
				text: "访问数量",
				dataIndex: "count"
			}
		];

		//固定菜单栏
		this.dockedItems = [
			{
				xtype: "toolbar",
				dock: "top",
				defaults: {
					labelWidth: 20
				},
				items: [
				
					{
						xtype: "label",
						text: "时间范围:",
						margin: "0 10"
					},
					{
						xtype: "datefield",
						format: "Y-m-d",
						emptyText: "日期格式：xxxx-xx-xx",
						fieldLabel: "从",
						itemId: "beginTime"
					},
					{
						xtype: "datefield",
						format: "Y-m-d",
						emptyText: "日期格式：xxxx-xx-xx",
						fieldLabel: "到",
						itemId: "endTime"
					},
					{
						xtype: "button",
						iconCls: "key_go",
						text: "查询",
						itemId: "query"
					}
				]
			}
		];


		this.callParent(arguments);
	},
	forceFit: true//强制充满表格
});