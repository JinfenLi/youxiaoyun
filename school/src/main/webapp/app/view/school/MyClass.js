/**
* @class School.view.school.MyClass
* @author ChessZhang
* @contact k3note2@gmial.com
* @description: 班级管理的主面板
*/

Ext.define("School.view.school.MyClass", {
	extend: "Ext.grid.Panel",
	requires:[
		"School.store.school.MyClass",
	],
	alias:"widget.myclass",
	itemId: "myclass",
	forceFit: true, 
	initComponent: function() {
		this.store = Ext.create("School.store.school.MyClass", {
			pageSize: 100
		});

		this.editStatus = false;
		var cellEditingPlugin = Ext.create('Ext.grid.plugin.CellEditing', {
			clicksToEdit: 1,
			editStatus: false,
			listeners: { 
				beforeedit: function( editor, e, eOpts ) {
					if(this.editStatus == false) return false;
					if(e.field == "studentPhone") {
						var tips = Ext.create("Ext.tip.ToolTip", {
							html: "请输入纯数字",
							style: {
								padding: "5px 10px"
							},
							title: "输入提示",
							anchor: "left",
							autoHide: true,
							draggable: true             //可以允许被拖动
						});
						tips.showAt([e.column.getX() + e.column.getWidth() + 10, e.column.getY() + (e.column.getHeight()-5) * (e.rowIdx + 1)]);
					}
					return true;
					// return this.editStatus;
				},
				validateedit: function(editor, e, eOpts) {
					var originalVal = e.originalValue;
					var inputVal = e.value;
					if(e.field == "parentPhone") {	
						if(inputVal) {
							return true;
						}
						else {
							School.util.Util.showErrorMsg("监护人手机为必填项");
							return false;
						}

					}
					if(e.field == "studentPhone") {
						var pattern = /^[0-9]*$/;
						if(pattern.test(inputVal)) {
							return true;
						}
						else {
							console.log("error");
							School.util.Util.showErrorMsg("请输入纯数字");
							return false;
						}

					}
					return true;
				}
			}
		});
		this.plugins = [cellEditingPlugin];

		this.columns = [
			//"emergencyPhone", , , 
			{
				text: "学生id",
				hidden: true,
				dataIndex: "studentId"
			},
			{
				text: "家长id",
				hidden: true,
				dataIndex: "parentId"
			},
			{
				text: "出生日期",
				hidden: true,
				xtype: 'datefield',
				format: 'Y-m-d',
				dataIndex: "studentBirthday"
			},
			{
				text: "学号",
				flex: 3,
				dataIndex: "studentIDCard",
				editor: {
						xtype: "textfield",
						allowBlank: true,
						maxLength: 20,
						regex: /^[0-9]*$/
				}
			},
			{
				text: "姓名",
				flex: 3,
				dataIndex: "studentName",
				editor: {
						xtype: "textfield",
						allowBlank: true,
						maxLength:25
				}
			},
			{
				text: "性别",
				flex: 3,
				dataIndex: "studentGender",
				editor: {
						xtype: 'combobox',
    				hideLabel: true,
            lazyRender: true, //值为true时阻止ComboBox渲染直到该对象被请求
            displayField: "sex",
            valueField: "sex",
            mode: "local",
            editable: false,
            triggerAction: "all",
						store: Ext.create("School.store.SexStore")
				}
			},
			{
				text: "住址",
				flex: 3,
				dataIndex: "studentAddress",
				editor: {
						xtype: "textfield",
						allowBlank: true,
						maxLength:64
				}
			},
			{
				text: "固定电话",
				flex: 3,
				dataIndex: "studentPhone",
				editor: {
						xtype: "textfield",
						allowBlank: true,
						regex: /^[0-9]*$/,
						maxLength: 16
				}
			},
			{
				text: "监护人",
				flex: 3,
				dataIndex: "parentName",
				editor: {
						xtype: "textfield",
						allowBlank: true,
						maxLength:25
				}
			},

			{
				text: "监护人手机",
				readOnly: true,
				flex: 3,
				dataIndex: "parentPhone",
				editor: {
						xtype: "textfield",
						allowBlank: true
				}
			},
			{
				text: "紧急电话",
				readOnly: true,
				flex: 3,
				dataIndex: "emergencyPhone",
				editor: {
						xtype: "textfield",
						allowBlank: true
				}
			},
			{
				xtype: "actioncolumn",
				flex: 1,
				header: "删除",
				permissionId: 'deleteStudent',
				align: "center",
				items: [
					{
						iconCls: "delete",
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
					// {
					// 	fieldLabel: "科目选择",
					// 	labelWidth:60,
					// 	itemId: "curriculacombo",
					// 	xtype: "combo",
					// 	triggerAction: "all",
					// 	width: 150,
					// 	displayField: "name",
					// 	emptyText: "请选择...",
					// 	editable: false,
					// 	valueField: "id",
					// 	//获取数据集
					// 	store: Ext.create("School.store.school.CourseMgr"),
					// 	queryMode: "remote"
					// },
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
					},
					{
						xtype: "button",
						text: "导入学生",
						itemId: "upload",
						permissionId: 'uploadStudent',
						iconCls: "upload"
					},
					
					{
						xtype:  "button",
						text:"导出学生",
						itemId: "download",
						permissionId: 'downloadStudent',
						iconCls: "download"
					},
					{
						xtype: "button",
						text: "编辑",
						itemId: "edit",
						permissionId: 'editStudent',
						iconCls: "edit"
					},
					{
						xtype: "button",
						text: "新增",
						itemId: "addstudent",
						permissionId: 'insertStudent',
						iconCls: "add"						
					},
					{
						xtype: "button",
						text: "编辑所有学生",
						itemId: "editAll",
						permissionId: 'editStudent',
						iconCls: "edit"
					},
					{
						xtype: "button",
						hidden: true,
						text: "保存",
						itemId: "saveAll",
						permissionId: 'editStudent',
						iconCls: "edit"
					},
					{
						xtype: "button",
						hidden: true,
						text: "取消",
						itemId: "cancelEdit",
						permissionId: 'editStudent',
						iconCls: "edit"
					},
					{
						xtype:  "button",
						text:"查看课表",
						itemId: "viewSyllabus",
						permissionId: 'viewSyllabus',
						iconCls: "key_go"
					},
					{
						xtype:  "button",
						text: "查看帖子",
						itemId: "viewPost",
						permissionId: 'viewPost',
						iconCls: "key_go"
					}, 
					{
						xtype: "label",
						margin: "0 0 0 50",
						text: "当前班级："
					},
					{
						xtype: "label",
						itemId: "className",
						style: {
							color: "red",
							fontWeight: "800"
						}
					}
					// {
					// 	xtype:  "button",
					// 	text:　"导出学生信息",
					// 	itemId: "download",
					// 	iconCls: "download"
					// }
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