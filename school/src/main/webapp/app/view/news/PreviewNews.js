/**
* @class School.view.news.PreviewNews
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新闻预览的Ext.Panel
*/
Ext.define("School.view.news.PreviewNews", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.previewnews",
	closable: true,
	resizable: false,
	draggable: false,
	modal: true,
	autoShow: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	width: 320,
	height: 580,
	title: "预览",
	//底部的按钮栏
	buttons : [
			{
				text : '发布',
				itemId: "save",
				width: "100%"
			}
	]
		
	
});