/**
* @class School.view.MyViewport
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 这个是系统的总的布局Viewport，
*/

Ext.define("School.view.MyViewport", {
	extend: "Ext.container.Viewport",

	alias: "widget.mainviewport",

	requires: [
		'Ext.layout.container.Border',
		"School.view.Header",
		'School.view.menu.Accordion',
		'School.view.MainPanel'
	],

	layout: {
		type: "border"
	},

	items: [
		{
			xtype: "mainmenu",
			region: "west",
			collapsible: true,
			width: 185,
			style: {
				borderRight: "3px #abcdef solid"
			}
		}, 
		{
			xtype: "appheader",
			region: "north"
		},
	 {
			xtype: "mainpanel",

			region: "center"
		}
	]

});
