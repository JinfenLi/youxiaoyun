/**
* @class School.model.school.DepartMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 部门管理的model
*/
Ext.define("School.model.school.DepartMgr", {
	extend: "Ext.data.Model",
	fields: [

		"id", "info", "name", "phone", "tScSchoolId", "tScSubjectId",
		 "template", "templateName", "type", "teachers"
		
	]
});