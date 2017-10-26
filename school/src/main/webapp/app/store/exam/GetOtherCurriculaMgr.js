/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 查看本校所有老师选课情况的store
 * 
 */
Ext.define("School.store.exam.GetOtherCurriculaMgr", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.school.GetOtherCurriculaMgr"
	],
	model: "School.model.school.GetOtherCurriculaMgr",

	proxy: {
		type: "postproxy",
		url: zyHost + 'curricula/showCurriculaVariable.action',
		reader: {
			type: 'json',
			totalProperty: 'totalCount', 
			root: 'info'
		}
	},
	autoload: false
});