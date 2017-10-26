/**
* @class School.view.multimedia.PassCheck
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 审核照片的Ext.Window
*/
Ext.define("School.view.multimedia.PassCheck", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.passcheck",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "相册选择",
	items: [
		{
			xtype: "form",
			border: false,
			style: {
				padding: "10px"
			},
			layout: {
				type: "anchor"
			},
			defaults: {
				xtype: "textfield",
				anchor: "100%",
				labelWidth: 90,
				afterLabelTextTpl : '<span style="color:red">*</span>', 
				labelSeparator: ":",
				allowBlank: false,
				style: {
					marginTop: "20px"
				}
				
			},
			items: [
				{
					fieldLabel: "保存到该相册",
					margin: "0 0 20 0",
					itemId: "selectAlbum",
					name: "tMId",
					xtype: "combobox",
					triggerAction: "all",
					emptyText: "请选择...",
					editable: false,
					displayField: "name",
					valueField: "id",
					//获取数据集
					store: Ext.create("School.store.multimedia.AlbumMgr", {
						autoLoad: false,
						proxy: {
							type: "postproxy",
							url: zyHost + "album/getAlbumNoPager.action",
							reader: {
								type: 'json',
								totalProperty: "totalCount", 
								root: "albums"
							}
						}
					}),
					queryMode: "remote"
				}
				
			],
			//底部的按钮栏
			buttons : [
					{
						text : '保存',
						itemId: "save",
						width: "100%"
					}
			]
		}
	]
	
});