/**
* @class School.model.school.TermCombo
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 学期选择的model
*/
Ext.define("School.model.school.TermCombo", {
	extend: "Ext.data.Model",
	idProperty: "value",
	fields: [
		"name",
		"value"
	]
});