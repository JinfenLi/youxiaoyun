/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 根据school获取年级的model
 * 
 */
Ext.define("School.model.clazz.GradeStore", {
	extend: "Ext.data.Model",
	fields: [
		"name",
		"id",
		'level'
	]
});