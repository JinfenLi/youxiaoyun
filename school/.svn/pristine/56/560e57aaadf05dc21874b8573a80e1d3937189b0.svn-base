/**
* @class School.view.school.Syllabus
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 课程表的面板，应该注意：
*	课程表的布局是在course-schedule.html里面用bootstrap来写的
*	这里通过iframe把课程表页面引入来的，
*	如果需要修改课程表的的布局，请修改course-schedule.html
*/

Ext.define("School.view.school.Syllabus", {
	extend: "Ext.panel.Panel",
	alias: "widget.syllabus",
	itemId: "syllabus",
	html: '<iframe src="course-schedule.html" id="mycourseSchedule"  frameborder=0 width=100% height=100%></iframe>',
	
	buttons: [
		{
			xtype:  "button",
			margin: "0 20 0 20",
			text:　"导出课程表",
			itemId: "download",
			iconCls: "download"
		},
		{
			xtype: "button",
			text: "导入课程表",
			itemId: "upload",
			iconCls: "upload"
		}		
	]
			
});