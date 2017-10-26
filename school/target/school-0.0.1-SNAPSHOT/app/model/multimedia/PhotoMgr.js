/**
* @class School.model.multimedia.PohtoMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 相片管理的model
*/
Ext.define("School.model.multimedia.PhotoMgr", {
	extend: "Ext.data.Model",
	fields: [
		//id: 照片id； name: 照片名称； videoPath: 照片地址 description:照片描述,sort:照片排序
		"id", "name", "videoPath","uploadTime","description","sort"
	]
});

