/**
* @class School.view.multimedia.EditPhoto
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 编辑照片信息的Ext.Window
*/

Ext.define("School.view.multimedia.EditPhoto", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.editphoto",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 350,
	title: "修改照片信息",
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
				labelWidth: 70,
				labelSeparator: ":"
			},
			items: [
				{
					itemId: "photoName",
					margin: "30 0 30 0",
					fieldLabel: "照片名称"
				}
			],
			//底部的按钮栏
			buttons : [
					{
						text : '保存',
						width: "100%",
						itemId: "save"
					}
			]
		}
	]
	
});