/**
* @class School.view.multimedia.PhotoMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 照片管理的GridPanel轮播相册部分
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
					paddingLeft: "50px",
					borderTop: "2px solid #ccc"
				},
				tpl: [
				//viewer图片查看器要求ul下li放置img才能左右实现轮子效果
				'<ul class="images">',
					'<tpl for=".">',
						'<li class="passed-photolist">',

							'<img width="160" height="160" id="{id}" src="{videoPath}" name="{name}" description="{description}" sort="{sort}" title="双击放大" class="passed-photo"/><br />',
							'<p style="word-break:break-all;height="100" text-align:left" width="160"> {description}</p>',//防止文字过长得溢出

						'</li>',
					'</tpl>',
					'</ul>',
					'<div class="x-clear"></div>'   
				]
			}
		];

		this.dockedItems = [
			{
				xtype: "toolbar",
				dock: "top",

				defaults:{
                   margin:"10 10 5 10"
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
						// margin: "0 20 0 50",
						text: "上传照片"
					},
					{
						xtype: "button",
						itemId: "deletePhoto",
						permissionId: "deletePhoto",
						iconCls: "delete",
						text: "删除照片"
					},
					{
						xtype: "button",
						itemId: "editPhoto",
						iconCls: "edit",
						text: "编辑照片",
						permissionId: 'updatePhoto'
					},
					{
						xtype: "label",
						// margin: "0 0 0 100",
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