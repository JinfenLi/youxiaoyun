/**
* @class School.model.school.User
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 用户信息的的model
*/
Ext.define("School.model.school.User", {
	extend: "Ext.data.Model",
	fields: [
		"id", "name", "idcard", "email", "phone", "picUrl", "sex"
	]
});
