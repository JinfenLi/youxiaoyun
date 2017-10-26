/**
* @class School.model.school.MyClass
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 我的班级的model
*/
Ext.define("School.model.school.MyClass", {
	extend: "Ext.data.Model",
	fields: [
		"parentId",
		 "emergencyPhone",
		 "parentName", 
		 "parentPhone", 
		 "studentAddress", 
		 "studentGender",
		 {
		 	name: "studentIDCard",
		 	type: "number"
		 }, 		  
		 "studentId", 
		 "studentName", 
		 "studentPhone"
	]
});