/**
 * 
 * Time: 2016/07/25
 * Author: 小玉
 * Contact: 1604653221@qq.com
 * Path: School.model.push.SchoolContact
 * Description: 消息推送，发送至全校各年级树
 * 
 */
 Ext.define('School.model.push.SchoolContact',{
 	extend:'Ext.data.Model',
 	fields:[{
 		name:"name"
 	},{
 		name:"leaf"
 	}],
 	idProperty: 'null'

 })