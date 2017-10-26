/**
* @class School.model.school.TeacherMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 教师管理的model
*/
Ext.define("School.model.school.TeacherMgr", {
	extend: "Ext.data.Model",
	fields: [
		"id",
		"isAuthc", 
		"tScClassId", 
		"tScGradeId", 
		{name: "idcard", type: "number"},
		"tScSchoolId", 
		"name", 
		"sex", 
		"email",
		"birthday", 
		"education",
		"depart",  
		"subject", 
		"course",  
		"phone", 
		"picUrl"
	]
});