/**
* @class School.view.news.ReadNews
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 阅读新闻的Panel，事实上，这只是提供一个容器而已，
	在controller.NewsMgr里面会动态的通过iframe添加新闻进来的
*/
Ext.define("School.view.news.ReadNews", {
	extend: "Ext.panel.Panel",
	alias: "widget.readnews",
	title: "查看新闻",
	layout: {
		type: "fit"
	}
	//html: '<iframe src="publish-class-news.html"  frameborder=0 width=100% height=100%></iframe>'
});