/**
* @class School.view.multimedia.PhotoMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 照片管理的GridPanel
*/
Ext.define("School.view.multimedia.PhotoMgr", {
	alias: "widget.photomgr",
	extend: "Ext.panel.Panel",
	layout: {
		type: "fit"
	},
	initComponent: function() {

		this.store = Ext.create("School.store.multimedia.PhotoMgr", {
			pageSize: 12
		});

		this.items = [
			{
				xtype: "dataview",
				align: "center",
				store: this.store,
				autoScroll: true,
				itemSelector: "div.passed-photolist",
				style: {
					paddingLeft: "50px"
				},
				tpl: [
					'<tpl for=".">',
						'<div class="passed-photolist">',
							'<img width="160" heigth="160" id="{id}" src="{videoPath}" class="passed-photo"/><br />',
							'<p class="ellipsis">{name}</p>',//防止文字过长得溢出
						'</div>',
					'</tpl>',
					'<div class="x-clear"></div>'   
				]
			}
		];

		this.dockedItems = [
			{
				xtype: "toolbar",
				dock: "top",
				style: {
					background: "#abcdef"
				},
				items: [
					// {
					// 	xtype: "tbfill",
					// },
					{
						xtype: "button",
						itemId: "uploadPhoto",
						permissionId: "uploadPhoto",
						iconCls: "upload",
						margin: "0 20 0 50",
						text: "上传照片"
					},
					{
						xtype: "button",
						itemId: "deletePhoto",
						permissionId: "deletePhoto",
						iconCls: "delete",
						text: "删除照片"
					},
					// {
					// 	xtype: "button",
					// 	itemId: "delete",
					// 	iconCls: "delete",
					// 	text: "删除照片"
					// },
					{
						xtype: "label",
						margin: "0 0 0 100",
						text: "当前相册："
					},
					{
						xtype: "label",
						itemId: "albumName",
						style: {
							color: "red",
							fontWeight: "800"
						}
					}
					
				]
			},
			{
				xtype: 'pagingtoolbar',  
			    store: this.store,
		        dock: 'bottom',  
		        displayInfo: true  
			}
		];

		this.callParent(arguments);
	}
	
});