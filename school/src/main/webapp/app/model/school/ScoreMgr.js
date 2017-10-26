/**
* @class School.model.school.Score
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 成绩管理的model
*/
Ext.define("School.model.school.ScoreMgr", {
	extend: "Ext.data.Model",
	fields: [
		"examName",
		 "id",
		 {
		 	 name: "idCard",
		 	 type: "number"
		 },
		
		 {
		 	name: "ranking", 
		 	type: "number"
		 },
		 
		 {
		 	name: "score",
		 	type: "number"
		}, 
		"studentName"
	]
});