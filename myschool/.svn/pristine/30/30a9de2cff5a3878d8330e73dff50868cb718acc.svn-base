/**
* @class School.view.multimedia.VideoMgr
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 视频管理的gridpanel
*/

Ext.define("School.view.multimedia.VideoMgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.multimedia.VideoMgr"
	],
	alias:"widget.videomgr",
	//itemId: "videomgr",
	forceFit: true, 
	initComponent: function() {
		this.store = Ext.create("School.store.multimedia.VideoMgr");
		this.columns = [
		
			{ 
				text: "视频id",
				align: "center",
				hidden: true,
				dataIndex: "id"
			},
			
			{
				text: "视频库id",
				align: "center",
				hidden: true,
				dataIndex: "tMId"
			},
			{
				text: "视频名称",
				dataIndex: "name"
			},
			{
				text: "视频路径",
				dataIndex: "videoPath"
			},
			{
				xtype: "actioncolumn",
				header: "播放视频",
				itemId: "playvideo",
				align: "center",
				items: [
					{
						iconCls: "play",
						handler: function(grid, rowIndex, colIndex) {
							this.fireEvent("itemclick", grid, rowIndex, colIndex);
						}
					}
				]
			}
		];

		//固定菜单栏
		this.dockedItems = [
			{
				xtype: "toolbar",
				flex: 1,
				dock: "top",
				items: [
					{
						xtype: "button",
						iconCls: "add",
						itemId: "uploadVideo",
						text: "上传视频"
					},
					{
						xtype: "button",
						iconCls: "delete",
						itemId: "deleteVideo",
						text: "删除视频"
					},
					{
						xtype: "label",
						margin: "0 0 0 100",
						text: "当前视频库："
					},
					{
						xtype: "label",
						itemId: "libName",
						style: {
							color: "red",
							fontWeight: "800"
						}
					}
				]
			},
			{  
		        xtype: 'pagingtoolbar',  
		        store: this.getStore(),   // same store GridPanel is using  
		        dock: 'bottom',  
		        displayInfo: true  
		    }  
		];




		this.callParent(arguments);
	}

});