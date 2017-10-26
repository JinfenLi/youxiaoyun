/*
*author：小玉
*description:文件共享
**/
Ext.define('School.view.clazz.FileShare',{
	extend:'Ext.panel.Panel',
	alias:'widget.fileshare',
	itemId:'fileshare',
	layout:'border',
	// requires:[
 //       'School.store.clazz.FileContact',
 //       'School.store.clazz.FileShare'
	//  ],
	initComponent:function(){
		var me = this;
		Ext.apply(me,{
			items:[
			{
				region:'east',
				xtype:'treepanel',
				title:'选择文件共享人',
				itemId:'fileContact',
				rootVisible:false,
				displayField:'name',
				width:300,
				overflowY:'auto',
				frame: true,
				store:Ext.create('School.store.clazz.FileContact')
			},
			{
               region:'center',
               xtype:'gridpanel',
               forceFit: true,
               itemId:'filegrid',
               store:Ext.create('School.store.clazz.FileShare'),
               columns:[
                 {
                    text:'id',
                    hidden:true,
                    text:'文件id',
                    dataIndex:'id'
                 },
                 {
                 	text:'文件名',
                 	dataIndex:'name',
                 	flex:4
                 },
                 {
                 	text:'上传者',
                 	dataIndex:'uploader',
                 	flex:4
                 },
                 {
                 	text:'上传时间',
                 	dataIndex:'uploadTime',
                 	flex:4
                 },
                 {
                 	text:'文件大小',
                 	dataIndex:'size',
                 	flex:4
                 },
                 {
                 	text:'文件类型',
                 	dataIndex:'type',
                 	flex:4
                 },
                 {
                    text:'下载',//这个是通过后台返回的路径下载文件，可能兼容性不那么好
                    dataIndex:'filePath',
                    flex:4,
                    renderer:function(value,metaData){
                        console.log('localhost:8080'+zyHost+value)
                        return '<a href=localhost:8080'+zyHost+value+' download='+metaData.record.data.name+'>'+metaData.record.data.name+'</a>'
                    }
                 },
                 // {  这个是通过后台接口下载文件
                 // 	xtype:'actioncolumn',
                 // 	header:'下载',
                 // 	align:'center',
                 // 	flex:4,
                 // 	itemId:'download',
                 // 	items:[
                 // 	{
                 // 		iconCls:'download',
                 // 		handler:function(grid, rowIndex, colIndex){
                 // 			this.fireEvent("itemclick", grid, rowIndex, colIndex);
                 // 		}
                 // 	}]
                 // },
                 {
                    xtype:'actioncolumn',
                    header:'删除',
                    align:'center',
                    flex:4,
                    itemId:'delete',
                    items:[
                    {
                    	iconCls:'delete',
                    	handler:function(grid, rowIndex, colIndex){
                 			this.fireEvent("itemclick", grid, rowIndex, colIndex);
                 		}

                    }
                    ]
                 }
               ],
               dockedItems:[{
               	xtype:'toolbar',
               	flex:2,
               	dock:'top',
               	items:[
                    {
                    	xtype:'button',
                    	text:'上传文件',
                    	itemId:'upload',
                    	iconCls:'upload'
                    },
                    {
                    	xtype:'button',
                    	text:'修改文件',
                    	itemId:'edit',
                    	iconCls:'edit'
                    },
                    {
                        fieldLabel: "我的班级",
                        width: 200,
                        labelWidth: 60,
                        itemId: "myclasses",
                        xtype: "combobox",
                        triggerAction: "all",
                        emptyText: "请选择...",
                        editable: false,
                        displayField: "name",
                        valueField: "id",
                        //获取数据集
                        store: zy_classes,
                        queryMode: "local"
                    }
               	]
               }]
			}]
		});
		me.callParent(arguments);
	}
});