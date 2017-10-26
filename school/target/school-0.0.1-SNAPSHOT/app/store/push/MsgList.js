//MsgList.js
Ext.define("School.store.push.MsgList", {
	extend: "Ext.data.Store",
	requires: [
		"School.model.push.MsgList"
	],
	model: "School.model.push.MsgList",
	autoLoad: true,
	
	//因为每次请求传送的参数可能不一样，
	//所以把代理放到beforeload监听事件里面
	//可以动态设置proxy
	listeners: {
		beforeload: {
			fn: function(store) {
				var params = {
					beginTime: "2015-01-01",
					endTime: Ext.util.Format.date(new Date(), "Y-m-d")
				};
				store.setProxy(School.util.Util.setProxy('push/getHistoryMessage.action', params , 'historyMessages', 25, 'total'));
			}
		}
	}
});