/*
*author：小玉
*description:文件共享的编辑窗口
*/
Ext.define('School.view.clazz.FileShareEditWin',{
	extend:'Ext.window.Window',
	alias:'widget.fileshareeditwin',
	itemId:'fileshareeditwin',
    title: "编辑文件",
    closeAction: "destroy",
    modal:true,
	initComponent:function(){
		var me = this;
		Ext.apply(me,{
			items: [
               {
               	xtype:"form",
               	border:false,
               	style:{
               		padding:"10px"
               	},
               	layout:"anchor",
               	defaults:{
               		xtype:"textfield",
               		anchor:"100%",
               		labelWidth:70,
               		marginTop:"20px",
               		msgTarget:"under"
               	},
               	items:[
               	{
               		name: "name",
               		fieldLabel:"文件名",
               		allowBlank:false,
               		blankText:"不能为空"
               	}
               	],
               	buttons:[
               	{
               		text:"保存",
               		itemId: "save"
               	},
               	{
               		text:"取消",
               		itemId:"cancel"
               	}
               	]
               }
			]
		});
		me.callParent(arguments);
	}
})