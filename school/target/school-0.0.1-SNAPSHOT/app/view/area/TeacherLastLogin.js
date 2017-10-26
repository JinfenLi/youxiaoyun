/**
*
*Author:小玉
*contact: 1604653221@qq.com
*Description:查看学校老师最后一次登录信息
**/
Ext.define("School.view.area.TeacherLastLogin",{
	extend:"Ext.grid.Panel",
	requires:[
	    "School.store.area.TeacherLastLogin"
	],
	alias:"widget.teacherlastlogin",
	itemId:"teacherlastlogin",
	forceFit:true,
	initComponent:function(){
		this.store = Ext.create("School.store.area.TeacherLastLogin");
		this.columns = [{
			text:"姓名",
			dataIndex:"name",
			flex:3
		},{
			text:"手机号码",
			dataIndex:"phone",
			flex:3
		},{
			text:"邮箱",
			dataIndex:"email",
			flex:3
		},{
			text:"最后一次登录时间",
			dataIndex:"loginStatus",
			flex:3
		}];
		this.callParent(arguments);
	}
});