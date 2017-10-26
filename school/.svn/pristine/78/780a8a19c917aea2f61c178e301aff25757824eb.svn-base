/**
 * 
 * Time: 2015/09/10
 * Author: Jason Liao
 * Contact: lmovingon2014@gmail.com
 * Path: School.store.multimedia.GradeResource
 * Description: 学习园地界面
 * 
 */

Ext.define("School.store.multimedia.GradeResource", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.multimedia.GradeResource"
	],
	model: "School.model.multimedia.GradeResource",
 	proxy: {
 		type: 'ajax',
	  url: 'grade_resources/getAllResoureces.action',
	  reader: {
	  	type: 'json',
	  	root: 'result'
	  }
 	},
  autoLoad: false,
  pageSize: 15
});