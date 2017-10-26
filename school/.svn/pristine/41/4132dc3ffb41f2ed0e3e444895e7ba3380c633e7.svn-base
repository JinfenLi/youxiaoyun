/*
* School.store.school.Termmgr
* author:小玉
* description:学校管理下的学期管理store
*/
Ext.define("School.store.school.Termmgr",{
	extend:"Ext.data.Store",
	requires:[
        "School.model.school.Termmgr"
	],
	model: "School.model.school.Termmgr",
	listeners:{
		beforeload:{
			fn:function(store){
				var params = {schoolId:zy_schoolId};
				store.setProxy(School.util.Util.setProxy("semester/getSemesterBySchoolId.action",params,"semesters"))
			}
		}
	},
	autoLoad: true

});