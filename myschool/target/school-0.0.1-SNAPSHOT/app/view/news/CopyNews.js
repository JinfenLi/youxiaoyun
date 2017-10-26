/**
* @class School.view.news.CopyNews
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 复制新闻的一个Ext.Window
*/

Ext.define("School.view.news.CopyNews", {
	extend: "Ext.window.Window",
	requires: [
		"School.view.area.SelectSchool",
		"School.view.school.SelectGrade",
		"School.view.news.SchoolGroup"
	],
	closeAction: "destroy",
	alias: "widget.copynews",
	closable: true,
	resizable: false,
	modal: true,
	autoShow: true,
	width: 320,
	height: 400,
	overflowY: true,

	title: "复制新闻",
	items: [
		{
			xtype: "schoolgroup",
			// layout: "fit",
			// height: 1000,
			itemId: "content"
			//这里的items动态加载
		}			
	],
	//底部的按钮栏
	buttons : [
		{
			text : '提交',
			itemId: "save",
			width: "100%"
		}
	]
		
	
});