/**
 * 
 * Author: ccDeng
 * Contact: 173634610@qq.com
 * Description: 管理员管理校内学生的view
 * 
 */
Ext.define("School.view.clazz.Getstudentmgr", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.clazz.Getstudentmgr",
	],
	alias:"widget.getstudentmgr",
	itemId: "getstudentmgr",
	forceFit: true, 
	initComponent: function() {
		this.store = Ext.create("School.store.clazz.Getstudentmgr", {
			pageSize: 100
		});
		this.columns = [
			{
				text: "id",
				hidden: true,
				dataIndex: "id",
			},
			{
				text: "班级",
				dataIndex: "name",
				flex: 3,
			},
			{
				text: "班主任",
				dataIndex: "headTeacher",
				flex: 3
			},
			{
				xtype: "actioncolumn",
				flex: 1,
				header: "查看",
				align: "center",
				items: [
					{
						iconCls: "key_go",
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
				items: [					{
					fieldLabel: "年级选择",
					width: 200,
					labelWidth: 60,
					itemId: "gradeselect",
					xtype: "combobox",
					triggerAction: "all",
					emptyText: "请选择...",
					editable: false,
					displayField: "name",
					valueField: "id",
					//获取数据集
					store: Ext.create('School.store.clazz.GradeStore'),
				}, {
					xtype: "label",
					margin: "0 0 0 50",
					text: "温馨提示：请选择年级",
					style: {
						color: "red",
						fontWeight: "800"
					}
				}]
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