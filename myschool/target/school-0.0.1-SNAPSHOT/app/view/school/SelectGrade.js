/**
* @class School.view.school.SelectGrade
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 年级选择
*/

Ext.define("School.view.school.SelectGrade", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.GradeMgr"
	],
	alias:"widget.selectgrade",
	forceFit: true,
	selType: "checkboxmodel",
	initComponent: function() {

		this.store = Ext.create("School.store.school.GradeMgr");

		this.columns = [
		
			{
				text: "年级id",
				hidden: true,
				dataIndex: "id"
			},
			{
				text: "年级名称",
				dataIndex: "name"
			}
		];

		this.callParent(arguments);
	}

});