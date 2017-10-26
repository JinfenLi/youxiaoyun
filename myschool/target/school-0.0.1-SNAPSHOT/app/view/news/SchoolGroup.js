/**
* @class School.view.news.SchoolGroup
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 手风琴布局的学校分组，复制年级新闻用到
*/

Ext.define('School.view.news.SchoolGroup', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.schoolgroup',
	header: false,
	requires: [
		'Ext.layout.container.Accordion'
	],
	width: 300,
	layout: {
		type: 'accordion',
		pack: 'start'
	},
	multi: true,
	collapsible: true,
	hideCollapseTool: true,
	iconCls: 'sitemap'
});