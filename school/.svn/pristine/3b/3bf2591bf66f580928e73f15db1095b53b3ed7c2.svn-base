/**
 * 
 * Author: 小玉
 * Contact: 1604653221@qq.com
 * Description: 查看学校老师最后一次登录信息的store
 * 
 */
 Ext.define("School.store.area.TeacherLastLogin",{
 	extend:"Ext.data.Store",
 	requires:[
 	"School.model.area.TeacherLastLogin"
 	],
 	model:"School.model.area.TeacherLastLogin",
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {};
				var actTab = Ext.ComponentQuery.query("mainpanel")[0].getActiveTab();
				var itemId = actTab.getItemId();
				if(itemId == 'teacherlogin') {
					params = {
						schoolId: zy_schoolId
					};
				} else {
					params = {
						schoolId: actTab.schoolId
					};
				}
				store.setProxy(School.util.Util.setProxy(
						'/school/teacher/getAllteacherAndTime', params , 'teachers'));
			}
		}
	},
 	autoLoad:true
 })