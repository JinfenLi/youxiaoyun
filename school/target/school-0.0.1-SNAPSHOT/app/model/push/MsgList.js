/**
* @class School.model.push.MsgList
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 消息记录的model
*/
Ext.define("School.model.push.MsgList", {
	extend: "Ext.data.Model",
	fields: [
		"id", "content", "receiversName", "sendTime"
	]
});
