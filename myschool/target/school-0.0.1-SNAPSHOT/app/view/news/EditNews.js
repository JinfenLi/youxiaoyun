/**
* @class School.view.news.EidtNews
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 修改新闻的Ext.Window
* 	注意：目前该功能还没有实现
*/
Ext.define("School.view.news.EditNews", {
	extend: "Ext.panel.Panel",
	alias: "widget.editnews",
	html: '<iframe src="ueditor.html" frameborder=0 width=100% height=100% id="editNews"></iframe>',
	

	//预览新闻的按钮
	buttons: [
		{
			text : '预览&发布',
			itemId: "previewbtn",
			permissionId: 'doPublishNews',
			height: 30,
			width: "100%"
		}
	]
	
});