/**
 * 
 * Time: 2015/09/10
 * Author: Jason Liao
 * Contact: lmovingon2014@gmail.com
 * Path: School.store.multimedia.LearningCenter
 * Description: 学习园地界面
 * 
 */

Ext.define("School.store.multimedia.LearningCenter", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.multimedia.LearningCenter"
	],
	model: "School.model.multimedia.LearningCenter",
	proxy: {
        type: 'ajax',
        url: 'resource_type/getAllType.action',
        reader: {
            type: 'json',
            root: 'result',
            totalCount: 'totalCount'
        }
    },
    autoLoad: false
});