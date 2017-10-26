//MsgList.js
/**
 * 
 * Author: 张展宇
 * Contact: k3note2@gmail.com
 * Description: 消息列表控制器
 * 
 */
Ext.define("School.controller.push.MsgList", {
	extend: "Ext.app.Controller",
	views: [
		"push.MsgList"
	],
	init: function(application) {

		this.control({
			"msglist button#query": {
				click: function(button) {
					var toolbar,
						beginFiled,
						endField,
						beginTime,
						endTime;

					toolbar =  button.up("toolbar");
					beginFiled = toolbar.down("#beginTime");
					endField = toolbar.down("#endTime");

					//检查日期格式是否有效
					if(!beginFiled.isValid() || !endField.isValid()) {
						School.util.Util.showErrorMsg("日期格式不正确，请重新输入");
						return 0;
					}
					beginTime = beginFiled.getRawValue();
					endTime = endField.getRawValue();

					//开始查询
					toolbar.up("msglist").getStore().reload({
						params: {
							beginTime: beginTime,
							endTime: endTime
						}
					});
				}
			} 

		});
	
	}
});