/*
*author：小玉
*description:文件共享
**/
Ext.define('School.store.clazz.FileShare',{
	extend: 'Ext.data.Store',
	requires:[
	'School.model.clazz.FileShare'
	],
	model:'School.model.clazz.FileShare',
    listeners:{
    	beforeload:{
    		fn: function(store){
    			var params = {
    				clazzId: zy_classId
    			};
    			store.setProxy(School.util.Util.setProxy('file/getAllFiles', params , 'filelist' ));
    		}
    	}
    }
});