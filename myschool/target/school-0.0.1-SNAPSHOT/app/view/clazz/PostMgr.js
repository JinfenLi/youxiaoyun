/**
* @class School.view.clazz.PostMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 帖子管理的gridpanel
*/
Ext.define("School.view.clazz.PostMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.clazz.PostMgr",
	],
	alias:"widget.postmgr",
	itemId: "postmgr",
	forceFit: true, 
	initComponent: function() {
		this.store = Ext.create("School.store.clazz.PostMgr");
		this.columns = [

			{
				text: "帖子id",
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "班级id",
				hidden: true,
				dataIndex: "tMId"
			},
			{
				text: "图片地址",
				hidden: true,
				dataIndex: "urls",
				renderer: function(value) {
					return value.join(",");
				}
			},
			{
				text: "标题",
				flex: 3,
				dataIndex: "title",
				renderer: function(value) {
					return (value || "无");
				}
			},
			{
				text: "帖子内容",
				flex: 5,
				dataIndex: "context"
			},
			{
				text: "发帖人",
				flex: 3,
				dataIndex: "publisherName"
			},
			{
				text: "发帖时间",
				flex: 3,
				dataIndex: "createTime"
			},
			{
				text: "点赞数",
				flex: 1,
				dataIndex: "praisesCount"
			},
			{
				text: "评论数",
				flex: 1,
				dataIndex: "repliesCount"
			},
			{
				xtype: "actioncolumn",
				itemId: "viewpost",
				flex: 1,
				header: "详情",
				//permissionId: 'deleteStudent',
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
				flex: 1,
				itemId: "deletepost",
				header: "删除",
				permissionId: 'deleteStudent',
				align: "center",
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
		// this.dockedItems = [
		// 	{
		// 		xtype: "toolbar",
		// 		flex: 1,
		// 		dock: "top",
		// 		items: [
					
		// 			{
		// 				xtype:  "button",
		// 				text:　"查看帖子",
		// 				itemId: "viewPost",
		// 				permissionId: 'viewPost',
		// 				iconCls: "key_go"
		// 			}, 
		// 			{
		// 				xtype: "label",
		// 				margin: "0 0 0 50",
		// 				text: "当前班级："
		// 			},
		// 			{
		// 				xtype: "label",
		// 				itemId: "className",
		// 				style: {
		// 					color: "red",
		// 					fontWeight: "800"
		// 				}
		// 			}
		// 		]
		// 	},
		// 	{  
		//         xtype: 'pagingtoolbar',  
		//         store: this.getStore(),   // same store GridPanel is using  
		//         dock: 'bottom',  
		//         displayInfo: true  
		//     }  
		// ];




		this.callParent(arguments);
	}

});