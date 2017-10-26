/**
* @class School.model.school.GradeMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 年级管理的model
*/
Ext.define("School.model.school.GradeMgr", {
	extend: "Ext.data.Model",
	fields: [
		"id", "comment", "graduate", "info", "level", "name",
		 "sortName", "gradeTeacher", "year"
	]
});