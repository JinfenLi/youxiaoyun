/**
* @class School.view.multimedia.PlayVideoWin
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 播放视频的Ext.Win实例
*/
Ext.define("School.view.multimedia.PlayVideoWin", {
	extend: "Ext.window.Window",
	closeAction: "destroy",
	alias: "widget.playvideowin",
	closable: true,
	modal: true,
	layout: {
		type: "fit",
		align: 'stretch'
	},
	style: {
		backgroundColor: "black"
	},
	width: 620,
	height: 480,
	
	title: "播放视频",
	autoShow: true,
	html: '<iframe src="./CuPlayer/index.html" id="palyVideoPage" ' +
			'frameborder=0 width=100% height=100%></iframe>'
	
});