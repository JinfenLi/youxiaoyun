/**
* @class School.model.multimedia.VideoMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 视频管理的model
*/
Ext.define("School.model.multimedia.VideoMgr", {
	extend: "Ext.data.Model",
	fields: [
		//参数说明：tMId是其视频库的id
		"id", "tMId", "name", "videoPath"
	]
});
