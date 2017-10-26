/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 超级管理员查看管理的该学校班级的登陆情况的store
 * 
 */
Ext.define("School.store.area.ClassLogin", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.area.ClassLogin"
	],
	model: "School.model.area.ClassLogin",

	// proxy: {
	// 	type: "postproxy",
	// 	url: zyHost + 'user/getUserLoginInfo.action',
	// 	reader: {
	// 		type: 'json',
	// 		totalProperty: 'totalCount', 
	// 		root: 'loginInfoResult'
	// 	}
	// },
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {};
				var actTab = Ext.ComponentQuery.query("mainpanel")[0].getActiveTab();
				var itemId = actTab.getItemId();
				console.log(itemId);
				if(itemId == 'theclasslogin') {
					params = {
						classId: zy_classId
					};
					Ext.Ajax.request({
						url:"/school/user/getLoginCountOneClass",
						params: params,
						success:function(response){
	            var text = JSON.parse(response.responseText);
	            actTab.down('toolbar').down('label#numberOfAll').setText('班级总人数' + text.allMan);
	            actTab.down('toolbar').down('label#numberOfLogin').setText('登录人数' + text.loginMan);
	            actTab.down('toolbar').down('label#numberOfOffline').setText('离线人数' + text.notLoginMan);
	          },
						failure:function(){
		          School.util.Util.showErrorMsg('获取班级登录信息失败！');
						}
					});	
				} else {
					params = {
						classId: actTab.classId
					};
				}
				store.setProxy(School.util.Util.setProxy(
						zyHost + 'user/getUserLoginInfo.action', params , 'loginInfoResult'));

			}
		}
	},
 	autoLoad:true,
 	sorters: [{
 		property: 'IDCard',
 		direction: 'ASC'
 	}],
});