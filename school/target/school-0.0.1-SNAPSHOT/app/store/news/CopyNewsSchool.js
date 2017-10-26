/**
 * 
 * Time: 2016/07/27
 * Author: xiaoyu
 * Contact: 1604653221@qq.com
 * Path: School.store.news.CopyNewsSchool
 * Description: 复制新闻弹出窗口，获取所有学校班级数据的store
 * 
 */
 Ext.define('School.store.news.CopyNewsSchool',{
 	extend:'Ext.data.TreeStore',
 	requires:[
        'School.model.push.SchoolContact'
 	],
 	model:'School.model.push.SchoolContact',
    listeners:{
    	beforeload:function(store){
    		var winId = Ext.ComponentQuery.query('copynews')[0].winId;
    		var url ="";
             if(winId == "hotnewslist"){
                url ="sch/getSchoolOnlyByTree"//教育热讯的接口
             }else{
                url = "/school/sch/getAllSchoolByTree"
             }
             store.setProxy(School.util.Util.setProxy(url, "" ,"children"));
    	}
    }  

 })