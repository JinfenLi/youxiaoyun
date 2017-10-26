/**
* @class School.view.push.MsgList
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 查看信息推送记录的面板GridPanel
*/

Ext.define("School.view.push.MsgList", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.push.MsgList"
	],
	alias: "widget.msglist",
	forceFit: true,
	initComponent: function() {

		//数据Store的集合
		this.store = Ext.create("School.store.push.MsgList");
		this.columns = [
		
			{
				text: "消息id",
				hidden: true,
				
				dataIndex: "id"
			},
			{
				text: "消息内容",
				flex: 10,
				// hidden: true,
				dataIndex: "content"
			},
			{
				text: "收件人",
				flex: 10,
				// hidden: true,
				dataIndex: "receiversName",
				renderer: function(value) {
					return value.join(",");
				}
			},
			{
				text: "发送日期",
				flex: 4,
				dataIndex: "sendTime"
			}
			//,
			// {
			// 	xtype: "actioncolumn",
			// 	flex: 1,
			// 	header: "删除",
			// 	itemId: "delete",
			// 	align: "center",
			// 	items: [
			// 		{
			// 			iconCls: "delete",
			// 			handler: function(grid, rowIndex, colIndex) {
			// 				alert("ok");
			// 				//this.fireEvent("itemclick", grid, rowIndex, colIndex);
			// 			}
			// 		}
			// 	]
			// }
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
						// permissionId: 'queryPush',
						itemId: "query"
					}
				]
			},
			{  
		        xtype: 'pagingtoolbar',
		        // itemId: "paging",  
		        store: this.getStore(),   // same store GridPanel is using  
		        dock: 'bottom',  
		        displayInfo: true  
		    }  
		];

		this.plugins = [
			{
	            ptype: 'rowexpander',
	            rowBodyTpl : new Ext.XTemplate(
	            	'<p style="font-weight:800">信息详情</p>',
					'<p>{content}</p>',
					'<p style="font-weight:800">收件人</p>',
					'<p>{receiversName}</p>'
	           )
	        }
        ];

		this.callParent(arguments);
	}

});