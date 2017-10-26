/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 根据选择的school获取年级，年级的store
 * 
 */
Ext.define("School.store.clazz.SelectSchoolGrade", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.clazz.SelectSchoolGrade"
	],
	model: "School.model.clazz.SelectSchoolGrade",

	proxy: {
		type: "postproxy",
		url: zyHost + 'grade/getAllGrade.action',
		reader: {
			type: 'json',
			totalProperty: 'totalCount', 
			root: 'grades'
		}
	},
	// listeners: {
	// 	beforeload: {
	// 		fn: function(store) {
	// 			// store.setProxy(School.util.Util.setProxy('grade/getAllGrade.action', params , 'grades', 100 ));
	// 		}
	// 	}
	// },
	sorters: [{
 		property: 'level',
 		direction: 'ASC'
 	}],
	autoload: false
});