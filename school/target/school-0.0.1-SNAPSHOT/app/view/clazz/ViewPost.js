/**
* @class School.view.exam
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 查看帖子的Ext.Window,
	通过iframe嵌入viewpost.html进行查看帖子
*/
Ext.define("School.view.clazz.ViewPost", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.viewpost",
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
	title: "帖子详情",
	items: [
		{
			xtype: "panel",
			html: '<iframe src="viewpost.html" id="postWin" frameborder=0 width=100% height=100%></iframe>'
		}
	]
});