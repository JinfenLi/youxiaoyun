/**
* @class School.model.school.CourseMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 学科管理的model
*/
Ext.define("School.model.school.CourseMgr", {
	extend: "Ext.data.Model",
	fields: [
		"id", "name", "comment", "tScTeacherId", "teacherName", "trunk"
	]
});