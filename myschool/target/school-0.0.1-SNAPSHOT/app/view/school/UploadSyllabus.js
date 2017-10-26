/**
* @class School.view.school.UploadSyllabus
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 选择文件导入课程表的Ext.Window
*/
Ext.define("School.view.school.UploadSyllabus", {
	extend: "Ext.window.Window",
	title: "上传课程表",
	closeAction: "destroy",
	alias: "widget.uploadsyllabus",
	modal: true,
	layout: {
		type: "fit"
	},
	width: 350,
	items: [{
		xtype: "form",
		defaults: {
			width: 300,
			labelWidth: 70,
			margin: 10
		},
		items: [
		{
				fieldLabel: "适用学期",
				itemId: "termcombo",
				xtype: "combobox",
				triggerAction: "all",
				emptyText: "请选择...",
				editable: false,
				displayField: "name",
				valueField: "id",
				//获取数据集
				store: Ext.create("School.store.school.Semester"),
				queryMode: "remote"
			},
			{
				xtype: "filefield",
				name: "file",
				allowBlank: false,
				fieldLabel: "文件选择",
				buttonText: "浏览文件"
			}
		],
		//底部的按钮栏
		buttons: [
			{
				text: '上传',
				itemId: "save"
			},
			 {
				text: '取消',
				itemId: "cancel"
			}
		]
	}]

});