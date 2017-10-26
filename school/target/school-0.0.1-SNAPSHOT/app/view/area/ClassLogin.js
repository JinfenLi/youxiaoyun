/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 超级管理员查看管理的该学校班级的登陆情况的view
 * 
 */
Ext.define("School.view.area.ClassLogin", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.area.ClassLogin",
	],
	alias:"widget.classlogin",
	itemId: "classlogin",
	forceFit: true, 
	initComponent: function() {
		this.store = Ext.create("School.store.area.ClassLogin", {
			pageSize: 100
		});
		this.columns = [{
			text:"学号",
			dataIndex:"IDCard",
			flex:3
		},{
			text: "学生姓名",
			dataIndex: "studentName",
			flex: 3
		}, {
			text: "父母姓名",
			dataIndex: "parentName",
			flex: 3
		}, {
			text: "最后登陆时间",
			dataIndex: "lastLogin",
			flex: 3,
		}];
        this.dockedItems = [{
        	xtype:"toolbar",
        	dock:"top",
        	defaults:{
        		margin:"10 10 10 10",
        		text:"",
        		style:{
        			color:"red",
        			fontWeight:"800"
        		},
        		xtype:"label"
        	},
        	items:[{        		
        		itemId:"numberOfAll"
        	},{
        		itemId:"numberOfLogin"
        	},{
        		itemId:"numberOfOffline"
        	}]
        
        }]
		this.callParent(arguments);
	}

});