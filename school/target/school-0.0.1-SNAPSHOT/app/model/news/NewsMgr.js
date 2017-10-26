/**
* @class School.model.news.NewsMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 新闻管理的model
*/
Ext.define("School.model.news.NewsMgr", {
	extend: "Ext.data.Model",
	fields: [
		
		"id", "createTime", "title", "titlephoto", "type", "url"	
	]
});