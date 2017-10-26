//TermCombo.js
//年级下拉框
Ext.define("School.store.school.TermCombo", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.TermCombo"
	],
	model: "School.model.school.TermCombo",
	proxy: {
		type: "ajax",
		url: 'data/school/termCombo.json',
		reader: {
			type: 'json',
			root: 'items'
		}
	},
	autoLoad: true//自动加载数据
	
});
