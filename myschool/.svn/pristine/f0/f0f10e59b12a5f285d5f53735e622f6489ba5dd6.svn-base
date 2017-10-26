/**
 * 
 * Time: 2015/09/07
 * Author: Jason Liao
 * Contact: lmovingon2014@gmail.com
 * Path: School.view.multimedia.LearningCenter
 * Description: 学习园地界面
 * 
 */

Ext.define("School.view.multimedia.LearningCenter", {
	extend: "Ext.grid.Panel",
	alias: "widget.learningcenter",
	
	itemId: "learningcenter",
	initComponent: function() {
		
		this.store = Ext.create('School.store.multimedia.GradeResource');
		
		this.dockedItems = [{
			xtype: "toolbar",
			flex: 1,
			dock: "top",
			items: [{
				iconCls: "add",
				itemId: "addRes",
				permissionId: 'addResource',
				text: "新增资源"
			}, {
				iconCls: "delete",
				itemId: "deleteRes",
				permissionId: 'deleteResource',
				text: "删除资源"
			}, '-', {
				xtype: "combobox",
				fieldLabel: "资源类型",
				emptyText: "请选择...",
				itemId: 'resourcesType',
				editable: false,
				labelWidth: 60,
				store: Ext.create("School.store.multimedia.LearningCenter"),
				valueField: 'id',
				displayField: 'name',
				queryMode: "remote"
			}]
		}, {
			xtype: 'pagingtoolbar',  
      store: this.getStore(),   // same store GridPanel is using  
      dock: 'bottom', 
      displayInfo: true
		}];
		
		this.columns = [{
			text: "年级id",
			hidden: true,
			dataIndex: "gradeId"
		}, {
			text: "资源id",
			hidden: true,
			dataIndex: "id"
		}, {
			text: "资源名称",
			align: "center",
			width: 190,
			dataIndex: "name"
		}, {
			text: '资源类型',
			align: "center",
			width: 150,
			dataIndex: "typeName"
		},  {
			text: "上传时间",
			align: "center",
			width: 190,
			dataIndex: "uploadTime"
		}, {
			text: "操作",
			align: "center",
			dataIndex: "isLink",
			permissionId: 'doResource',
			renderer: function (value) {
				if (value == "true") {
					return '<span>查看</span>';
				} else {
					return '<span>下载</span>';
				}
			}
		}]
		
		this.callParent(arguments);
	}
});