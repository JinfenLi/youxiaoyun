/**
* @class School.view.multimedia.CheckPhoto
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 未通过审核的照片的面板
*/
Ext.define("School.view.multimedia.CheckPhoto", {
	alias: "widget.checkphoto",
	extend: "Ext.panel.Panel",
	id: "checkphoto",
	layout: {
		type: "fit"
	},
	
	initComponent: function() {
		this.store = Ext.create("School.store.multimedia.CheckPhoto", {
			pageSize: 12
		});
		this.items = [
			{
				xtype: "dataview",
				align: "center",
				store: this.store,
				autoScroll: true,
				itemSelector: "div.nopass-photolist",
				style: {
					paddingLeft: "50px"
				},
				tpl: [
					'<tpl for=".">',
						'<div class="nopass-photolist">',
							'<img width="160" heigth="160" id="{id}" src="{videoPath}" title="双击放大" class="nopass-photo"/><br />',
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
				items: [
					// {
					// 	xtype: "tbfill",
					// },
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
					// {
					// 	xtype: "button",
					// 	itemId: "uploadPhoto",
					// 	iconCls: "upload",
					// 	text: "上传照片"
					// },
					{
						xtype: 'label',
						margin: "0 5 0 30",
						text: "你已选择"
					},
					{
						xtype: "label",
						style: {
							color: "blue"
						},
						itemId: "photonum",
						text: "0"
					},
					{
						xtype: "label",
						text: "张照片"
					},
					{
						xtype:"checkbox",
						fieldLabel: "全选",
						labelWidth:35,
						margin: "0 30 0 0",
						width: 50,
						itemId: "selectAll"
					},
					
					// {
					// 	xtype: "label",

					// 	width: 30
					// },
					{
						xtype: "button",
						itemId: "pass",
						iconCls: "accept",
						permissionId: 'acceptPhoto',
						text: "通过审核"
					},
					{
						xtype: "button",
						itemId: "deletePhoto",
						permissionId: 'deletePhoto',
						iconCls: "delete",
						text: "删除照片"
					},
					{
						xtype: "button",
						itemId: "editPhoto",
						permissionId: 'editPhoto',
						iconCls: "edit",
						text: "修改照片信息"
					}
					// {
					// 	xtype: "label",
					// 	margin: "0 0 0 100",
					// 	text: "当前相册：",
					// },
					// {
					// 	xtype: "label",
					// 	itemId: "albumName",
					// 	style: {
					// 		color: "red",
					// 		fontWeight: "800"
					// 	}
					// }
					
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