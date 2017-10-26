/**
* @class School.model.area.AreaMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 区域管理的model
*/
Ext.define("School.model.area.AreaMgr", {
	extend: "Ext.data.Model",
	fields: [
		"id", "address", "name", "areaName",	"email", "phone", "postcode", "type", "website"
	]
});
