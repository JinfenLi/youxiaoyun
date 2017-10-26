/**
 * 
 * Time: 2016/07/25
 * Author: xiaoyu
 * Contact: 1604653221@qq.com
 * Path: School.store.push.SchoolContact
 * Description: 消息推送，发送至全校各年级树
 * 
 */

Ext.define('School.store.push.SchoolContact',{
    extend: 'Ext.data.TreeStore',
    requires:[
        'School.model.push.SchoolContact'
    ],
    model:'School.model.push.SchoolContact',
    proxy:{
    	type:'ajax',
    	url:'/school/grade/getAllGradeByZtree',
        reader:{
    	    type:'json',
    	    root:'children'
           }
    },
    autoLoad:false
})