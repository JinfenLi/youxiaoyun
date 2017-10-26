/*
* School.view.school.Termmgr
* author:小玉
* description:学校管理下的学期管理面板
*/
Ext.define("School.view.school.Termmgr",{
	extend: "Ext.grid.Panel",
	requires:[
        "School.store.school.Termmgr"
	],
	itemId:"termmgr",
	alias:"widget.termmgr",
	forceFit:true,
	initComponent:function(){
		var me = this;
	    me.store = Ext.create("School.store.school.Termmgr");
	    me.columns = [
	    {
	    	text:"学期id",
	    	dataIndex:"id",
	    	hidden:true
	    },{
            text:"学期名称",
            dataIndex:"name",
	    },{
	    	text:"开始时间",
	    	dataIndex:"startTime"
	    },{
	    	text:"结束时间",
	    	dataIndex:"endTime"
	    },{
	    	text:"周数",
	    	dataIndex:"week"
	    },{
	    	text:"创建时间",
	    	dataIndex:"createTime"
	    },{
	    	text:"是否为当前学期",
	    	dataIndex:"currentSemester",
	    	hidden:true
	    }];
	    this.dockedItems = [
	    {
	    	xtype:"toolbar",
	    	flex:1,
	    	dock:"top",
	    	items:[
            {
            	xtype:"button",
            	text:"新增学期",
            	itemId:"add",
            	iconCls:"add"
            },{
            	xtype:"button",
            	text:"编辑学期",
            	itemId:"edit",
            	iconCls:"edit"
            },{
            	xtype:"button",
            	text:"设为当前学期",
            	itemId:"set",
            	iconCls:"menu_users"
            },{
            	xtype:"form",
            	items:{
            		xtype: "label",
            		margin:"10 10 10 10",
            		text: "",
            		itemId:"currentSemesterName",
            		style:{
            			color:"red",
            			fontWeight: 800
            		}
            	}
            }
	    	]
	    },
	    {
	    	xtype:"pagingtoolbar",
	    	store:this.getStore(),
            dock:"bottom",
            displayInfo:true
	    }];
	    me.callParent(arguments);
	}

})